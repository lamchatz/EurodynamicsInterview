package com.example.mywebsite;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet that displays all the user info for a certain user.
 */
@WebServlet(name = "UserServlet", value = "/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Get the name and surname from the link  "http://localhost:8080/user?name=?&surName=?"
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        UserDao userDao = new UserDao();
        User user = null;
        //get the user info, if there is not a user with that name and surname an exception occurs and
        //the error.jsp is shown.
        try {
            user = userDao.getUser(name, surname);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("user", user);

        getServletContext().getRequestDispatcher("/user.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get the name and surname parameters and delete the user that matches them.
        // if something goes wrong, ie the user can't be found, the error.jsp is shown.
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        UserDao userDao = new UserDao();
        try {
            userDao.deleteUser(name,surname);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //after deletion go back to index.jsp
        getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
    }
}
