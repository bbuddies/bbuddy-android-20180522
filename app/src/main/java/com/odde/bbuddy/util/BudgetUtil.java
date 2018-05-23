package com.odde.bbuddy.util;

import com.odde.bbuddy.budget.viewmodel.Budget;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by AKI on 2018/5/23.
 */

public class BudgetUtil {

    private List<Budget> allBudgets = new ArrayList<>();

    //Given
    //2018/04 30
    //2018/05 31
    //2018/06 30


    public BudgetUtil(){
        Budget budget1 = new Budget();
        budget1.setMonth("2018/04");
        budget1.setBudget("30");
        Budget budget2 = new Budget();
        budget2.setMonth("2018/05");
        budget2.setBudget("31");
        Budget budget3 = new Budget();
        budget3.setMonth("2018/06");
        budget3.setBudget("30");
        allBudgets.add(budget1);
        allBudgets.add(budget2);
        allBudgets.add(budget3);
    }

    public BudgetUtil(List<Budget> budgets){
       allBudgets = budgets;
    }

    public String getBudget(Date dateStart, Date dateEnd) throws ParseException {

        Calendar c1 = Calendar.getInstance();
        c1.setTime(dateStart);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(dateEnd);
      //  c2.set(Calendar.HOUR,23);
     //   c2.set(Calendar.MINUTE,59);
      //  dateEnd = c2.getTime();

        SimpleDateFormat dfMonth = new SimpleDateFormat("yyyy/MM");

        Double Sum = 0.0;
        for (Budget bg : allBudgets) {


            Calendar b1 = Calendar.getInstance();
            b1.setTime(dfMonth.parse(bg.getMonth()));
            b1.set(Calendar.DAY_OF_MONTH,1);
            b1.set(Calendar.HOUR,0);
            b1.set(Calendar.MINUTE,0);

            Calendar b2 = Calendar.getInstance();
            b2.setTime(dateEnd);
            b2.set(Calendar.DAY_OF_MONTH,b2.getActualMaximum(Calendar.DAY_OF_MONTH));
            b2.set(Calendar.HOUR,23);
            b2.set(Calendar.MINUTE,59);

            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();

            if(c2.before(b1) || c1.after(b2)){
                //not in the budget period
                break;
            }

            if (c1.after(b1)){
                cal1 = c1;
            }else{
                cal1 = b1;
            }

            if (c2.before(b2)){
                cal2 = c2;
            }else{
                cal2 = b2;
            }

            if(cal1.get(Calendar.DAY_OF_MONTH)==1 && cal2.get(Calendar.DAY_OF_MONTH)==cal2.getActualMaximum(Calendar.DAY_OF_MONTH)){
                Sum += Double.parseDouble(bg.getBudget());
            }else{
                long diff = cal2.getTimeInMillis() - cal1.getTimeInMillis();

                float dayCount = ((float) diff / (24 * 60 * 60 * 1000))+1;

                Double budgetPerDay =  Double.parseDouble(bg.getBudget())/c1.getActualMaximum(Calendar.DAY_OF_MONTH);

                Sum += dayCount * budgetPerDay;
            }


        }

            return String.valueOf(Sum);


    }


}
