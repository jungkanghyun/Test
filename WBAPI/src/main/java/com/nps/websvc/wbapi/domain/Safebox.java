/*
 * @(#)SafeBox.java 2016. 7. 4.
 *
 * Copyright 2016 NeowizPlayStudio. All rights Reserved.
 */
package com.nps.websvc.wbapi.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @desc ±Ý°í
 * 
 * @author jkh120
 * @date 2016. 7. 4.
 * @modify
 */
public abstract class Safebox {
    
    public static final Long DEFAULT_SAFE_VALUE = 0L;
    
    @Getter
    @Setter
    private Long value;

    @Getter
    @Setter
    private String endDate;

    public Safebox() {
        super();
    }

}
