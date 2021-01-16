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


            </c:choose>
        </c:forEach>
        <hr>

        <button type="submit"
                onclick="${(resume.fullName == null || resume.fullName.trim().length() == 0) ?  false : true}">Сохранить
        </button>
        <button type="reset">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>