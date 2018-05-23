package com.odde.bbuddy.budget.api;

import com.odde.bbuddy.budget.viewmodel.Budget;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RawBudgetsApi {

    String BUDGETS = "/budgets";
    String BUDGETS_WITH_ID = BUDGETS + "/{id}";

    @POST(BUDGETS)
    Call<ResponseBody> addBudget(@Body Budget budget);

    @GET(BUDGETS)
    Call<List<Budget>> getAllBudgets();

    @PUT(BUDGETS_WITH_ID)
    Call<ResponseBody> editBudget(@Path("id") int accountId, @Body Budget budget);

    @DELETE(BUDGETS_WITH_ID)
    Call<ResponseBody> deleteBudget(@Path("id") int budgetId);
}
