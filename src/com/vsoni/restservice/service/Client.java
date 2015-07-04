package com.vsoni.restservice.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Client {

	private int custid;
	private String name;
	private String number;
	private String email;

	public int getCustid() {
		return custid;
	}

	public void setCustid(int custid) {
		this.custid = custid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "ClientModal [name=" + name + ", number=" + number + ", email="
				+ email + "]";
	}

	boolean getclient(int clientId) {
		System.out.println("get client info:  " + clientId);
		Connection con = databaseHandling.getdatabaseobj().con;
		try {
			Statement stmt = con.createStatement();
			String Query = "SELECT * FROM customer WHERE custid=" + clientId;
			ResultSet rs = stmt.executeQuery(Query);
			while (rs.next()) {
				this.setCustid(rs.getInt("custid"));
				this.setName(rs.getString("name"));
				this.setNumber(rs.getString("number"));
				this.setEmail(rs.getString("email"));
				return true;
			}
		} catch (Exception e) {
			System.err.println("error while reading saleemployee table");
		}
		return false;
	}
}
