package ru.job4j.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.store.AdRepository;
import ru.job4j.auto.Car;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class PhotoAdsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Collection<Car> curAds = AdRepository.instOf().showAllAdsWithPhoto();
        final Gson gson = new GsonBuilder().create();
        String adsToGson = gson.toJson(curAds);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("json");
        resp.getWriter().write(adsToGson);
    }
}
