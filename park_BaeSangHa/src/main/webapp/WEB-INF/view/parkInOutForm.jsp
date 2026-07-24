<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script>
	// 알림 메시지 출력
	<c:if test="${not empty msg}">
	alert("${msg}");
	</c:if>

	// 주차입고 버튼 클릭
	function searchInbound() {
		var carno = document.frm.carno.value.trim();
		if (carno === "") {
			alert("차량번호를 입력하세요.");
			return;
		}
		document.frm.type.value = "in";
		document.frm.action = "${pageContext.request.contextPath}/parkInOut.do";
		document.frm.method = "get";
		document.frm.submit();
	}

	// 주차출고 버튼 클릭
	function searchOutbound() {
		var carno = document.frm.carno.value.trim();
		if (carno === "") {
			alert("차량번호를 입력하세요.");
			return;
		}
		document.frm.type.value = "out";
		document.frm.action = "${pageContext.request.contextPath}/parkInOut.do";
		document.frm.method = "get";
		document.frm.submit();
	}

	// 입고 확인 버튼 클릭
	function submitInbound() {
		if (confirm("[차량입고] 하시겠습니까?")) {
			document.inboundFrm.submit();
		}
	}

	// 출고 확인 버튼 클릭
	function submitOutbound() {
		if (confirm("[차량출고] 하시겠습니까?")) {
			document.outboundFrm.submit();
		}
	}
</script>

<h2 align="center">주차 차량 입고.출고 관리</h2>

<!-- 검색 폼 -->
<form name="frm" align="center">
	<input type="hidden" name="type" value="">
	<table border="1" cellpadding="5" cellspacing="0" align="center">
		<tr>
			<th>차량번호</th>
			<td><input type="text" name="carno" value="${param.carno}">
				<input type="button" value="주차입고" onclick="searchInbound()">
				<input type="button" value="주차출고" onclick="searchOutbound()">
			</td>
		</tr>
	</table>
</form>

<br>

<!-- 주차입고 조회 결과 -->
<c:if test="${mode == 'in' and not empty ticketInfo}">
	<form name="inboundFrm"
		action="${pageContext.request.contextPath}/parkInOut.do" method="post"
		align="center">
		<input type="hidden" name="action" value="inboundSubmit"> <input
			type="hidden" name="carno" value="${ticketInfo.carno}"> <input
			type="hidden" name="grade" value="${ticketInfo.grade}">

		<table border="1" cellpadding="5" cellspacing="0" align="center">
			<tr>
				<th>정기권 번호</th>
				<td>${ticketInfo.tno}</td>
			</tr>
			<tr>
				<th>차량번호</th>
				<td>${ticketInfo.carno}</td>
			</tr>
			<tr>
				<th>차주전화</th>
				<td>${ticketInfo.phone}</td>
			</tr>
			<tr>
				<th>등급</th>
				<td>${ticketInfo.grade}</td>
			</tr>
			<tr>
				<th>정기권 시작일</th>
				<td><fmt:formatDate value="${ticketInfo.startdate}"
						pattern="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<th>정기권 종료일</th>
				<td><fmt:formatDate value="${ticketInfo.enddate}"
						pattern="yyyy-MM-dd" /></td>
			</tr>
		</table>

		<br> <input type="button" value="입고 확인" onclick="submitInbound()">
	</form>
</c:if>

<!-- 주차출고 조회 결과 -->
<c:if test="${mode == 'out' and not empty parkInfo}">
	<form name="outboundFrm"
		action="${pageContext.request.contextPath}/parkInOut.do" method="post"
		align="center">
		<input type="hidden" name="action" value="outboundSubmit"> <input
			type="hidden" name="carno" value="${parkInfo.carno}">

		<table border="1" cellpadding="5" cellspacing="0" align="center">
			<tr>
				<th>주차번호</th>
				<td>${parkInfo.parkno}</td>
			</tr>
			<tr>
				<th>차량번호</th>
				<td>${parkInfo.carno}</td>
			</tr>
			<tr>
				<th>등급</th>
				<td>${parkInfo.grade}</td>
			</tr>
			<tr>
				<th>상태</th>
				<td>${parkInfo.tstat}</td>
			</tr>
			<tr>
				<th>입고일시</th>
				<td><fmt:formatDate value="${parkInfo.indate}"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
			</tr>
			<tr>
				<th>출고일시</th>
				<td><c:choose>
						<c:when test="${not empty parkInfo.outdate}">
							<fmt:formatDate value="${parkInfo.outdate}"
								pattern="yyyy-MM-dd HH:mm:ss" />
						</c:when>
					</c:choose></td>
			</tr>
		</table>

		<br> <input type="button" value="출고 확인"
			onclick="submitOutbound()">
	</form>
</c:if>