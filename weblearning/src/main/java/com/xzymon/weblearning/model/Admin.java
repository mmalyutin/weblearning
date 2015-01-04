package com.xzymon.weblearning.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.xzymon.weblearning.model.util.UserType;

@Entity
@DiscriminatorValue("Admin")
public class Admin extends User {
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Admin: [").append(super.toString()).append("]");
		return sb.toString();
	}
}
