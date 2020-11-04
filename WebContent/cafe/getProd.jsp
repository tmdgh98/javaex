<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Shop Homepage - Start Bootstrap Template</title>

  <!-- Bootstrap core CSS -->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="css/shop-homepage.css" rel="stylesheet">
  
</head>

<body>

  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
      <a class="navbar-brand" href="#">Start Bootstrap</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
        aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <a class="nav-link" href="index.html">Home
              <span class="sr-only">(current)</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">About</a>
          </li>
          <li class="nav-item active">
            <a class="nav-link" href="product.html">Services</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Contact</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <!-- Page Content -->
  <div class="container">

    <div class="row">

      <div class="col-lg-3">

        <h1 class="my-4">Shop Name</h1>
        <div class="list-group">
          <a href="category.jsp?category=bean" class="list-group-item">Bean</a>
          <a href="category.jsp?category=dutch" class="list-group-item">Dutch</a>
          <a href="category.jsp?category=beverage" class="list-group-item">Beverage</a>
        </div>

      </div>
      <!-- /.col-lg-3 -->

      <div class="col-lg-9">


        <div style="height:100px;"></div>
        <div class="row" id="data">
          <form action="../PutProductServlet" id="frm" method="post">
            <table border="1">
              <tr>
                <td>상품코드</td>
                <td><input type="text" name="itemNo"></td>
              </tr>
              <tr>
                <td>상품명</td>
                <td><input type="text" name="itemName"></td>
              </tr>
              <tr>
                <td>가격</td>
                <td><input type="text" name="price"></td>
              </tr>
              <tr>
                <td>상품설명</td>
                <td><textarea name="itemDesc"></textarea></td>
              </tr>
              <tr>
                <td>평가</td>
                <td><input type="text" name="likeIt"></td>
              </tr>
              <tr>
                <td>상품카테고리</td>
                <td><input type="text" name="category"></td>
              </tr>
              <tr>
                <td>상품이미지</td>
                <td><input type="text" name="itemImg"></td>
              </tr>
              <tr>
                <td colspan="2"><input type="submit" value="저장"></td>
              </tr>
            </table>
          </form>
        </div>
        <!-- /.row -->

      </div>
      <!-- /.col-lg-9 -->

    </div>
    <!-- /.row -->

  </div>
  <!-- /.container -->

  <!-- Footer -->
  <footer class="py-5 bg-dark">
    <div class="container">
      <p class="m-0 text-center text-white">Copyright &copy; Your Website 2020</p>
    </div>
    <!-- /.container -->
  </footer>
	<% 
  	  String id = request.getParameter("item_no");
    %>
	
  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
 	<script>
 		let num = "<%=id%>"
 		$.ajax({
 			url: '../GetProduct',
 			dataType : 'json',
 			data:{'id' : num},
 			success:function(data){
 					console.log(data);
 				let title=['itemNo','itemName','price','likeIt','category','itemImg','itemDesc']
 				let put = $('input');
 				let i = 0;
 				for(let i = 0;i<title.length;i++){
 					if(title[i]=='itemDesc')
 						$('textarea').val(data[title[i]]);
 					else{
 						$(put[i]).val(data[title[i]]);
 					}
 				}
 			}
 		})
 	</script>
</body>

</html>