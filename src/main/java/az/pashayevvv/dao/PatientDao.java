package az.pashayevvv.dao;

import az.pashayevvv.model.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PatientDao {

    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "Hidi20052005";

    private Connection getConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(url, user, password);
    }

    public void insertPatient() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter table name: ");
        String table = scanner.nextLine();

        Patient patient = new Patient();
        System.out.println("Enter patient ID: ");
        patient.setId(scanner.nextInt());
        scanner.nextLine();
        System.out.println("Enter patient name: ");
        String name = scanner.next();
        patient.setName(name);
        System.out.println("Enter patient surname: ");
        String surname = scanner.next();
        patient.setSurname(surname);
        scanner.nextLine();
        System.out.println("Enter patient age: ");
        patient.setAge(scanner.nextInt());
        scanner.nextLine();
        System.out.println("Enter patient gender: ");
        patient.setGender(scanner.next());
        scanner.nextLine();
        System.out.println("Enter patient disease type: ");
        patient.setDiseaseType(scanner.next());
        scanner.nextLine();

        String sql = "INSERT INTO " + table + " (id, name, surname, age, gender, diseaseType ) VALUES (?,?,?,?,?,?)";
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, patient.getId());
            statement.setString(2, patient.getName());
            statement.setString(3, patient.getSurname());
            statement.setInt(4, patient.getAge());
            statement.setString(5, patient.getGender());
            statement.setString(6, patient.getDiseaseType());
            statement.execute();
            System.out.println("Patient inserted");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<Patient> getAllPatients() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter table name: ");
        String table = scanner.nextLine();
        String sql = "SELECT * FROM " + table;
        List<Patient> patients = new ArrayList<>();
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Patient patient = new Patient();
                patient.setId(resultSet.getInt("id"));
                patient.setName(resultSet.getString("name"));
                patient.setSurname(resultSet.getString("surname"));
                patient.setAge(resultSet.getInt("age"));
                patient.setGender(resultSet.getString("gender"));
                patient.setDiseaseType(resultSet.getString("diseaseType"));
                patients.add(patient);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patients;
    }

    public Patient getById(int id) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter table name: ");
        String table = scanner.nextLine();
        String sql = "SELECT * FROM " + table + " WHERE id = ?";

        Patient patient = new Patient();

        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                patient = new Patient();

                patient.setId(resultSet.getInt("id"));
                patient.setName(resultSet.getString("name"));
                patient.setSurname(resultSet.getString("surname"));
                patient.setAge(resultSet.getInt("age"));
                patient.setGender(resultSet.getString("gender"));
                patient.setDiseaseType(resultSet.getString("diseaseType"));

            }
            return patient.getId() == null ? null : patient;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patient;
    }

    public void createPatient() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your table name: ");
        String tableName = scanner.nextLine();
        try (Connection connection = getConnection();) {
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE " + tableName +
                    "(id INTEGER, " +
                    "name VARCHAR(255), " +
                    " surname VARCHAR(255), " +
                    " gender VARCHAR(255), " +
                    " diseaseType VARCHAR(255), " +
                    " age INTEGER)";
            statement.executeUpdate(sql);
            System.out.println("table created in given database...");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deletePatient(int id) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter table name: ");
        String table = scanner.nextLine();
        String sql = "DELETE FROM " + table + " WHERE id = ?";
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Patient with ID " + id + " deleted successfully.");
            } else {
                System.out.println("No patient found with ID " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePatient(Patient patient, int id) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter table name: ");
        String table = scanner.nextLine();
        String sql = "UPDATE " + table + " set name = ?, surname = ?, age = ?, gender = ?, diseaseType =? WHERE id = ?";
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, patient.getName());
            statement.setString(2, patient.getSurname());
            statement.setInt(3, patient.getAge());
            statement.setString(4, patient.getGender());
            statement.setInt(5, id);
            statement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}

