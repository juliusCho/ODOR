package com.back.odor.common.controller;

import io.swagger.annotations.ApiModelProperty;

public class ApiResultVO<T> {

    @ApiModelProperty(value = "API request result", required = true)
    private boolean result;

    @ApiModelProperty(value = "API response http code", required = true)
    private final int status;

    @ApiModelProperty(value = "API response value", required = true)
    private final T response;

    public ApiResultVO(T response) {
        this(200, response);
        result = true;
    }

    public ApiResultVO(int status, T response) {
        this.result = false;
        this.status = status;
        this.response = response;
    }

    public boolean isResult() {
        return result;
    }

    public T getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "ApiResultVO{" +
                "result=" + result +
                ", response=" + response +
                '}';
    }

}
