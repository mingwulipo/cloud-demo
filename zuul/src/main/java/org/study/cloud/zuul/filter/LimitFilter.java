package org.study.cloud.zuul.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.study.cloud.common.util.RequestWrapper;
import org.study.cloud.common.util.ResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

/**
 * 限流
 * @author lipo
 * @version v1.0
 * @date 2019-10-25 10:34
 */
@Slf4j
@Component
public class LimitFilter extends OncePerRequestFilter {
    /**
     * 1。查询key，如果不存在，就创建key
     * 2。计数器+1，查出来
     * 3。计数器>限定值，返回true，限流；否则，返回false，不限流
     */

    private static final String LUA_SCRIPT = "local count = redis.call('GET', KEYS[1])\n" +
                    "if not count then\n" +
                    "   redis.call('SET', KEYS[1], 1, 'EX', ARGS[1])\n" +
                    "   return false\n" +
                    "end\n" +

                    "redis.call('INCR', KEYS[1])\n" +
                    "if (count + 1 > ARGS[2]) then\n" +
                    "   return true\n" +
                    "end\n" +

                    "return false\n"
            ;

    private final RedisScript<Boolean> redisScript = new DefaultRedisScript<>(LUA_SCRIPT, Boolean.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //过滤图标请求
        String requestUri = request.getRequestURI();
        if ("/favicon.ico".equals(requestUri)) {
            return;
        }

        RequestWrapper requestWrapper = new RequestWrapper(request);
        ResponseWrapper responseWrapper = new ResponseWrapper(response);

        boolean b = limitFlow();
        if (b) {
            String result = "请求繁忙，已限流";
            log.error(result);
            responseWrapper.responseMessage(result);
        }

        filterChain.doFilter(requestWrapper, responseWrapper);
    }

    private boolean limitFlow() {
        Boolean limit10 = stringRedisTemplate.execute(redisScript, Collections.singletonList("LIMIT10"), Arrays.asList(10, 5));
        if (Boolean.TRUE.equals(limit10)) {
            return true;
        }

        Boolean limit100 = stringRedisTemplate.execute(redisScript, Collections.singletonList("LIMIT100"), Arrays.asList(100, 20));
        return Boolean.TRUE.equals(limit100);
    }
}
