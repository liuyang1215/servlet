package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmpDAO;
import entity.Emp;

public class LoadEmpServlet extends HelloServlet{ 
	public void service(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		EmpDAO dao = new EmpDAO();
		String id = request.getParameter("id");
		try {
			Emp emp = dao.findById(Integer.parseInt(id));
			out.println("<form action='upd' method='post'>");
			out.println("ID:"+emp.getId()+"<br/>");
			out.println("姓名：<input type = 'text' name='name' value='"+emp.getName()+"'/><br/>");
			out.println("年龄：<input type='text' name='age' value='"+emp.getAge()+"'/><br/>");
			out.println("薪水：<input type='text' name='salary' value='"+emp.getSalary()+"'/><br/>");
			out.println("<input type='hidden' name='id' value='"+emp.getId()+"'/>");
			out.println("<input type='submit' value='提交'/>");
			out.println("</form>");
		} catch (Exception e) {
			e.printStackTrace();
			out.println("系统繁忙，稍后再试");
		}
		
	}

}
