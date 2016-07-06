/*
 * @(#)Asset.java 2016. 7. 5.
 *
 * Copyright 2016 NeowizPlayStudio. All rights Reserved.
 */
package com.nps.websvc.wbapi.domain;

import lombok.Getter;
import lombok.Setter;

/**
* @desc 재화 추상 클래스
* 
* @author	jkh120
* @date		2016. 7. 5.
* @modify
*/
public abstract class Asset implements AssetValidator{
    
    public static final Long DEFAULT_EXT_VALUE = 0L;
    
    @Getter
    @Setter
    private Long value;
    
    @Getter
    @Setter
    private Long extValue;
    
    @Getter
    @Setter
    private String extEndDate;

    public Asset() {
        super();
    }

    public Asset(Long value, Long extValue, String extEndDate) {
        super();
        this.value = value;
        this.extValue = extValue;
        this.extEndDate = extEndDate;
    }
    

    
    
}
