package com.example.crudwithvaadin.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Customer")
public class Customer extends SecureEntity<Long>{

	private String firstName;

	private String lastName;

	private Integer age;



	protected Customer() {
		this.firstName="";
		this.lastName="";
		this.age=0;
	}

	public Customer(String firstName, String lastName, Integer age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return String.format("Customer[id=%d, firstName='%s', lastName='%s', age='%s']", getId(),
				firstName, lastName,age);
	}

}
