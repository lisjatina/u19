package jtm.activity13;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import jtm.testsuite.AllTests;

import static jtm.testsuite.AllTests.user;
import static jtm.testsuite.AllTests.password;
import static jtm.testsuite.AllTests.database;

import static jtm.testsuite.AllTests.*;

public class TeacherManager {
	protected Connection conn;


	public TeacherManager() {
		/* #1 When new TeacherManager is created, create connection to the database server:
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
	 * Returns a Teacher instance represented by the specified ID.
	 * 
	 * @param id the ID of teacher
	 * @return a Teacher object
	 */
	public Teacher findTeacher(int id) {
		// TODO #2 Write an sql statement that searches teacher by ID.
		// If teacher is not found return Teacher object with zero or null in
		// its fields!
		// Hint: Because default database is not set in connection,
		// use full notation for table "databaseXX.Teacher"
		try {
			String sql = "SELECT * FROM " + database + ".Teacher where id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet results = statement.executeQuery();
			if (results.next()){
				return new Teacher(results.getInt("id"), results.getString(2), results.getString(3));
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return new Teacher();
	}

	/**
	 * Returns a list of Teacher object that contain the specified first name and
	 * last name. This will return an empty List of no match is found.
	 * 
	 * @param firstName the first name of teacher.
	 * @param lastName  the last name of teacher.
	 * @return a list of Teacher object.
	 */
	public List<Teacher> findTeacher(String firstName, String lastName) {
		// #3 Write an sql statement that searches teacher by first and
		// last name and returns results as List<Teacher>.
		// Note that search results of partial match
		// in form ...like '%value%'... should be returned
		// Note, that if nothing is found return empty list!
		List <Teacher> teachers = new ArrayList<>();
		try {
			String sql = "SELECT * FROM " + database + ".Teacher where firstName like ? and lastName like ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, "%" + firstName + "%");
			statement.setString(2, "%" + lastName + "%");
			ResultSet results = statement.executeQuery();
			while (results.next()){
				teachers.add(new Teacher(results.getInt("id"), results.getString(2), results.getString(3)));
			}

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return teachers;
	}

	/**
	 * Insert an new teacher (first name and last name) into the repository.
	 * 
	 * @param firstName the first name of teacher
	 * @param lastName  the last name of teacher
	 * @return true if success, else false.
	 */

	public boolean insertTeacher(String firstName, String lastName) {
		// #4 Write an sql statement that inserts teacher in database.

		try {
			String sql = "INSERT INTO " + database + ".Teacher (firstName, lastName) VALUES (?, ?)";
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
	 * Insert teacher object into database
	 * 
	 * @param teacher
	 * @return true on success, false on error (e.g. non-unique id)
	 */
	public boolean insertTeacher(Teacher teacher) {
		//#5 Write an sql statement that inserts teacher in database.
		try {
			String sql = "INSERT INTO " + database + ".Teacher (id, firstName, lastName) VALUES (?, ?, ?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, teacher.getId());
			statement.setString(2, teacher.getFirstName());;
			statement.setString(3, teacher.getLastName());
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
	 * Updates an existing Teacher in the repository with the values represented by
	 * the Teacher object.
	 * 
	 * @param teacher a Teacher object, which contain information for updating.
	 * @return true if row was updated.
	 */
	public boolean updateTeacher(Teacher teacher) {
		// #6 Write an sql statement that updates teacher information.
		try {
			String sql = "UPDATE " + database + ".Teacher SET firstName = ?, lastName = ? WHERE (id = ?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, teacher.getFirstName());
			statement.setString(2, teacher.getLastName());
			statement.setInt(3, teacher.getId());
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
	 * Delete an existing Teacher in the repository with the values represented by
	 * the ID.
	 * 
	 * @param id the ID of teacher.
	 * @return true if row was deleted.
	 */
	public boolean deleteTeacher(int id) {
		// TODO #7 Write an sql statement that deletes teacher from database.
		try {
			String sql = "DELETE FROM " + database + ".Teacher WHERE (id = ?)";
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
		try {
			conn.close();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

}
