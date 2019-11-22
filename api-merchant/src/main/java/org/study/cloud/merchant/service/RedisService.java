package org.study.cloud.merchant.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.study.cloud.common.constant.RedisConstant;
import org.study.cloud.common.dto.OrderDTO;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author lipo
 * @version v1.0
 * @date 2019-11-21 11:28
 */
@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void consumeMqList() {
        ListOperations<String, String> listOperations = redisTemplate.opsForList();
        //0时间，表示阻塞永久
        //待机一小时后，再次发消息，消费不了了，阻塞有问题啊。还得轮寻啊
        //String s = listOperations.rightPop(RedisConstant.MQ_LIST, 0L, TimeUnit.SECONDS);
        String s = listOperations.rightPop(RedisConstant.MQ_LIST);
        if (s == null) {
            return;
        }

        log.info("{} = {}", RedisConstant.MQ_LIST, s);

        OrderDTO dto = JSON.parseObject(s, OrderDTO.class);
        log.info("dto = {}", dto);
    }

    public void consumeMqZset() {
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        Set<ZSetOperations.TypedTuple<String>> typedTuples = zSetOperations.reverseRangeByScoreWithScores(RedisConstant.MQ_ZSET, 0, System.currentTimeMillis(), 0, 1);
        if (typedTuples == null || typedTuples.isEmpty()) {
            return;
        }

        ZSetOperations.TypedTuple<String> typedTuple = typedTuples.iterator().next();

        Double score = typedTuple.getScore();
        if (score == null) {
            return;
        }

        long l = score.longValue();
        if (l > System.currentTimeMillis()) {
            return;
        }

        String value = typedTuple.getValue();
        OrderDTO dto = JSON.parseObject(value, OrderDTO.class);
        System.out.println(dto);

        zSetOperations.remove(RedisConstant.MQ_ZSET, value);
    }
}
