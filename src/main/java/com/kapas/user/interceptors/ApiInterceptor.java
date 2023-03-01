package com.kapas.user.interceptors;

import com.kapas.user.entity.User;
import com.kapas.user.service.SessionManagerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ApiInterceptor implements HandlerInterceptor {
    public static final String SESSION_ID = "Session-Id";
    private final SessionManagerService sessionManagerService;

    public ApiInterceptor(SessionManagerService sessionManagerService) {
        this.sessionManagerService = sessionManagerService;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String sessionId = request.getHeader(SESSION_ID);
        User user = sessionManagerService.validateSession(sessionId);
        request.setAttribute("principal", user);
        return true;
    }
}
