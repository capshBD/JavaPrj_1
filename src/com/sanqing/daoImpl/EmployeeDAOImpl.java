package com.sanqing.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sanqing.bean.Employee;
import com.sanqing.dao.EmployeeDAO;
import com.sanqing.util.DBConnection;

public class EmployeeDAOImpl implements EmployeeDAO {

	@Override
	public void addEmployee(Employee employee) {
		Connection conn=DBConnection.getConnection();
		String queryIdSql="select max(employeeID) from tb_employee";
		String addSql="insert into tb_employee values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int empID=0;
		
		try {
			pstmt=conn.prepareStatement(queryIdSql);
			rs=pstmt.executeQuery();
			if(rs.next()){
				empID=rs.getInt(1);
			}
			pstmt=conn.prepareStatement(addSql);
			pstmt.setInt(1, empID+1);
			pstmt.setString(2, employee.getEmployeeName());
			pstmt.setBoolean(3, employee.isEmployeeSex());
			pstmt.setDate(4, new Date(employee.getEmployeeBirth().getTime()));
			pstmt.setString(5, employee.getEmployeePhone());
			pstmt.setString(6, employee.getEmployeePlace());
			pstmt.setDate(7, new Date(employee.getJoinTime().getTime()));
			pstmt.setString(8, employee.getPassword());
			pstmt.setBoolean(9, employee.isLead());
			pstmt.executeUpdate();
			employee.setEmployeeID(empID+1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnection.close(rs);
			DBConnection.close(pstmt);
			DBConnection.close(conn);
			
		}
	}

	@Override
	public void updateEmployee(Employee employee) {
		

	}

	@Override
	public void deleteEmployee(int employeeID) {
		

	}

	@Override
	public List<Employee> findAllEmployee() {
		
		return null;
	}

	@Override
	public Employee findEmployeeById(int employeeID) {
		Connection conn=DBConnection.getConnection();
		String findByIdSql="select * from tb_employee where employeeID=?";
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Employee employee=null;
		
		try {
			pstmt=conn.prepareStatement(findByIdSql);
			pstmt.setInt(1, employeeID);
			rs=pstmt.executeQuery();
			if(rs.next()){
				employee=new Employee();
				employee.setEmployeeID(rs.getInt(1));
				employee.setEmployeeName(rs.getString(2));
				employee.setEmployeeSex(rs.getBoolean(3));
				employee.setEmployeeBirth(rs.getDate(4));
				employee.setEmployeePhone(rs.getString(5));
				employee.setEmployeePlace(rs.getString(6));
				employee.setJoinTime(rs.getDate(7));
				employee.setPassword(rs.getString(8));
				employee.setLead(rs.getBoolean(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnection.close(rs);
			DBConnection.close(pstmt);
			DBConnection.close(conn);
			
		}
		
		return employee;
	}

}
