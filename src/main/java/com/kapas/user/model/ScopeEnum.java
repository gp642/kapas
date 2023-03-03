package com.kapas.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ScopeEnum {

    VENDOR, TOKEN, GRADING, WEIGHING, UNLOADING;
}
