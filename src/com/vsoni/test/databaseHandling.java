package com.vsoni.test;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;

class databaseHandling {

	static final databaseHandling dbobj = new databaseHandling();
	Connection con;

	private databaseHandling() {
	}

	public static databaseHandling getdatabaseobj() {

		if (dbobj.con == null) {
			try {
				dbobj.con = getConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return dbobj;
	}

	private static Connection getConnection() throws Exception {
		Class.forName("org.postgresql.Driver");
		URI dbUri = new URI(
				"postgres://rjewavevkcfjxg:0MWDh6J_IgcLldtc7cuLJ9CHTz@ec2-54-204-13-220.compute-1.amazonaws.com:5432/d5q3egpp6ad5");

		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://"
				+ dbUri.getHost()
				+ dbUri.getPath()
				+ "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
		return DriverManager.getConnection(dbUrl, username, password);
	}

}
