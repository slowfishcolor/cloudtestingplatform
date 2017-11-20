package com.mist.cloudtestingplatform.interceptor;

import com.mist.cloudtestingplatform.annotation.Auth;
import com.mist.cloudtestingplatform.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Prophet on 2017/6/14.
 */
public class LoginInterceptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            logger.info("not handler");
            // 非 handler 直接放行，如静态资源
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        Auth beanAuth = handlerMethod.getBean().getClass().getAnnotation(Auth.class);
        Auth methodAuth = handlerMethod.getMethodAnnotation(Auth.class);

        // no auth present
        if (beanAuth == null && methodAuth == null) {
            return true;
        }
        // method auth == false
        if (methodAuth != null && !methodAuth.value()) {
            return true;
        }
        // bean auth == false && method auth is null
        if (methodAuth == null && !beanAuth.value()) {
            return true;
        }

        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            logger.info("unauthorized request at " + request.getRequestURI());
            logger.info("ip: " + request.getRemoteAddr());
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
