package com.nps.websvc.wbapi.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @desc ÇÇ¾¾¹æ
 * 
 * @author jkh120
 * @date 2016. 7. 1.
 * @modify
 */
public class Pcbang {

    @Getter
    @Setter
    private String pcbId;

    public Pcbang() {
        super();
    }

    public Pcbang(String pcbId) {
        super();
        this.pcbId = pcbId;
    }

}
