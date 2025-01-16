package az.pashayevvv.model;

public class Patient {
    private Integer id;
    private String name;
    private String surname;
    private int age;
    private String gender;
    private String diseaseType;

    public Patient() {
    }

    public Patient(int id, String name, String surname, int age, String gender, String diseaseType) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.gender = gender;
        this.diseaseType = diseaseType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDiseaseType() {
        return diseaseType;
    }

    public void setDiseaseType(String diseaseType) {
        this.diseaseType = diseaseType;
    }

    @Override
    public String toString() {
        return "patient {id= " + id + ", name= " + name + ", surname= " + surname + ", age= " + age + ", gender= " + gender + " disease type= " + diseaseType + "}";
    }
}
