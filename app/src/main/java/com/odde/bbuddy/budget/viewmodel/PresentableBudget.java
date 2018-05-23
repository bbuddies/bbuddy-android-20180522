package com.odde.bbuddy.budget.viewmodel;

import com.odde.bbuddy.account.viewmodel.Account;

import org.robobinding.itempresentationmodel.ItemContext;
import org.robobinding.itempresentationmodel.ItemPresentationModel;

public class PresentableBudget implements ItemPresentationModel<Budget> {

    private String month;
    private String budget;
    private int id;

    @Override
    public void updateData(Budget budget, ItemContext itemContext) {
        this.month = budget.getMonth();
        this.budget = budget.getBudget();
    }

    public String getDisplayOfBudget() {
        return month + " " + budget;
    }
}
