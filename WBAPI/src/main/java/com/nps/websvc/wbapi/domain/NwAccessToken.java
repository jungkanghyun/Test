/*
 * @(#)AccessToken.java 2016. 7. 1.
 *
 * Copyright 2016 NeowizPlayStudio. All rights Reserved.
 */
package com.nps.websvc.wbapi.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @desc �׿����������ū
 * 
 * @author jkh120
 * @date 2016. 7. 1.
 * @modify
 */
public class NwAccessToken {

    @Getter
    @Setter
    private String nwAccessToken = null;

    @Getter
    @Setter
    private String nwSsid = null;

    public NwAccessToken() {
        super();
    }

    public NwAccessToken(String nwAccessToken, String nwSsid) {
        super();
        this.nwAccessToken = nwAccessToken;
        this.nwSsid = nwSsid;
    }

}
