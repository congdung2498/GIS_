/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model;

/**
 *
 * @author ABC
 */
public class District {
    private String name;
    private String province;
    private String geometry;//vị trí
    private Double acreage;//diện tích
    private Integer populartion;//dân số
    private Boolean isStandardCommune = false;//đạt chuẩn là xã
    
    public String getGeometry() {
        return geometry;
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

	public Double getAcreage() {
		return acreage;
	}

	public void setAcreage(Double acreage) {
		this.acreage = acreage;
	}

	public Integer getPopulartion() {
		return populartion;
	}

	public void setPopulartion(Integer populartion) {
		this.populartion = populartion;
	}

	public Boolean getIsStandardCommune() {
		return isStandardCommune;
	}

	public void setIsStandardCommune(Boolean isStandardCommune) {
		this.isStandardCommune = isStandardCommune;
	}
    
}
