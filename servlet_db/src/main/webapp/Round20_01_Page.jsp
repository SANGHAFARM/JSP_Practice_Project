<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.*, java.text.*"%>

<%!
	private String title;
	private String time;

	public void init() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		time = format.format(new Date());
	}
%>

<%
	title = "JSP Hello!";
	int start_num = 1;
	int end_num = 5;
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>INPUT BOX 5개 생성</h2>
	<% for (int i = start_num; i <= end_num; ++i) { %>
	<%=i%> : <input type='text' name='box_<%=i%>'/><br/>
	<% } %>
</body>
</html>