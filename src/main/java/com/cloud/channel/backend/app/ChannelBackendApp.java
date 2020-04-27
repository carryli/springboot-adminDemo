package com.cloud.channel.backend.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.cloud.channel.backend.business.service.SecretKeyService;
import com.cloud.channel.backend.core.config.GameCache;
import com.cloud.channel.backend.util.SpringUtil;
import com.xengine.frame.utils.FileUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 启动类
 * 
 * @author Bruce
 */
@SpringBootApplication(exclude = {MongoAutoConfiguration.class, DataSourceAutoConfiguration.class,
    DataSourceTransactionManagerAutoConfiguration.class}, scanBasePackages = "com.cloud.channel.backend")
@EnableScheduling
@Slf4j
public class ChannelBackendApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ChannelBackendApp.class, args);
        log.debug("启动成功");
        SecretKeyService secretKeyService = SpringUtil.getBean(SecretKeyService.class);
        /** 秘钥交换 */
        secretKeyService.exchangeAll();
        /** 加载AES key到本地缓存 */
        GameCache.loadAesKey();
        // 程序关闭时一定执行的代码 调用System.exit(0)时才会触发
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("服务器准备关闭...");
            try {
                // 本地记录一个服务器状态,方便运维脚本处理
                List<String> rs = new ArrayList<>();
                rs.add("off");// 关闭中
                FileUtils.saveChatTxt(rs, "status.txt");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                log.error("服务器关闭异常：", e);
            }
            log.info("服务器关闭完成...");
        }));

        List<String> rs = new ArrayList<>();
        // 开启中
        rs.add("on");
        FileUtils.saveChatTxt(rs, "status.txt");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("请输入命令:<stop/test/other>");
            try {
                String str = br.readLine().trim();
                if ("stop".equals(str)) {
                    System.out.println("正在关闭服务器，请稍等......");
                    SpringApplication.exit(context);
                    try {
                        // 等待10s
                        Thread.sleep(10 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("服务器已经安全关闭:" + Instant.now());
                    break;
                } else {
                    doSomeThing(str);
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("执行命令失败:遇到致命错误");
            }
        }
        System.exit(0);
    }

    private static void doSomeThing(String str) {
        if ("test".equals(str)) {
            System.out.println("服务器正在运行,当前时间:" + Instant.now());
        } else {
            System.out.println("不接受此命令: " + str);
        }
    }

}
