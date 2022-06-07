package com.example.sandboxsubject.common.util;

import java.sql.Timestamp;
import java.util.Date;

public class Utils {

    /**
     * String to TimeStamp
     * */
    public Timestamp stringToTimestamp(String str) {

        if(str != null){
            Date date = java.sql.Date.valueOf(str);
            long time = date.getTime();
            Timestamp ts = new Timestamp(time);
            return ts;
        }else{
            return null;
        }

    }

}
