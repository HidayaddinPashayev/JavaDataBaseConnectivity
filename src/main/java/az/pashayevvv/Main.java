package az.pashayevvv;

import az.pashayevvv.dao.PatientDao;
import az.pashayevvv.model.Patient;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        PatientDao patientDao = new PatientDao();

        while (true) {
            System.out.println("What do you want? \n1. Create Patient table\n" +
                    "2. Save Patient\n" +
                    "3. Get All Patients\n" +
                    "4. Get Patient by ID\n" +
                    "5. Update Patient by ID\n" +
                    "6. Delete Patient by ID");

            Scanner scanner = new Scanner(System.in);
            Patient patient = new Patient();
            switch (scanner.nextInt()) {
                case 1:
                    patientDao.createPatient();
                    break;
                case 2:
                    patientDao = new PatientDao();
                    patientDao.insertPatient();
                    break;
                case 3:
                    System.out.println(patientDao.getAllPatients());
                    break;
                case 4:
                    System.out.println("Enter patient ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println(patientDao.getById(id));
                    break;
                case 5:
                    System.out.println("Enter patient ID to update: ");
                    int idToUpdate = scanner.nextInt();
                    scanner.nextLine();
                    Patient patientt = patientDao.getById(idToUpdate);
                    if (patientt == null) {
                        throw new RuntimeException("Patient not found");
                    }
                    System.out.println(patientt);
                    System.out.println("Enter patient name: ");
                    String name = scanner.next();
                    patientt.setName(name);
                    System.out.println("Enter patient surname: ");
                    String surname = scanner.next();
                    patientt.setSurname(surname);
                    System.out.println("Enter patient age: ");
                    patientt.setAge(scanner.nextInt());
                    scanner.nextLine();
                    System.out.println("Enter patient gender: ");
                    patientt.setGender(scanner.next());
                    scanner.nextLine();
                    System.out.println("Enter patient disease type: ");
                    patientt.setDiseaseType(scanner.next());
                    scanner.nextLine();
                    patientDao.updatePatient(patientt, idToUpdate); // Replace with update method if needed
                    break;
                case 6:
                    System.out.println("Enter patient ID to delete: ");
                    id = scanner.nextInt();
                    Patient patientToDelete = patientDao.getById(id);
                    if (patientToDelete == null) {
                        throw new RuntimeException("Patient not found");
                    }
                    patientDao.deletePatient(patientToDelete.getId()); // Replace with delete method if needed
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }

    }
}
