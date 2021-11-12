package ru.job4j.cars.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.cars.model.Selling;
import ru.job4j.cars.store.HbmStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * Класс DetailsServlet - сервлет получения деталей по объявлению.
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public class DetailsServlet extends HttpServlet {

    private final static Gson GSON = new GsonBuilder().create();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int sellingId = Integer.parseInt(req.getParameter("sellingId"));
        Selling selling = HbmStore.instOf().findSellingById(sellingId);
        String json = GSON.toJson(selling);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        OutputStream output = resp.getOutputStream();
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }
}
