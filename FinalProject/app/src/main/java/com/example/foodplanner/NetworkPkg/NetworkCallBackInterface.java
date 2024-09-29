package com.example.foodplanner.NetworkPkg;

import java.util.List;

public interface NetworkCallBackInterface <T>{

    /* Take data Pojo */
    public void onSuccessfulResult(List<T> randomMeal);
    public void onFailureResult(String errMsg);

}
