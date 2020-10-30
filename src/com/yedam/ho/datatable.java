package com.yedam.ho;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class datatable
 */
@WebServlet("/datatable")
public class datatable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public datatable() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// {"draw":1, "recordsTotal": 57, "recordsFiltered": 57,
//			"data": [[ val1, va2, val3, val4, val5, val6],[],[],[],[]
		// ]}
		EmpDAO dao = new EmpDAO();
		List<Employee> employees = dao.getEmpList();
		int dataCnt = employees.size();
		String json = "{\"draw\":1, \"recordsTotal\": "+ dataCnt+", \"recordsFiltered\": "+dataCnt+",";
		json += "\"data\": [";
		for(int i=0; i<dataCnt; i++) {
			if(i!=0) {
				json+=",";
			}
			json += "[";
		
			json += employees.get(i).getEmployeeId()+","+employees.get(i).getFirstName()+","+employees.get(i).getEmail()+","+employees.get(i).getPhoneNumber()+","+employees.get(i).getDepartmentId()+","+employees.get(i).getSalary();
			
			json += "]";
		}
		json += "]}";
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
