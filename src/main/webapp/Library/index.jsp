<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import="dto.library.Member" %>
<%
    if (session.getAttribute("loginUser") == null) {
        response.sendRedirect(request.getContextPath() + "/Library/login");
        return;
    }
    Member m = (Member)session.getAttribute("loginUser");
    String userEmail = (String)m.getEmail();
    String userName = (String)m.getName();
%>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>결과 페이지</title>
    <style>
        *{
            padding : 0;
            margin : 0;
            box-sizing : border-box;
        }
        body {
            display:flex;
            justify-content : center;
            min-height:100vh;
            padding-top : 100px;
        }
        .container {
            text-align : center;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2><%= userName %> 님! 환영합니다.</h2>
        <p>이메일 : <strong><%= userEmail %></strong></p>
        <a href="${pageContext.request.contextPath}/Library/update">회원 정보 수정하기</a>
        <a href="${pageContext.request.contextPath}/Library/logout">로그 아웃</a>
    </div>
</body>
</html>