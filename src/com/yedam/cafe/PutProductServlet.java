package com.yedam.cafe;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PutProductServlet
 */
@WebServlet("/PutProductServlet")
public class PutProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PutProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		ProductDAO dao = new ProductDAO();
		String num = request.getParameter("itemNo");
		String name = request.getParameter("itemName");
		String price = request.getParameter("price");
		String desc = request.getParameter("itemDesc");
		String like = request.getParameter("likeIt");
		String img = request.getParameter("itemImg");
		
		dao.insertProduct(num, name, price, desc, like, img);
		//response.getWriter().append("success");
		PrintWriter out = response.getWriter();
//		out.print("<script>alert(\"OK\")</script>");
		out.print("<script>location.href=\"cafe/index.html\";</script>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
