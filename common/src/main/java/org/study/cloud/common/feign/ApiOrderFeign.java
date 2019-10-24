package org.study.cloud.common.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.study.cloud.common.form.OrderForm;
import org.study.cloud.common.model.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lipo
 * @version v1.0
 * @date 2019-10-23 11:06
 */
@FeignClient(value = "api-order", fallbackFactory = ApiOrderFeign.ApiOrderFeignHystrix.class)
public interface ApiOrderFeign {

    /**
     * feign调用，只能有一个入参
     * 可以比实际方法少参数，比如HttpServletRequest request没有，也能成功调用
     * @param form
     * @return
     */
    @RequestMapping("order/listOrder")
    Result listOrder(OrderForm form);


    class ApiOrderFeignHystrix implements ApiOrderFeign {

        @Override
        public Result listOrder(OrderForm form) {
            return Result.hystrix();
        }
    }

}
