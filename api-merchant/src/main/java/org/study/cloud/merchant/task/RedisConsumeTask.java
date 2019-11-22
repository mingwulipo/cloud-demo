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

    @TaskLock(RedisConstant.CONSUME_REDIS_LIST_TASK)
    @Scheduled(cron = "0/10 * * * * ?")
    public void consumeMqList() {
        redisService.consumeMqList();
    }

    @TaskLock(RedisConstant.CONSUME_REDIS_ZSET_TASK)
    @Scheduled(cron = "0/10 * * * * ?")
    public void consumeMqZset() {
        redisService.consumeMqZset();
    }
}
