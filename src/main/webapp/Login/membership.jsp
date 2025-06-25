<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!doctype html>
<html lang="ko">
<head>
    <title>회원가입 페이지</title>
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
        <%
            String error = (String)request.getAttribute("error");
            if(error != null){
        %>
            <div> <%= error %> </div>
        <% } %>
        <h2>회원가입 페이지</h2>
        <form method="post" action="membership">
            <p>이름 : <input type="text" name="name"></p>
            <p>이메일 : <input type="text" name="email"></p>
            <p>비밀번호 : <input type="password" name="password"></p>
            <input type="submit" value="회원가입">
            <a href="login.jsp">로그인</a>
        </form>
    </div>
</body>
</html>