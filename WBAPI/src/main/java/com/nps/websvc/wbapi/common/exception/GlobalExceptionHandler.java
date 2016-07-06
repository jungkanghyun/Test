/*
 * @(#)CommonExceptionHandler.java 2016. 7. 4.
 *
 * Copyright 2016 NeowizPlayStudio. All rights Reserved.
 */
package com.nps.websvc.wbapi.common.exception;

import java.sql.SQLException;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.nps.websvc.wbapi.domain.ResponseMessage;
import com.nps.websvc.wbapi.domain.ResponseState;

import ch.qos.logback.classic.Logger;

/**
 * @desc 전역 예외처리 advice
 * 
 * @author jkh120
 * @date 2016. 7. 4.
 * @modify
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseMessage handleSQLException(SQLException e) {
        ResponseMessage resultMap = new ResponseMessage(
                new ResponseState(true, HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), null);

        logger.info("SQLException:", e);

        return resultMap;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseMessage handleAnyException(Exception e, WebRequest request) {

        ResponseMessage responseMessage = new ResponseMessage(
                new ResponseState(true, HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), null);

        logger.info("Exception:", e);

        return responseMessage;
    }

}
