package com.nps.websvc.wbapi.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @desc ÇÇ¸ÁÀ¯Àú
 * 
 * @author jkh120
 * @date 2016. 7. 1.
 * @modify
 */
public class User implements Serializable {

    @Getter
    @Setter
    private int usn;
    
    @Getter
    @Setter
    private String userId;

    @Getter
    @Setter
    private String displayName;

    @Getter
    @Setter
    private String lastSigninDate;

    @Getter
    @Setter
    private String profileImgUrl;
    
    public User() {
        super();
    }

    public User(int usn, String userId, String displayName, String lastSigninDate, String profileImgUrl) {
        super();
        this.usn = usn;
        this.userId = userId;
        this.displayName = displayName;
        this.lastSigninDate = lastSigninDate;
        this.profileImgUrl = profileImgUrl;
    }

}
