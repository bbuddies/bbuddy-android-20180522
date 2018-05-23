package com.odde.bbuddy.budget.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.odde.bbuddy.R;
import com.odde.bbuddy.budget.viewmodel.PresentableBudgets;

import org.robobinding.ViewBinder;

import javax.inject.Inject;

import static com.odde.bbuddy.di.component.ActivityComponentFactory.createActivityComponentBy;

public class BudgetActivity extends Fragment {

    @Inject
    PresentableBudgets presentableBudgets;

    @Inject
    ViewBinder viewBinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createActivityComponentBy(getActivity()).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return viewBinder.inflateAndBindWithoutAttachingToRoot(R.layout.activity_budget, presentableBudgets, container);
    }

    @Override
    public void onResume() {
        super.onResume();
        presentableBudgets.refresh();
    }

}
