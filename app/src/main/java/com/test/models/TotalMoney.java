package com.test.models;

public class TotalMoney {
   String yearSum, monthSum, dateSum;

    public String getYearSum() {
        return yearSum;
    }

    public void setYearSum(String yearSum) {
        this.yearSum = yearSum;
    }

    public String getMonthSum() {
        return monthSum;
    }

    public void setMonthSum(String monthSum) {
        this.monthSum = monthSum;
    }

    public String getDateSum() {
        return dateSum;
    }

    public void setDateSum(String dateSum) {
        this.dateSum = dateSum;
    }

    public TotalMoney(String yearSum, String monthSum, String dateSum) {
        this.yearSum = yearSum;
        this.monthSum = monthSum;
        this.dateSum = dateSum;
    }
}
