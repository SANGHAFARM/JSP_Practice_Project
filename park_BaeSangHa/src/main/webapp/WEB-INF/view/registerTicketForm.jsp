<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주차 정기권 등록</title>
<script>
	// 시작일 자동 세팅
	window.onload = function() {
		var today = new Date();
		var year = today.getFullYear();
		var month = ('0' + (today.getMonth() + 1)).slice(-2);
		var day = ('0' + today.getDate()).slice(-2);
		document.getElementById("startdate").value = year + '-' + month + '-'
				+ day;
	};

	// 폼 유효성 검사
	function submitForm() {
		var f = document.frm;

		var carno = f.carno.value.trim();
		var phone = f.phone.value.trim();
		var grade = f.grade.value.trim().toUpperCase();

		// 빈 값 검사
		if (carno === "" || phone === "" || grade === "") {
			alert("데이터가 입력되지 않았습니다");
			return;
		}

		// 주차등급 Y/M 검사
		if (grade !== "Y" && grade !== "M") {
			alert("주차등급은 'M' 또는 'Y'만 입력 가능합니다.");
			return;
		}

		// 대문자로 자동 변환
		f.grade.value = grade;

		// 컨트롤러로 폼 전송
		f.submit();
	}
</script>
</head>
<body>

	<h2 align="center">주차 정기권 등록</h2>
	<form name="frm" action="registerTicket.do" method="post"
		align="center">
		<table border="1" cellpadding="5" cellspacing="0" align="center">
			<tr>
				<th>정기권 번호</th>
				<td><input type="text" name="tno" value="${nextTno}" readonly
					style="background-color: #eee;"></td>
			</tr>
			<tr>
				<th>차량번호</th>
				<td><input type="text" name="carno"></td>
			</tr>
			<tr>
				<th>차주전화</th>
				<td><input type="text" name="phone"></td>
			</tr>
			<tr>
				<th>주차등급(M/Y)</th>
				<td><input type="text" name="grade" placeholder="M 또는 Y"></td>
			</tr>
			<tr>
				<th>시작일</th>
				<td><input type="text" name="startdate" id="startdate" readonly
					style="background-color: #eee;"></td>
			</tr>
		</table>

		<br> <input type="button" value="등록" onclick="submitForm()">
	</form>

</body>
</html>