package ro.uaic.info.controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ro.uaic.info.daos.RecordDAO;
import ro.uaic.info.entities.Record;
import ro.uaic.info.entities.RecordCategories;
import ro.uaic.info.services.RecordsService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/", name = "Lab2")
public class LabController extends HttpServlet {
    @Inject
    private RecordsService recordsService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        recordsService.handlePost(req, resp);
        req.getRequestDispatcher("/result.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/input.jsp").forward(req, resp);
    }
}
