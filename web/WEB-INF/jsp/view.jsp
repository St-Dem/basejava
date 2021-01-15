<%@ page import="com.urise.webapp.model.ListSectionType" %>
<%@ page import="com.urise.webapp.model.OrganizationsSectionType" %>
<%@ page import="com.urise.webapp.model.TextSectionType" %>
<%@ page import="com.urise.webapp.util.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="com.urise.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<com.urise.webapp.model.ContactsType, java.lang.String>"/>
            <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
        </c:forEach>
    </p>

    <c:forEach var="sectionEntry" items="${resume.sections}">
        <jsp:useBean id="sectionEntry"
                     type="java.util.Map.Entry<com.urise.webapp.model.SectionType, com.urise.webapp.model.AbstractSection>"/>
        <c:set var="sectionType" value="${sectionEntry.key}"/>
        <c:set var="section" value="${sectionEntry.value}"/>
        <jsp:useBean id="section"
                     type="com.urise.webapp.model.AbstractSection"/>
        <tr>
            <td><h3>
                    ${sectionType.title}
            </h3></td>
        </tr>
        <c:choose>
            <c:when test="${sectionType == 'PERSONAL' || sectionType == 'OBJECTIVE'}">

                </h3><br/>
                <%=((TextSectionType) section).getText()%><br/>
            </c:when>

            <c:when test="${sectionType == 'ACHIEVEMENT' || sectionType == 'QUALIFICATIONS'}">

                <ul>
                    <c:forEach var="list" items="<%=((ListSectionType) section).getItems()%>">
                        <jsp:useBean id="list" type="java.lang.String"/>
                        <li>${list}</li>
                    </c:forEach>
                </ul>
            </c:when>

            <c:otherwise>

                <c:forEach var="organization" items="<%=((OrganizationsSectionType) section).getOrganizations()%>">
                    <jsp:useBean id="organization" type="com.urise.webapp.model.Organization"/>
                    <table>
                    <tbody>
                    <tr>
                        <td colspan="2">
                            <c:choose>
                                <c:when test="${empty organization.link.url}">
                                    <h3>${organization.link.name}</h3>
                                </c:when>
                                <c:otherwise>
                                    <a href="${organization.link.url}"><h3>${organization.link.name}</h3></a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <c:forEach var="position" items="<%=organization.getPositionInTime()%>">
                        <jsp:useBean id="position" type="com.urise.webapp.model.Organization.PositionInTime"/>
                        <tr>
                            <td style="width : 15%; vertical-align: top">
                                <%=DateUtil.toHtml(position.getDateStart())%>
                                - <%=DateUtil.toHtml(position.getDateEnd())%>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${sectionType == 'EXPERIENCE'}">
                                        <b>${position.position}</b><br/>${position.description}
                                    </c:when>
                                    <c:otherwise>
                                        <b>${position.position}</b><br/>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </c:forEach>
                </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </c:forEach>

</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>