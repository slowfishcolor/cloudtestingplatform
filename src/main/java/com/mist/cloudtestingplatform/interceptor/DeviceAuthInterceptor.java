package com.mist.cloudtestingplatform.interceptor;

import com.mist.cloudtestingplatform.annotation.DeviceAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Prophet on 2017/12/27.
 */
public class DeviceAuthInterceptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(DeviceAuthInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            logger.info("not handler");
            // 非 handler 直接放行，如静态资源
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        DeviceAuth beanAuth = handlerMethod.getBean().getClass().getAnnotation(DeviceAuth.class);
        DeviceAuth methodAuth = handlerMethod.getMethodAnnotation(DeviceAuth.class);

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

        try {
            // update this list in DeviceController.deviceListPage()
            List<String> deviceIdList = (List<String>) httpServletRequest.getSession().getAttribute("deviceIdList");
            String deviceId = httpServletRequest.getParameter("deviceId");
            if (deviceIdList.contains(deviceId)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn(e.toString());
        }

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
