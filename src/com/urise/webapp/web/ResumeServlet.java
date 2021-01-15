package com.urise.webapp.web;

import com.urise.webapp.Config;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.*;
import com.urise.webapp.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ResumeServlet extends HttpServlet {
    private Storage storage;

    public void init(ServletConfig config) throws ServletException {
        storage = Config.get().getStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        if (fullName == null || fullName.trim().length() == 0) {
            request.setAttribute("resume", Resume.EMPTY());
            request.setAttribute("nameNull", "10");
            request.getRequestDispatcher("/WEB-INF/jsp/edit.jsp").forward(request, response);
            return;
        }

        try {
            Resume r = storage.get(uuid);
            storage.update(resumePost(request, fullName, r));
        } catch (NotExistStorageException e) {
            storage.save(resumePost(request, fullName, new Resume(uuid, fullName)));
        }
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
                r = storage.get(uuid);
                break;
            case "edit":
                r = storage.get(uuid);
                for (SectionType sectionType : SectionType.values()) {
                    AbstractSection section = r.getSection(sectionType);
                    r.addSection(sectionType, getSection(sectionType, section));
                }
                break;
            case "add":
                r = Resume.EMPTY();
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", r);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
    }

    private Resume resumePost(HttpServletRequest request, String fullName, Resume r) {
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
            } else {
                r.getSections().remove(type);
            }
        }
        return r;
    }

    private AbstractSection addSection(SectionType type, String value) {
        return switch (type) {
            case PERSONAL, OBJECTIVE -> new TextSectionType(value.trim());
            case ACHIEVEMENT, QUALIFICATIONS -> new ListSectionType(Arrays.stream(value.split(System.lineSeparator()))
                    .filter(s -> s.trim().length() != 0).collect(Collectors.toList()));
            case EDUCATION, EXPERIENCE -> OrganizationsSectionType.EMPTY;
        };
    }

    private AbstractSection getSection(SectionType type, AbstractSection section) {
        return switch (type) {
            case PERSONAL, OBJECTIVE -> section == null ? section = TextSectionType.EMPTY : section;
            case ACHIEVEMENT, QUALIFICATIONS -> section == null ? section = ListSectionType.EMPTY : section;
            case EDUCATION, EXPERIENCE -> section == null ? section = OrganizationsSectionType.EMPTY : section;
        };
    }
}
