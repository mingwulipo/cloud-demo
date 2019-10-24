package org.study.cloud.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.study.cloud.common.feign.ApiOrderFeign;
import org.study.cloud.common.form.OrderForm;
import org.study.cloud.common.model.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2019\8\17 0017.
 */
@RequestMapping("order")
@RestController
@Slf4j
public class OrderController {
    @Autowired
    private ApiOrderFeign apiOrderFeign;

    @RequestMapping("listOrder")
    public Result listOrder(OrderForm form) {
        Result result = apiOrderFeign.listOrder(form);
        return result;
    }
}
