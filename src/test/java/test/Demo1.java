package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.jdt.internal.compiler.classfmt.MethodInfoWithAnnotations;
import org.junit.Test;

import dao.EmpDAO;
import entity.Emp;
import util.DBUtil;


public class Demo1 {
	
	@Test
	public void test1()  {
		Connection connection= null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
//			connection = DriverManager.getConnection( "jdbc:mysql://localhost:3306/my","root","123456");
			connection = DriverManager.getConnection( "jdbc:mysql://127.0.0.1:3306/my?characterEncoding=utf8&useSSL=false","root","123456");
			System.out.println("Success loading Mysql Driver!");
			Statement statement  =  connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from emp");
			while(rs.next()){
				System.out.println(rs.getString("name"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("找不到驱动",e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("创建连接失败",e);
		}
		
	}
	
	@Test
	public void test2() {
		Connection connection = null;
		try {
			connection = DBUtil.getConnection();
			System.out.println(connection);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("创建连接失败",e);
		}
	}
	
	@Test
	public void test3() {
		Connection connection = null;
		try {
			connection = DBUtil.getConnection();
			EmpDAO dao  = new EmpDAO();
			Emp emp = new Emp();
			emp.setName("sally");
			emp.setAge(20);
			emp.setSalary(20000);
			dao.save(emp);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("创建连接失败",e);
		}
	}
	
	@Test
	public void test4() {
		EmpDAO dao = new EmpDAO();
		dao.delete(3);
	}

	@Test
	public void test5() {
		EmpDAO dao = new EmpDAO();
		Emp emp = dao.findById(9);
		System.out.println(emp);
	}
	
	@Test
	public void test6() {
		EmpDAO dao  = new EmpDAO();
		Emp emp = dao.findById(9);
		emp.setSalary(emp.getSalary()*2);
		dao.update(emp);
		
	}
}
