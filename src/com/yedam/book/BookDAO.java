package com.yedam.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.common.DBconnect;

public class BookDAO {
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	private String sql;
	
	public List<Book> bookAll(){
		List<Book> list = new ArrayList<Book>();
		conn = DBconnect.getConnection();
		sql = "select * from book";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				Book book = new Book(rs.getInt("book_no"), rs.getString("book_title"), rs.getString("book_author"), rs.getInt("book_price"));
				list.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public int addBook(String title, String author, int price) {
		int id = 10;
		
		conn = DBconnect.getConnection();
		try {
			sql = "select book_seq.nextval from dual";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if(rs.next());
			id = rs.getInt("NEXTVAL");
			System.out.println(id);
			
			sql ="insert into book values("+id+", ?, ?, ?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.setString(2, author);
			psmt.setInt(3, price);
			int r =psmt.executeUpdate();
			System.out.println(r +"건 추가완료");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("에러러러러");
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return id;
	}
	
	public void delBook(String id) {
		conn = DBconnect.getConnection();
		sql = "delete from book where book_no = ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			int r = psmt.executeUpdate();
			System.out.println(r + "건이 삭제되었습니다");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void updataBook(String title, String author, String price, String id) {
		conn = DBconnect.getConnection();
		sql = "update book set book_title = ?,book_author = ?,book_price= ? where book_no = ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.setString(2, author);
			psmt.setString(3, price);
			psmt.setString(4, id);
			int r = psmt.executeUpdate();
			System.out.println(r+"건 수정되었습니다");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
