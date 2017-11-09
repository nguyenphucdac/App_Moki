package com.example.dac.app_moki.model.object;

import java.util.Date;
import java.util.List;

/**
 * Created by Dac on 11/4/2017.
 */

public class Product {
    private int id;
    private String code;
    private String name;
    private int price;
    private float pricePercen;
    private int number;
    private String fromDate;
    private String endDate;
    private int statusId;
    private String description;
    private float rating;
    private int numberLike;
    private int numberComment;
    private boolean isLike;
    private String brand;
    private boolean isBlocked;
    private boolean canEdit;
    private boolean banned;
    private float priceNew;
    private String priceType;
    private Date modified;
    private String codition;
    private String shipFrom;
    private String size;
    private String weigh;
    private List<String> lstImage;
    private Seller seller;
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public String getShipFrom() {
        return shipFrom;
    }

    public void setShipFrom(String shipFrom) {
        this.shipFrom = shipFrom;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getWeigh() {
        return weigh;
    }

    public void setWeigh(String weigh) {
        this.weigh = weigh;
    }

    public String getCodition() {
        return codition;
    }

    public void setCodition(String codition) {
        this.codition = codition;
    }


    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public List<String> getListImage() {
        return lstImage;
    }

    public void setImage(List<String> lstImage) {
        this.lstImage = lstImage;
    }
    public String getImageOnList(int index){
        if(lstImage == null || lstImage.size() <=0 ){
            return null;
        }
        return lstImage.get(index);
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public float getPricePercen() {
        return pricePercen;
    }

    public void setPricePercen(float pricePercen) {
        this.pricePercen = pricePercen;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getNumberLike() {
        return numberLike;
    }

    public void setNumberLike(int numberLike) {
        this.numberLike = numberLike;
    }

    public int getNumberComment() {
        return numberComment;
    }

    public void setNumberComment(int numbẻComment) {
        this.numberComment = numbẻComment;
    }

    public float getPriceNew() {
        return priceNew;
    }

    public void setPriceNew(float priceNew) {
        this.priceNew = priceNew;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }


    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }



}
