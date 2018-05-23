package com.odde.bbuddy.budget.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.odde.bbuddy.R;
import com.odde.bbuddy.budget.viewmodel.EditableBudget;

import org.robobinding.ViewBinder;

import javax.inject.Inject;

import static com.odde.bbuddy.di.component.ActivityComponentFactory.createActivityComponentBy;

public class AddBudgetActivity extends AppCompatActivity {

    @Inject
    EditableBudget editableBudget;

    @Inject
    ViewBinder viewBinder;
    //private TextInputLayout month;
    //private TextInputLayout budget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createActivityComponentBy(this).inject(this);

        setContentView(viewBinder.inflateAndBind(R.layout.activity_add_budget,editableBudget));


//        month = (TextInputLayout) findViewById(R.id.MonthError);
//        budget = (TextInputLayout) findViewById(R.id.BudgetError);
    }
}
