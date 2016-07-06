/*
 * @(#)MoneyInfo.java 2016. 7. 4.
 *
 * Copyright 2016 NeowizPlayStudio. All rights Reserved.
 */
package com.nps.websvc.wbapi.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @desc 
 * 
 * @author jkh120
 * @date 2016. 7. 4.
 * @modify
 */
public class AssetInfo {

    @Getter
    @Setter
    private Money money;

    @Getter
    @Setter
    private Pokerchip pokerchip;

    @Getter
    @Setter
    private MoneySafebox moneySafebox;

    @Getter
    @Setter
    private PokerchipSafebox pokerchipSafebox;

    public AssetInfo() {
        super();
    }

    public AssetInfo(Money money, Pokerchip pokerchip, MoneySafebox moneySafebox, PokerchipSafebox pokerchipSafebox) {
        super();
        this.money = money;
        this.pokerchip = pokerchip;
        this.moneySafebox = moneySafebox;
        this.pokerchipSafebox = pokerchipSafebox;
    }

}
