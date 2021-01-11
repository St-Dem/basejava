<%@ page import="com.urise.webapp.model.ContactsType" %>
<%@ page import="com.urise.webapp.model.OrganizationsSectionType" %>
<%@ page import="com.urise.webapp.model.SectionType" %>
<%@ page import="com.urise.webapp.model.AbstractSection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css.style.css">
    <jsp:useBean id="resume" type="com.urise.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
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
        </dl>
        <h3>Контакты:</h3>
        <c:forEach var="type" items="<%=ContactsType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd><label>
                    <input type="text" name="${type.name()}" size=30 value="${resume.getContacts(type)}">
                </label></dd>
            </dl>
        </c:forEach>
        <h3>Секции:</h3>
        <c:forEach var="sectionEntry" items="${resume.sections}">
            <jsp:useBean id="sectionEntry"
                         type="java.util.Map.Entry<com.urise.webapp.model.SectionType, com.urise.webapp.model.AbstractSection>"/>
            <c:set var="type" value="${sectionEntry.key}"/>
            <c:set var="section" value="${resume.getSection(type)}"/>
            <jsp:useBean id="section" type="com.urise.webapp.model.AbstractSection"/>
            <c:choose>
                <c:when test="${type == 'EXPERIENCE' || type == 'EDUCATION'}">
                    <dl>
                        <dt>${type.title}</dt>
                        <dd><label>
                            <c:forEach var="org" items="<%=((OrganizationsSectionType) section).getOrganizations()%>"
                                       varStatus="counter">
                            <input type="text" name="${type.name()}" size=200 value="${orga}">
                            </c:forEach>
                        </label></dd>
                    </dl>
                    <%=sectionEntry.getKey() + " : " +  sectionEntry.getValue()%><br/>
                </c:when>
                <c:otherwise>
                    <dl>
                        <dt>${type.title}</dt>
                        <dd><label>
                            <input type="text" name="${type.name()}" size=300 value="${section}">
                        </label></dd>
                    </dl>
                </c:otherwise>

            </c:choose>
        </c:forEach>
        <hr>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>