package models;

public class Course implements Comparable<Course> {
    int courseId;
    int teacherId;
    String name;
    int maxStudents;
    int credits;

    public Course(int courseId, int teacherId, String name, int maxStudents, int credits) {
        this.courseId = courseId;
        this.teacherId = teacherId;
        this.name = name;
        this.maxStudents = maxStudents;
        this.credits = credits;
    }

    public int getCourseId() {
        return courseId;
    }


    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public int compareTo(Course c){
        return this.getName().compareTo(c.getName());
    }
}
