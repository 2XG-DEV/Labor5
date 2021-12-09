package repos;

import models.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherJDBCRepo implements ICrudRepo<Teacher>{

    public Teacher create(Teacher obj){
        String url = "jdbc:mysql://localhost:3306/university";
        String user = "doubleg";
        String password = "1234";
        try {
            Connection conn = DriverManager.getConnection(url,user,password);
            String query1 = "insert into teachers (firstName, lastName) values (?,?)";
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

    public List<Teacher> getAll(){
        List<Teacher> toReturn = new ArrayList<Teacher>();
        String url = "jdbc:mysql://localhost:3306/university";
        String user = "doubleg";
        String password = "1234";
        try {
            Connection conn = DriverManager.getConnection(url,user,password);
            Statement myStatement = conn.createStatement();
            ResultSet resultSet = myStatement.executeQuery("SELECT * FROM teachers");
            while(resultSet.next()){
                toReturn.add(new Teacher(resultSet.getInt("teacherId"),resultSet.getString("firstName"),resultSet.getString("lastName")));
            }
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    public Teacher update(Teacher obj){
        String url = "jdbc:mysql://localhost:3306/university";
        String user = "doubleg";
        String password = "1234";
        try {
            Connection conn = DriverManager.getConnection(url,user,password);

            String query1 = "update teachers set firstName=? , lastName=?  where teacherId=?";
            PreparedStatement myStatement = conn.prepareStatement(query1);
            myStatement.setString(1,obj.getFirstName());
            myStatement.setString(2,obj.getLastName());
            myStatement.setInt(3,obj.getTeacherId());
            myStatement.executeUpdate();
            conn.close();
            return obj;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Teacher obj){
        String url = "jdbc:mysql://localhost:3306/university";
        String user = "doubleg";
        String password = "1234";
        try {
            Connection conn = DriverManager.getConnection(url,user,password);

            String query1 = "delete from teachers where teacherId = ?";
            PreparedStatement myStatement = conn.prepareStatement(query1);
            myStatement.setInt(1,obj.getTeacherId());
            myStatement.execute();
            conn.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
