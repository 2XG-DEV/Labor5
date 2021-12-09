package controllers;

import models.Course;
import models.Enrollment;
import models.Student;
import models.Teacher;

import java.util.ArrayList;
import java.util.List;

public class RegistrationSystemController {
    StudentController studentController;
    CourseController courseController;
    TeacherController teacherController;
    EnrollmentController enrollmentController;


    public RegistrationSystemController(StudentController studentController, CourseController courseController, TeacherController teacherController, EnrollmentController enrollmentController) {
        this.studentController = studentController;
        this.courseController = courseController;
        this.teacherController = teacherController;
        this.enrollmentController = enrollmentController;
    }



    public boolean register(Course c , Student s){
        //check if student already enrolled
        List<Enrollment> enrolls = enrollmentController.getAllEnrollmentsForStudent(s);
        for(Enrollment e : enrolls){
            if(e.getCourseId() == c.getCourseId()){
                return false;
            }
        }
        //check if registering the student to course will cause his credits to go beyond 30
        if(calculateStudentCredits(s) + c.getCredits() > 30){
            return false;
        }
        //check if course has free places
        if(calculateFreePlacesInCourse(c) == 0){
            return false;
        }
        enrollmentController.addEnrollment(s,c);
        return true;
    }

    public void addTeacherToSystem(Teacher t){
        teacherController.addTeacherToRepo(t);
    }

    public void addStudentToSystem(Student s){
        studentController.addStudentToRepo(s);
    }

    public void addCourseToSystem(Course c){
        courseController.addCourseToRepo(c);
    }

    public void removeCourseFromSystem(Course c){
        courseController.removeCourseFromRepo(c);
    }

    public Teacher getTeacherById(int id){
        return teacherController.getTeacherById(id);
    }

    public List<Student> retrieveStudentsEnrolledForCourse(Course c){
        List<Enrollment> enrolls = enrollmentController.getAllStudentEnrolledInCourse(c);
        List<Student> toReturn = new ArrayList<Student>();
        for(Enrollment e : enrolls){
            toReturn.add(studentController.getStudentById(e.getStudentId()));
        }
        return toReturn;
    }

    public List<Course> retrieveAllCourses(){
        return courseController.getAllCourses();
    }

    public List<Course> retrieveCoursesWithFreePlaces(){
        List<Course> all = courseController.getAllCourses();
        List<Course> toReturn = new ArrayList<Course>();
        for(Course c : all){
            if(enrollmentController.getAllStudentEnrolledInCourse(c).size() < c.getMaxStudents()){
                toReturn.add(c);
            }
        }
        return toReturn;
    }

    public int calculateFreePlacesInCourse(Course c){
        int total = 0;
        List<Enrollment> enrolls = enrollmentController.getAllStudentEnrolledInCourse(c);
        return c.getMaxStudents() - enrolls.size();
    }

    public int calculateStudentCredits(Student s){
        int total = 0;
        List<Enrollment> enrolls = enrollmentController.getAllEnrollmentsForStudent(s);
        List<Course> enrolledCourses = new ArrayList<Course>();
        for(Enrollment e : enrolls){
            enrolledCourses.add(courseController.getCourseById(e.getCourseId()));
        }
        for(Course c : enrolledCourses){
            total += c.getCredits();
        }
        return total;
    }

    public List<Student> getAllStudents(){
        return studentController.getAllStudents();
    }

    public List<Teacher> getAllTeachers(){
        return teacherController.getAllTeachers();
    }

    public List<Course> getAllCourses(){
        return courseController.getAllCourses();
    }


    public void changeCourseCredits(Course c,int newCredits){
        courseController.changeCourseCredits(c,newCredits);
    }

    public List<Student> getSortedStudents(){
        return studentController.getSortedStudentsAlphabetic();
    }

    public List<Student> getStudentsWithLessThan30Credits(){
        return studentController.getStudentsWithLessThan30Credits();
    }

    public List<Course> getSortedCourses(){
        return courseController.getSortedCoursesAlphabetical();
    }

    public List<Course> getCoursesWithMoreThan5Credits(){
        return courseController.getCoursesWithMoreThan5Credits();
    }


    public Student getStudentById(int id) {
        return studentController.getStudentById(id);
    }

    public Course getCourseById(int id) {
        return courseController.getCourseById(id);
    }

}
