package repos;

import models.Course;
import models.Enrollment;
import models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentsJDBCRepo implements ICrudRepo<Enrollment> {

    public Enrollment create(Enrollment obj){
        String url = "jdbc:mysql://localhost:3306/university";
        String user = "doubleg";
        String password = "1234";
        try {
            Connection conn = DriverManager.getConnection(url,user,password);
            Statement myStatement = conn.createStatement();
            myStatement.executeUpdate(String.format("insert into enrolls(courseId,studentId) values (%d,%d)",obj.getStudentId(),obj.getCourseId()) );
            conn.close();
            return obj;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Enrollment update(Enrollment obj){
        String url = "jdbc:mysql://localhost:3306/university";
        String user = "doubleg";
        String password = "1234";
        try {
            Connection conn = DriverManager.getConnection(url,user,password);
            String query1 = "update enrolls set studentId=? , courseID=?  where studentId=? AND courseID=?";
            PreparedStatement myStatement = conn.prepareStatement(query1);
            myStatement.setInt(1,obj.getStudentId());
            myStatement.setInt(2,obj.getCourseId());
            myStatement.setInt(3,obj.getStudentId());
            myStatement.setInt(4,obj.getCourseId());
            myStatement.executeUpdate();
            conn.close();
            return obj;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Enrollment> getAll(){
        List<Enrollment> toReturn = new ArrayList<Enrollment>();
        String url = "jdbc:mysql://localhost:3306/university";
        String user = "doubleg";
        String password = "1234";
        try {
            Connection conn = DriverManager.getConnection(url,user,password);
            Statement myStatement = conn.createStatement();
            ResultSet resultSet = myStatement.executeQuery("SELECT * FROM enrolls");
            while(resultSet.next()){
                toReturn.add(new Enrollment(resultSet.getInt("studentId"),resultSet.getInt("courseID")));
            }
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    public void delete(Enrollment obj){
        String url = "jdbc:mysql://localhost:3306/university";
        String user = "doubleg";
        String password = "1234";
        try {
            Connection conn = DriverManager.getConnection(url,user,password);

            String query1 = "delete from enrolls where courseID = ? AND studentId = ?";
            PreparedStatement myStatement = conn.prepareStatement(query1);
            myStatement.setInt(1,obj.getCourseId());
            myStatement.setInt(2,obj.getStudentId());
            myStatement.execute();
            conn.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
