/*
 * @(#)PcbangService.java 2016. 7. 4.
 *
 * Copyright 2016 NeowizPlayStudio. All rights Reserved.
 */
package com.nps.websvc.wbapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nps.websvc.wbapi.domain.Pcbang;
import com.nps.websvc.wbapi.repositories.PcbangMapper;

/**
 * @desc
 * 
 * @author jkh120
 * @date 2016. 7. 4.
 * @modify
 */
@Service
public class PcbangService {

    @Autowired
    private PcbangMapper pcbangMapper;

    public Pcbang getPcbang(String ip) {
        return pcbangMapper.selectOne(ip);
    }
}
