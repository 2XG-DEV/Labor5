package controllers;


import models.Teacher;
import repos.TeacherJDBCRepo;

import java.util.List;

public class TeacherController {
    TeacherJDBCRepo repo;



    public TeacherController(TeacherJDBCRepo repo) {
        this.repo = repo;
    }

    public void addTeacherToRepo(Teacher t){
        this.repo.create(t);
    }

    public Teacher getTeacherById(int id){
        List<Teacher> all = repo.getAll();
        for(Teacher s : all){
            if(s.getTeacherId() == id){
                return s;
            }
        }
        return null;
    }

    public List<Teacher> getAllTeachers(){
        return repo.getAll();
    }
}
