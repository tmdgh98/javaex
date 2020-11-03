package com.yedam.cafe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.yedam.common.DBconnect;

public class ProductDAO {
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	private String sql;

	public List<Product> getProductList(){
		conn = DBconnect.getConnection();
		sql = "select * from product";
		List<Product> list = new LinkedList<Product>();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				Product pr = new Product(rs.getString("item_no"),rs.getString("item_name"),rs.getInt("price"),rs.getString("item_desc"),rs.getDouble("like_it"),rs.getString("category"),rs.getString("item_img"));
				list.add(pr);
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
	
	public void insertProduct(String num, String name, String price, String desc, String like, String img) {
		conn = DBconnect.getConnection();
		sql = "insert into product values(?,?,?,?,?,'been',?)";
		//'bean_01','케냐원두',4000,'케냐산 맛있는 원두입니다',4.5,'been','케냐.jpg'
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, num);
			psmt.setString(2, name);
			psmt.setString(3, price);
			psmt.setString(4, desc);
			psmt.setString(5, like);
			psmt.setString(6, img);
			int r = psmt.executeUpdate();
			System.out.println(r+"건 업데이트되었습니다");
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
