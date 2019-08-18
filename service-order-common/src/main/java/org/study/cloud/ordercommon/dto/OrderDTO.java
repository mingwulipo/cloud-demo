package org.study.cloud.ordercommon.dto;

import lombok.Data;

import java.util.Date;

/**
 * Created by Administrator on 2019\8\17 0017.
 */
@Data
public class OrderDTO {
    private Integer id;
    private String orderNo;
    private String money;
    private Date createTime;
}
