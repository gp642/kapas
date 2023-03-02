package com.kapas.user.interceptors;

import com.kapas.user.entity.User;
import com.kapas.user.service.SessionManagerService;
import com.kapas.util.Constants;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ApiInterceptor implements HandlerInterceptor {

    private final SessionManagerService sessionManagerService;

    public ApiInterceptor(SessionManagerService sessionManagerService) {
        this.sessionManagerService = sessionManagerService;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String sessionId = request.getHeader(Constants.SESSION_ID);
        User user = sessionManagerService.validateSession(sessionId);
        request.setAttribute("principal", user);
        return true;
    }
}
