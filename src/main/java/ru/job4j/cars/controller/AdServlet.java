package ru.job4j.cars.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Selling;
import ru.job4j.cars.model.User;
import ru.job4j.cars.store.CarStore;
import ru.job4j.cars.store.SellingStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;

/**
 * Класс AdServlet - сервлет добавления объявления в базу данных.
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public class AdServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Selling selling = saveAndGetSelling(req, 0);
        int sellingId = selling.getId();
        resp.sendRedirect("photo_upload.html?sellingId=" + sellingId);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        saveAndGetSelling(req, Integer.parseInt(req.getParameter("sellingId")));
        resp.sendRedirect("index.html");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int sellingId = Integer.parseInt(req.getParameter("sellingId"));
        Selling selling = SellingStore.instOf().findById(sellingId);
        String json = GSON.toJson(selling);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        OutputStream output = resp.getOutputStream();
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }

    private Selling saveAndGetSelling(HttpServletRequest req, int sellingId) {
        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        int year = Integer.parseInt(req.getParameter("year"));
        Car car = new Car.Builder()
                .buildBrand(brand)
                .buildModel(model)
                .buildYear(year)
                .buildTransmission(req.getParameter("transmission"))
                .buildBody(req.getParameter("body"))
                .buildEngine(req.getParameter("engine"))
                .buildMileage(Integer.parseInt(req.getParameter("mileage")))
                .buildEngineCapacity(Double.parseDouble(req.getParameter("enginecapacity")))
                .buildWheel(req.getParameter("wheel"))
                .buildColor(req.getParameter("color"))
                .build();
        CarStore.instOf().save(car);
        String header = brand + " " + model + ", " + year + " года.";
        Selling selling = Selling.of(
                header,
                req.getParameter("description"),
                Double.parseDouble(req.getParameter("price")),
                new Timestamp(System.currentTimeMillis()),
                false,
                (User) req.getSession().getAttribute("user"),
                car);
        if (sellingId != 0) {
            selling.setId(sellingId);
        }
        SellingStore.instOf().save(selling);
        return selling;
    }

}
