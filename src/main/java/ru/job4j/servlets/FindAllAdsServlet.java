package ru.job4j.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.auto.Car;
import ru.job4j.store.AdRepository;

public class FindAllAdsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Collection<Car> allAds = AdRepository.instOf().showAllAds();
        final Gson gson = new GsonBuilder().create();
        String adsToGson = gson.toJson(allAds);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("json");
        resp.getWriter().write(adsToGson);
    }
}
