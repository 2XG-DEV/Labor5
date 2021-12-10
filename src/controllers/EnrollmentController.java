package controllers;

import models.Course;
import models.Enrollment;
import models.Student;
import repos.EnrollmentsJDBCRepo;
import repos.ICrudRepo;

import java.util.List;

public class EnrollmentController {
    ICrudRepo repo;

    public EnrollmentController(ICrudRepo repo) {
        this.repo = repo;
    }

    /**
     * Adds enrollement to database
     * @param s student to be enrolled
     * @param c course to be enrolled to
     */
    public void addEnrollment(Student s ,Course c){
        repo.create(new Enrollment(s.getStudentId(),c.getCourseId()));
    }

    /**
     * Find all enrollments of a student
     * @param s student to find enrollments for
     * @return list of all enrollments of the student
     */
    public List<Enrollment> getAllEnrollmentsForStudent(Student s){
        List<Enrollment> all = repo.getAll();
        all.removeIf(e->e.getStudentId()!=s.getStudentId());
        return all;
    }

    /**
     * Finds all the students enrolled in a course
     * @param c course to find enrolled students for
     * @return list of students who are enrolled in the course
     */
    public List<Enrollment> getAllStudentEnrolledInCourse(Course c){
        List<Enrollment> all = repo.getAll();
        all.removeIf(e->e.getCourseId()!=c.getCourseId());
        return all;
    }

}
