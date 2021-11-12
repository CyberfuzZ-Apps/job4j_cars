package ru.job4j.cars.controller;

import ru.job4j.cars.Config;
import ru.job4j.cars.store.HbmStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Класс DeleteServlet - сервлет удаления объявления.
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        HbmStore.instOf().deleteSelling(Integer.parseInt(id));
        String path = Config.value("path");
        File file = new File(path + id);
        file.delete();
        req.getRequestDispatcher("/index.do").forward(req, resp);
    }
}
