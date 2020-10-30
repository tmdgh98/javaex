<%@page import="com.yedam.ho.Employee"%>
<%@page import="java.util.List"%>
<%@page import="com.yedam.ho.EmpDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="public/jquery-3.5.1.min.js"></script>
<script src="js/empInfo.js"></script>

<style>
	div#detail{
		border : 1px dotted blue;
		background : black;
	}
	div#detail>label{
		color : white;	
		display : inline-block;
	}
	div#detail>button{
		border-radius:3px;
		background:black;
		color:white;
		border : 3px solid red;
		position : relative;
		left: 100px;
	}
</style>
</head>
<body>
	<%
		String empId = request.getParameter("id");
		
	%>
	<h1>아이디 : <%=empId %></h1>
	
	<div id = "detail">
		<label for='eid'>EmpId</label>
		<input type="text" id="eid" value="100" readonly><br>
		<label for='fName'>Fname</label>
		<input type="text" id="fName" value="kim" readonly><br>
		<label for='lName'>Lname</label>
		<input type="text" id="lName" value="ho" readonly><br>
		<label for='salary'>salary</label>
		<input type="text" id="salary" value="4000"><br>
		<Label for="job">Job</Label>
		<select id="job">
			<option value="AD_PRES">President</option>
			<option value="AD_VP">Administration Vice President</option>
			<option value="AD_ASST">Administration Assistant</option>
			<option value="AC_MGR">Accounting Manager</option>
			<option value="AC_ACCOUNT">Public Accountant</option>
			<option value="SA_MAN">Sales Manager</option>
			<option value="SA_REP">Sales Representative</option>
			<option value="ST_MAN">Stock Manager</option>
			<option value="ST_CLERK">Stock Clerk</option>
			<option value="IT_PROG">Programmer</option>
			<option value="MK_MAN">Marketing Manager</option>
			<option value="MK_REP">Marketing Representative</option>
		</select><br>
		<button id = 'changeBtn'>OK</button>
	</div>
	<div id = 'a'></div>
	<div id = 'show'></div>
	
	<script>
	$(document).ready(function(){
		let id = <%=empId %>;
		$.ajax({
			url:'GetEmpInfoServlet',
			data:{id : id},
			success: show,
			error:function(reject){
				console.log(new Error(reject));
			},
			dataType: 'json'
		})
	})
</script>
</body>
</html>