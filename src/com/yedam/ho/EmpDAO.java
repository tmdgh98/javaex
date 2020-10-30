package com.yedam.ho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yedam.common.DBconnect;

public class EmpDAO {
	
	PreparedStatement psmt;
	ResultSet rs;
	Connection conn;
	
	public Employee getEmpInfo(String id) {
		conn = DBconnect.getConnection();
		Employee emp = new Employee();
		try {
			psmt = conn.prepareStatement("select * from employees where employee_id = ?");
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if(rs.next()) {
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setPhoneNumber(rs.getString("phone_number"));
				emp.setHireDate(rs.getString("hire_date"));
				emp.setJobId(rs.getString("job_id"));
				emp.setSalary(rs.getInt("salary"));
				emp.setCommissionPct(rs.getDouble("commission_pct"));
				emp.setManagerId(rs.getInt("manager_id"));
				emp.setDepartmentId(rs.getInt("department_id"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return emp;
	}
	
	public List<Employee> getEmpList(){
		conn = DBconnect.getConnection();
		List<Employee> employees = new ArrayList<Employee>();
		try {
			psmt = conn.prepareStatement("select * from employees order by 1");
			rs = psmt.executeQuery();
			while(rs.next()) {
				Employee emp = new Employee();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setPhoneNumber(rs.getString("phone_number"));
				emp.setHireDate(rs.getString("hire_date"));
				emp.setJobId(rs.getString("job_id"));
				emp.setSalary(rs.getInt("salary"));
				emp.setCommissionPct(rs.getDouble("commission_pct"));
				emp.setManagerId(rs.getInt("manager_id"));
				emp.setDepartmentId(rs.getInt("department_id"));
				
				employees.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return employees;
	}
	
	public Map<String, Integer> getMemberPerDept() {
		conn = DBconnect.getConnection();
		String sql = "select d.department_name as a, e.cnt as b from (select department_id, count(*) as cnt from employees group by department_id) e, departments d where e.department_id = d.department_id";
		Map<String, Integer> memberPerDept= new HashMap<String, Integer>();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				memberPerDept.put(rs.getString("a"), rs.getInt("b"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return memberPerDept;
	}
	
	public void updateEmpInfo(String eid, String f, String l, String s, String j) {
		conn = DBconnect.getConnection();
		String sql = "update employees set salary = ?, job_id = ? where employee_id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, s);
			psmt.setString(2, j);
			psmt.setString(3, eid);
			int r = psmt.executeUpdate();
			System.out.println(r + "건 변경완료.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
