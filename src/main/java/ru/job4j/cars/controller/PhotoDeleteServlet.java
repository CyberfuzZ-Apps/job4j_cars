package ru.job4j.cars.controller;

import ru.job4j.cars.Config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Класс PhotoDeleteServlet - сервлет удаления фото.
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public class PhotoDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = Config.value("path");
        String sellingId = req.getParameter("sellingId");
        File file = new File(path + sellingId);
        file.delete();
        resp.sendRedirect(req.getContextPath() + "/ad/editad.html?id=" + sellingId);
    }
}
