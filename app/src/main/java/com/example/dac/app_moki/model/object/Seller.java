package com.example.dac.app_moki.model.object;

/**
 * Created by Dac on 11/6/2017.
 */

public class Seller {
    private int id;
    private int userId;
    private String nameShop;
    private int score;
    private int numberProduct;
    private String adress;
    private String image;

    public Seller(){
        this.adress = "Chưa có";
        this.score = 0;
        this.numberProduct = 0;
        this.nameShop = "Chưa có";
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNameShop() {
        return nameShop;
    }

    public void setNameShop(String nameShop) {
        this.nameShop = nameShop;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNumberProduct() {
        return numberProduct;
    }

    public void setNumberProduct(int numberProduct) {
        this.numberProduct = numberProduct;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
