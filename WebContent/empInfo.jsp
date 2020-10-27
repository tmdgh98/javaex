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
</head>
<body>
	<%
		String empId = request.getParameter("id");
		
	%>
	<h1>아이디 : <%=empId %></h1>
	<div id = show></div>
	<script>
		let xhtp = new XMLHttpRequest();
		xhtp.onreadystatechange = function(){
			if(xhtp.readyState == 4 && xhtp.status == 200){
				console.log(xhtp.responseText);
				let data = JSON.parse(xhtp.responseText);
				console.log(data);
				console.log(data[0].id)
				let ul = document.createElement('ul');
				for(a in data[0]){
					let li = document.createElement('li');
					li.innerHTML = a +" : "+data[0][a];
					ul.append(li);
				}
				document.getElementById("show").append(ul);
			}
		}
		xhtp.open('get','GetEmpInfoServlet?id=<%=empId %>');
		xhtp.send();
		
	</script>
</body>
</html>