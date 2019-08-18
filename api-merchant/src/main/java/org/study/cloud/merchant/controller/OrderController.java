package org.study.cloud.merchant.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.study.cloud.ordercommon.dto.OrderDTO;
import org.study.cloud.ordercommon.form.OrderForm;

import java.util.Collections;
import java.util.Date;

/**
 * Created by Administrator on 2019\8\17 0017.
 */
@RequestMapping("order")
@RestController
public class OrderController {

    //zuul转发： http://localhost:8771/merchant/order/listOrder, 转发成http://windows10.microdone.cn:9020/order/listOrder
    //直接请求： http://localhost:9020/order/listOrder
    @RequestMapping("listOrder")
    public Object listOrder(OrderForm form) {
        OrderDTO dto = new OrderDTO();
        dto.setMoney("12.3");
        dto.setOrderNo("merchantNo");
        dto.setCreateTime(new Date());
        return Collections.singletonList(dto);
    }
}
