package ru.job4j.cars.controller;

import ru.job4j.cars.model.Selling;
import ru.job4j.cars.store.SellingStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Класс UpdateServlet - сервлет меняет статус объявления с "Продается"
 * на "Продано".
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public class UpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int sellingId = Integer.parseInt(req.getParameter("sellingId"));
        Selling selling = SellingStore.instOf().findById(sellingId);
        selling.setSold(true);
        SellingStore.instOf().save(selling);
        req.getRequestDispatcher("index.do").forward(req, resp);
    }
}
