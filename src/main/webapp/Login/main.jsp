<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%
    String userName = (String) session.getAttribute("userName");
    String userEmail = (String) session.getAttribute("userEmail");

    if(userName == null || userEmail == null){
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!doctype html>
<html lang="ko">
<head>
    <title>회원 정보 페이지</title>
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
        p {
            margin-top:5px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>간단한 회원 정보</h1>
        <p>이름 : <%= userName %></P>
        <p>이메일 : <strong><%= userEmail %></strong></p>
    </div>
</body>
</html>