package org.study.cloud.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.study.cloud.manage.service.RedisService;

/**
 * @author lipo
 * @version v1.0
 * @date 2019-11-21 11:04
 */
@RestController
@RequestMapping("redisList")
public class RedisListController {

    @Autowired
    private RedisService redisService;

    @GetMapping("publish")
    public Object publish() {
        return redisService.publish();
    }
}
