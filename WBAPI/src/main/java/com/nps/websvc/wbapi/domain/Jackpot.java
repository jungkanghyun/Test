/*
 * @(#)Jackpot.java 2016. 7. 4.
 *
 * Copyright 2016 NeowizPlayStudio. All rights Reserved.
 */
package com.nps.websvc.wbapi.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @desc ¿Ë∆Ã
 * 
 * @author jkh120
 * @date 2016. 7. 4.
 * @modify
 */
public class Jackpot implements Serializable {
    
    @Getter
    @Setter
    private int rank;

    @Getter
    @Setter
    private String nickName;

    @Getter
    @Setter
    private Long jackpotMoney;

    @Getter
    @Setter
    private String regDate;

    @Getter
    @Setter
    private int gameInfoSrl;
    
    @Getter
    @Setter
    private String gameName;

    public Jackpot() {
        super();
    }

    public Jackpot(int rank, String nickName, Long jackpotMoney, String regDate, int gameInfoSrl, String gameName) {
        super();
        this.rank = rank;
        this.nickName = nickName;
        this.jackpotMoney = jackpotMoney;
        this.regDate = regDate;
        this.gameInfoSrl = gameInfoSrl;
        this.gameName = gameName;
    }

}
