package com.kapas.workorder.model;

public enum Status {
    NOT_STARTED("NOT STARTED"),
    IN_PROGRESS("IN PROGRESS"),
    COMPLETED("COMPLETED"),
    RE_OPENED("RE OPENED"),
    CLOSED("CLOSED");

    private final String status;

    private String getStatus() {
        return this.status;
    }

    private Status(String status) {
        this.status = status;
    }
}
