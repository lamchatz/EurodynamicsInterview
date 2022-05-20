<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%-- User page. It shows the selected user's info in a table. --%>

<head>
    <script src="https://cdn.tailwindcss.com"></script>  <!-- TailwindCss framework -->

    <title>User Page</title>
</head>
<body class="bg-blue-300">
<%@ include file="menubar.jsp" %>

<div>
    <div class="bg-white mx-auto text-center w-1/2 py-5 shadow-lg rounded-3xl my-20 max-w-2xl">
        <%-- A form is used to submit a post request and use the name and surname as parameters --%>

        <form action="/user" method="post">
            <table class="mx-auto w-full text-xl">
                <tr>
                    <td class="text-left px-7">Name</td>
                    <td name="name" class="text-left px-5">${user.name}</td>
                </tr>
                <tr>
                    <td class="text-left px-7">Surname</td>
                    <td class="text-left px-5">${user.surname}</td>
                </tr>
                <tr>
                    <td class="text-left px-7">Birthdate</td>
                    <td class="text-left px-5">${user.showDate()}</td> <%-- showDate() is used to eliminate the timestamp --%>

                </tr>
                <tr>
                    <td class="text-left px-7">Gender</td>
                    <td class="text-left px-5">${user.gender}</td>
                </tr>
                <tr>
                    <td class="text-left px-7">Work Address</td>
                    <td class="text-left px-5">${user.workAddress}</td>
                </tr>
                <tr>
                    <td class="text-left px-7">Home Address</td>
                    <td class="text-left px-5">${user.homeAddress}</td>
                </tr>
            </table>

            <%-- The delete button. When pressed it asks for confirmation from the user. If the user
             confirms it,the form is submitted.
             --%>

            <button id="deleteButton " onclick="const result=confirm('Are you sure you want to delete this user?')
                        if (!result){
                          return false;
                        }

                         this.form.submit();"
                    class="h-12 px-5 m-4 text-white bg-red-700 rounded-lg focus:shadow-outline hover:bg-red-800">Delete
                User
            </button>
            <%-- Hidden input fields. Used to pass the name and surname as parameters --%>

            <input type="hidden" name="name" value="${user.name}">
            <input type="hidden" name="surname" value="${user.surname}">

        </form>
    </div>
</div>

</body>
</html>
