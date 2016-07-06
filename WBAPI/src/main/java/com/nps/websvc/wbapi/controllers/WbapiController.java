/*
 * @(#)WbapiController.java 2016. 7. 4.
 *
 * Copyright 2016 NeowizPlayStudio. All rights Reserved.
 */
package com.nps.websvc.wbapi.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nps.websvc.wbapi.domain.Cash;
import com.nps.websvc.wbapi.domain.GameUser;
import com.nps.websvc.wbapi.domain.Jackpot;
import com.nps.websvc.wbapi.domain.AssetInfo;
import com.nps.websvc.wbapi.domain.ResponseMessage;
import com.nps.websvc.wbapi.domain.ResponseState;
import com.nps.websvc.wbapi.domain.User;
import com.nps.websvc.wbapi.services.CashService;
import com.nps.websvc.wbapi.services.GameService;
import com.nps.websvc.wbapi.services.GameUserService;
import com.nps.websvc.wbapi.services.MoneyService;
import com.nps.websvc.wbapi.services.UserService;

/**
 * @desc wbapi controller
 * 
 * @author jkh120
 * @date 2016. 7. 4.
 * @modify
 */
@RestController
@RequestMapping(value = "v1")
public class WbapiController {
    @Autowired
    private MoneyService moneyService;

    @Autowired
    private UserService userService;

    @Autowired
    private CashService cashService;

    @Autowired
    private GameService gameService;

    @Autowired
    private GameUserService gameUserService;

    @RequestMapping(value = "/me/asset", method = GET)
    public ResponseMessage asset() {
        User user = userService.getUser();
        int usn = user.getUsn();
        
        AssetInfo assetInfo = moneyService.getMoneyInfo(usn);
        Cash cash = cashService.getCash(usn);

        Map<String, Object> result = new HashMap<>();
        result.put("assetInfo", assetInfo);
        result.put("cash", cash);

        ResponseMessage responseMessage = new ResponseMessage(new ResponseState(), result);

        return responseMessage;
    }
    
    @RequestMapping(value = "/me/profile", method = GET)
    public ResponseMessage user() {
        User user = userService.getUser();
        int usn = user.getUsn();

        User me = userService.getUserFromGpm();
        GameUser gameUser = gameUserService.getGameUser(usn);
        
        if (gameUser == null) {
            gameUser = new GameUser();
        }
        
        Map<String, Object> result = new HashMap<>();
        result.put("user", me);
        result.put("gameUser", gameUser);

        ResponseMessage responseMessage = new ResponseMessage(new ResponseState(), result);

        return responseMessage;
    }

    @RequestMapping(value = "/user/current/cnt", method = GET)
    public ResponseMessage currentUser() {
        int currentUser = userService.getCurrentUserCnt();

        Map<String, Object> result = new HashMap<>();
        result.put("currentUser", currentUser);

        ResponseMessage responseMessage = new ResponseMessage(new ResponseState(), result);

        return responseMessage;
    }

    @RequestMapping(value = "/game/jackpot", method = GET)
    public ResponseMessage totalJackpot() {

        ArrayList<Jackpot> totalJackpot = gameService.getTotalJackpot();
        ArrayList<Jackpot> yesterdayJackpot = gameService.getYesterdayJackpot();

        Map<String, Object> result = new HashMap<>();
        result.put("top5", totalJackpot);
        result.put("yesterday", yesterdayJackpot);
        result.put("lastUpdate", gameService.getJackpotLastUpdate());

        ResponseMessage responseMessage = new ResponseMessage(new ResponseState(), result);

        return responseMessage;
    }
}
