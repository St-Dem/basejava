package com.urise.webapp.web;

import com.urise.webapp.Config;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResumeServlet extends HttpServlet {
    private Storage storage;

    public void init(ServletConfig config) throws ServletException {
        storage = Config.get().getStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Content-Type", "text/html; charset=UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        response.getWriter().write("<html><head>" +
                "<title>Список резюме.</title>" +
                "<style> table {\n" +
                "background: white; \n" +
                "color: white; \n" +
                "border-spacing: 1px; \n" +
                "}\n td, th {\n" +
                "background: green; \n" +
                "padding: 15px; \n" +
                "}</style></head>" +
                "<body><table><tr>" +
                "<th><h3>uuid</h3></th>" +
                "<th><h3>full name</h3></th>" +
                "</tr>\n");
        for (Resume resume : storage.getAllSorted()) {
            response.getWriter().write("<tr><td>" + resume.getUuid() + "</td>\n" +
                    "<td>" + resume.getFullName() + "</td>\n" +
                    "</tr>");
        }
        response.getWriter().write("</table></body></html>");
    }
}
