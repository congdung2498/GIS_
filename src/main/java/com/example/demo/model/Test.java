package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pham")
public class Test {
	@Id
	//@Column(name = "_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long _id;
	//@Column(name = "name")
	//private String name;
	public Long get_id() {
		return _id;
	}
	public void set_id(Long _id) {
		this._id = _id;
	}
	/*public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}*/
	
	
}
