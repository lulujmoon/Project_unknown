<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Insert title here</title>
</head>
<body>
	<h2>Product Insert Page</h2>
	<form action="./insert" method="POST" enctype="multipart/form-data">

		<div class="form-group">
			<label>제목</label> <input type="text" name="socialTitle">
		</div>

		<div class="form-group">
			<label>작성자</label> <input type="text" name="username">
		</div>

		<div class="form-group">
			<label>카테고리</label> <input type="text" name="categoryCode">
		</div>

		<div class="form-group">
			<label>글 내용</label>
			<input type="text" name="socialContent">
		</div>
		
		<div>
			<input type="file" name="files">
		</div>

		<button class="btn btn-outline-secondary">작성</button>
	</form>
</body>
</html>