<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%-- HomePage, has two images, one to register a new user and one to display all users, and a menu bar.  --%>

<!DOCTYPE html>
<html>
<head>
    <title>HomePage</title>
</head>
<body class="bg-blue-300">
<script src="https://cdn.tailwindcss.com"></script>  <!-- TailwindCss framework -->

<%@ include file="menubar.jsp" %>

<h1 class="text-xl text-center font-semibold">Welcome to my web-app!</h1>
    <div>
        <div   class="flex justify-center items-center h-screen">
                    <a class="shadow-lg hover:shadow-2xl rounded-full" href="/registerUser">
                        <img alt="Register new user" src="images/registerUser.png"></a>

                    <a  class="shadow-lg hover:shadow-2xl rounded-full mx-20 "  href="/displayUsers">
                        <img alt="Display Users" src="images/displayUser.png"></a>
        </div>
    </div>
</body>
</html>