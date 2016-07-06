package com.nps.websvc.wbapi.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @desc 피망캐시 클래스
 * 
 * @author jkh120
 * @date 2016. 7. 1.
 * @modify
 */
public class Cash {

    @Getter
    @Setter
    private Long cash;

    public Cash() {
        super();
    }

    public Cash(Long cash) {
        super();
        this.cash = cash;
    }

}
