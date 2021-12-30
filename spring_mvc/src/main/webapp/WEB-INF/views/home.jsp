<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- List를 순회하기 위해서 태그 라이브러리 설정 -->
<%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!-- List의 데이터가 있는지 없는지 확인하기 위해서 List의 길이를 확인하기 위해서
태그 라이브러리 설정 -->    
<%@ taglib prefix="fn"
uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring MVC</title>
<!-- css 링크 설정 -->
<!-- 이 파일은 webapp 디렉토리의 css 디렉토리에 style.css 입니다.
이런 이유로 외부 자원의 링크는 절대 경로로 설정하는 경우가 많습니다.-->
<link rel="stylesheet" href="./css/style.css" />
</head>
<body>
	<a href="hello">처음 만들어보는 요청</a><br/>
	<a href="/detail/1025">상세보기</a><br/>
	<a href="/param">파라미터 입력</a><br/>
	
	<a href="/forward">forwarding - 데이터 전달</a><br/>
	<a href="/redirect">redirect - 데이터 전달</a><br/>
	
	<div align="center" class="body">
		<h2>상품 목록</h2>
		<table border="1">
			<tr class="header">
				<th align="center" width="100">상품 아이디</th>
				<th align="center" width="320">상품 이름</th>
				<th align="center" width="100">상품 가격</th>
			</tr>
			
			<!-- list에 데이터가 없는 경우 -->
			<c:if test="${fn:length(list) == 0 }">
				<tr>
					<td colspan="3">
						데이터가 없습니다.
					</td>
				</tr>		
			</c:if>
			<!-- list에 데이터가 있는 경우 -->
			<c:if test="${fn:length(list) != 0 }">
				<c:forEach var="item" items="${list}">
					<tr class="record">
						<td align="center">${item.itemid}</td>
						<td align="left">&nbsp;
						<!-- 파라미터를 이용해서 데이터를 전달하는 방식 -->
						<!-- 
						<a href="/detail.html?itemid=${item.itemid}">${item.itemname}</a>
						 -->
						<!-- URL을 이용해서 데이터를 전달하는 방식 --> 
						<a href="/detail.html/${item.itemid}">${item.itemname}</a>
						</td>
						<td align="right">${item.price} 원&nbsp;</td>
					</tr>	
					
				</c:forEach>
			</c:if>
			
			
		</table>
	</div>
</body>
</html>




