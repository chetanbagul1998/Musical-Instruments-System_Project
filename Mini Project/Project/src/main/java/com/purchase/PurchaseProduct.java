package com.purchase;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.*;
import com.connection.DatabaseConnection;

@WebServlet("/PurchaseProduct")
public class PurchaseProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		int productQuanity = 0;
		int purchaseProduct = 0;
		int updateProductQuantity = 0;
		String purchaseId = DatabaseConnection.generatePurchaseId();
		String vendorId = request.getParameter("vendorId");
		String productId = request.getParameter("productId");
		int quantity = Integer.parseInt(request.getParameter("orderQuantity"));
		HttpSession hs = request.getSession();
		try {
			purchaseProduct = DatabaseConnection.insertUpdateFromSqlQuery("insert into purchase(purchase_id,vendor_id,prod_id,order_qty)values('"+ purchaseId + "','" + vendorId + "','" + productId + "','" + quantity + "')");
			updateProductQuantity = DatabaseConnection.insertUpdateFromSqlQuery("update product_details set prod_qty=prod_qty + '" + quantity+ "' where prod_id='" + productId + "'");
			if (purchaseProduct > 0) {
				String message = "Purchase product succesfully.";
				hs.setAttribute("purchase", message);
				response.sendRedirect("purchase-product.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}