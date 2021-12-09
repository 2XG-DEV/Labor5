package models;

import java.util.ArrayList;

public class Student  implements Comparable<Student>{
    int studentId;
    String firstName;
    String lastName;


    public Student(int studentId, String firstName, String lastName){
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getCredits() {
        return 0;
    }

    public ArrayList<Course> getEnrolledCourses() {
        return new ArrayList<Course>();
    }

    @Override
    public int compareTo(Student s){
        return this.getLastName().compareTo(s.getLastName());
    }
}
