package com.urise.webapp.model;

import com.urise.webapp.util.DateUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ResumeTestData {
    private final Resume resume;

    public ResumeTestData(Resume resume) {
        this.resume = resume;
    }


    public static void main(String[] args) {
        Resume resume = createResume("uuid5", "Grigory Kislin");
        printContacts(resume);
        printSection(resume);

    }

    public static Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
 /*       setContacts(resume);
        setSections(resume);*/
        return resume;
    }

    public static void setContacts(Resume resume) {
        resume.addContacts(ContactsType.PHONE, "+7(921) 855-0482)");
        resume.addContacts(ContactsType.SKYPE, "grigory.kislin");
        resume.addContacts(ContactsType.EMAIL, "gkislin@yandex.ru");
        resume.addContacts(ContactsType.LINKEDID, "https://www.linkedin.com/in/gkislin");
        resume.addContacts(ContactsType.GITHUB, "https://github.com/gkislin");
        resume.addContacts(ContactsType.STACKOVERFLOW,
                "https://stackoverflow.com/users/548473/grigory-kislin");
        resume.addContacts(ContactsType.HOMEPAGE, "http://gkislin.ru/");
    }

    public static void setSections(Resume resume) {
        resume.addSection(SectionType.OBJECTIVE, new TextSectionType("Ведущий стажировок " +
                "и корпоративного обучения по Java Web и Enterprise технологиям"));
        resume.addSection(SectionType.PERSONAL, new TextSectionType("Аналитический склад ума, " +
                "сильная логика, креативность, инициативность. Пурист кода и архитектуры."));

        List<String> achievement = new ArrayList<>();
        achievement.add("С 2013 года: разработка проектов " +
                "\"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven." +
                " Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). " +
                "Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение " +
                "проектов. Более 1000 выпускников.");
        achievement.add("Реализация двухфакторной аутентификации для онлайн платформы управления" +
                " проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achievement.add("Налаживание процесса разработки и непрерывной интеграции ERP системы " +
                "River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления " +
                "окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и " +
                "авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        achievement.add("Реализация c нуля Rich Internet Application приложения на стеке " +
                "технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock " +
                "для алгоритмического трейдинга.");
        achievement.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия " +
                "слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). " +
                "Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. " +
                "Реализация онлайн клиента для администрирования и мониторинга системы по JMX " +
                "(Jython/ Django).");
        achievement.add("Реализация протоколов по приему платежей всех основных платежных системы России " +
                "(Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        resume.addSection(SectionType.ACHIEVEMENT, new ListSectionType(achievement));

        List<String> qualifications = new ArrayList<>();
        qualifications.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualifications.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualifications.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        qualifications.add("MySQL, SQLite, MS SQL, HSQLDB");
        qualifications.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,");
        qualifications.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,");
        qualifications.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, " +
                "MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), " +
                "Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, " +
                "JUnit, Selenium (htmlelements).");
        qualifications.add("Python: Django.");
        qualifications.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        qualifications.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        qualifications.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, " +
                "JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, " +
                "HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        qualifications.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix,");
        qualifications.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, " +
                "JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer.");
        qualifications.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов " +
                "проектрирования, архитектурных шаблонов, UML, функционального программирования");
        qualifications.add("Родной русский, английский \"upper intermediate\"");
        resume.addSection(SectionType.QUALIFICATIONS, new ListSectionType(qualifications));

        List<Organization> workOrganizations = new ArrayList<>();
        Organization javaOnlineProjects = new Organization("Java Online Projects",
                "https://javaops.ru/", new ArrayList<>(Collections.singletonList(
                new Organization.PositionInTime(LocalDate.of(2013, 10, 1),
                        DateUtil.NOW, "Автор проекта.", "Создание, " +
                        "организация и проведение Java онлайн проектов и стажировок."))));
        workOrganizations.add(javaOnlineProjects);

        Organization wrike = new Organization("Wrike", "https://www.wrike.com/",
                new ArrayList<>(Collections.singletonList(new Organization.PositionInTime(
                        LocalDate.of(2014, 10, 1),
                        LocalDate.of(2016, 1, 1),
                        "Старший разработчик (backend)", "Проектирование и разработка " +
                        "онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, " +
                        "MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, " +
                        "авторизация по OAuth1, OAuth2, JWT SSO."))));
        workOrganizations.add(wrike);


        Organization rITCenter = new Organization("RIT Center", null, new ArrayList<>
                (Collections.singletonList(new Organization.PositionInTime(
                        LocalDate.of(2012, 4, 1),
                        LocalDate.of(2014, 10, 1),
                        "Java архитектор", "Организация процесса разработки системы ERP " +
                        "для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), " +
                        "миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), " +
                        "AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных " +
                        "сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт " +
                        "в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера " +
                        "документов MS Office. Maven + plugin development, Ant, Apache Commons, " +
                        "Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting," +
                        " Unix shell remote scripting via ssh tunnels, PL/Python"))));
        workOrganizations.add(rITCenter);

        Organization luxoftDeutscheBank = new Organization("Luxoft (Deutsche Bank)",
                "https://career.luxoft.com/locations/russia/",
                new ArrayList<>(Collections.singletonList(
                        new Organization.PositionInTime(LocalDate.of(2010, 12, 1),
                                LocalDate.of(2012, 4, 1), "Ведущий программист",
                                "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, " +
                                        "SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. " +
                                        "Реализация RIA-приложения для администрирования, мониторинга и анализа " +
                                        "результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, " +
                                        "GWT, ExtGWT (GXT), Highstock, Commet, HTML5."))));
        workOrganizations.add(luxoftDeutscheBank);

        Organization yota = new Organization("Yota", "https://www.yota.ru/",
                new ArrayList<>(Collections.singletonList(new Organization.PositionInTime
                        (LocalDate.of(2008, 6, 1),
                                LocalDate.of(2010, 12, 1),
                                "Ведущий специалист", "Дизайн и имплементация Java EE фреймворка " +
                                "для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, " +
                                "Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и " +
                                "мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)"))));
        workOrganizations.add(yota);

        Organization enkata = new Organization("Enkata",
                "https://www.pega.com/products/robotic-process-automation",
                new ArrayList<>(Collections.singletonList(new Organization.PositionInTime(LocalDate.of(2007, 3, 1),
                        LocalDate.of(2008, 6, 1), "Разработчик ПО",
                        "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, " +
                                "Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining)."))));
        workOrganizations.add(enkata);

        Organization siemensAG = new Organization("Siemens AG", "https://new.siemens.com/ru/ru.html",
                new ArrayList<>(Collections.singletonList(new Organization.PositionInTime(LocalDate.of(2005, 1, 1),
                        LocalDate.of(2007, 2, 1), "Разработчик ПО",
                        "Разработка информационной модели, проектирование интерфейсов, реализация и " +
                                "отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix)."))));
        workOrganizations.add(siemensAG);

        Organization alcatel = new Organization("Alcatel", "http://www.alcatel.ru/",
                new ArrayList<>(Collections.singletonList(new Organization.PositionInTime
                        (LocalDate.of(1997, 9, 1),
                                LocalDate.of(2005, 1, 1),
                                "Инженер по аппаратному и программному тестированию",
                                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel " +
                                        "1000 S12 (CHILL, ASM)."))));
        workOrganizations.add(alcatel);
        resume.addSection(SectionType.EXPERIENCE, new OrganizationsSectionType(workOrganizations));

        List<Organization> educationOrganization = new ArrayList<>();
        Organization coursera = new Organization("Coursera", "https://www.coursera.org/learn/progfun1",
                new ArrayList<>(Collections.singletonList(new Organization.PositionInTime(LocalDate.of(2013, 3, 1),
                        LocalDate.of(2013, 5, 1),
                        "\t\"Functional Programming Principles in Scala\" by Martin Odersky"))));
        educationOrganization.add(coursera);

        Organization luxoft = new Organization("Luxoft",
                "https://www.luxoft-training.ru/kurs/" +
                        "obektno-orientirovannyy_analiz_i_proektirovanie_na_uml.html",
                new ArrayList<>(Collections.singletonList(new Organization.PositionInTime
                        (LocalDate.of(2011, 3, 1),
                                LocalDate.of(2011, 4, 1),
                                "Курс \"Объектно-ориентированный анализ ИС. " +
                                        "Концептуальное моделирование на UML.\""))));
        educationOrganization.add(luxoft);

        Organization siemens = new Organization("Siemens AG", "https://new.siemens.com/ru/ru.html",
                new ArrayList<>(Collections.singletonList(new Organization.PositionInTime
                        (LocalDate.of(2005, 1, 1),
                                LocalDate.of(2005, 4, 1),
                                "3 месяца обучения мобильным IN сетям (Берлин)"))));
        educationOrganization.add(siemens);

        Organization alcatelEdu = new Organization("Alcatel", "http://www.alcatel.ru/",
                new ArrayList<>(Collections.singletonList(new Organization.PositionInTime
                        (LocalDate.of(1997, 9, 1),
                                LocalDate.of(1998, 3, 1),
                                "6 месяцев обучения цифровым телефонным сетям (Москва)"))));
        educationOrganization.add(alcatelEdu);

        Organization spb = new Organization("Санкт-Петербургский национальный исследовательский " +
                "университет информационных технологий, механики и оптики", "https://itmo.ru/ru/",
                new ArrayList<>(Arrays.asList(new Organization.PositionInTime
                                (LocalDate.of(1993, 9, 1),
                                        LocalDate.of(1996, 7, 1), "Аспирантура (программист С, С++)"),
                        new Organization.PositionInTime(LocalDate.of(1987, 9, 1),
                                LocalDate.of(1993, 7, 1), "Инженер (программист Fortran, C)"))));
        educationOrganization.add(spb);


        Organization zft = new Organization("Заочная физико-техническая школа при МФТИ",
                "http://www.school.mipt.ru/", new ArrayList<>(Collections.singletonList
                (new Organization.PositionInTime(LocalDate.of(1984, 9, 1),
                        LocalDate.of(1987, 6, 1), "\tЗакончил с отличием"))));
        educationOrganization.add(zft);
        resume.addSection(SectionType.EDUCATION, new OrganizationsSectionType(educationOrganization));

        educationOrganization.add(new Organization("test1", new Organization.PositionInTime(DateUtil.NOW, DateUtil.NOW, "test1")));
        educationOrganization.add(new Organization("test2", (String) null, new Organization.PositionInTime(DateUtil.NOW, DateUtil.NOW, "test2", null)));
    }

    public static void printContacts(Resume resume) {
        for (ContactsType s : ContactsType.values()) {
            System.out.println(resume.getContacts(s));
        }
    }

    public static void printSection(Resume resume) {
        for (SectionType s : SectionType.values()) {
            System.out.println(resume.getSection(s));
        }
    }

}