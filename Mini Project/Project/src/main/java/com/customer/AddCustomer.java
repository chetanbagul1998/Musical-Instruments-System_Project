package com.customer;
import java.io.IOException;
import javax.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.connection.DatabaseConnection;

@WebServlet("/AddCustomer")
public class AddCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid=DatabaseConnection.generateCustomerId();
		String cname=request.getParameter("cname");
		String mobile=request.getParameter("mobile");
		String email=request.getParameter("email");;
		String address=request.getParameter("address");
		HttpSession hs=request.getSession();		
		try {
			int addCustomer=DatabaseConnection.insertUpdateFromSqlQuery("insert into customer(c_id,c_name,c_contact,c_email,c_address)values('"+cid+"','"+cname+"','"+mobile+"','"+email+"','"+address+"')");
			if(addCustomer>0) {
				String message="Customer add successfully.";
				hs.setAttribute("customer-add", message);
				response.sendRedirect("add-customer.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}