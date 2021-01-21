<%@ page import="com.urise.webapp.util.DateUtil" %>
<%@ page import="com.urise.webapp.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css.style.css">
    <jsp:useBean id="resume" type="com.urise.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
    <style>
        h4 {
            color: tomato;
        }
    </style>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>

<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl>
            <dt>Имя:</dt>
            <dd><label>
                <input type="text" name="fullName" size=50 value="${resume.fullName}">
            </label></dd>
            <c:choose>
                <c:when test="${resume.fullName == null || resume.fullName.trim().length() == 0}">
                    <h4 id="nameNull">Пожалуйста введите ваше имя!</h4>
                </c:when>
            </c:choose>
        </dl>
        <h3>Контакты:</h3>
        <c:forEach var="type" items="<%=ContactsType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd><input type="text" name="${type.name()}" size=30 value="${resume.getContacts(type)}"></dd>
            </dl>
        </c:forEach>
        <h3>Секции:</h3>
        <c:forEach var="sectionType" items="<%=SectionType.values()%>">
            <c:set var="section" value="${resume.getSection(sectionType)}"/>
            <jsp:useBean id="section"
                         type="com.urise.webapp.model.AbstractSection"/>
            <c:choose>
                <c:when test="${sectionType == 'PERSONAL' || sectionType == 'OBJECTIVE'}">
                    <dl>
                        <dt>${sectionType.title}</dt>
                        <dd>
                            <input type="text" name="${sectionType.name()}" size=150
                                   value="<%=section%>">
                        </dd>
                    </dl>
                </c:when>
                <c:when test="${sectionType == 'ACHIEVEMENT' || sectionType == 'QUALIFICATIONS'}">
                    <dl>
                        <dt>${sectionType.title}</dt>
                        <dd>
                            <textarea name="${sectionType.name()}" rows="8"
                                      cols="150"><%=String.join(System.lineSeparator(), ((ListSectionType) section).getItems())%></textarea>
                        </dd>
                    </dl>
                </c:when>
                <c:otherwise>
                    <dt>${sectionType.title}</dt>

                    <c:forEach var="organization" varStatus="count"
                               items="<%=((OrganizationsSectionType)section).getOrganizations()%>">
                        <jsp:useBean id="organization" type="com.urise.webapp.model.Organization"/>
                        <dl>
                            <dt>Название организации</dt>
                            <dd>
                                <input type="text" name="${sectionType.name()}" size=150
                                       value="${organization.link.name}">
                            </dd>
                            <dt>Сайт</dt>
                            <dd>
                                <input type="text" name="${sectionType.name()}url" size=150
                                       value="${organization.link.url}">
                            </dd>
                            <c:forEach var="position" items="${organization.positionInTime}">
                                <jsp:useBean id="position" type="com.urise.webapp.model.Organization.PositionInTime"/>
                                <dt>Дата начала</dt>
                                <dd>
                                    <input type="date" name="${sectionType.name()}${count.index}dateStart" size=150
                                           value="<%=DateUtil.toHtmlEdit(position.getDateStart())%>">
                                </dd>
                                <dt>Дата окончания</dt>
                                <dd>
                                    <input type="date" name="${sectionType.name()}${count.index}dateEnd" size=150
                                           value="<%=DateUtil.toHtmlEdit(position.getDateEnd())%>">
                                </dd>
                                <c:choose>
                                    <c:when test="${sectionType == 'EXPERIENCE'}">
                                        <dt>Должность</dt>
                                    </c:when>
                                    <c:otherwise>
                                        <dt>Что изучали</dt>
                                    </c:otherwise>
                                </c:choose>
                                <dd>
                                    <input type="text" name="${sectionType.name()}${count.index}position" size=150
                                           value="${position.position}">
                                </dd>
                                <c:choose>
                                    <c:when test="${sectionType == 'EXPERIENCE'}">
                                        <dd>
                                        <dt>Опишите ваши рабочие обязанности</dt>
                                        <input type="text" name="${sectionType.name()}${count.index}description"
                                               size=150
                                               value="${position.description}">
                                        </dd>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="text" name="${sectionType.name()}${count.index}description"
                                               style="display: none"
                                               value="${position.description}">
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </dl>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <hr>

        <button type="submit"
                onclick="${(resume.fullName == null || resume.fullName.trim().length() == 0) ?  false : true}">Сохранить
        </button>
        <button type="reset">Отменить</button>
        <button onclick="history.back()">Назад</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>