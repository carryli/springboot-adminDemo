package com.cloud.channel.backend.business.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Bruce
 * @classname StatementsController
 * @description 报表
 * @date 2020/4/28 0028 20:57
 */

@RestController
@Slf4j
@Validated
@RequestMapping(value = "/statements")
public class StatementsController {}
