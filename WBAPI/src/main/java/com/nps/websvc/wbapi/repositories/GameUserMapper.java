/*
 * @(#)MtpkUserMapper.java 2016. 7. 1.
 *
 * Copyright 2016 NeowizPlayStudio. All rights Reserved.
 */
package com.nps.websvc.wbapi.repositories;

import java.util.HashMap;

import com.nps.websvc.wbapi.common.annotation.Saymtpk;
import com.nps.websvc.wbapi.domain.GameUser;

/**
 * @desc
 * 
 * @author jkh120
 * @date 2016. 7. 1.
 * @modify
 */
@Saymtpk
public interface GameUserMapper {

    public GameUser selectOne(int usn);
    
    public String getMoneyLimitInfo(HashMap<String, Object> map);
    
    public String getSubMoneyLimitInfo(HashMap<String, Object> map);
}
