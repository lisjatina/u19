package jtm.activity14;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import jtm.TestUtils;

import static jtm.testsuite.AllTests.user;
import static jtm.testsuite.AllTests.password;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DatabaseUnitTest1 {

    static StudentManager manager;
    static String name1 = "Student";
    static String name2 = "Student";
    static String surname1 = "1";
    static String surname2 = "2";
    static int id1 = 0;
    static int id2 = 300;
    static int wrongId = -1;
    static Student student1;
    static Student student2;
    static Connection mockedConn;

    @Test
    public void test01StudentManager() {
        manager = new StudentManager();
    }

    @Test
    public void test02InsertStudent() {
        student1 = new Student();
        student1.setFirstName(name1+surname1);
        student1.setLastName(name1+surname1);
        assertEquals("Wrong values for student fields " + name1+surname1 + " " +
                name1+surname1, student1.toString());
       // student2 = new Student(id2, name2+surname2, name2+surname2);
        manager.insertStudent(name1+surname1, name1+surname1);
        id1 = manager.findStudent(name1+surname1, name1+surname1).get(0).getId();
        student1.setId(id1);
        assertFalse("Student with null fields was created ", manager.insertStudent(null,null));
        }

    @Test
    public void test03InsertStudentStudent() {
        student2 = new Student(id2, name2+surname2, name2+surname2);
        assertTrue("Student " +student2 + " was not created", manager.insertStudent(student2));
        assertFalse("Duplicate " +student2 + "was created", manager.insertStudent(student2));
        }

    @Test
    public void test04FindStudentByID() {
        assertEquals(student2.toString(), manager.findStudent(id2).toString());
        assertEquals("Student with non existing ID ",
                new Student(0, null, null).toString(),
                manager.findStudent(wrongId).toString());
       }

    @Test
    public void test05FindStudentStringString() {
        manager.findStudent(name1,name1);
        manager.findStudent(name1+surname1, name1+surname1);
     }

    @Test
    public void test06UpdateStudent() {
        student2.setFirstName(name2);
        student2.setLastName(name2);
        manager.updateStudent(student2);
        manager.updateStudent(new Student(wrongId, "", ""));
          }

    @Test
    public void test07DeleteStudent() {
        manager.deleteStudent(id1);
        manager.deleteStudent(id2);
        manager.deleteStudent(wrongId);
            }

    @Test
    public void test08CloseConnection() {
        manager.closeConnecion();
    }

    @Test
    public void test09Exceptions() {
        try {
            // Create mocked connection which will throw exceptions
            mockedConn = spy(DriverManager.getConnection("jdbc:mysql://localhost/?serverTimezone=UTC", user, password));
            doThrow(new SQLException("Mocked commit() exception")).when(mockedConn).commit();
            doThrow(new SQLException("Mocked setAutoCommit() exception")).when(mockedConn).prepareStatement(anyString());

            manager.conn = mockedConn;
            manager.findStudent(id1);
            manager.findStudent(name1,name1);

            assertFalse("insertStudent error ", manager.insertStudent(name1, name1));
            assertFalse("insertStudent error ", manager.insertStudent(student1));
            assertFalse("deleteStudentById error ", manager.deleteStudent(id1));
            assertFalse("updateStudent error ", manager.updateStudent(student1));

            doThrow(new SQLException("Mocked setAutoCommit() exception")).when(mockedConn).setAutoCommit(false);
            doThrow(new SQLException("Mocked close() exception")).when(mockedConn).close();

            manager = new StudentManager();
        } catch (Throwable t) {
            TestUtils.handleErrorAndFail(t);
        }
    }

    public static void main(String[] args) {
        DatabaseUnitTest dbUnitTest = new DatabaseUnitTest();
        dbUnitTest.test();
    }
}