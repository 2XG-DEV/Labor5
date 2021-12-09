package repos;


import models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class StudentJDBCRepo implements ICrudRepo<Student>{
    public Student create(Student obj){
        String url = "jdbc:mysql://localhost:3306/university";
        String user = "doubleg";
        String password = "1234";
        try {
            Connection conn = DriverManager.getConnection(url,user,password);
            String query1 = "insert into students (firstName, lastName) values (?,?)";
            PreparedStatement myStatement = conn.prepareStatement(query1);
            myStatement.setString(1,obj.getFirstName());
            myStatement.setString(2,obj.getLastName());
            myStatement.executeUpdate();
            conn.close();
            return obj;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Student> getAll(){
        List<Student> toReturn = new ArrayList<Student>();
        String url = "jdbc:mysql://localhost:3306/university";
        String user = "doubleg";
        String password = "1234";
        try {
            Connection conn = DriverManager.getConnection(url,user,password);
            Statement myStatement = conn.createStatement();
            ResultSet resultSet = myStatement.executeQuery("SELECT * FROM students");
            while(resultSet.next()){
                toReturn.add(new Student(resultSet.getInt("studentId"),resultSet.getString("firstName"),resultSet.getString("lastName")));
            }
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    public Student update(Student obj){
        String url = "jdbc:mysql://localhost:3306/university";
        String user = "doubleg";
        String password = "1234";
        try {
            Connection conn = DriverManager.getConnection(url,user,password);
            String query1 = "update students set firstName=? , lastName=?  where studentId=?";
            PreparedStatement myStatement = conn.prepareStatement(query1);
            myStatement.setString(1,obj.getFirstName());
            myStatement.setString(2,obj.getLastName());
            myStatement.setInt(3,obj.getStudentId());
            myStatement.executeUpdate();
            conn.close();
            return obj;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Student obj){
        String url = "jdbc:mysql://localhost:3306/university";
        String user = "doubleg";
        String password = "1234";
        try {
            Connection conn = DriverManager.getConnection(url,user,password);

            String query1 = "delete from students where studentId = ?";
            PreparedStatement myStatement = conn.prepareStatement(query1);
            myStatement.setInt(1,obj.getStudentId());
            myStatement.execute();
            conn.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
