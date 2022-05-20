<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%-- Register user page. It shows a form in the center of the page --%>

<head>
    <title>Register User</title>

    <%-- Used for the date picker --%>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <!-- No build steps, good for development, not production -->
    <script src="https://cdn.tailwindcss.com"></script>  <!-- TailwindCss framework -->
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script> <!-- JS query / JS query ui -->
    <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
    <script>
        <%-- The jquery-ui datepicker. Has a default date of 01-01-2000 --%>

        $(function () {
            $("#Birthdate").datepicker({
                    dateFormat: "dd-mm-yy",
                    defaultDate: new Date("01-01-2000")
                }
            );
        });
    </script>
</head>

<body class="bg-blue-300 ">

<%@ include file="menubar.jsp" %>

<%-- The register user form. Name, surname, gender and birthdate are required fields.
     When the button is pressed a post request is sent to "/registerUser".
     If the registration is successfull "${success}" is shown, if not "${error}" is shown,
     they get their values from RegisterUserServlet
     --%>

<div>
    <div class="bg-white mx-auto text-center w-1/2 py-5 shadow-lg rounded-3xl my-20 max-w-2xl">
        <h1 class="text-3xl font-semibold border-b pb-2 mx-6">Welcome to Eurodynamics</h1>
        <h3 class="bg-red-300 text-red-900 font-semibold text-xl w-80 rounded-lg my-2 mx-auto">${error}</h3>
        <h3 class="bg-green-300 text-green-900 font-semibold text-xl w-80 rounded-lg my-2 mx-auto">${success}</h3>
        <form method="post" action="/registerUser">
            <%--             grid colums 2  margin x=5 margin y=8 --%>
            <div class="mx-5 my-8 grid grid-cols-2 gap-4">
                <%-- Name --%>
                <label class="flex items-center text-xl" for="Name">Name
                    <span class="text-red-600 font-semibold tx-l">*</span></label>
                <input class="w-full p-1 border-2 placeholder-blue-800 border-blue-700 appearance-none
                 rounded-lg focus:outline-none focus:ring-2"
                       type="text" id="Name" name="Name" required value="${Name} "/>

                <%-- Surname --%>
                <label class="text-xl flex items-center" for="Surname">Surname
                    <span class="text-red-600 font-semibold tx-l">*</span></label>
                <input class="w-full p-1 border-2 placeholder-blue-800 border-blue-700 appearance-none
                rounded-lg focus:outline-none focus:ring-2"
                       type="text" id="Surname" name="Surname" value="${Surname}" required/>

                <%-- Birthdate --%>
                <label class="text-xl flex items-center" for="Birthdate">Birthdate
                    <span class="text-red-600 font-semibold tx-l">*</span></label>
                <input class="w-full p-1 border-2 placeholder-blue-800 border-blue-700 appearance-none
                rounded-lg focus:outline-none focus:ring-2"
                       type="text" id="Birthdate" name="Birthdate" required/>

                <%-- Gender --%>
                <label class="text-xl flex items-center " for="Gender">Gender
                    <span class="text-red-600 font-semibold tx-l">*</span></label>
                <select class="w-full p-1 border-2 placeholder-blue-800 border-blue-700 appearance-none
                rounded-lg focus:outline-none focus:ring-2"
                        id="Gender" name="Gender" value="${Gender}" required>
                    <option value="M">Male</option>
                    <option value="F">Female</option>
                </select>

                <%-- Work Address --%>
                <label class="flex items-center text-xl" for="WorkAddress">Work Address</label>
                <input class="w-full p-1 border-2 placeholder-blue-800 border-blue-700 appearance-none
                rounded-lg focus:outline-none focus:ring-2"
                       type="text" id="WorkAddress" name="WorkAddress" value="${WorkAddress}"/>

                <%-- Home Address --%>
                <label class="flex items-center text-xl" for="HomeAddress">HomeAddress</label>
                <input class="w-full p-1 border-2 placeholder-blue-800 border-blue-700 appearance-none
                rounded-lg focus:outline-none focus:ring-2"
                       type="text" id="HomeAddress" name="HomeAddress" value="${HomeAddress}"/>
            </div>

            <%-- Button --%>
            <button class="bg-blue-300 text-xl font-semibold px-4 py-1 rounded-lg hover:bg-blue-500 hover:text-white"
                    type="submit">Register User
            </button>
        </form>
    </div>
</div>


</body>

</html>
