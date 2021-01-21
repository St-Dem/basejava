package com.urise.webapp.web;

import com.urise.webapp.Config;
import com.urise.webapp.model.*;
import com.urise.webapp.storage.Storage;
import com.urise.webapp.util.DateUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ResumeServlet extends HttpServlet {
    private Storage storage;
    boolean isRemove = false;

    public void init(ServletConfig config) throws ServletException {
        storage = Config.get().getStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        if (fullName == null || fullName.trim().length() == 0) {
            request.setAttribute("resume", Resume.EMPTY);
            request.getRequestDispatcher("/WEB-INF/jsp/edit.jsp").forward(request, response);
            return;
        }
        if (uuid == null || uuid.length() == 0) {
            storage.save(resumePost(request, fullName, new Resume(fullName)));
        } else {
            Resume r = storage.get(uuid);
            storage.update(resumePost(request, fullName, r));
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
                r = Resume.EMPTY;
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
            if (isNotEmpty(value)) {
                r.addContacts(type, value);
            } else {
                r.getContacts().remove(type);
            }
        }

        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            String[] parameterValues = request.getParameterValues(type.name());
            if (!isNotEmpty(value) && parameterValues.length < 2) {
                r.getSections().remove(type);
            } else {
                addSection(request, type, value, parameterValues, r);
            }
        }
        return r;
    }

    private void addSection(HttpServletRequest request, SectionType type, String value, String[] parameterValues, Resume r) {
        switch (type) {
            case PERSONAL, OBJECTIVE -> r.addSection(type, new TextSectionType(value.trim()));
            case ACHIEVEMENT, QUALIFICATIONS -> r.addSection((type), new ListSectionType(Arrays.stream
                    (value.split(System.lineSeparator())).filter(s -> s.trim().length() != 0).collect(Collectors.toList())));
            case EDUCATION, EXPERIENCE -> {
                List<Organization> arrayList = new ArrayList<>();
                String[] urls = request.getParameterValues(type.name() + "url");

                for (int i = 0; i < parameterValues.length; i++) {
                    String name = parameterValues[i];
                    if (isNotEmpty(name)) {
                        List<Organization.PositionInTime> positionInTimes = new ArrayList<>();
                        String[] datesStart = request.getParameterValues(type.name() + i + "dateStart");
                        String[] datesEnd = request.getParameterValues(type.name() + i + "dateEnd");
                        String[] positions = request.getParameterValues(type.name() + i + "position");
                        String[] descriptions = request.getParameterValues(type.name() + i + "description");
                        for (int j = 0; j < positions.length; j++) {
                            if (isNotEmpty(positions[j])) {
                                LocalDate dateEnd = LocalDate.parse(datesEnd[j]);
                                positionInTimes.add(new Organization.PositionInTime(LocalDate.parse(datesStart[j]),
                                        (dateEnd.equals(LocalDate.now()) ? DateUtil.NOW : dateEnd), positions[j], descriptions[j]));
                            }
                        }
                        arrayList.add(new Organization(name, urls[i], positionInTimes));
                    }
                }
                r.addSection(type, new OrganizationsSectionType(arrayList));
            }
        }
    }

    private AbstractSection getSection(SectionType type, AbstractSection section) {
        return switch (type) {
            case PERSONAL, OBJECTIVE -> section == null ? section = TextSectionType.EMPTY : section;
            case ACHIEVEMENT, QUALIFICATIONS -> section == null ? section = ListSectionType.EMPTY : section;
            case EDUCATION, EXPERIENCE -> section == null ? section = OrganizationsSectionType.EMPTY : addEmpty(section);
        };
    }

    private AbstractSection addEmpty(AbstractSection section) {
        List<Organization> orgFirstEmpty = new ArrayList<>();
        orgFirstEmpty.add(Organization.EMPTY);
        for (Organization org : ((OrganizationsSectionType) section).getOrganizations()) {
            List<Organization.PositionInTime> positionFirstEmpty = new ArrayList<>();
            positionFirstEmpty.add(Organization.PositionInTime.EMPTY);
            positionFirstEmpty.addAll(org.getPositionInTime());
            orgFirstEmpty.add(new Organization(org.getLink(), positionFirstEmpty));
        }
        return new OrganizationsSectionType(orgFirstEmpty);
    }


    public boolean isNotEmpty(String str) {
        return !(str == null || str.trim().isEmpty());
    }
}
