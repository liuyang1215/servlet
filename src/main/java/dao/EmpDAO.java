package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.core.ApplicationFilterRegistration;

import entity.Emp;
import util.DBUtil;

public class EmpDAO {
	
	public void save(Emp e) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into emp(name,age,salary) values(?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, e.getName());
			ps.setInt(2, e.getAge());
			ps.setDouble(3, e.getSalary());
			ps.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new RuntimeException(e1);
		} finally {
			DBUtil.close(conn);
		}
		
	}
	
	public List<Emp> findAll() {
		List<Emp> list = new ArrayList<Emp>();
		Connection connection = null;
		try {
			connection = DBUtil.getConnection();
			String sql = "select * from emp";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Emp emp = new Emp();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setAge(rs.getInt("age"));
				emp.setSalary(rs.getDouble("salary"));
				list.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(connection);
		}
		return list;
	}
	
	public void delete(int id) {
		Connection connection = null;
		try {
			connection = DBUtil.getConnection();
			String sql = "delete from emp where id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(connection);
		}
		
	}
	
	public Emp findById(int id) {
		Emp emp = null;
		Connection connection = null;
		try {
			connection = DBUtil.getConnection();
			String sql = "select * from emp where id =?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				emp = new Emp();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setAge(rs.getInt("age"));
				emp.setSalary(rs.getDouble("salary"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(connection);
		}
		return emp;
	}
	
	public void update(Emp emp) {
		Connection connection = null;
		try {
			connection = DBUtil.getConnection();
			String sql = "update emp set name=? , age=?, salary=? where id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, emp.getName());
			ps.setInt(2, emp.getAge());
			ps.setDouble(3, emp.getSalary());
			ps.setInt(4, emp.getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(connection);
		}
	}
}
