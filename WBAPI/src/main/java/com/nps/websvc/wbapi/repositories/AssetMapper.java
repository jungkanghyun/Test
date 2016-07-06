/*
 * @(#)MoneyMapper.java 2016. 7. 4.
 *
 * Copyright 2016 NeowizPlayStudio. All rights Reserved.
 */
package com.nps.websvc.wbapi.repositories;

import com.nps.websvc.wbapi.common.annotation.Saygame;
import com.nps.websvc.wbapi.domain.AssetInfo;

/**
 * @desc
 * 
 * @author jkh120
 * @date 2016. 7. 4.
 * @modify
 */

@Saygame
public interface AssetMapper {

    public AssetInfo selectOne(int usn);

}
