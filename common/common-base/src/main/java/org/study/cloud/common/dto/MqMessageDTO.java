package org.study.cloud.common.dto;

import lombok.Data;

/**
 * @author lipo
 * @version v1.0
 * @date 2019-11-22 13:53
 */
@Data
public class MqMessageDTO<T> {
    private T data;
    private Long executeTime;
}
