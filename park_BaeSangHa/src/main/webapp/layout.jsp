<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주차 관리 시스템</title>
</head>
<body>

	<!-- Header 영역 -->
	<header align="center">
		<h1>
			<a href="index.jsp" style="text-decoration: none; color: black;">
				주차관리 프로그램 ver 1.0 </a>
		</h1>
	</header>

	<hr>

	<!-- Nav 영역 -->
	<nav align="center">
		<a href="registerTicket.do">정기권 등록</a> &nbsp;&nbsp;|&nbsp;&nbsp; <a
			href="ticketList.do">정기권 조회</a> &nbsp;&nbsp;|&nbsp;&nbsp; <a
			href="parkInOut.do">주차장입·출고</a> &nbsp;&nbsp;|&nbsp;&nbsp; <a
			href="parkStatusList.do">주차 현황 조회</a>
	</nav>

	<hr>

	<!-- Section 영역 -->
	<section>
		<jsp:include page="${viewPage}" />
	</section>

</body>
</html>