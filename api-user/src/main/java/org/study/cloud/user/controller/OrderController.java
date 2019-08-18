package org.study.cloud.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.study.cloud.ordercommon.dto.OrderDTO;
import org.study.cloud.ordercommon.form.OrderForm;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Date;

/**
 * Created by Administrator on 2019\8\17 0017.
 */
@RequestMapping("order")
@RestController
@Slf4j
public class OrderController {

    //zuul转发： http://localhost:8771/user/order/listOrder, 转发成http://windows10.microdone.cn:9010/order/listOrder
    //直接请求： http://localhost:9010/order/listOrder
    @RequestMapping("listOrder")
    public Object listOrder(OrderForm form, HttpServletRequest request) {
        log.info(request.getRequestURL().toString());
        OrderDTO dto = new OrderDTO();
        dto.setMoney("12.3");
        dto.setOrderNo("userNo");
        dto.setCreateTime(new Date());
        return Collections.singletonList(dto);
    }

}
