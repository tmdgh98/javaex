package com.yedam.ho;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetEmpInfoServlet
 */
@WebServlet("/GetEmpInfoServlet")
public class GetEmpInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetEmpInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String empId = request.getParameter("id");
		EmpDAO dao = new EmpDAO();
		Employee emp = dao.getEmpInfo(empId);
		String json = "[{";
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
		json += "}]";
		
		response.getWriter().append(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
