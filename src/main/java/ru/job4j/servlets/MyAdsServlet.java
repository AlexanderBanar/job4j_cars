package ru.job4j.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.store.AdRepository;
import ru.job4j.auto.Car;
import ru.job4j.auto.User;
import ru.job4j.store.CarRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

public class MyAdsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Collection<Car> myAds = AdRepository.instOf().showMyAds(user);
        final Gson gson = new GsonBuilder().create();
        String adsToGson = gson.toJson(myAds);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("json");
        resp.getWriter().write(adsToGson);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int idToClose = Integer.parseInt(req.getParameter("closedAd"));
        HttpSession sc = req.getSession();
        User user = (User) sc.getAttribute("user");
        CarRepository.instOf().closeCarAd(user, idToClose);
        req.getRequestDispatcher("/myads.jsp").forward(req, resp);
    }
}