package com.yedam.ho;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class DataTableServletJSON
 */
@WebServlet("/DataTableServletJSON")
public class DataTableServletJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataTableServletJSON() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmpDAO dao = new EmpDAO();
		List<Employee> list = dao.getEmpList();
		int dataCnt = list.size();
		JSONObject obj = new JSONObject();
		obj.put("draw", 1);
		obj.put("recordsTotal", dataCnt);
		obj.put("recordsFiltered", dataCnt);
		JSONArray oAry = new JSONArray();
		JSONArray iAry;
		for(Employee emp : list) {
			iAry = new JSONArray();
			iAry.add(emp.getEmployeeId());
			iAry.add(emp.getFirstName());
			iAry.add(emp.getEmail());
			iAry.add(emp.getPhoneNumber());
			iAry.add(emp.getHireDate());
			iAry.add(emp.getSalary());
			
			oAry.add(iAry);
		}
		obj.put("data", oAry);
		response.getWriter().append(obj.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
