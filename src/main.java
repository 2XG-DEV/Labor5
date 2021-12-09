import UI.UI;
import controllers.*;
import repos.CourseJDBCRepo;
import repos.EnrollmentsJDBCRepo;
import repos.StudentJDBCRepo;
import repos.TeacherJDBCRepo;

import java.io.IOException;
import java.sql.*;


public class main {
    public static void main(String[] args) throws IOException {
        TeacherJDBCRepo teacherRepo = new TeacherJDBCRepo();
        StudentJDBCRepo studentRepo = new StudentJDBCRepo();
        EnrollmentsJDBCRepo enrollmentRepo = new EnrollmentsJDBCRepo();
        CourseJDBCRepo courseRepo = new CourseJDBCRepo();
        TeacherController teacherController = new TeacherController(teacherRepo);
        StudentController studentController = new StudentController(studentRepo);
        CourseController courseController = new CourseController(courseRepo);
        EnrollmentController enrollmentController = new EnrollmentController(enrollmentRepo);
        RegistrationSystemController registrationSystemController = new RegistrationSystemController(studentController,courseController,teacherController,enrollmentController);
        UI ui = new UI(registrationSystemController);
        ui.render();

    }
}
