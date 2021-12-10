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

    /**
     * Adds course to repository
     * @param c course to be added
     */
    public void addCourseToRepo(Course c){
        this.repo.create(c);
    }

    /**
     * Removes a course from repository
     * @param c course to be removed
     */
    public void removeCourseFromRepo(Course c){
        repo.delete(c);
    }

    /**
     * Get a course by using its id
     * @param id id of the course
     * @return course with said id or null if not found
     */
    public Course getCourseById(int id){
        List<Course> all = repo.getAll();
        for(Course c : all){
            if(c.getCourseId() == id){
                return c;
            }
        }
        return null;
    }

    /**
     * Change the credits of a course
     * @param c course whose credits are to be changed
     * @param newCredits new credit amount of the course
     */
    public void changeCourseCredits(Course c , int newCredits){
        c.setCredits(newCredits);
        repo.update(c);
    }

    /**
     * gets all the courses in the repo
     * @return list of all courses
     */
    public List<Course> getAllCourses(){
        return repo.getAll();
    }

    /**
     * gets all courses in the repo in alphabetical order
     * @return list of all courses sorted
     */
    public List<Course> getSortedCoursesAlphabetical(){
        List<Course> all = repo.getAll();
        Collections.sort(all);
        return all;
    }

    /**
     * filters the list of courses for those worth more than 5 credits
     * @return list of courses worth more than 5 credits
     */
    public List<Course> getCoursesWithMoreThan5Credits(){
        List<Course> all = repo.getAll();
        return all.stream().filter(c->c.getCredits() > 5).collect(Collectors.toList());
    }

}
