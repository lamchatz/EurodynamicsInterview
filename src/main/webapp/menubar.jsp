<%-- Menubar that is include in all pages. The options are
 the Eurodynamics' homepage
 the project's homepage
 the register user page
 and the display users page
 --%>

<nav class="bg-white p-6 align-middle">
    <div class="mx-auto items-center justify-between ">
        <a href="https://www.eurodyn.com/">
            <span class="self-center text-xl font-semibold text-black">Eurodynamics</span>
        </a>
        <a href="index.jsp" class="pl-16 text-sm text-gray-600 hover:text-blue-600">
            <span>Home</span>
        </a>
        <a href="/registerUser" class="pl-16 text-sm text-gray-600 hover:text-blue-600">
            <span>Register User</span>
        </a>
        <a href="/displayUsers" class="pl-16 text-sm text-gray-600 hover:text-blue-600">
            <span>Display Users</span>
        </a>
    </div>
</nav>
