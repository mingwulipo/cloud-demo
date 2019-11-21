package org.study.cloud.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.study.cloud.common.constant.RedisConstant;
import org.study.cloud.common.entity.User;
import org.study.cloud.order.component.MqProducer;

/**
 * @author lipo
 * @version v1.0
 * @date 2019-11-13 11:37
 */
@RestController
@RequestMapping("mq")
public class MqController {
    @Autowired
    private MqProducer mqProducer;

    @GetMapping("publish")
    public Object publish() {
        mqProducer.send(RedisConstant.TOPIC, new User(1, "lipo"));
        return "ok";
    }
}
