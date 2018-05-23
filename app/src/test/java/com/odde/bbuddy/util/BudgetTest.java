package com.odde.bbuddy.util;

import com.odde.bbuddy.budget.viewmodel.Budget;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by AKI on 2018/5/23.
 */

public class BudgetTest {

    @Test
    public void verifyBudget_In_Half_Month() throws ParseException {
        Assert.assertEquals("16.0",new BudgetUtil(makeBudgetList()).getBudget(makeBudgetDate("2018/04/15") ,makeBudgetDate("2018/04/30")));
    }

    @Test
    public void verifyBudget_In_One_Month() throws ParseException  {
        Assert.assertEquals("30.0",new BudgetUtil(makeBudgetList()).getBudget(makeBudgetDate("2018/04/01") ,makeBudgetDate("2018/04/30")));
    }

    @Test
    public void verifyBudget_Cross_One_Month() throws ParseException  {
        Assert.assertEquals("21.0",new BudgetUtil(makeBudgetList()).getBudget(makeBudgetDate("2018/03/15") ,makeBudgetDate("2018/04/20")));
    }

    @Test
    public void verifyBudget_Cross_One_and_Half_Month() throws ParseException  {
        Assert.assertEquals("61.0",new BudgetUtil(makeBudgetList()).getBudget(makeBudgetDate("2018/03/15") ,makeBudgetDate("2018/05/31")));
    }


    private List<Budget> makeBudgetList(){
        ArrayList<Budget> budgets = new ArrayList<Budget>();
        Budget budget1 = new Budget();
        budget1.setMonth("2018/04");
        budget1.setBudget("30");
        Budget budget2 = new Budget();
        budget2.setMonth("2018/05");
        budget2.setBudget("31");
        Budget budget3 = new Budget();
        budget3.setMonth("2018/06");
        budget3.setBudget("30");
        budgets.add(budget1);
        budgets.add(budget2);
        budgets.add(budget3);
        return budgets;
    }

    private Date makeBudgetDate(String date){
        Calendar c1 = Calendar.getInstance();
        c1.set(Calendar.YEAR, Integer.parseInt(date.split("/")[0]));
        c1.set(Calendar.MONTH,Integer.parseInt(date.split("/")[1])-1);
        c1.set(Calendar.DAY_OF_MONTH,Integer.parseInt(date.split("/")[2]));
        return c1.getTime();
    }

}
