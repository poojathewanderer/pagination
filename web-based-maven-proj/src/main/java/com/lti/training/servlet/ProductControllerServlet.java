package com.lti.training.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lti.training.dao.DataAccessException;
import com.lti.training.dao.ProductDao;
import com.lti.training.model.Product;


public class ProductControllerServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageSize = 5;
		
		HttpSession httpSession = request.getSession();
		
		Integer currentPosition = (Integer) request.getAttribute("currentPosition");
		if(currentPosition ==  null)
			currentPosition = 1;
		
		
		String go = request.getParameter("go");
		if(go != null) {
			if(go.equals("next"))
				currentPosition += pageSize;
			else if(go.equals("prev"))
				currentPosition -= pageSize;
		}
		else
			currentPosition = 1;
		
		httpSession.setAttribute("currentPosition", currentPosition);
		
		ProductDao dao = new ProductDao();
		try {
			System.out.println(currentPosition);
			List<Product> currentProducts = dao.fetchProduct(currentPosition, currentPosition + pageSize);
			request.setAttribute("currentProducts", currentProducts);
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("viewProducts.jsp");
			dispatcher.forward(request, response);
			
		} catch (DataAccessException e) {e.printStackTrace();}
		
	}

}
