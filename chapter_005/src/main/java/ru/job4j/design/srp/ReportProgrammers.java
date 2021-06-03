package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportProgrammers implements Report {

    private Store store;

    public ReportProgrammers(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text
                .append("<table>")
                .append("<tr><th>Name</th><th>Hired</th><th>Fired</th><th>Salary</th></tr>");
        for (Employee employee : store.findBy(filter)) {
            text
                    .append("<tr>")
                    .append(String.format("<td>%s</td>", employee.getName()))
                    .append(String.format("<td>%s</td>", employee.getHired()))
                    .append(String.format("<td>%s</td>", employee.getFired()))
                    .append(String.format("<td>%s</td>", employee.getSalary()))
                    .append("</tr>");
        }
        text.append("</table>");
        return text.toString();
    }
}
