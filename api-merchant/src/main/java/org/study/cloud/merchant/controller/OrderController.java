package org.study.cloud.merchant.controller;

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
public class OrderController {
    @Autowired
    private ApiOrderFeign apiOrderFeign;

    @RequestMapping("listOrder")
    public Result listOrder(OrderForm form, HttpServletRequest request) {
        Result result = apiOrderFeign.listOrder(form, request);
        return result;
    }
}
