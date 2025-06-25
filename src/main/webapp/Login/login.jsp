<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!doctype html>
<html lang="ko">
<head>
    <title>로그인 페이지</title>
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
        .login-input {
            margin-top : 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <%
            String error = (String)request.getAttribute("error");
            if(error != null){
        %>
            <p><%= error %></p>
        <% } %>

        <h2>로그인 페이지</h2>
        <form method="post" action="login">
            <p>이메일 : <input type="text" name="email"></p>
            <p>비밀번호 : <input type="password" name="password"></p>
            <div class="login-input">
                <input type="submit" value="로그인">
                <a href="membership.jsp">회원가입</a> <br>
                <a href="../index.jsp">연습 페이지 모음 이동</a>
            </div>
        </form>
    </div>
</body>
</html>