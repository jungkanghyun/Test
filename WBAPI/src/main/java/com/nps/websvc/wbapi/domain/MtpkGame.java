/*
 * @(#)MtpkGame.java 2016. 7. 6.
 *
 * Copyright 2016 NeowizPlayStudio. All rights Reserved.
 */
package com.nps.websvc.wbapi.domain;

/**
* @desc
* 
* @author	jkh120
* @date		2016. 7. 6.
* @modify
*/
public class MtpkGame {
    
    public static final String GAME_INFO_NAME_7POKER = "7포커";
    public static final String GAME_INFO_NAME_LOW = "로우바둑이";
    public static final String GAME_INFO_NAME_HIGH = "하이로우";
    
    public static final int GAME_INFO_SRL_7POKER = 1;
    public static final int GAME_INFO_SRL_LOW = 2;
    public static final int GAME_INFO_SRL_HIGH = 3;
    
    public static String getName(int gameInfoSrl) {
        String gameInfoName = null;
        
        switch (gameInfoSrl) {
        case MtpkGame.GAME_INFO_SRL_7POKER:
            gameInfoName =  MtpkGame.GAME_INFO_NAME_7POKER;
            break;
        case MtpkGame.GAME_INFO_SRL_LOW:
            gameInfoName =  MtpkGame.GAME_INFO_NAME_LOW;
            break;
        case MtpkGame.GAME_INFO_SRL_HIGH:
            gameInfoName =  MtpkGame.GAME_INFO_NAME_HIGH;
            break;
        }
        
        return gameInfoName;
    }
}
