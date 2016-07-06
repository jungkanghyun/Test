/*
 * @(#)MoneyService.java 2016. 7. 4.
 *
 * Copyright 2016 NeowizPlayStudio. All rights Reserved.
 */
package com.nps.websvc.wbapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nps.websvc.wbapi.domain.AssetValidator;
import com.nps.websvc.wbapi.domain.AssetInfo;
import com.nps.websvc.wbapi.repositories.AssetMapper;

/**
 * @desc
 * 
 * @author jkh120
 * @date 2016. 7. 4.
 * @modify
 */
@Service
public class MoneyService {

    @Autowired
    private AssetMapper moneyMapper;

    public AssetInfo getMoneyInfo(int usn) {
        AssetInfo assetInfo = moneyMapper.selectOne(usn);

        doAssetValidation(assetInfo.getMoney());
        doAssetValidation(assetInfo.getPokerchip());

        return assetInfo;
    }

    private void doAssetValidation(AssetValidator asset) {
        asset.doValidValue();
    }
}
