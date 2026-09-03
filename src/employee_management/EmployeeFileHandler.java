package employee_management;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeFileHandler {

    private final String filePath;

    public EmployeeFileHandler(String filePath) {
        this.filePath = filePath;
    }

    public void saveEmployees(List<Employee> employees) {

        File file = new File(filePath);
        File parent = file.getParentFile();

        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(new FileOutputStream(file))) {

            outputStream.writeObject(new ArrayList<>(employees));

            System.out.println("Employee data saved successfully.");

        } catch (IOException e) {

            System.out.println("Error while saving employee data: "+ e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Employee> loadEmployees() {

        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("No existing employee data found. Starting with empty database.");
            return new ArrayList<>();
        }

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(new FileInputStream(file))) {

            Object data = inputStream.readObject();

            if (data instanceof ArrayList<?>) {
                return (ArrayList<Employee>) data;
            }

        } catch (IOException | ClassNotFoundException e) {

            System.out.println("Error while loading employee data: "+ e.getMessage());
        }

        return new ArrayList<>();
    }
}