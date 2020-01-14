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
public class Village {
    private String gid;
    private String name;
    private String District;
    private String geometry;//vị trí
    private Double acreage;//diện tích
    private Integer populartion;//dân số
    private Boolean isStandardCommune = false;//đạt chuẩn là xã
    private Boolean isVillage = true;
    private Double xMax;
    private Double xMin;
    private Double yMax;
    private Double yMin;

    public Double getxMax() {
        return xMax;
    }

    public void setxMax(Double xMax) {
        this.xMax = xMax;
    }

    public Double getxMin() {
        return xMin;
    }

    public void setxMin(Double xMin) {
        this.xMin = xMin;
    }

    public Double getyMax() {
        return yMax;
    }

    public void setyMax(Double yMax) {
        this.yMax = yMax;
    }

    public Double getyMin() {
        return yMin;
    }

    public void setyMin(Double yMin) {
        this.yMin = yMin;
    }
    
    
    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public Boolean getIsVillage() {
        return isVillage;
    }

    public void setIsVillage(Boolean isVillage) {
        this.isVillage = isVillage;
    }
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String District) {
        this.District = District;
    }

    public String getGeometry() {
        return geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
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
