package employee_management;

import java.io.Serializable;
import java.time.LocalDate;

public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String department;
    private String position;
    private double salary;
    private LocalDate joinDate;

    public Employee(String id, String name, String department,
                    String position, double salary, LocalDate joinDate) {

        setId(id);
        setName(name);
        setDepartment(department);
        setPosition(position);
        setSalary(salary);
        setJoinDate(joinDate);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Employee ID cannot be empty.");
        }

        this.id = id.trim().toUpperCase();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Employee name cannot be empty.");
        }

        this.name = name.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        if (department == null || department.isBlank()) {
            throw new IllegalArgumentException("Department cannot be empty.");
        }

        this.department = department.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        if (position == null || position.isBlank()) {
            throw new IllegalArgumentException("Position cannot be empty.");
        }

        this.position = position.trim();
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative.");
        }

        this.salary = salary;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        if (joinDate == null) {
            throw new IllegalArgumentException("Join date cannot be null.");
        }

        this.joinDate = joinDate;
    }

    @Override
    public String toString() {

        return String.format(
                "ID: %s | Name: %s | Department: %s | Position: %s | Salary: ₹%.2f | Join Date: %s",
                id,
                name,
                department,
                position,
                salary,
                joinDate
        );
    }
}