package com.kapas.user.aspect;

import com.kapas.user.annotation.PermissionScopeValidation;
import com.kapas.user.entity.User;
import com.kapas.user.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
@RequiredArgsConstructor
public class PermissionAspect {

    private final PermissionService permissionService;

    @Before("@annotation(permissionScopeValidation) && args(request,..)")
    public void validatePermission(JoinPoint joinPoint, PermissionScopeValidation permissionScopeValidation, HttpServletRequest request) {
        User user = (User) request.getAttribute("principal");
        permissionService.validatePermissionOn(user, permissionScopeValidation.scope(), permissionScopeValidation.permission());
    }
}
