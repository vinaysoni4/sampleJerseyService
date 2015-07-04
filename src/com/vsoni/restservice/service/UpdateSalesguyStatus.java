package com.vsoni.restservice.service;

import java.sql.Connection;
import java.sql.Statement;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;


@Path("/updatesgstatus")
public class UpdateSalesguyStatus {

	@POST
	public void updatestatus(@QueryParam("empid") int empid) {
		System.out.println("emp id: " + empid + "  got free");
		Connection con = databaseHandling.getdatabaseobj().con;
		try {
			Statement stmt = con.createStatement();
			String Query = "UPDATE salesemployee set status=0 where saleempid="
					+ empid;
			stmt.executeUpdate(Query);
			Query = "UPDATE customerassign set endtime=now() WHERE saleempid="
					+ empid + " AND endtime IS NULL";
			stmt.executeUpdate(Query);
			checkCustomerQueue(); // whenver any employee get free it will check
									// customer queue
			// return "Sucessfull";
		} catch (Exception e) {
			e.printStackTrace();
		}
		// return "update failure";
	}

	// check customer queue
	private void checkCustomerQueue() {

		if (!ClientLogin.customerQueue.isEmpty()) {
			int custid = ClientLogin.customerQueue.poll();
			System.out.println("cust id+" + custid + " picked up from queue");
			ClientLogin cl = new ClientLogin();
			cl.assignCustomer(custid); // call assign customer method
		} else {
			System.out.println("cusotmer queeue is empty");
		}

	}
}
