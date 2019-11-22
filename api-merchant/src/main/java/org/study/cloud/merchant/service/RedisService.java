package org.study.cloud.merchant.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.study.cloud.common.constant.RedisConstant;
import org.study.cloud.common.dto.OrderDTO;

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
        String s = listOperations.rightPop(RedisConstant.MQ_LIST, 0L, TimeUnit.SECONDS);
        //String s = listOperations.rightPop(RedisConstant.MQ_LIST);
        if (s == null) {
            return;
        }

        log.info("{} = {}", RedisConstant.MQ_LIST, s);

        OrderDTO dto = JSON.parseObject(s, OrderDTO.class);
        log.info("dto = {}", dto);
    }
}
