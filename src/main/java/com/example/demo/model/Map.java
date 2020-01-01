package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "namdinh")
public class Map {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer gid;
    private String full_id;;
    private String name;
	public Integer getGid() {
		return gid;
	}
	public void setGid(Integer gid) {
		this.gid = gid;
	}
	public String getFull_id() {
		return full_id;
	}
	public void setFull_id(String full_id) {
		this.full_id = full_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    
}
