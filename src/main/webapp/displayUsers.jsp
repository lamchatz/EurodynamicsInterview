<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.mywebsite.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%-- DisplayUsers page. It shows a table in the center of the page with 2 columns, name and surname --%>

<%-- <% %> Scriplet tag -> java code --%>
<%-- <%= %> Expression tag -> print values --%>
<%-- <%! %> Declaration tag -> functions --%>
<%-- <%@ inlcude Directive tag -> include = --%>

<head>
    <title>Display Users</title>


    <script src="https://code.jquery.com/jquery-3.5.0.js"></script> <%-- Jquery framework --%>
    <script src="https://cdn.tailwindcss.com"></script>  <%-- Tailwindcss framework  --%>
    <script>
        <%-- Funtion that makes table rows clickable. Searches the document for tr elements with data-href and
             makes them clickable and able to open links in a new tab
        --%>

        $(document).ready(function () {
            $(document.body).on("click", "tr[data-href]", function () {
                window.open(this.dataset.href);
            });
        });
    </script>
</head>
<%-- When the cursor is on table row it changes to pointer --%>

<style>
    tr[data-href] {
        cursor: pointer;
    }
</style>
<body class="bg-blue-300">
<%@ include file="menubar.jsp" %>

<%-- for each user in userList store the name and surname for simplicity and then show them
     using expression tags and put them in the href value.
 --%>

<div>

    <div class="bg-white mx-auto my-20 w-2/5  shadow-lg sm:rounded-lg overflow-auto">
        <table class="mx-auto w-full text-xl shadow-md ">
            <tr class="bg-slate-800 text-gray-200 ">
                <th class="text-left px-7">Name</th>
                <th class="text-left px-5">Surname</th>
            </tr>
            <%
                ArrayList<User> userList = (ArrayList<User>) request.getAttribute("userList");
                for (User user : userList) {
                    String name = user.getName();
                    String surName = user.getSurname();
            %>

            <%-- /user?name=Lampros&surName=Chatziathanasiou --%>
            <tr data-href="user?name=<%=name%>&surname=<%=surName%>"
                class="bg-gray-200 hover:bg-gray-300 text-slate-800">
                <td class="px-7"><%=name%>
                </td>
                <td class="px-5"><%=surName %><% } %></td>
            </tr>
        </table>
    </div>

</div>

</body>
</html>
