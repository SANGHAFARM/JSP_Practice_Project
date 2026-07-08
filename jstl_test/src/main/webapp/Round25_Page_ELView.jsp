<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page isELIgnored="false" %>

<%!
    public class Inner {
        private String data;
        public void setData(String data) { this.data = data; }
        public String getData( ) { return this.data; }
    }
%>

<%
    Inner inner = new Inner( );
    inner.setData("김승현");
    request.setAttribute("inner", inner);

    request.setAttribute("su1", "100");
    request.setAttribute("su2", "30");

    request.setAttribute("bool", "false");
    request.setAttribute("data", "");
%>

<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EL 연산자</title>
</head>
<body>
    <h3>EL 연산 사용!</h3>
    inner.data : ${ requestScope.inner.data }<br/>
    inner["data"] : ${ requestScope.inner["data"] }<br/>
    su1 * (su2 + 3) : ${ requestScope.su1 * (requestScope.su2 + 3) }<br/>
    !bool : ${ !requestScope.bool }<br/>
    not bool : ${ not requestScope.bool }<br/>
    empty(data) : ${ empty(requestScope.data) }<br/>
    su1 / su2 : ${ requestScope.su1 / requestScope.su2 }<br/>
    su1 div su2 : ${ requestScope.su1 div requestScope.su2 }<br/>
    su1 != su2 : ${ requestScope.su1 != requestScope.su2 }<br/>
    su1 ne su2 : ${ requestScope.su1 ne requestScope.su2 }<br/>
    (su1 % 2 == 0) and (su2 mod 4 == 0) : ${ (requestScope.su1 % 2 == 0) and (requestScope.su2 mod 4 == 0) }<br/>
</body>
</html>