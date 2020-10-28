package com.yedam.ho;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class getMemberPerDept
 */
@WebServlet("/getMemberPerDept")
public class GetMemberPerDept extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMemberPerDept() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		EmpDAO dao = new EmpDAO();
		Map<String, Integer> members = dao.getMemberPerDept();
		Set<String> keySet = members.keySet();
		String json = "[";
		int cnt =0;
		int datalength = keySet.size();
		for(String key : keySet) {
			System.out.println(key + ", " + members.get(key));
			json += "{\""+key+ "\" : "+ members.get(key)+"}";
			cnt++;
			if(cnt!=datalength) {
				json += ",";
			}
		}
		json += "]";
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
