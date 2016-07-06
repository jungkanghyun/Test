/*
 * @(#)ResultState.java 2016. 7. 4.
 *
 * Copyright 2016 NeowizPlayStudio. All rights Reserved.
 */
package com.nps.websvc.wbapi.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @desc 음답메세지 상태
 * 
 * @author jkh120
 * @date 2016. 7. 4.
 * @modify
 */
public class ResponseState {

    @Getter
    @Setter
    boolean error = false;

    @Getter
    @Setter
    int code = 0;

    @Getter
    @Setter
    String msg = null;

    public ResponseState() {
        super();
    }

    public ResponseState(boolean error, int code, String msg) {
        super();
        this.error = error;
        this.code = code;
        this.msg = msg;
    }

}
