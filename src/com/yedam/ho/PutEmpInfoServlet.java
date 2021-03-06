package com.yedam.ho;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PutEmpInfoServlet
 */
@WebServlet("/PutEmpInfoServlet")
public class PutEmpInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PutEmpInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String eid = request.getParameter("id");
		String fName = request.getParameter("fristName");
		String lName = request.getParameter("lastName");
		String salary = request.getParameter("salary");
		String job = request.getParameter("jobId");
		
		//수정기능 추가
		EmpDAO dao = new EmpDAO();
		dao.updateEmpInfo(eid, fName, lName, salary, job);
		
		response.getWriter().append("complet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
