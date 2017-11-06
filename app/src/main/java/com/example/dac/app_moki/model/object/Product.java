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
    private int userId;
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
    private int addressId;
    private String brand;
    private boolean isBlocked;
    private boolean canEdit;
    private boolean banned;
    private int categoryId;
    private float priceNew;
    private String priceType;
    private int coditionId;
    private Date modified;
    private List<String> image;

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }
    public String getImage(int index){
        if(image.get(index) == null || image.get(index) == ""){
            return "";
        }
        return image.get(index);
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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

    public int getCoditionId() {
        return coditionId;
    }

    public void setCoditionId(int coditionId) {
        this.coditionId = coditionId;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }



}
