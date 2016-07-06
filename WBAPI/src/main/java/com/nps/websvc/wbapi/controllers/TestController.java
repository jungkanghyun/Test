package com.nps.websvc.wbapi.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nps.websvc.wbapi.domain.Cash;
import com.nps.websvc.wbapi.domain.GameUser;
import com.nps.websvc.wbapi.domain.AssetInfo;
import com.nps.websvc.wbapi.domain.AssetLimit;
import com.nps.websvc.wbapi.domain.Pcbang;
import com.nps.websvc.wbapi.domain.ResponseMessage;
import com.nps.websvc.wbapi.domain.ResponseState;
import com.nps.websvc.wbapi.domain.User;
import com.nps.websvc.wbapi.services.CashService;
import com.nps.websvc.wbapi.services.GameUserService;
import com.nps.websvc.wbapi.services.MoneyService;
import com.nps.websvc.wbapi.services.PcbangService;
import com.nps.websvc.wbapi.services.UserService;

/**
 * @desc 개발 테스트 controller
 * 
 * @author jkh120
 * @date 2016. 7. 1.
 * @modify
 */
@RestController
@RequestMapping(value = "test")
public class TestController {

    @Autowired
    private MoneyService moneyService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private CashService cashService;

    @Autowired
    private PcbangService pcbangService;
    
    @Autowired
    private GameUserService gameUserService;
    
    @Autowired
    private RedisTemplate<String, String> klpRedisTemplate;

    @RequestMapping(value = "/money", method = GET)
    public ResponseMessage money() {
        User user = userService.getUser();
        int usn = user.getUsn();
        AssetInfo moneyInfo = moneyService.getMoneyInfo(usn);
        
        ResponseMessage responseMessage = new ResponseMessage(new ResponseState(), moneyInfo);
        
        return responseMessage; 
    }

    @RequestMapping(value = "/gameuser", method = GET)
    public ResponseMessage gameUser() {
        User user = userService.getUser();
        int usn = user.getUsn();
        GameUser gameUser = gameUserService.getGameUser(usn);
        
        ResponseMessage responseMessage = new ResponseMessage(new ResponseState(), gameUser);
        
        return responseMessage;
    }
    
    @RequestMapping(value = "/cash", method = GET)
    public ResponseMessage cash() {
        User user = userService.getUser();
        int usn = user.getUsn();
        Cash cash =  cashService.getCash(usn);
        
        ResponseMessage responseMessage = new ResponseMessage(new ResponseState(), cash);
        
        return responseMessage;
    }

    @RequestMapping(value = "/pcb", method = GET)
    public ResponseMessage pcb() {
        String ip = "10.30.164.119";
        Pcbang pcbang =  pcbangService.getPcbang(ip);
        
        ResponseMessage responseMessage = new ResponseMessage(new ResponseState(), pcbang);
        
        return responseMessage;
    }
    
    @RequestMapping(value ="/currentuser", method = GET)
    public ResponseMessage currentUser() {
        int rank = 0;
        rank = Integer.parseInt((String) klpRedisTemplate.opsForValue().get("connect"));
        
        ResponseMessage responseMessage = new ResponseMessage(new ResponseState(), rank);
        return  responseMessage;
    }
    
    @RequestMapping(value ="/func", method = GET)
    public ResponseMessage func() {
        User user = userService.getUser();
        int usn = user.getUsn();
        AssetLimit map = gameUserService.getMoneyLimitInfo(usn);
        
        ResponseMessage responseMessage = new ResponseMessage(new ResponseState(), map);
        return  responseMessage;
    }
}
