package com.example.sws.gongmoex1.GoodStore;

/**
 * Created by sws on 2017-08-24.
 */

public class GoodPriceListItem {
    //착한가게 카드뷰를 위한 모든 정보들을 선언
    private String name, image, info, address, pride, way, phone, information;
    private int rank;

    void setName(String name){
        this.name = name;
    }

    void setImage(String image){
        this.image = image;
    }

    void setAddress(String address) {this.address = address;}

    void setRank(int rank){this.rank = rank;}

    void setInfo(String info){this.info = info;}

    void setPride(String pride){this.pride = pride;}

    void setWay(String way){this.way = way;}

    void setPhone(String phone){this.phone = phone;}

    void setInformation(String information){this.information = information;}

    String getName(){return name;}
    String getImage(){return image;}
    String getAddress(){return address;}
    String getInfo(){return info;}
    int getRank(){return rank;}
    String getPride(){return pride;}
    String getWay(){return way;}
    String getPhone(){return phone;}
    String getInformation(){return information;}

    }

