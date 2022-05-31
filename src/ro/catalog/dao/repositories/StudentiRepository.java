package ro.catalog.dao.repositories;

import ro.catalog.dao.configuration.DatabaseConfiguration;
import ro.catalog.entitati.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentiRepository {
    private static StudentiRepository studentiRepository;

    private StudentiRepository() {
    }

    public static StudentiRepository getStudentiRepository() {
        if (studentiRepository == null) {
            studentiRepository = new StudentiRepository();
        }
        return studentiRepository;
    }

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS studenti" +
                "(id int PRIMARY KEY AUTO_INCREMENT, nume varchar(30), prenume varchar(30), nrMatricol varchar(30), grupa int)";

        Connection connection = DatabaseConfiguration.getConnection();

        try {
            Statement statement = connection.createStatement();
            statement.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //CREATE - INSERT, READ - SELECT, UPDATE, DELETE

    //INSERT
    public void insert(String nume, String prenume, String nrMatricol, int grupa) {
        String insertSql = "INSERT INTO studenti(nume, prenume, nrMatricol, grupa) VALUES(?, ?, ?, ?)";

        Connection connection = DatabaseConfiguration.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1, nume);
            preparedStatement.setString(2, prenume);
            preparedStatement.setString(3, nrMatricol);
            preparedStatement.setInt(4, grupa);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //READ
    public List<Student> select() {
        String selectSql = "SELECT * FROM studenti";

        Connection connection = DatabaseConfiguration.getConnection();

        List<Student> studenti = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);
            while (resultSet.next()) {
                studenti.add(new Student(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studenti;
    }

    // UPDATE
    public void update(String nume, String nrMatricol) {
        String updateSql = "UPDATE studenti SET nrMatricol=? WHERE nume=?";

        Connection connection = DatabaseConfiguration.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setString(1, nrMatricol);
            preparedStatement.setString(2, nume);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void delete(int id) {
        String deleteSql = "DELETE FROM studenti WHERE id=?";

        Connection connection = DatabaseConfiguration.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAll() {
        String deleteSql = "DELETE FROM studenti";

        Connection connection = DatabaseConfiguration.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


