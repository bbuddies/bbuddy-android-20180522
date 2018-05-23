package com.odde.bbuddy.budget.viewmodel;

import com.odde.bbuddy.budget.api.BudgetsApi;
import com.odde.bbuddy.common.functional.Consumer;
import com.odde.bbuddy.di.scope.ActivityScope;

import org.robobinding.annotation.ItemPresentationModel;
import org.robobinding.annotation.PresentationModel;
import org.robobinding.presentationmodel.HasPresentationModelChangeSupport;
import org.robobinding.presentationmodel.PresentationModelChangeSupport;
import org.robobinding.widget.adapterview.ItemClickEvent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Lazy;

@PresentationModel
@ActivityScope
public class PresentableBudgets implements HasPresentationModelChangeSupport {

    private final Lazy<PresentationModelChangeSupport> changeSupportLazyLoader;
    private final BudgetsApi budgetsApi;
    private final List<Budget> allBudgets = new ArrayList<>();

    @Inject
    public PresentableBudgets(BudgetsApi budgetsApi, @Named("budgets") Lazy<PresentationModelChangeSupport> changeSupportLazyLoader) {
        this.budgetsApi = budgetsApi;
        this.changeSupportLazyLoader = changeSupportLazyLoader;
        refresh();
    }

    private PresentationModelChangeSupport changeSupport() {
        return this.changeSupportLazyLoader.get();
    }

    @ItemPresentationModel(value = PresentableBudget.class)
    public List<Budget> getBudgets() {
        return allBudgets;
    }

    private Budget budgetOf(ItemClickEvent event) {
        return allBudgets.get(event.getPosition());
    }

    @Override
    public PresentationModelChangeSupport getPresentationModelChangeSupport() {
        return changeSupport();
    }

    public void refresh() {
        budgetsApi.processAllBudgets(new Consumer<List<Budget>>() {
            @Override
            public void accept(List<Budget> list) {
                allBudgets.clear();
                allBudgets.addAll(list);
                changeSupport().refreshPresentationModel();
            }
        });
    }
}
