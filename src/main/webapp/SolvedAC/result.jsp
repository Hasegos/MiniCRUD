<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import="java.util.*,dto.solvedAC.Member" %>

<!doctype html>
<html lang="ko">
<head>
    <title>Solved Ac</title>
</head>
<body>

    <%-- Solved AC 에서 받아온 API 찍기 --%>
    <%
        int result =(Integer) request.getAttribute("result");
        String name = (String) request.getAttribute("name");
        int solvedCount = (Integer) request.getAttribute("solvedCount");
        int Class = (Integer) request.getAttribute("Class");
        String joinedAt = (String) request.getAttribute("joinedAt");
        List<Member> getAllMembers = (List<Member>) request.getAttribute("getAllMembers");
    %>

    <% if(result > 0 ) { %>
        <h3> 이름 : <%= name %></h3>
        <h3> 문제 푼수 : <%= solvedCount %></h3>
        <h3> Class 단계 : <%= Class %></h3>
        <h3> 가입 날짜 : <%= joinedAt %></h3>
    <% } else { %>
        조회가 실패했습니다.
    <% } %>

    <button type="button" onclick="show()">전체 조회하기</button>
    <div id="memberList"></div>

    <script>
        const members = [
        <% for (Member member : getAllMembers) {  %>
                {
                    name : "<%= member.getName() %>",
                    SolvedCount: "<%= member.getSolvedCount() %>",
                    Class : "<%= member.getclass() %>",
                    JoinedAt : "<%= member.getJoinedAt() %>"
                },
            <% } %>
        ];
        function show(){
            const memberList = document.getElementById("memberList");
            memberList.innerHTML = "";

            if(members.length === 0){
                container.innerHTML = "<p>회원 정보가 없습니다</p>"
                return;
            }

            let html = "<ul>";
            members.forEach(member => {
                    html += `<li> 이름 : ${member.name}, 문제 푼수 : ${member.SolvedCount},
                    클래스 단계 : ${member.Class}, 가입 날짜 : ${member.JoinedAt}`;
                });
                html += "</ul>";
                memberList.innerHTML = html;
        }
    </script>
</body>
</html>
