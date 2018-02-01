package com.example.sws.gongmoex1.Mulga;

import java.io.Serializable;

/**
 * Created by Hoon on 2017-10-03.
 */

public class Mulga_Cal_Item implements Serializable{
    //mulga_calculate에서 필요한 정보를 선언하고 반환한다.
    private String cal_price, cal_product;
    public int[] cal_Num = new int[17];
    public int[] cal_Price_two = new int[17];

    public int num_Cal=0;

    public String getCal_price() {
        return cal_price;
    }

    public void setCal_price(String cal_price) {
        this.cal_price = cal_price;
    }

    public String getCal_product() {
        return cal_product;
    }

    public void setCal_product(String cal_product) {
        this.cal_product = cal_product;
    }

    public int[] getCal_Num() {
        return cal_Num;
    }

    public int[] getCal_Price_two() {
        return cal_Price_two;
    }

    public void setCal_Num(int[] cal_Num) {
        this.cal_Num = cal_Num;
    }

    public void setCal_Price_two(int[] cal_Price) {
        this.cal_Price_two = cal_Price;
    }

    public int getNum_Cal() {
        return num_Cal;
    }

    public void setNum_Cal(int num_Cal) {
        this.num_Cal = num_Cal;
    }
}
