package com.example.foodplanner.NetworkPkg;

import java.util.List;

public interface NetworkCallBackInterface <T>{

    /* Take data Pojo */
    public void onSuccessfulResult(List<T> meal);
    public void onFailureResult(String errMsg);

}
