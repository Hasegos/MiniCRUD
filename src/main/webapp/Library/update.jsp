<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%
    if (session.getAttribute("loginUser") == null) {
        response.sendRedirect(request.getContextPath() + "/Library/login");
        return;
    }
%>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>회원 가입 페이지</title>
</head>
<body>
<c:if test="${not empty errorMessage}">
    <p style="color:red;">${errorMessage}</p>
</c:if>

<h2>회원 수정하기</h2>
<form action="${pageContext.request.contextPath}/Library/updatePost" method="post">
    <!-- CSRF 토큰 -->
    <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">

    <p>이름 : <input type="text" name="name" value="${member.name}"></p>
    <p>이메일 : <input type="email" name="email" value="${member.email}"></p>
    <p>비밀번호 : <input type="password" name="password"></p>
    <input type="submit" value="회원 수정하기">
</form>
</body>
</html>