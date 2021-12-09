package controllers;

import models.Course;
import models.Enrollment;
import models.Student;
import repos.EnrollmentsJDBCRepo;

import java.util.List;

public class EnrollmentController {
    EnrollmentsJDBCRepo repo;

    public EnrollmentController(EnrollmentsJDBCRepo repo) {
        this.repo = repo;
    }

    public void addEnrollment(Student s ,Course c){
        repo.create(new Enrollment(s.getStudentId(),c.getCourseId()));
    }

    public List<Enrollment> getAllEnrollmentsForStudent(Student s){
        List<Enrollment> all = repo.getAll();
        all.removeIf(e->e.getStudentId()!=s.getStudentId());
        return all;
    }

    public List<Enrollment> getAllStudentEnrolledInCourse(Course c){
        List<Enrollment> all = repo.getAll();
        all.removeIf(e->e.getCourseId()!=c.getCourseId());
        return all;
    }

}
