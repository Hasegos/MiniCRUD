<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>

<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>로그인 페이지</title>
</head>
<body>
    <c:if test="${not empty errorMessage}">
        <p style="color:red;">${errorMessage}</p>
    </c:if>

    <h2>로그인 페이지</h2>

    <form action="${pageContext.request.contextPath}/Library/loginPost" method="post">
        <!-- CSRF 토큰 -->
        <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}"/>

        <p>이메일 : <input type="email" name="email" placeholder="이메일을 적어주세요"></p>
        <p>비밀번호 : <input type="password" name="password" placeholder="비밀번호를 적어주세요"></p>
        <input type="submit" value="로그인">
        <a href="${pageContext.request.contextPath}/Library/member">회원 가입</a>
    </form>

</body>
</html>