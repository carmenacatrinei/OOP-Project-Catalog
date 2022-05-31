package ro.catalog.dao.repositories;

import ro.catalog.dao.configuration.DatabaseConfiguration;
import ro.catalog.entitati.Profesor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfesoriRepository {

    private static ProfesoriRepository profesoriRepository;

    private ProfesoriRepository() {
    }

    public static ProfesoriRepository getProfesoriRepository() {
        if (profesoriRepository == null) {
            profesoriRepository = new ProfesoriRepository();
        }
        return profesoriRepository;
    }

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS profesori" +
                "(id int PRIMARY KEY AUTO_INCREMENT, nume varchar(30), prenume varchar(30))";

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
    public void insert(String nume, String prenume) {
        String insertSql = "INSERT INTO profesori(nume, prenume) VALUES(?, ?)";

        Connection connection = DatabaseConfiguration.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1, nume);
            preparedStatement.setString(2, prenume);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //READ
    public List<Profesor> select() {
        String selectSql = "SELECT * FROM profesori";

        Connection connection = DatabaseConfiguration.getConnection();

        List<Profesor> profesori = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);
            while (resultSet.next()) {
                profesori.add(new Profesor(resultSet.getString(2), resultSet.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return profesori;
    }

    // UPDATE
    public void update(int id, String nume) {
        String updateSql = "UPDATE profesori SET nume=? WHERE id=?";

        Connection connection = DatabaseConfiguration.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setString(1, nume);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void delete(String nume) {
        String deleteSql = "DELETE FROM profesori WHERE nume=?";

        Connection connection = DatabaseConfiguration.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.setString(1, nume);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAll() {
        String deleteSql = "DELETE FROM profesori";

        Connection connection = DatabaseConfiguration.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
