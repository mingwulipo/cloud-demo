package org.study.cloud.common.util.es;

import lombok.Data;

/**
 * @author lipo
 * @version v1.0
 * @date 2019-11-11 10:27
 */
@Data
public class EsNoEntity {
    public static final String INDEX = "distribute_no";
    public static final String TYPE = "es";

    private String noName;
    private Long initNo;
    private Long currentNo;

}
