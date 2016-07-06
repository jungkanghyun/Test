package com.nps.websvc.wbapi.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @desc api intro controller
 * 
 * @author jkh120
 * @date 2016. 7. 1.
 * @modify
 */
@RestController
public class IndexController {

    @RequestMapping(value = "", method = GET)
    public String index() {
        return "Wbapi !!";
    }
}
