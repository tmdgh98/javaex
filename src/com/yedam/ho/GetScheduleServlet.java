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
 * Servlet implementation class GetScheduleServlet
 */
@WebServlet("/GetScheduleServlet")
public class GetScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetScheduleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray jAry = new JSONArray();
		JSONObject obj;
		EmpDAO dao = new EmpDAO();
		List<FullCalendar> list = dao.getSchedules();
		for(FullCalendar fc : list) {
			obj = new JSONObject();
			obj.put("title",fc.getTitle());
			obj.put("start",fc.getStartDate());
			obj.put("end",fc.getEndDate());
			jAry.add(obj);
		}
		response.getWriter().append(JSONArray.fromObject(jAry).toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
