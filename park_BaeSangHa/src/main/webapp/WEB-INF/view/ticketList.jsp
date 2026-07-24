<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h2 align="center">정기권 조회</h2>

<table border="1" cellpadding="5" cellspacing="0" align="center">
	<thead>
		<tr>
			<th>정기권번호</th>
			<th>차량번호</th>
			<th>차주전화</th>
			<th>주차등급</th>
			<th>정기권상태</th>
			<th>시작일</th>
			<th>종료일</th>
		</tr>
	</thead>
	<tbody>
		<!-- 컨트롤러에서 넘겨준 데이터 유효성 확인 -->
		<c:choose>
			<c:when test="${empty ticketList}">
				<tr>
					<td colspan="7" align="center">등록된 정기권 내역이 없습니다.</td>
				</tr>
			</c:when>
			<c:otherwise>
				<!-- 데이터가 유효하다면 목록 출력 -->
				<c:forEach var="ticket" items="${ticketList}">
					<tr>
						<td>${ticket.tno}</td>
						<td>${ticket.carno}</td>
						<td>${ticket.phone}</td>

						<!-- 주차등급 출력 -->
						<td><c:choose>
								<c:when test="${ticket.grade == 'M'}">월회원</c:when>
								<c:when test="${ticket.grade == 'Y'}">연회원</c:when>
								<c:otherwise>${ticket.grade}</c:otherwise>
							</c:choose></td>

						<td>${ticket.tstat}</td>

						<!-- 날짜 출력 -->
						<td><fmt:formatDate value="${ticket.startdate}"
								pattern="yyyy-MM-dd" /></td>
						<td><fmt:formatDate value="${ticket.enddate}"
								pattern="yyyy-MM-dd" /></td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</tbody>
</table>