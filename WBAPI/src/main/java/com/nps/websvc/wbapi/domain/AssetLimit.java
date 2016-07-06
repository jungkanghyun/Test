/*
 * @(#)MoneyLimit.java 2016. 7. 5.
 *
 * Copyright 2016 NeowizPlayStudio. All rights Reserved.
 */
package com.nps.websvc.wbapi.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @desc 재화 한도
 * 
 * @author jkh120
 * @date 2016. 7. 5.
 * @modify
 */
public class AssetLimit {

    @Getter
    @Setter
    private Long valueLimit;
    
    @Getter
    @Setter
    private Long safeValueLimit;
    
    public AssetLimit() {
        super();
    }

    public AssetLimit(Long valueLimit, Long safeValueLimit) {
        super();
        this.valueLimit = valueLimit;
        this.safeValueLimit = safeValueLimit;
    }

}
