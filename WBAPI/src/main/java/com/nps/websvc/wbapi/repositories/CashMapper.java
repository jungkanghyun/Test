package com.nps.websvc.wbapi.repositories;

import com.nps.websvc.wbapi.common.annotation.Saycashweb;
import com.nps.websvc.wbapi.domain.Cash;

/**
 * @desc
 * 
 * @author jkh120
 * @date 2016. 7. 1.
 * @modify
 */
@Saycashweb
public interface CashMapper {

    public Cash selectOne(String usn);

}
