package com.nps.websvc.wbapi.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @desc saygame db접근을 위한 annotation
 * 
 * @author jkh120
 * @date 2016. 7. 1.
 * @modify
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Saygame {

}
