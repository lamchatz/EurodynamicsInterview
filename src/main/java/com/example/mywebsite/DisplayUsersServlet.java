package com.example.mywebsite;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Servlet used to Display Users' name and surname
 */
@WebServlet(name = "DisplayUsersServlet", value = "/displayUsers")
public class DisplayUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Create an ArrayList for User objects and retrieve any user in the database
        ArrayList<User> userList;
        UserDao userDao = new UserDao();
        try {
            userList = userDao.getUsersNames();
            request.setAttribute("userList", userList);
            //Show /displayUsers.jsp
            getServletContext().getRequestDispatcher("/displayUsers.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //doNothing
    }
}
