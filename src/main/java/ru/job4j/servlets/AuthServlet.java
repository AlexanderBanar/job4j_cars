package ru.job4j.servlets;

import ru.job4j.auto.User;
import ru.job4j.auto.AdRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        User userSearchedInDB = AdRepository.instOf().getUser(User.of(name, password));
        if (userSearchedInDB != null) {
            HttpSession sc = req.getSession();
            sc.setAttribute("user", userSearchedInDB);
            resp.sendRedirect(req.getContextPath() + "/ads.jsp");
        } else {
            req.setAttribute("error", "wrong name or password or user is not registered");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}
