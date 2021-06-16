package jtm.activity14;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import static jtm.testsuite.AllTests.user;
import static jtm.testsuite.AllTests.password;
import static jtm.testsuite.AllTests.database;

public class StudentManager {
	protected Connection conn;


	public StudentManager() {
		/*-
		 * TODO #1 When new StudentManager is created, create connection to the database server:
		 * - url = "jdbc:mysql://localhost/?autoReconnect=true&serverTimezone=UTC&characterEncoding=utf8"
		 * - user = AllTests.user
		 * - pass = AllTests.password
		 * Notes:
		 * 1. Use database name imported from jtm.testsuite.AllTests.database
		 * 2. Do not pass database name into url, because some statements in tests need to be executed
		 * server-wise, not just database-wise.
		 * 3. Set AutoCommit to false and use conn.commit() where necessary in other methods
		 */
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/?serverTimezone=UTC", user, password);
			conn.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns a Student instance represented by the specified ID.
	 * 
	 * @param id the ID of student
	 * @return a Student object
	 */
	public Student findStudent(int id) {
		// #2 Write an sql statement that searches student by ID.
		// If student is not found return Student object with zero or null in
		// its fields!
		// Hint: Because default database is not set in connection,
		// use full notation for table "databaseXX.Student"
		try {
			String sql = "SELECT * FROM " + database + ".Student where id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet results = statement.executeQuery();
			if (results.next()){
				return new Student(results.getInt("id"), results.getString(2), results.getString(3));
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return new Student();
	}

	/**
	 * Returns a list of Student object that contain the specified first name and
	 * last name. This will return an empty List of no match is found.
	 * 
	 * @param firstName the first name of student.
	 * @param lastName  the last name of student.
	 * @return a list of Student object.
	 */
	public List<Student> findStudent(String firstName, String lastName) {
		// #3 Write an sql statement that searches student by first and
		// last name and returns results as ArrayList<Student>.
		// Note that search results of partial match
		// in form ...like '%value%'... should be returned
		// Note, that if nothing is found return empty list!
		List <Student> students = new ArrayList<>();
		try {
			String sql = "SELECT * FROM " + database + ".Student where firstName like ? and lastName like ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, "%" + firstName + "%");
			statement.setString(2, "%" + lastName + "%");
			ResultSet results = statement.executeQuery();
			while (results.next()){
				students.add(new Student(results.getInt("id"), results.getString(2), results.getString(3)));
			}

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return students;

	}

	/**
	 * Insert an new student (first name and last name) into the repository.
	 * 
	 * @param firstName the first name of student
	 * @param lastName  the last name of student
	 * @return true if success, else false.
	 */

	public boolean insertStudent(String firstName, String lastName) {
		// #4 Write an sql statement that inserts student in database.
		try {
			String sql = "INSERT INTO " + database + ".Student (firstName, lastName) VALUES (?, ?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			int updatedRows = statement.executeUpdate();
			conn.commit();
			if(updatedRows == 1)
				return true;
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return false;
	}

	/**
	 * Insert student object into database
	 * 
	 * @param student
	 * @return true on success, false on error (e.g. non-unique id)
	 */
	public boolean insertStudent(Student student) {
		// #5 Write an sql statement that inserts student in database.
		try {
			String sql = "INSERT INTO " + database + ".Student (id, firstName, lastName) VALUES (?, ?, ?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, student.getId());
			statement.setString(2, student.getFirstName());;
			statement.setString(3, student.getLastName());
			int updatedRows = statement.executeUpdate();
			conn.commit();
			if(updatedRows == 1)
				return true;
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return false;
	}

	/**
	 * Updates an existing Student in the repository with the values represented by
	 * the Student object.
	 * 
	 * @param student a Student object, which contain information for updating.
	 * @return true if row was updated.
	 */
	public boolean updateStudent(Student student) {
		boolean status = false;
		// #6 Write an sql statement that updates student information.
		try {
			String sql = "UPDATE " + database + ".Student SET firstName = ?, lastName = ? WHERE (id = ?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, student.getFirstName());
			statement.setString(2, student.getLastName());
			statement.setInt(3, student.getId());
			int updatedRows = statement.executeUpdate();
			conn.commit();
			if(updatedRows == 1)
				return true;
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return false;
	}

	/**
	 * Delete an existing Student in the repository with the values represented by
	 * the ID.
	 * 
	 * @param id the ID of student.
	 * @return true if row was deleted.
	 */
	public boolean deleteStudent(int id) {
		// #7 Write an sql statement that deletes student from database.
		try {
			String sql = "DELETE FROM " + database + ".Student WHERE (id = ?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			int updatedRows = statement.executeUpdate();
			conn.commit();
			if(updatedRows == 1)
				return true;
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return false;
	}

	public void closeConnecion() {
		// Close connection to the database server
		try {
			conn.close();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

}
