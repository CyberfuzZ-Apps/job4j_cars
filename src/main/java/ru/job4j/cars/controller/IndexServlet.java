package ru.job4j.cars.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.cars.model.Selling;
import ru.job4j.cars.model.User;
import ru.job4j.cars.store.SellingStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Класс IndexServlet - сервлет получения авторизированного пользователя
 * и списка объявлений.
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public class IndexServlet extends HttpServlet {

    private final static Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("index.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String json = GSON.toJson(user);
        jsonOut(resp, json);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Selling> sellingList;
        boolean onlyMy = Boolean.parseBoolean(req.getParameter("only_my_ads"));
        if (onlyMy) {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            sellingList = (List<Selling>) SellingStore.instOf().findSellingByUser(user);
        } else {
            sellingList = (List<Selling>) SellingStore.instOf().findAll();
        }
        String json = GSON.toJson(sellingList);
        jsonOut(resp, json);
    }

    private void jsonOut(HttpServletResponse resp, String json) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        OutputStream output = resp.getOutputStream();
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }
}
