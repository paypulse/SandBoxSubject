package com.example.sandboxsubject.common.util;

import java.math.BigDecimal;
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

    /**
     * Bigdecimal
     * 소수점 계산 정확성
    * */
    public BigDecimal sumRs(String rs1, String rs2){
        if(rs1 != null && rs2 != null){
            BigDecimal number1 = new BigDecimal(rs1);
            BigDecimal number2 = new BigDecimal(rs2);

            return number1.add(number2);

        }else{
            return null;
        }
    }



}
