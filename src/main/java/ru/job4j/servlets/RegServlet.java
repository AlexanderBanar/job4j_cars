package ru.job4j.servlets;

import ru.job4j.auto.User;
import ru.job4j.auto.AdRepository;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        User user = AdRepository.instOf().getUserByName(req.getParameter("name"));
        if (user != null) {
            req.setAttribute("registration", user);
        } else {
            AdRepository.instOf().saveUser(
                    User.of(
                            req.getParameter("name"),
                            req.getParameter("password")
                    )
            );
        }
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}