package com.odde.bbuddy.budget.api;

import android.support.annotation.NonNull;

import com.odde.bbuddy.budget.viewmodel.Budget;
import com.odde.bbuddy.common.functional.Consumer;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BudgetsApi {

    private final RawBudgetsApi rawBudgetsApi;

    public BudgetsApi(RawBudgetsApi rawBudgetsApi) {
        this.rawBudgetsApi = rawBudgetsApi;
    }

    public void processAllBudgets(final Consumer<List<Budget>> consumer) {
        rawBudgetsApi.getAllBudgets().enqueue(new Callback<List<Budget>>() {
            @Override
            public void onResponse(Call<List<Budget>> call, Response<List<Budget>> response) {
                consumer.accept(response.body());
            }

            @Override
            public void onFailure(Call<List<Budget>> call, Throwable t) {

            }
        });
    }

    public void addBudget(Budget budget, final Runnable afterSuccess) {
        rawBudgetsApi.addBudget(budget).enqueue(callbackOfAfterSuccess(afterSuccess));
    }

    public void editBudget(Budget budget, final Runnable afterSuccess) {
        rawBudgetsApi.editBudget(budget.getId(), budget).enqueue(callbackOfAfterSuccess(afterSuccess));
    }

    public void deleteBudget(Budget budget, final Runnable afterSuccess) {
        rawBudgetsApi.deleteBudget(budget.getId()).enqueue(callbackOfAfterSuccess(afterSuccess));
    }

    @NonNull
    private Callback<ResponseBody> callbackOfAfterSuccess(final Runnable afterSuccess) {
        return new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                afterSuccess.run();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        };
    }

}
