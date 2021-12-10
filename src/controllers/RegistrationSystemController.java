package controllers;

import models.Course;
import models.Enrollment;
import models.Student;
import models.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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


    /**
     * Registers a student to a course
     * @param c course to be registered to
     * @param s student to be registered
     * @return true if succesfully registered , false otherwise
     */
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

    /**
     * Adds teacher to system
     * @param t teacher to be added
     */
    public void addTeacherToSystem(Teacher t){
        teacherController.addTeacherToRepo(t);
    }

    /**
     * Adds student to system
     * @param s student to be added
     */
    public void addStudentToSystem(Student s){
        studentController.addStudentToRepo(s);
    }

    /**
     * Adds course to system
     * @param c course to be added
     */
    public void addCourseToSystem(Course c){
        courseController.addCourseToRepo(c);
    }

    /**
     * rempoves a course from system
     * @param c course to be removed
     */
    public void removeCourseFromSystem(Course c){
        courseController.removeCourseFromRepo(c);
    }

    /**
     * finds a teacher using its id
     * @param id id of teacher to be found
     * @return teacher object if found null otherwise
     */
    public Teacher getTeacherById(int id){
        return teacherController.getTeacherById(id);
    }

    /**
     * Finds all students who are enrolled in a course
     * @param c course for which to find students
     * @return list of students enrolled for said course
     */
    public List<Student> retrieveStudentsEnrolledForCourse(Course c){
        List<Enrollment> enrolls = enrollmentController.getAllStudentEnrolledInCourse(c);
        List<Student> toReturn = new ArrayList<Student>();
        for(Enrollment e : enrolls){
            toReturn.add(studentController.getStudentById(e.getStudentId()));
        }
        return toReturn;
    }

    /**
     * Finds all the courses in the system
     * @return list of all courses
     */
    public List<Course> retrieveAllCourses(){
        return courseController.getAllCourses();
    }

    /**
     * Finds courses which have free place
     * @return list courses with available places
     */
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

    /**
     * Calculates free places in a course
     * @param c course for which to calculate free places
     * @return amount of free places in said course
     */
    public int calculateFreePlacesInCourse(Course c){
        int total = 0;
        List<Enrollment> enrolls = enrollmentController.getAllStudentEnrolledInCourse(c);
        return c.getMaxStudents() - enrolls.size();
    }

    /**
     * Calculates a students credits
     * @param s student for which to calculate credits
     * @return amount of credits of said student
     */
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

    /**
     * Finds all students in the system
     * @return list of all students
     */
    public List<Student> getAllStudents(){
        return studentController.getAllStudents();
    }

    /**
     * Finds all teachers in the system
     * @return list of all teachers in system
     */
    public List<Teacher> getAllTeachers(){
        return teacherController.getAllTeachers();
    }

    /**
     * Finds all courses in the system
     * @return list of all courses
     */
    public List<Course> getAllCourses(){
        return courseController.getAllCourses();
    }

    /**
     * Changes a courses credits
     * @param c course to change credits for
     * @param newCredits the new amount of credits for the course
     */
    public void changeCourseCredits(Course c,int newCredits){
        courseController.changeCourseCredits(c,newCredits);
    }

    /**
     * Gets all students sorted alphabetical
     * @return list of all students sorted
     */
    public List<Student> getSortedStudents(){
        return studentController.getSortedStudentsAlphabetic();
    }

    /**
     * Gets all courses sorted alphabetical
     * @return list of all courses sorted
     */
    public List<Course> getSortedCourses(){
        return courseController.getSortedCoursesAlphabetical();
    }

    /**
     * Gets all courses which have more than 5 credits
     * @return list of courses worth more than 5 credits
     */
    public List<Course> getCoursesWithMoreThan5Credits(){
        return courseController.getCoursesWithMoreThan5Credits();
    }

    /**
     * Finds student in system using its id
     * @param id id of the student to be found
     * @return student object if found , null otherwise
     */
    public Student getStudentById(int id) {
        return studentController.getStudentById(id);
    }

    /**
     * Finds a course in system using its id
     * @param id id of the course to be found
     * @return course object if found , null otherwise
     */
    public Course getCourseById(int id) {
        return courseController.getCourseById(id);
    }

    /**
     * Gets all the students in the repository who have less than 30 credits
     * @return list of students with less than 30 credits;
     */
    public List<Student> getStudentsWithLessThan30Credits(){
        List<Student> all = studentController.getAllStudents();
        return all.stream().filter(s->calculateStudentCredits(s) < 30).collect(Collectors.toList());
    }

}
