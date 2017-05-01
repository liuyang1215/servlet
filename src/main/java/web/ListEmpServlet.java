package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmpDAO;
import entity.Emp;

public class ListEmpServlet extends HelloServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		EmpDAO dao = new EmpDAO();
		try{
			List<Emp>list = dao.findAll();
			out.println("<table border='1' width=50% cellspacing='0' celllpadding='8' bgcolor='pink'>");
			out.println("<tbody align=center>");
			out.println("<tr><td>ID</td><td>姓名</td><td>年龄</td><td>薪水</td><td>操作</td>");
			for(int i=0;i<list.size();i++) {
				Emp emp  = list.get(i);
				out.println("<tr><td>"+emp.getId()+"</td><td>"
												+emp.getName()+"</td><td>"
												+emp.getAge()+"</td><td>"
												+emp.getSalary()+"<td><a href='del?id=" + emp.getId() + "'>删除</a>&nbsp;&nbsp;"
														+ "<a href='load?id=" + emp.getId() + "'>修改</a></td></tr>");
			}
			out.println("</tbody>");
			out.println("</table>");
			out.println("<h3><a href='addEmp.html'>添加员工</a></h3>");
		} catch (Exception e) {
				e.printStackTrace();
				out.println("系统繁忙，请稍后重试");
		}
	}
}
