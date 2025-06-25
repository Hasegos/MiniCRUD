<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!doctype html>
<html lang="ko">
<head>
    <title>각종 연습 페이지</title>
    <style>
        * {
            padding: 0;
            margin : 0;
            box-sizing : border-box;
        }
        body {
            display:flex;
            min-height : 100vh;
            justify-content : center;
            padding-top : 100px;
        }
        ul {
            margin-top : 15px;
            margin-left : 30px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>각 종 연습 페이지 모음</h2>
        <ul>
            <li><a href="SolvedAC/index.jsp">Solved AC</a></li>
            <li><a href="Login/login.jsp">회원가입 페이지</a></li>
            <li><a href="${pageContext.request.contextPath}/Library/login">도서 회원가입시스템</a> </li>
        </ul>
    </div>
</body>
</html>
