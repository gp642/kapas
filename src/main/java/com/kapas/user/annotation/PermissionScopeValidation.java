package com.kapas.user.annotation;

import com.kapas.user.model.PermissionEnum;
import com.kapas.user.model.ScopeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PermissionScopeValidation {

    ScopeEnum scope();
    PermissionEnum permission();
}
