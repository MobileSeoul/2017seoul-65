package com.example.sws.gongmoex1.Mulga;

/**
 * Created by Hoon on 2017-09-04.
 */

public class ViewItem_mulga {
    //물가 카드뷰를 위한 모든 정보들을 선언한다.
    private String mktName, Product_Price, Gu_name, Product_Name, Unit, Date , Info;

    public void setMktName(String mktName) {
        this.mktName = mktName;
    }

    public void setProduct_Price(String product_Price) {
        Product_Price = product_Price;
    }

    public void setGu_name(String gu_name) {Gu_name = gu_name;}

    public void setProduct_Name(String product_Name) {
        Product_Name = product_Name;
    }

    public void setDate(String date) {Date = date;}

    public void setUnit(String unit){Unit = unit;}

    public void setInfo(String info){Info = info;}

    public String getMktName() {
        return mktName;
    }

    public String getProduct_Price() {
        return Product_Price;
    }

    public String getGu_name() {
        return Gu_name;
    }

    public String getProduct_Name() {
        return Product_Name;
    }

    public String getUnit(){return Unit;}

    public String getDate() {return Date;}

    public String getInfo() {return Info;}
}
