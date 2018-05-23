package com.odde.bbuddy.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by AKI on 2018/5/23.
 */

public class DateUtil {

    public String getNowString(){
        return getDateString(new Date());
    }

    public String getDateString(Date date){
        return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS").format(date);
    }


}
