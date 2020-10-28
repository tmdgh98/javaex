package com.yedam.ho;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {
	public static void main(String[] args) {
		EmpDAO ed = new EmpDAO();
		
		Map<String, Integer> aa = ed.getMemberPerDept();
		
		System.out.println(aa.size());
		String a = "14124";
		a.length();
	}
}
