package com.yedam.book;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String cmd = request.getParameter("cmd");
		if(cmd.equals("bookAll")) {
			BookDAO dao = new BookDAO();
			List<Book> list = dao.bookAll();
			JSONArray jarr = new JSONArray();
			for (Book book : list) {
				jarr.add(book);
			}
			response.getWriter().append(JSONArray.fromObject(jarr).toString());
		}else if(cmd.equals("addBook")) {
			String title = request.getParameter("title");
			String author = request.getParameter("author");
			String price = request.getParameter("price");
			int p = Integer.parseInt(price);
			BookDAO dao = new BookDAO();
			int id = dao.addBook(title, author, p); 
			
			response.getWriter().append(Integer.toString(id));
		}else if(cmd.equals("delBook")) {
			String id = request.getParameter("id");
			BookDAO dao = new BookDAO();
			dao.delBook(id);
		}else if(cmd.equals("updateBook")) {
			String id = request.getParameter("id");
			String title = request.getParameter("title");
			String author = request.getParameter("author");
			String price = request.getParameter("price");
			BookDAO dao = new BookDAO();
			dao.updataBook(title, author, price, id);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
