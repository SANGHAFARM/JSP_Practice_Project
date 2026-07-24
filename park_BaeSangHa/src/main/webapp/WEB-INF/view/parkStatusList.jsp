<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h2 align="center">주차 현황 조회</h2>

<table border="1" cellpadding="5" cellspacing="0" align="center">
	<thead>
		<tr>
			<th>주차번호</th>
			<th>차량번호</th>
			<th>주차등급</th>
			<th>상태</th>
			<th>입고일시</th>
			<th>출고일시</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${empty parkList}">
				<tr>
					<td colspan="6" align="center">주차 내역이 존재하지 않습니다.</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach var="park" items="${parkList}">
					<tr>
						<td>${park.parkno}</td>
						<td>${park.carno}</td>

						<!-- 주차등급 -->
						<td><c:choose>
								<c:when test="${park.grade == 'M'}">월회원</c:when>
								<c:when test="${park.grade == 'Y'}">연회원</c:when>
								<c:otherwise>${park.grade}</c:otherwise>
							</c:choose></td>

						<!-- 상태 -->
						<td><c:choose>
								<c:when test="${park.tstat == '1'}">입고</c:when>
								<c:when test="${park.tstat == '0'}">출고</c:when>
								<c:otherwise>${park.tstat}</c:otherwise>
							</c:choose></td>

						<!-- 입고일시 -->
						<td><fmt:formatDate value="${park.indate}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>

						<!-- 출고일시 -->
						<td><c:if test="${not empty park.outdate}">
								<fmt:formatDate value="${park.outdate}"
									pattern="yyyy-MM-dd HH:mm:ss" />
							</c:if></td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</tbody>
</table>