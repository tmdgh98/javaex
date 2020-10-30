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
		let xhtp = new XMLHttpRequest();
		xhtp.onreadystatechange = function(){
			if(xhtp.readyState == 4 && xhtp.status == 200){
				console.log(xhtp.responseText);
				let data = JSON.parse(xhtp.responseText);
				console.log(data);
				console.log(data.id)
				document.getElementById('eid').value = data.id;
				document.getElementById('fName').value = data.firstName;
				document.getElementById('lName').value = data.lastName;
				document.getElementById('salary').value = data.salary;
				let option = document.querySelectorAll('option');
				
				for(a of option){
					if(a.value==data.jobId){
						a.setAttribute('selected','true');
					}
				}
				
				let div = document.createElement('div');
				div.innerHTML="";
				for(a in data){
					div.innerHTML += a + " "; 
					let input = document.createElement('input');
					input.type = "text";
					input.setAttribute('value',data[a]);
					if(a=='id'){
						input.setAttribute('readonly','true');
					}
					div.append(input);
					div.innerHTML += '<br>';
				}
				let btn = document.createElement('button');
				btn.innerHTML = 'button';
				div.append(btn);
				document.querySelector('#a').append(div);
				
				let ul = document.createElement('ul');
				for(a in data){
					let li = document.createElement('li');
					li.innerHTML = a +" : "+data[a];
					ul.append(li);
				}
				document.getElementById("show").append(ul);
			}
		}
		xhtp.open('get','GetEmpInfoServlet?id=<%=empId %>');
		xhtp.send();
		
		let btn = document.getElementById('changeBtn');
		btn.addEventListener('click', myChangeFunc);
		function myChangeFunc(){
			let eid = document.getElementById('eid').value;
			let fName = document.getElementById('fName').value;
			let lName = document.getElementById('lName').value;
			let salary = document.getElementById('salary').value;
			let job = document.getElementById('job').value;
			let para = 'id='+eid+'&firstName='+fName+'&lastName='+lName+'&salary='+salary+'&jobId='+job;
			let xhttp = new XMLHttpRequest();			
			xhttp.onreadstatechange = function(){
				if(xhttp.readyState == 4 && xhttp.status==200){
					console.log(xhttp.responseText);
					location.herf = "index.html";
					
				}
			}
			xhttp.open("get","PutEmpInfoServlet?"+para);
			xhttp.send();
		}
	</script>
</body>
</html>