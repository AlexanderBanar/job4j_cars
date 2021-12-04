package ru.job4j.servlets;

import ru.job4j.auto.Car;
import ru.job4j.auto.Photo;
import ru.job4j.auto.User;
import ru.job4j.store.CarRepository;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

public class NewAdServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Photo photo = Photo.of("empty path");
        photo.setAvailable(false);

        System.out.println(req.getParameter("year"));
        System.out.println(req.getParameter("brand"));
        System.out.println(req.getParameter("model"));
        System.out.println(req.getParameter("transmission"));
        System.out.println(req.getParameter("body"));
        System.out.println(req.getParameter("engine"));
        System.out.println(req.getParameter("price"));



        Car car = Car.of(
                req.getParameter("brand"),
                req.getParameter("model"),
                req.getParameter("transmission"),
                req.getParameter("body"),
                req.getParameter("engine"),
                Integer.parseInt(req.getParameter("year")),
                Integer.parseInt(req.getParameter("price")),
                user,
                photo
        );
        CarRepository.instOf().saveCar(car);
        resp.sendRedirect(req.getContextPath() + "/ads.jsp");
    }
}
