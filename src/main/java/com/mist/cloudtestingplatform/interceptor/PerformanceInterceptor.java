package com.mist.cloudtestingplatform.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Prophet on 2017/11/20.
 */
public class PerformanceInterceptor implements HandlerInterceptor{

    Logger logger = LoggerFactory.getLogger(PerformanceInterceptor.class);

    private ThreadLocal<Long> startTimeThreadLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        long startTime = System.currentTimeMillis();
        startTimeThreadLocal.set(startTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
        long endTime = System.currentTimeMillis();
        long consumeTime = endTime - startTimeThreadLocal.get();
        if (consumeTime > 500) {
            logger.warn("too much process time: {}, request url: {}", consumeTime, request.getRequestURI());
        }
    }
}
