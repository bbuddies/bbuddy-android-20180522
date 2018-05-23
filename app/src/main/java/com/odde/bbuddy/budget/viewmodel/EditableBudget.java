package com.odde.bbuddy.budget.viewmodel;

import com.odde.bbuddy.budget.api.BudgetsApi;
import com.odde.bbuddy.budget.view.BudgetView;
import com.odde.bbuddy.budget.view.BudgetsNavigation;
import com.odde.bbuddy.common.functional.Consumer;
import com.odde.bbuddy.common.functional.ValueCaptor;
import com.odde.bbuddy.common.validation.Validator;
import com.odde.bbuddy.common.validation.Violation;
import com.odde.bbuddy.di.scope.ActivityScope;

import org.hibernate.validator.constraints.NotBlank;
import org.robobinding.annotation.PresentationModel;

import javax.inject.Inject;
import javax.validation.constraints.Size;

@PresentationModel
@ActivityScope
public class EditableBudget {

    private final BudgetsApi budgetsApi;
    private final BudgetsNavigation budgetsNavigation;
    private final Validator validator;
    private final BudgetView budgetView;

    @NotBlank @Size(max = 50)
    private String month;
    private String budget;
    private int id;

    @Inject
    public EditableBudget(BudgetsApi budgetsApi, BudgetsNavigation budgetsNavigation, Validator validator, BudgetView budgetView) {
        this.budgetsApi = budgetsApi;
        this.budgetsNavigation = budgetsNavigation;
        this.validator = validator;
        this.budgetView = budgetView;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }



    public void add() {
        if (isValid())
            addBudget();
    }

    private void addBudget() {
        budgetsApi.addBudget(new Budget(month,budget, 0), new Runnable() {
            @Override
            public void run() {
                budgetsNavigation.navigate();
            }
        });
    }

    private Boolean isValid() {
        final ValueCaptor<Boolean> isValid = new ValueCaptor<>(true);
        validator.processEachViolation(this, new Consumer<Violation>() {
            @Override
            public void accept(Violation violation) {
                budgetView.showError(violation);
                isValid.capture(false);
            }
        });
        return isValid.value();
    }



    public void setId(int id) {
        this.id = id;
    }

    public void update() {
        if (isValid())
            editBudget();
    }

    private void editBudget() {
        budgetsApi.editBudget(new Budget(month, budget, id), new Runnable() {
            @Override
            public void run() {
                budgetsNavigation.navigate();
            }
        });
    }

    public void delete() {
        Budget budget = new Budget();
        budget.setId(id);
        budgetsApi.deleteBudget(budget, new Runnable() {
            @Override
            public void run() {
                budgetsNavigation.navigate();
            }
        });
    }

}
