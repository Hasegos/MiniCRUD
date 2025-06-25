<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>에러페이지</title>
</head>
<body>
    <h2>에러 페이지</h2>
    <c:if test="${not empty errorMessage}">
        <p style="color:red">${errorMessage}</p>
    </c:if>
</body>
</html>