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

    /**
     * Finds teacher object in the repository based on its id
     * @param id id of teacher to be found
     * @return teacher object with said id , null if not found
     */
    public Teacher getTeacherById(int id){
        List<Teacher> all = repo.getAll();
        for(Teacher s : all){
            if(s.getTeacherId() == id){
                return s;
            }
        }
        return null;
    }

    /**
     * Finds all teachers in the repository
     * @return list of all teachers
     */
    public List<Teacher> getAllTeachers(){
        return repo.getAll();
    }
}
