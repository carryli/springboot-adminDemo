package com.cloud.channel.backend.core.config;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import com.cloud.channel.backend.business.entity.SecretKey;
import com.cloud.channel.backend.business.service.SecretKeyService;
import com.cloud.channel.backend.util.SpringUtil;

/**
 * @Title GameCache
 * @Description 服务器缓存
 * @Author Craig
 * @Version 1.0.0
 * @Date 2019/10/9 21:28
 */
public class GameCache {
    /**
     * AES_KEY 缓存
     */
    private static final Map<Integer, SecretKey> AES_KEY_MAP = new ConcurrentHashMap<>();

    /**
     * 转账凭证缓存
     */
    private static final Map<String, String> TRANSFER_CODE_MAP = new ConcurrentHashMap<>();

    /**
     * 开服加载aesKey
     */
    public static void loadAesKey() {
        SecretKeyService secretKeyService = SpringUtil.getBean(SecretKeyService.class);
        List<SecretKey> allSecretKey = secretKeyService.getAllAesKey();
        AES_KEY_MAP.clear();
        allSecretKey.forEach(secretKey -> AES_KEY_MAP.put(secretKey.getServerId(), secretKey));
    }

    /**
     * 添加一个aesKey到缓存
     * 
     * @param secretKey
     */
    public static void addAesKey(SecretKey secretKey) {
        if (AES_KEY_MAP.containsKey(secretKey.getServerId())) {
            return;
        }
        AES_KEY_MAP.put(secretKey.getServerId(), secretKey);
    }

    /**
     * 获取一个aesKey
     * 
     * @param serviceId
     * @return
     */
    public static Optional<String> getAesKey(int serviceId) {
        Optional<SecretKey> secretKeyOptional = Optional.ofNullable(AES_KEY_MAP.get(serviceId));
        return secretKeyOptional.map(SecretKey::getSecretKey);
    }

    /**
     * 根据玩家id获取玩家的转账凭证
     *
     * @param userId
     *            玩家id
     * @return 转账凭证
     */
    public static String getTransferCode(String userId) {
        return TRANSFER_CODE_MAP.get("userId");
    }

    /**
     * 设置玩家的转账凭证
     *
     * @param userId
     *            玩家id
     * @param transferCode
     *            转账凭证
     */
    public static void setTransferCode(String userId, String transferCode) {
        TRANSFER_CODE_MAP.put(userId, transferCode);
    }
}
