package com.urise.webapp.web;

import com.urise.webapp.Config;
import com.urise.webapp.model.*;
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
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        Resume r = storage.get(uuid);
        r.setFullName(fullName);
        for (ContactsType type : ContactsType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                r.addContacts(type, value);
            } else {
                r.getContacts().remove(type);
            }
        }

        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                r.addSection(type, addSection(type, value));
            }
        }
        storage.update(r);
        response.sendRedirect("resume");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume r;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "view":
            case "edit":
                r = storage.get(uuid);
                for (SectionType sectionType : SectionType.values()) {
                    AbstractSection section = r.getSection(sectionType);
                    r.addSection(sectionType, getSection(sectionType, section));
                }

                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", r);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
    }

    private AbstractSection addSection(SectionType type, String value) {
        return switch (type) {
            case PERSONAL, OBJECTIVE -> new TextSectionType(value);
            case ACHIEVEMENT, QUALIFICATIONS -> new ListSectionType(value.split(System.lineSeparator()));
            case EDUCATION, EXPERIENCE -> OrganizationsSectionType.EMPTY;
        };
    }

    private AbstractSection getSection(SectionType type, AbstractSection section) {
      return switch (type) {
            case PERSONAL, OBJECTIVE -> section == null ? section = TextSectionType.EMPTY : section;
            case ACHIEVEMENT, QUALIFICATIONS -> section == null ?  section = ListSectionType.EMPTY : section;
            case EDUCATION, EXPERIENCE -> section == null ? section = OrganizationsSectionType.EMPTY : section;
        };
    }
}
