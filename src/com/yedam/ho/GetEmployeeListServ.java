package com.yedam.ho;

import java.io.IOException;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/GetEmployeeListServ")
public class GetEmployeeListServ extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    
    public GetEmployeeListServ() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		response.getWriter().append("Served at: ").append(request.getContextPath()).append("<b>Hello</b>");
		
		//{"name" : "Hong", "age" : 20, "score" : 80}
//		String json = "{\"name\" : \"Hong\", \"age\" : 20, \"score\" : 80}";
//		String json = "[{"+"}]";
		EmpDAO dao = new EmpDAO();
		List<Employee> list = dao.getEmpList();
		String json = "[";
		boolean first = true;
		for(Employee emp : list) {
			if(first) {
				json += "{";
				first = false;
			}else {
				json += ",{";
			}
			json += "\"id\":\""+emp.getEmployeeId()+"\",";
			json += "\"firstName\":\""+emp.getFirstName()+"\",";
			json += "\"lastName\":\""+emp.getLastName()+"\",";
			json += "\"email\":\""+emp.getEmail()+"\",";
			json += "\"phoneNumber\":\""+emp.getPhoneNumber()+"\",";
			json += "\"hireDate\":\""+emp.getHireDate()+"\",";
			json += "\"jobId\":\""+emp.getJobId()+"\",";
			json += "\"salary\":\""+emp.getSalary()+"\",";
			json += "\"commissionPct\":\""+emp.getCommissionPct()+"\",";
			json += "\"managerId\":\""+emp.getManagerId()+"\",";
			json += "\"departmentId\":\""+emp.getDepartmentId()+"\"";
			json += "}";
		}
		json += "]";
		response.getWriter().append(json);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
