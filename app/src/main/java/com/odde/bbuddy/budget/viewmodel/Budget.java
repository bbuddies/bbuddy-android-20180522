package com.odde.bbuddy.budget.viewmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Budget implements Serializable {

    private String month;
    private String budget;
    private int id;

//    public Budget(String month,String budget, int id) {
//        this.month = month;
//        this.budget = budget;
//        this.id = id;
//    }
//
//    public Budget() {
//        this.month = "";
//        this.budget = "";
//        this.id = 0;
//    }
//
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public void setMonth(String month) {
//        this.month = month;
//    }
//
//    public void setBudget(String budget) {
//        this.budget = budget;
//    }
//
//    public int getId() {
//        return this.id;
//    }
//
//    public String getMonth() {
//        return this.month;
//    }
//
//    public String getBudget() {
//        return this.budget;
//    }

}
