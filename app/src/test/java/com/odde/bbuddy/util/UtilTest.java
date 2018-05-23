package com.odde.bbuddy.util;

import com.odde.bbuddy.budget.viewmodel.Budget;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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



}
