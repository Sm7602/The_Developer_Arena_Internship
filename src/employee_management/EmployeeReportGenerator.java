package employee_management;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeReportGenerator {

    public void generateSalaryStatistics(List<Employee> employees) {

        if (employees.isEmpty()) {
            System.out.println("No employees available for reporting.");
            return;
        }

        double totalSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .sum();

        double averageSalary = totalSalary / employees.size();

        Employee highestPaid = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);

        Employee lowestPaid = employees.stream()
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);

        System.out.println("\n========== SALARY STATISTICS ==========");
        System.out.println("Total Employees : " + employees.size());
        System.out.printf("Total Salary    : ₹%.2f%n",totalSalary);
        System.out.printf("Average Salary  : ₹%.2f%n",averageSalary);

        if (highestPaid != null) {
            System.out.printf("Highest Salary  : ₹%.2f (%s)%n",
            		highestPaid.getSalary(),highestPaid.getName()
            );
        }

        if (lowestPaid != null) {
            System.out.printf("Lowest Salary   : ₹%.2f (%s)%n",
                    lowestPaid.getSalary(),lowestPaid.getName()
            );
        }

        System.out.println("=======================================");
    }
    

    public void generateDepartmentReport(List<Employee> employees) {

        if (employees.isEmpty()) {
            System.out.println("No employees available for reporting.");
            return;
        }

        Map<String, List<Employee>> departmentGroups =employees.stream()
        		                                            .collect(Collectors.groupingBy(
                                                                 Employee::getDepartment,
                                                                 TreeMap::new,
                                                                 Collectors.toList()));

        System.out.println("\n========== DEPARTMENT REPORT ==========");

        for (Map.Entry<String, List<Employee>> entry :departmentGroups.entrySet()) {

            String department = entry.getKey();
            List<Employee> departmentEmployees =entry.getValue();

            double averageSalary =departmentEmployees.stream()
                            .mapToDouble(Employee::getSalary)
                            .average()
                            .orElse(0);

            System.out.printf("%-20s | Employees: %-3d | Average Salary: ₹%.2f%n",
                    department,departmentEmployees.size(),averageSalary );
        }

        System.out.println("=======================================");
    }

    public void generatePositionReport(List<Employee> employees) {

        if (employees.isEmpty()) {
            System.out.println("No employees available for reporting.");
            return;
        }

        Map<String, Long> positionCount = employees.stream()
                                .collect(Collectors.groupingBy(
                                Employee::getPosition,
                                TreeMap::new,
                                Collectors.counting()));

        System.out.println("\n========== POSITION REPORT ==========");

        positionCount.forEach((position, count) ->
                System.out.printf("%-25s : %d employee(s)%n",position,count));

        System.out.println("=====================================");
    }
}