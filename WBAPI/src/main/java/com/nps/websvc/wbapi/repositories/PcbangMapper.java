package com.nps.websvc.wbapi.repositories;

import com.nps.websvc.wbapi.common.annotation.Saypcb;
import com.nps.websvc.wbapi.domain.Pcbang;

/**
 * @desc
 * 
 * @author jkh120
 * @date 2016. 7. 1.
 * @modify
 */
@Saypcb
public interface PcbangMapper {

    public Pcbang selectOne(String ip);

}
