/*
 * @(#)ResultSet.java 2016. 7. 4.
 *
 * Copyright 2016 NeowizPlayStudio. All rights Reserved.
 */
package com.nps.websvc.wbapi.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @desc 응답메세지
 * 
 * @author jkh120
 * @date 2016. 7. 4.
 * @modify
 */
public class ResponseMessage {

    @Getter
    @Setter
    ResponseState state = null;

    @Getter
    @Setter
    Object data = null;

    public ResponseMessage() {
        super();
    }

    public ResponseMessage(ResponseState resultState, Object data) {
        super();
        this.state = resultState;
        this.data = data;
    }

}
