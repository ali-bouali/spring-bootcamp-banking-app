package com.alibou.banking.fraud;

public enum FraudStatus {
    UNDER_INVESTIGATION,
    CONFIRMED,//CANCELLED
    REJECTED,//COMPLETED
    //RESOLVED//COMPELTED

    //RESOlVED==COMPLETED||REJECTED
}
