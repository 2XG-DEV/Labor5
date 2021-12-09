package repos;

import models.Course;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseJDBCRepo implements ICrudRepo<Course>{

    public Course create(Course obj){
        String url = "jdbc:mysql://localhost:3306/university";
        String user = "doubleg";
        String password = "1234";
        try {
            Connection conn = DriverManager.getConnection(url,user,password);
            String query1 = "insert into courses (teacherId, name, maxStudents, credits) values (?,?,?,?)";
            PreparedStatement myStatement = conn.prepareStatement(query1);
            myStatement.setInt(1,obj.getTeacherId());
            myStatement.setString(2,obj.getName());
            myStatement.setInt(3,obj.getMaxStudents());
            myStatement.setInt(4,obj.getCredits());
            myStatement.executeUpdate();
            conn.close();
            return obj;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Course> getAll(){
        List<Course> toReturn = new ArrayList<Course>();
        String url = "jdbc:mysql://localhost:3306/university";
        String user = "doubleg";
        String password = "1234";
        try {
            Connection conn = DriverManager.getConnection(url,user,password);
            Statement myStatement = conn.createStatement();
            ResultSet resultSet = myStatement.executeQuery("SELECT * FROM courses");
            while(resultSet.next()){
                toReturn.add(new Course(resultSet.getInt("courseId"),resultSet.getInt("teacherId"),resultSet.getString("name"),resultSet.getInt("maxStudents"),resultSet.getInt("credits")));
            }
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    public Course update(Course obj){
        String url = "jdbc:mysql://localhost:3306/university";
        String user = "doubleg";
        String password = "1234";
        try {
            Connection conn = DriverManager.getConnection(url,user,password);
            String query1 = "update courses set teacherId=? , name=? ,maxStudents=?,credits=? where courseId=?";
            PreparedStatement myStatement = conn.prepareStatement(query1);
            myStatement.setInt(1,obj.getTeacherId());
            myStatement.setString(2,obj.getName());
            myStatement.setInt(3,obj.getMaxStudents());
            myStatement.setInt(4,obj.getCredits());
            myStatement.setInt(5,obj.getCourseId());
            myStatement.executeUpdate();
            conn.close();
            return obj;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Course obj){
        String url = "jdbc:mysql://localhost:3306/university";
        String user = "doubleg";
        String password = "1234";
        try {
            Connection conn = DriverManager.getConnection(url,user,password);

            String query1 = "delete from courses where courseId = ?";
            PreparedStatement myStatement = conn.prepareStatement(query1);
            myStatement.setInt(1,obj.getCourseId());
            myStatement.execute();
            conn.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
