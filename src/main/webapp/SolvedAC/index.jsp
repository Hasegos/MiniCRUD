<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!doctype html>
<html lang="ko">
<head>
    <title>Solved Ac</title>
    <style>
        * {
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
        <h2>Solved AC</h2>
        <form action="member" method="post">
            이름 : <input type="text" placeholder="백준 아이디를 입력해주세요" name="name">
            <input type="submit" value="제출">
        </form>
        <a href="../index.jsp">연습 페이지 모음 이동</a>
    </div>
</body>
</html>
