package org.study.cloud.manage.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @author lipo
 * @version v1.0
 * @date 2019-11-13 13:56
 */
@Component
@Slf4j
public class MqConsumer implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String msg = new String(message.getBody());
        String topic = new String(message.getChannel());
        String p = new String(pattern);
        log.info("topic ={}, msg = {}, pattern = {}", topic, msg, p);
    }
}
