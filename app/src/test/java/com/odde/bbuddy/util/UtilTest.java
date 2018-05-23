package com.odde.bbuddy.util;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by AKI on 2018/5/23.
 */

public class UtilTest {


    @Test
    public void verifyNowString() throws ParseException {

        String dateExpectResultString = "2018/05/23 14:42:01.123";
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");

        Date testDate = df.parse(dateExpectResultString);

        DateUtil du = new DateUtil();
        Assert.assertEquals(du.getDateString(testDate),dateExpectResultString);


    }

    @Test
    public void verifyBudget() throws ParseException  {

        Calendar c1 = Calendar.getInstance();
        c1.set(Calendar.YEAR,2018);
        c1.set(Calendar.MONTH,3);
        c1.set(Calendar.DAY_OF_MONTH,15);
        Calendar c2 = Calendar.getInstance();
        c2.set(Calendar.YEAR,2018);
        c2.set(Calendar.MONTH,3);
        c2.set(Calendar.DAY_OF_MONTH,30);

        Assert.assertEquals("16.0",new BudgetUtil().getBudget(c1.getTime(),c2.getTime()));
    }



}
