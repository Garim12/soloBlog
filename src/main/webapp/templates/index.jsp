<%@ page import="static org.eclipse.tags.shaded.org.apache.regexp.RETest.test" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html lang="ko-KR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>메인화면</title>
</head>
<body>
<h1>
    Hello, Wolrd!
</h1>
<%--계산 태그--%>
<%
    int sum = 0;
    String s = "";

    for (int i = 1; i <= 100; i++) {
        sum += i;
    }

    if (sum > 5000) {
        s = "sum이 5000보다 큽니다.";
    } else {
        s = "sum이 5000보다 작습니다.";
    }
%>

<div><%=s%></div>

<table>
        <%
            for (int i = 1; i <= 9; i++) {
                %>
                <tr>
                    <%
                        for (int j = 1; j <= 9; j++) {
                            %>
                            <td><%=i%> * <%=j%> = <%=i*j%></td>
                            <%
                        }
                    %>
                </tr>
                <%
            }
        %>
</table>

<sec:authorize access="isAuthenticated()">
<div>인증된 사용자에게만 보이는 내용</div>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_ADMIN')">
<div>관리자에게만 보이는 내용</div>
</sec:authorize>
</html>