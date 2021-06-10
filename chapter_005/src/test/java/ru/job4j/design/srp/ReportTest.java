package ru.job4j.design.srp;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ReportTest {
    @Test
    public void whenReportPlainThenPlainTextGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportPlain(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenReportBookkeepingThenGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportBookkeeping(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary())
                .append(" RUB").append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenReportProgrammersThenHTMLGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportProgrammers(store);
        StringBuilder expect = new StringBuilder()
                .append("<table>")
                .append("<tr><th>Name</th><th>Hired</th><th>Fired</th><th>Salary</th></tr>")
                .append("<tr>")
                .append(String.format("<td>%s</td>", worker.getName()))
                .append(String.format("<td>%s</td>", worker.getHired()))
                .append(String.format("<td>%s</td>", worker.getFired()))
                .append(String.format("<td>%s</td>", worker.getSalary()))
                .append("</tr>")
                .append("</table>");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenReportHrThenGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 70);
        Employee worker2 = new Employee("Petr", now, now, 150);
        Employee worker3 = new Employee("Pavel", now, now, 100);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report engine = new ReportHr(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker3.getName()).append(";")
                .append(worker3.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenReportXmlThenGenerated() {
        MemStore store = new MemStore();
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.FEBRUARY, 4);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss X");
        String dateString = formatter.format(date.getTime());
        Employee worker1 = new Employee("Ivan", date, date, 70);
        Employee worker2 = new Employee("Petr", date, date, 150);
        Employee worker3 = new Employee("Pavel", date, date, 100);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report engine = new ReportXml(store);
        String employeeXmlTemplate =
                "<employee name=\"%s\" hired=\"%s\" fired=\"%s\" salary=\"%.1f\"/>";
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
                .append("<employees>")
                .append(String.format(Locale.US, employeeXmlTemplate,
                        worker1.getName(), dateString, dateString, worker1.getSalary()))
                .append(String.format(Locale.US, employeeXmlTemplate,
                        worker2.getName(), dateString, dateString, worker2.getSalary()))
                .append(String.format(Locale.US, employeeXmlTemplate,
                        worker3.getName(), dateString, dateString, worker3.getSalary()))
                .append("</employees>");
        String result = engine.generate(em -> true).replaceAll("\\n\\s*", "");
        assertThat(result, is(expect.toString()));
    }

    @Test
    public void whenReportJsonThenGenerated() {
        MemStore store = new MemStore();
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.FEBRUARY, 4);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss X");
        String dateString = formatter.format(date.getTime());
        Employee worker1 = new Employee("Ivan", date, date, 70);
        Employee worker2 = new Employee("Petr", date, date, 150);
        Employee worker3 = new Employee("Pavel", date, date, 100);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report engine = new ReportJson(store);
        String employeeJsonTemplate =
                "{\"name\":\"%s\",\"hired\":\"%s\",\"fired\":\"%s\",\"salary\":%.1f}";
        StringBuilder expect = new StringBuilder()
                .append("{\"employees\":[")
                .append(String.format(Locale.US, employeeJsonTemplate + ",",
                        worker1.getName(), dateString, dateString, worker1.getSalary()))
                .append(String.format(Locale.US, employeeJsonTemplate + ",",
                        worker2.getName(), dateString, dateString, worker2.getSalary()))
                .append(String.format(Locale.US, employeeJsonTemplate,
                        worker3.getName(), dateString, dateString, worker3.getSalary()))
                .append("]}");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}