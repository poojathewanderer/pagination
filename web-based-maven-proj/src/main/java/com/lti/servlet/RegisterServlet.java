package com.lti.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lti.dto.DataAccessException;
import com.lti.dto.Employee;
import com.lti.dto.EmployeeDao;

@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// get data from form
		int empno = Integer.parseInt(request.getParameter("empno"));
		String name = request.getParameter("name");
		double salary = Double.parseDouble(request.getParameter("salary"));
		
		// Add employee details to dao object
		Employee emp = new Employee();
		emp.setEmpno(empno);
		emp.setEname(name);
		emp.setEsal(salary);

		// Insert into database
		EmployeeDao eDao = new EmployeeDao();
		boolean bInserted = false;
		try {
			bInserted = eDao.insert(emp);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// If data inserted send redirect with status of request
		HttpSession session = request.getSession();
		if(bInserted) {
			session.setAttribute("message", "Record added Successfully!");
		}
		else {
			session.setAttribute("message", "Record not added !");
		}
		response.sendRedirect("addEmployee.jsp");
	}
}
