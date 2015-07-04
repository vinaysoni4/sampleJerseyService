package com.vsoni.test;

import java.sql.Connection;
import java.sql.Statement;

public class Salesguy {

	private int empid;
	private String name;
	private String number;
	private int status;

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	void sendNotification(Client cls) {
		Connection con = databaseHandling.getdatabaseobj().con;

		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE salesemployee set status=1 where saleempid="
					+ this.getEmpid());
			this.status = 1;
		} catch (Exception e) {
			e.printStackTrace();
			ClientLogin.customerQueue.add(cls.getCustid()); // again put the
															// customer to queue
			if (this.status == 1) {
				UpdateSalesguyStatus ss = new UpdateSalesguyStatus();
				ss.updatestatus(this.empid); // free the salesguy
			}

		}
		try {
			Statement stmt = con.createStatement();
			String Query = "INSERT INTO customerassign VALUES("+this.empid+","+cls.getCustid()+",now())";
			stmt.executeUpdate(Query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		sendMessage(number, cls);
	}

	void sendMessage(String number, Client cl) { // send client info to salesguy
													// number
		System.out.println("Message has been sent to saleguy: " + this.name);
		System.out.println(cl);
	}
}
