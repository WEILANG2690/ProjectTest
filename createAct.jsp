<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="styLesheet" href="../css/bootstrap.min.css">

<!-- <script src="js/bootstrap.min.js"></script> -->
<script src="js/jquery-3.2.1.min.js"></script>

<!-- Custom styles for this template -->
<link href="../css/navbar-fixed-top.css" rel="stylesheet">

<!-- jQuery UI Datepicker -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>
	//選擇活動
	$(function() {
		$("#actID").selectmenu();
	});

	//出發日期
	$(function() {
		$("#from").datepicker({
			changeMonth : true,
			changeYear : true
		});
	});
	//結束日期
	$(function() {
		$("#to").datepicker({
			changeMonth : true,
			changeYear : true
		});
	});

	//上下架
	$(function() {
		$("#select").selectmenu();
	});
</script>
</head>

<body>

	<!-- Fixed navbar -->
	<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Project name</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">Home</a></li>
				<li><a href="#about">About</a></li>
				<li><a href="#contact">Contact</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">Dropdown <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">Action</a></li>
						<li><a href="#">Another action</a></li>
						<li><a href="#">Something else here</a></li>
						<li role="separator" class="divider"></li>
						<li class="dropdown-header">Nav header</li>
						<li><a href="#">Separated link</a></li>
						<li><a href="#">One more separated link</a></li>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="../navbar/">Default</a></li>
				<li><a href="../navbar-static-top/">Static top</a></li>
				<li class="active"><a href="./">Fixed top <span
						class="sr-only">(current)</span></a></li>
			</ul>
		</div>
	</div>
	</nav>

  <jsp:useBean id="actSvc" scope="page" class="com.iii.eeit9703.activity.model.ActService" />

	<!-- Main component for a primary marketing message or call to action -->
	<div class="jumbotron">
		<div class="container">
			<h1>Activity</h1>
			<form  class="form-horizontal" method="post" action="activity.do">
				<div class="from-group">
					<label class="col-sm-2 control-lable" for="actID">選擇活動</label>
					<div class="col-sm-10">
						<select class="form-control input-sm" size="1" name="actID">
						<c:forEach var="activityVO" items="${actSvc.all}" > 
                        <option value="${activityVO.actID}">${activityVO.actID}
                        </c:forEach> 
						</select>
					</div>
				</div>
				<div class="from-group">
					<label for="actname" class="col-sm-2 control-lable">活動名稱</label>
					<div class="col-sm-10">
						<input type="text" class="form-control input-sm" id="actname">
					</div>
				</div>
				<div class="from-group">
					<label for="actgroup" class="col-sm-2 control-lable">成團人數</label>
					<div class="col-sm-10">
						<input type="text" class="form-control input-sm" id="actgroup">
					</div>
				</div>
				<div class="from-group">
					<label for="from" class="col-sm-2 control-lable">出發日期</label>
					<div class="col-sm-10">
						<input type="text" name="from" class="form-control input-sm"
							id="from">
					</div>
				</div>
				<div class="from-group">
					<label for="to" class="col-sm-2 control-lable">結束日期</label>
					<div class="col-sm-10">
						<input type="text" name="to" class="form-control input-sm" id="to">
					</div>
				</div>
				<div class="from-group">
					<label for="select" class="col-sm-2 control-lable">活動狀態</label>
					<div class="col-sm-10">
						<select class="form-control input-sm" id="select">
							<option value="0">建構中</option>
							<option value="1">上架</option>
							<option value="3">下架</option>
						</select>
					</div>
				</div>
				<div class="from-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-lg btn-primary">送出
							&raquo;</button>
						<button type="reset" class="btn btn-lg btn-primary">清除
							&raquo;</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- /container -->


</body>
</html>