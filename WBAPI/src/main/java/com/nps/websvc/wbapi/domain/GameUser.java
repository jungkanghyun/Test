/*
 * @(#)MtpkUser.java 2016. 7. 1.
 *
 * Copyright 2016 NeowizPlayStudio. All rights Reserved.
 */
package com.nps.websvc.wbapi.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @desc 게임별 유저 데이터
 * 
 * @author jkh120
 * @date 2016. 7. 1.
 * @modify
 */
public class GameUser {

    @Getter
    @Setter
    private long gsn;

    @Getter
    @Setter
    private int lev;

    public GameUser() {
        super();
        gsn = 0;
        lev = 0;
    }

    public GameUser(long gsn, int lev) {
        super();
        this.gsn = gsn;
        this.lev = lev;
    }

}
