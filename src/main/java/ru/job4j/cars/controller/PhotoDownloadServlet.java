package ru.job4j.cars.controller;

import ru.job4j.cars.Config;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * Класс PhotoDownloadServlet - сервлет загружает фото автомобиля на сайт.
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public class PhotoDownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String path = Config.value("path");
        String sellingId = req.getParameter("sellingId");
        File downloadFile = null;
        for (File file : Objects.requireNonNull(new File(path).listFiles())) {
            if (sellingId.equals(file.getName())) {
                downloadFile = file;
                break;
            }
        }
        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment; filename=\""
                + Objects.requireNonNull(downloadFile).getName() + "\"");
        try (FileInputStream stream = new FileInputStream(downloadFile)) {
            resp.getOutputStream().write(stream.readAllBytes());
        }
    }
}
