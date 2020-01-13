/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model;

import java.math.BigInteger;

/**
 *
 * @author ABC
 */
public class District {

    private String gid;
    private String name;
    private String province;
    private String geometry;//vị trí
    private Integer acreage;//diện tích
    private BigInteger populartion;//dân số
    private Integer numberInValidVillage; //so luong xa ko đạt chuẩn 
    private Boolean isVillage = false;

    public Boolean getIsVillage() {
        return isVillage;
    }

    public void setIsVillage(Boolean isVilllage) {
        this.isVillage = isVilllage;
    }

    public String getGeometry() {
        return geometry;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Integer getAcreage() {
        return acreage;
    }

    public void setAcreage(Integer acreage) {
        this.acreage = acreage;
    }

    public BigInteger getPopulartion() {
        return populartion;
    }

    public void setPopulartion(BigInteger populartion) {
        this.populartion = populartion;
    }

    public Integer getNumberInValidVillage() {
        return numberInValidVillage;
    }

    public void setNumberInValidVillage(Integer numberInValidVillage) {
        this.numberInValidVillage = numberInValidVillage;
    }

    public District() {
        this.isVillage = false;
    }

}
