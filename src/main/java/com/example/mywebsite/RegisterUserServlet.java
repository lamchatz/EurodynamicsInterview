package com.example.mywebsite;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Servlet used to register a new User in the database.
 */
@WebServlet(name = "RegisterUserServlet", value = "/registerUser")
public class RegisterUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //show /registerUser.jsp
        getServletContext().getRequestDispatcher("/registerUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Get the data from the submitted form.
        String name = request.getParameter("Name");
        String surname = request.getParameter("Surname");
        String birthDate = request.getParameter("Birthdate");
        String gender = request.getParameter("Gender");
        String workAddress = request.getParameter("WorkAddress");
        String homeAddress = request.getParameter("HomeAddress");


        Date bd;

        try {
            bd = new SimpleDateFormat("dd-MM-yy").parse(birthDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        //Create new User
        User user = new User(name, surname, bd, gender);

        user.setWorkAddress(workAddress);
        user.setHomeAddress(homeAddress);

        UserDao userDao = new UserDao();
        //Register the User in the database. If userDao.add(user) returns false, there was an error
        //and it takes you back to the register form with all the data and an error message
        if (!userDao.add(user)) {
            request.setAttribute("error", "An error has occurred! Please try again.");
            request.setAttribute("Name", name);
            request.setAttribute("Surname", surname);
            request.setAttribute("Gender", gender);
            request.setAttribute("WorkAddress", workAddress);
            request.setAttribute("HomeAddress", homeAddress);
        } else {
            //if userDao.add(user) returns true the addition was successful and it takes you to the register
            //form with a success message
            request.setAttribute("success", "Congratulations! You are now a member of EuroDynamics");
        }

        //show /registerUser.jsp
        getServletContext().getRequestDispatcher("/registerUser.jsp").forward(request, response);

    }
}
