package controllers;

import models.Course;
import models.Teacher;
import repos.CourseJDBCRepo;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CourseController {
    CourseJDBCRepo repo;

    public CourseController(CourseJDBCRepo repo) {
        this.repo = repo;
    }

    public void addCourseToRepo(Course c){
        this.repo.create(c);
    }

    public void removeCourseFromRepo(Course c){
        repo.delete(c);
    }

    public Course getCourseById(int id){
        List<Course> all = repo.getAll();
        for(Course c : all){
            if(c.getCourseId() == id){
                return c;
            }
        }
        return null;
    }

    public void changeCourseCredits(Course c , int newCredits){
        c.setCredits(newCredits);
        repo.update(c);
    }

    public List<Course> getAllCourses(){
        return repo.getAll();
    }

    public List<Course> getSortedCoursesAlphabetical(){
        List<Course> all = repo.getAll();
        Collections.sort(all);
        return all;
    }

    public List<Course> getCoursesWithMoreThan5Credits(){
        List<Course> all = repo.getAll();
        return all.stream().filter(c->c.getCredits() > 5).collect(Collectors.toList());
    }

}
