package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmpDAO;
import entity.Emp;

public class UpdateEmpServlet extends HelloServlet {
	 
	public void service(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String salary = request.getParameter("salary");
		EmpDAO dao = new EmpDAO();
		Emp emp = new Emp();
		emp.setId(Integer.parseInt(id));
		emp.setName(name);
		emp.setAge(Integer.parseInt(age));
		emp.setSalary(Double.parseDouble(salary));
		try {
			dao.update(emp);
			response.sendRedirect("list");
		} catch (Exception e) {
			e.printStackTrace();
			out.println("系统繁忙，稍后在试试");
		}
		
	}

}
