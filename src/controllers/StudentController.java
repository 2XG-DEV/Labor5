package controllers;

import models.Student;
import repos.StudentJDBCRepo;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StudentController {
    StudentJDBCRepo repo;



    public StudentController(StudentJDBCRepo repo) {
        this.repo = repo;
    }

    public void addStudentToRepo(Student s){
        this.repo.create(s);
    }


    public Student getStudentById(int id){
        List<Student> all = repo.getAll();
        for(Student s : all){
            if(s.getStudentId() == id){
                return s;
            }
        }
        return null;
    }

    public List<Student> getAllStudents(){
        return repo.getAll();
    }

    public List<Student> getSortedStudentsAlphabetic(){
        List<Student> all =repo.getAll();
        Collections.sort(all);
        return all;
    }

    public List<Student> getStudentsWithLessThan30Credits(){
        List<Student> all = repo.getAll();
        return all.stream().filter(s->s.getCredits() < 30).collect(Collectors.toList());
    }


}
