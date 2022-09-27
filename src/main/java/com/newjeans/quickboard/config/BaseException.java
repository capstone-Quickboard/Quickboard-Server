package com.newjeans.quickboard.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseException extends Exception {
    private com.newjeans.quickboard.config.BaseResponseStatus status;
}
