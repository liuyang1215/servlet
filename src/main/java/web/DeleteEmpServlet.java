package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.InsufficientResourcesException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmpDAO;
import entity.Emp;

public class DeleteEmpServlet extends HelloServlet {
	
	public void service(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		EmpDAO dao = new EmpDAO();
		String id = request.getParameter("id");
		
		try{
			dao.delete(Integer.parseInt(id));
			response.sendRedirect("list");
		} catch (Exception e) {
			e.printStackTrace();
			out.println("系统繁忙，请稍后在试试");
		}
		
	}

}
