package org.study.cloud.manage.service;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.study.cloud.common.constant.RedisConstant;
import org.study.cloud.common.dto.OrderDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lipo
 * @version v1.0
 * @date 2019-11-21 11:05
 */
@Service
public class RedisService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    public Object publish() {
        OrderDTO dto = new OrderDTO();
        dto.setId(1);
        dto.setCreateTime(new Date());
        dto.setMoney("12.34");
        dto.setOrderNo("orderNo1");
        String s = JSON.toJSONString(dto);

        ListOperations<String, String> listOperations = redisTemplate.opsForList();
        //leftPush和rightPop对应，左边入队，右边出队
        listOperations.leftPush(RedisConstant.MQ_LIST, s);

        //因为出队是阻塞读取的，所以上一步入队后，数据立刻就被驱走了，下一步size=0
        Long size = listOperations.size(RedisConstant.MQ_LIST);
        List<String> list = new ArrayList<>();
        if (size != null && size > 0) {
             list = listOperations.range(RedisConstant.MQ_LIST, 0, size - 1);
        }
        return list;

    }
}
