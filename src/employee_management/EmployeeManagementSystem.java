package employee_management;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class EmployeeManagementSystem {

    private final ArrayList<Employee> employees;

    private final HashMap<String, Employee> employeeMap;

    private final EmployeeFileHandler fileHandler;

    private final EmployeeReportGenerator reportGenerator;

    private final Scanner scanner;

    public EmployeeManagementSystem() {

        employees = new ArrayList<>();
        employeeMap = new HashMap<>();
        fileHandler =new EmployeeFileHandler("data/employees.dat");
        reportGenerator =new EmployeeReportGenerator();
        scanner = new Scanner(System.in);

        loadEmployees();
    }

    // =========================
    // CREATE
    // =========================

    public void addEmployee() {

        System.out.println("\n========== ADD EMPLOYEE ==========");

        String id = readNonEmpty("Enter Employee ID: ").toUpperCase();

        if (employeeMap.containsKey(id)) {
            System.out.println("Employee with ID " + id + " already exists.");
            return;
        }

        String name =readNonEmpty("Enter Name: ");

        String department =readNonEmpty("Enter Department: ");

        String position =readNonEmpty("Enter Position: ");

        double salary =readSalary();

        LocalDate joinDate =readJoinDate();

        try {

            Employee employee =new Employee(id,name,department,position,salary,joinDate);

            employees.add(employee);
            employeeMap.put(employee.getId(),employee);

            saveEmployees();

            System.out.println("Employee added successfully!");

        } catch (IllegalArgumentException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }

    // =========================
    // READ
    // =========================

    public void displayAllEmployees() {

        System.out.println("\n========== ALL EMPLOYEES ==========");

        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        System.out.printf(
                "%-8s %-20s %-18s %-22s %-14s %-12s%n",
                "ID",
                "Name",
                "Department",
                "Position",
                "Salary",
                "Join Date"
        );

        System.out.println("-".repeat(100));

        for (Employee employee : employees) {

            System.out.printf(
                    "%-8s %-20s %-18s %-22s ₹%-13.2f %-12s%n",
                    employee.getId(),
                    employee.getName(),
                    employee.getDepartment(),
                    employee.getPosition(),
                    employee.getSalary(),
                    employee.getJoinDate()
            );
        }

        System.out.println("-".repeat(100));

        System.out.println("Total Employees: " + employees.size());
    }

    // =========================
    // UPDATE
    // =========================

    public void updateEmployee() {

        System.out.println("\n========== UPDATE EMPLOYEE ==========");

        String id =readNonEmpty("Enter Employee ID: ").toUpperCase();

        Employee employee =employeeMap.get(id);

        if (employee == null) {
            System.out.println("Employee with ID " + id + " not found.");
            return;
        }

        System.out.println("\nCurrent Employee:");
        System.out.println(employee);
        System.out.println("\nEnter new information.");

        String name =readNonEmpty("Enter Name: ");

        String department =readNonEmpty("Enter Department: ");

        String position =readNonEmpty("Enter Position: ");

        double salary = readSalary();

        LocalDate joinDate =readJoinDate();

        try {

            employee.setName(name);

            employee.setDepartment(department);

            employee.setPosition(position);

            employee.setSalary(salary);

            employee.setJoinDate(joinDate);

            saveEmployees();

            System.out.println("Employee updated successfully!");

        } catch (IllegalArgumentException e) {

            System.out.println("Update failed: " + e.getMessage());
        }
    }

    // =========================
    // DELETE
    // =========================

    public void deleteEmployee() {

        System.out.println("\n========== DELETE EMPLOYEE ==========");

        String id =readNonEmpty("Enter Employee ID: ").toUpperCase();

        Employee employee =employeeMap.get(id);

        if (employee == null) {
            System.out.println("Employee with ID " + id + " not found.");
            return;
        }

        System.out.println("\nEmployee:");

        System.out.println(employee);

        String confirmation =readNonEmpty( "Are you sure you want to delete? (yes/no): ");

        if (!confirmation.equalsIgnoreCase("yes")) {
            System.out.println("Delete operation cancelled.");
            return;
        }

        employees.remove(employee);

        employeeMap.remove(id);

        saveEmployees();

        System.out.println("Employee deleted successfully!");
    }

    // =========================
    // SEARCH
    // =========================

    public void searchEmployee() {

        while (true) {

            System.out.println("\n========== SEARCH EMPLOYEE ==========");
            System.out.println("1. Search by ID");
            System.out.println("2. Search by Name");
            System.out.println("3. Search by Department");
            System.out.println("4. Search by Position");
            System.out.println("5. Back");

            int choice =readInt("Enter choice: ", 1, 5);

            switch (choice) {

                case 1 -> searchById();

                case 2 -> searchByName();

                case 3 -> searchByDepartment();

                case 4 -> searchByPosition();

                case 5 -> {
                    return;
                }
            }
        }
    }

    private void searchById() {

        String id =readNonEmpty("Enter Employee ID: ").toUpperCase();

        Employee employee =employeeMap.get(id);

        if (employee == null) {
            System.out.println("Employee not found."
            );
            return;
        }

        System.out.println("\nEmployee Found:");
        System.out.println(employee);
    }

    private void searchByName() {

        String name =readNonEmpty("Enter Name: ").toLowerCase();

        List<Employee> results =employees.stream().filter(employee ->
                                        employee.getName()
                                        .toLowerCase()
                                        .contains(name))
                                        .toList();

        displaySearchResults(results);
    }

    private void searchByDepartment() {

        String department =readNonEmpty("Enter Department: ").toLowerCase();

        List<Employee> results = employees.stream().filter(employee ->
                                        employee.getDepartment()
                                        .toLowerCase()
                                        .contains(department))
                                        .toList();

        displaySearchResults(results);
    }

    private void searchByPosition() {

        String position =readNonEmpty("Enter Position: ").toLowerCase();

        List<Employee> results = employees.stream().filter(employee ->
                                        employee.getPosition()
                                        .toLowerCase()
                                        .contains(position))
                                        .toList();

        displaySearchResults(results);
    }

    private void displaySearchResults(List<Employee> results) {

        if (results.isEmpty()) {
            System.out.println("No matching employees found.");
            return;
        }

        System.out.println("\nSearch Results: "+ results.size()+ " employee(s)");
        System.out.println("-".repeat(100));
        results.forEach(System.out::println);
        System.out.println("-".repeat(100));
    }

    // =========================
    // REPORTS
    // =========================

    public void generateReports() {

        while (true) {

            System.out.println("\n========== EMPLOYEE REPORTS ==========");
            System.out.println("1. Salary Statistics");
            System.out.println("2. Department Summary");
            System.out.println("3. Position Summary");
            System.out.println("4. Back");

            int choice =readInt("Enter choice: ", 1, 4);

            switch (choice) {

                case 1 ->
                        reportGenerator.generateSalaryStatistics(employees);

                case 2 ->
                        reportGenerator.generateDepartmentReport(employees);

                case 3 ->
                        reportGenerator.generatePositionReport(employees);

                case 4 -> {
                    return;
                }
            }
        }
    }

    // =========================
    // FILE PERSISTENCE
    // =========================

    public void saveEmployees() {

        fileHandler.saveEmployees(employees);
    }

    private void loadEmployees() {

        ArrayList<Employee> loadedEmployees =fileHandler.loadEmployees();

        employees.clear();
        employeeMap.clear();

        for (Employee employee : loadedEmployees) {
            employees.add(employee);
            employeeMap.put(employee.getId(),employee);
            }
        
        if (!employees.isEmpty()) {
            System.out.println( employees.size()+ " employee(s) loaded successfully.");
        }
    }

    // =========================
    // INPUT VALIDATION
    // =========================

    private String readNonEmpty(String message) {

        while (true) {

            System.out.print(message);
            String input =scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty.");
        }
    }

    private double readSalary() {

        while (true) {

            System.out.print("Enter Salary: ");
            String input =scanner.nextLine().trim();

            try {
                double salary =Double.parseDouble(input);

                if (salary < 0) {
                    System.out.println("Salary cannot be negative.");
                    continue;
                }
                return salary;
                
            } catch (NumberFormatException e) {
                System.out.println("Invalid salary. Enter a valid number.");
            }
        }
    }

    private LocalDate readJoinDate() {

        while (true) {

            System.out.print("Enter Join Date (YYYY-MM-DD): ");

            String input =scanner.nextLine().trim();

            try {

                return LocalDate.parse(input);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date. Example: 2026-09-03");
            }
        }
    }

    private int readInt(String message,int min,int max) {

        while (true) {

            System.out.print(message);
            String input =scanner.nextLine().trim();

            try {

                int value =Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.printf("Enter a number between %d and %d.%n",min,max);

            } catch (NumberFormatException e) {

                System.out.println("Invalid input. Enter a number.");
            }
        }
    }

    // =========================
    // MENU
    // =========================

    public void start() {

        while (true) {

            printMenu();

            int choice =readInt("Enter your choice: ",1,9);

            switch (choice) {

                case 1 ->
                        addEmployee();

                case 2 ->
                        displayAllEmployees();

                case 3 ->
                        searchEmployee();

                case 4 ->
                        updateEmployee();

                case 5 ->
                        deleteEmployee();

                case 6 ->
                        generateReports();

                case 7 ->
                        saveEmployees();

                case 8 ->
                        loadEmployees();

                case 9 -> {
                	        saveEmployees();
                    System.out.println("\nThank you for using "+ "Employee Management System!");
                    scanner.close();
                    return;
                }
            }
        }
    }

    private void printMenu() {

        System.out.println("\n");
        System.out.println("==============================================");
        System.out.println("       EMPLOYEE MANAGEMENT SYSTEM");
        System.out.println("==============================================");
        System.out.println("1. Add New Employee");
        System.out.println("2. View All Employees");
        System.out.println("3. Search Employee");
        System.out.println("4. Update Employee");
        System.out.println("5. Delete Employee");
        System.out.println("6. Generate Reports");
        System.out.println("7. Save to File");
        System.out.println("8. Load from File");
        System.out.println("9. Exit");
        System.out.println( "==============================================");
    }
}