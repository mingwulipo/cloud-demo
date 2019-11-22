package org.study.cloud.merchant.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.study.cloud.common.aop.task.TaskLock;
import org.study.cloud.common.constant.RedisConstant;
import org.study.cloud.merchant.service.RedisService;

/**
 * @author lipo
 * @version v1.0
 * @date 2019-11-21 11:28
 */
@Component
public class RedisConsumeTask {
    @Autowired
    private RedisService redisService;

    @TaskLock(RedisConstant.CONSUME_REDIS_LIST)
    @Scheduled(cron = "0/1 * * * * ?")
    public void consumeMqList() {
        redisService.consumeMqList();
    }
}
