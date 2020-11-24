package com.urise.webapp.model;

import org.junit.Before;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {
    private final Resume resume;


    public ResumeTestData() {
        resume = new Resume("uuid5", "Grigory Kislin");
    }

    @Before
    public void setUp() {
        resume.addContacts(ContactsType.PHONE, "+7(921) 855-0482)");
        resume.addContacts(ContactsType.SKYPE, "grigory.kislin");
        resume.addContacts(ContactsType.EMAIL, "gkislin@yandex.ru");
        resume.addContacts(ContactsType.LINKEDID, "https://www.linkedin.com/in/gkislin");
        resume.addContacts(ContactsType.GITHUB, "https://github.com/gkislin");
        resume.addContacts(ContactsType.STACKOVERFLOW, "https:/" +
                "/stackoverflow.com/users/548473/grigory-kislin");
        resume.addContacts(ContactsType.HOMEPAGE, "http://gkislin.ru/");

        resume.addSecton(SectionType.OBJECTIVE, new TextSectionType("Ведущий стажировок " +
                "и корпоративного обучения по Java Web и Enterprise технологиям"));
        resume.addSecton(SectionType.PERSONAL, new TextSectionType("Аналитический склад ума, " +
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
        resume.addSecton(SectionType.ACHIEVEMENT, new ListSectionType(achievement));

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
        resume.addSecton(SectionType.QUALIFICATIONS, new ListSectionType(qualifications));

        List<Organization> workOrganizations = new ArrayList<>();
        Organization javaOnlineProjects = new Organization("Java Online Projects",
                "https://javaops.ru/", LocalDate.of(2013, 9, 0),
                LocalDate.now(), "Автор проекта.", "Создание, " +
                "организация и проведение Java онлайн проектов и стажировок.");
        workOrganizations.add(javaOnlineProjects);
        Organization wrike = new Organization("Wrike", "https://www.wrike.com/",
                LocalDate.of(2014, 9, 0),
                LocalDate.of(2016, 0, 0),
                "Старший разработчик (backend)", "Проектирование и разработка " +
                "онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, " +
                "MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, " +
                "авторизация по OAuth1, OAuth2, JWT SSO.");
        workOrganizations.add(wrike);
        Organization rITCenter = new Organization("RIT Center", "",
                LocalDate.of(2012, 3, 0),
                LocalDate.of(2014, 9, 0),
                "Java архитектор", "Организация процесса разработки системы ERP " +
                "для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), " +
                "миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), " +
                "AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных " +
                "сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт " +
                "в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера " +
                "документов MS Office. Maven + plugin development, Ant, Apache Commons, " +
                "Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting," +
                " Unix shell remote scripting via ssh tunnels, PL/Python");
        workOrganizations.add(rITCenter);
        Organization luxoftDeutscheBank = new Organization("Luxoft (Deutsche Bank)",
                "https://career.luxoft.com/locations/russia/",
                LocalDate.of(2010, 11, 0),
                LocalDate.of(2012, 3, 0), "Ведущий программист",
                "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, " +
                        "SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. " +
                        "Реализация RIA-приложения для администрирования, мониторинга и анализа " +
                        "результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, " +
                        "GWT, ExtGWT (GXT), Highstock, Commet, HTML5.");
        workOrganizations.add(luxoftDeutscheBank);
        Organization yota = new Organization("Yota", "https://www.yota.ru/",
                LocalDate.of(2008, 5, 0),
                LocalDate.of(2010, 11, 0),
                "Ведущий специалист", "Дизайн и имплементация Java EE фреймворка " +
                "для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, " +
                "Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и " +
                "мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)");
        workOrganizations.add(yota);
        Organization enkata = new Organization("Enkata",
                "https://www.pega.com/products/robotic-process-automation",
                LocalDate.of(2007, 2, 0),
                LocalDate.of(2008, 5, 0), "Разработчик ПО",
                "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, " +
                        "Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).");
        workOrganizations.add(enkata);
        Organization siemensAG = new Organization("Siemens AG", "https://new.siemens.com/ru/ru.html",
                LocalDate.of(2005, 0, 0),
                LocalDate.of(2007, 1, 0), "Разработчик ПО",
                "Разработка информационной модели, проектирование интерфейсов, реализация и " +
                        "отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).");
        workOrganizations.add(siemensAG);
        Organization alcatel = new Organization("Alcatel", "http://www.alcatel.ru/",
                LocalDate.of(1997, 8, 0),
                LocalDate.of(2005, 0, 0),
                "Инженер по аппаратному и программному тестированию",
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel " +
                        "1000 S12 (CHILL, ASM).");
        workOrganizations.add(alcatel);
        resume.addSecton(SectionType.EXPERIENCE, new OrganizationsSectionType(workOrganizations));

        List<Organization> educationOrganization = new ArrayList<>();
        Organization coursera = new Organization("Coursera", "https://www.coursera.org/learn/progfun1",
                LocalDate.of(2013, 2, 0),
                LocalDate.of(2013, 4, 0),
                "\t\"Functional Programming Principles in Scala\" by Martin Odersky");
        educationOrganization.add(coursera);
        Organization luxoft = new Organization("Luxoft",
                "https://www.luxoft-training.ru/kurs/" +
                        "obektno-orientirovannyy_analiz_i_proektirovanie_na_uml.html",
                LocalDate.of(2011, 02, 0), LocalDate.of(2011, 3, 0),
                "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"");
        educationOrganization.add(luxoft);
        Organization siemens = new Organization("Siemens AG", "https://new.siemens.com/ru/ru.html",
                LocalDate.of(2005, 0, 0),
                LocalDate.of(2005, 3, 0),
                "3 месяца обучения мобильным IN сетям (Берлин)");
        educationOrganization.add(siemens);
        Organization alcatelEdu = new Organization("Alcatel", "http://www.alcatel.ru/",
                LocalDate.of(1997, 8, 0),
                LocalDate.of(1998, 02, 0),
                "6 месяцев обучения цифровым телефонным сетям (Москва)");
        educationOrganization.add(alcatelEdu);
        Organization spb = new Organization("Санкт-Петербургский национальный исследовательский " +
                "университет информационных технологий, механики и оптики", "https://itmo.ru/ru/",
                LocalDate.of(1993, 8, 0),
                LocalDate.of(1996, 6, 0), "Аспирантура (программист С, С++)");
        educationOrganization.add(spb);
        Organization spb2 = new Organization("Санкт-Петербургский национальный исследовательский " +
                "университет информационных технологий, механики и оптики", "https://itmo.ru/ru/",
                LocalDate.of(1987, 8, 0),
                LocalDate.of(1993, 6, 0), "Инженер (программист Fortran, C)");
        educationOrganization.add(spb2);
        Organization zft = new Organization("Заочная физико-техническая школа при МФТИ",
                "http://www.school.mipt.ru/", LocalDate.of(1984, 8, 0),
                LocalDate.of(1987, 5, 0), "\tЗакончил с отличием");
    }


}