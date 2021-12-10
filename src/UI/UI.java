package UI;

import controllers.RegistrationSystemController;
import models.Course;
import models.Student;
import models.Teacher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {
    RegistrationSystemController controller;

    public UI(RegistrationSystemController controller) {
        this.controller = controller;
    }

    /**
     * Shows menu and responds to user interaction
     * @throws IOException
     */
    public void render() throws IOException {
        int choice = -1;
        do {
            System.out.println("1. Register To Course");
            System.out.println("2. Show Courses With Free Places");
            System.out.println("3. Show Students in Course");
            System.out.println("4. Show Classes");
            System.out.println("5. Delete a Course");
            System.out.println("6. Add a Student");
            System.out.println("7. Add a Teacher");
            System.out.println("8. Add a Course");
            System.out.println("9. Sort Students Alphabetical");
            System.out.println("10. Sort Courses Alphabetical");
            System.out.println("11. Filter Students with less than 30 Credits");
            System.out.println("12. Filter Courses with less than 5 Credits");
            System.out.println("13. Exit");
            Scanner myObj = new Scanner(System.in);
            choice = myObj.nextInt();
            switch (choice) {
                case 1:
                    showStudents(controller.getAllStudents());
                    System.out.println("Pick a student(id)");
                    int choice2 = myObj.nextInt();
                    Student s = controller.getStudentById(choice2);
                    showCourses(controller.getAllCourses());
                    System.out.println("Pick a course(id)");
                    int choice3 = myObj.nextInt();
                    Course c = controller.getCourseById(choice3);
                    controller.register(c,s);
                    break;
                case 2 :
                    showCourses(controller.retrieveCoursesWithFreePlaces());
                    break;
                case 3 :
                    showCourses(controller.retrieveAllCourses());
                    System.out.println("Pick a course(id)");
                    int choice4 = myObj.nextInt();
                    showStudents(controller.retrieveStudentsEnrolledForCourse(controller.getCourseById(choice4)));
                    break;
                case 4 :
                    showCourses(controller.getAllCourses());
                    break;
                case 5 :
                    showCourses(controller.getAllCourses());
                    System.out.println("Pick a course(id)");
                    int choice5 = myObj.nextInt();
                    controller.removeCourseFromSystem(controller.getCourseById(choice5));
                    break;
                case 6:
                    System.out.println("Enter Student First Name,Last Name");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String firstName = reader.readLine();
                    String lastName = reader.readLine();

                    controller.addStudentToSystem(new Student(-1,firstName,lastName));
                    break;
                case 7:
                    System.out.println("Enter Teacher First Name, last name");
                    String firstName2 = myObj.nextLine();
                    String lastName2 = myObj.nextLine();

                    controller.addTeacherToSystem(new Teacher(-1,firstName2,lastName2));
                    break;
                case 8:
                    System.out.println("Enter Course Name , Enter Teacher , Enter Credits, Enter Max Enrollment");
                    BufferedReader reader2 = new BufferedReader(new InputStreamReader(System.in));
                    String name = reader2.readLine();
                    System.out.println("Pick a teacher(id)");
                    showTeachers(controller.getAllTeachers());
                    int ch = Integer.parseInt(reader2.readLine());
                    int credits =Integer.parseInt(reader2.readLine());
                    int maxE = Integer.parseInt(reader2.readLine());
                    controller.addCourseToSystem(new Course(-1,ch,name,maxE,credits));
                    break;
                case 9:
                    showStudents(controller.getSortedStudents());
                    break;
                case 10:
                    showCourses(controller.getSortedCourses());
                    break;
                case 11:
                    showStudents(controller.getStudentsWithLessThan30Credits());
                    break;
                case 12:
                    showCourses(controller.getCoursesWithMoreThan5Credits());
                    break;
            }
        }while(choice < 13);
    }


    /**
     * Render students to screen
     * @param all list of students to be rendered
     */
    public void showStudents(List<Student> all){

        for(Student s : all){
            System.out.println("Student : " + s.getLastName() +" "+ s.getFirstName() + " mit " + controller.calculateStudentCredits(s)+" credits und StudentId: "+s.getStudentId());
        }
    }

    /**
     * Render courses to screen
     * @param all list of courses to be rendered
     */
    public void showCourses(List<Course> all){
        for(Course c : all){
            System.out.println("Course : " + c.getName() + "leitet bei : " + controller.getTeacherById(c.getTeacherId()).getLastName() + " mit credits " + c.getCredits() +" freie platze : "+controller.calculateFreePlacesInCourse(c) +" von " + c.getCredits()+" mit id "+c.getCourseId());
        }
    }

    /**
     * Render teachers to screen
     * @param all list of teachers to be rendered
     */
    public void showTeachers(List<Teacher> all){
        for(Teacher t : all){
            System.out.println("Prof. "+t.getLastName()+" "+t.getFirstName()+" mit id "+t.getTeacherId());
        }
    }


}
