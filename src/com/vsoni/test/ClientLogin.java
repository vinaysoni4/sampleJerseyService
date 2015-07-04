package com.vsoni.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Queue;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/customerlogin")
public class ClientLogin {

	static final Queue<Integer> customerQueue = new LinkedList<Integer>();

	@POST
	public void assignCustomer(@QueryParam("custid") int custid) {

		System.out.println("login customerid:  " + custid);
		Salesguy salesguy = getAvailableSG();
		if (salesguy != null) {
			Client cl = new Client();
			if (cl.getclient(custid)) {
				System.out.println("assigned emp id of sales guy:"
						+ salesguy.getEmpid());
				salesguy.sendNotification(cl);
			} else {
				System.out.println("customer is not registered");
			}
		} else {
			System.out
					.println("not sales guy is avaialbe and customer is kept in queue");
			customerQueue.add(custid);
		}
	}

	// get next available salesguy
	private Salesguy getAvailableSG() {
		Salesguy sg = null;
		Connection con = databaseHandling.getdatabaseobj().con;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM salesemployee WHERE status=0 LIMIT 1");
			while (rs.next()) {
				sg = new Salesguy();
				sg.setEmpid(rs.getInt("saleempid"));
				sg.setName(rs.getString("name"));
				sg.setNumber(rs.getString("number"));
				sg.setStatus(rs.getInt("status"));
			}

		} catch (Exception e) {
			System.err.println("error while reading saleemployee table");
		}
		return sg;
	}

}
