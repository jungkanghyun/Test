package com.nps.websvc.wbapi.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @desc �Ǹ�ĳ�� Ŭ����
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
