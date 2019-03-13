package com.secdn.secdnrapid.common.Enum;

public enum Status {

    处理成功("OK"),
    处理失败("FALL");

    private final String status;

    Status(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
}
