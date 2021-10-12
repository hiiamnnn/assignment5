/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentdatabase;
import java.sql.SQLException;
import java.util.ArrayList;
import utilities.DatabaseDriver;
import utilities.DatabaseHandler;
/**
 *
 * @author nkotchs
 */
public class StudentDatabase {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String driver = "org.apache.derby.jdbc.ClientDriver";
        String url = "jdbc:derby://localhost:1527/Student";
        String user = "app";
        String passwd = "app";
        DatabaseDriver dbDriver = new DatabaseDriver(driver, url, user, passwd);
        DatabaseHandler dbHandler = new DatabaseHandler(dbDriver);
        //insert
        Student std1 = new Student(1, "Kotchakorn", 3.70);
        Student std2 = new Student(2, "Marry", 4.00);
        Student std3 = new Student(3, "Jones", 4.00);
        StudentTable.insertStudent(dbHandler, std1);
        StudentTable.insertStudent(dbHandler, std2);
        StudentTable.insertStudent(dbHandler, std3);
        //update
        Student std = StudentTable.findStudentById(dbHandler, 3);
        std.setName("Jack");
        std.setGpa(3.89);
        StudentTable.updateStudent(dbHandler, std);
        ArrayList<Student> studentList = StudentTable.findAllStudent(dbHandler);
        if (studentList != null) {
            printAllStudent(studentList);
        }
        //delete
        StudentTable.removeStudent(dbHandler, std);
        studentList = StudentTable.findAllStudent(dbHandler);
        if (studentList != null) {
            printAllStudent(studentList);
        }
        //select student name is kotchakorn
        studentList = StudentTable.findStudentByName(dbHandler, "Kotchakorn");
        if (studentList != null) {
            printAllStudent(studentList);
        }
        dbHandler.closeConnection();
    }

    public static void printAllStudent(ArrayList<Student> studentList) {
        for(int i=0; i< studentList.size(); i++){
            System.out.print(studentList.get(i).getId()+ " ");
            System.out.print(studentList.get(i).getName()+ " ");
            System.out.println(studentList.get(i).getGpa()+ " ");
        }
    }
    
    
}
