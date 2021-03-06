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
@RequestMapping("redisHyperLogLog")
public class RedisHyperLogLogController {

    @Autowired
    private RedisService redisService;

    //http://localhost:9040/redisHyperLogLog/publishHyperLog
    @GetMapping("publishHyperLog")
    public Object publishHyperLog() {
        return redisService.publishHyperLog();
    }
}
