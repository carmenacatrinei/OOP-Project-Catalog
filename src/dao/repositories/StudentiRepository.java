package dao.repositories;

import dao.configuration.DatabaseConfiguration;

import java.sql.*;

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
    public void insert(String nume, String prenume, int nrMatricol, int grupa) {
        String insertSql = "INSERT INTO studenti(nume, prenume, nrMatricol, grupa) VALUES(?, ?, ?, ?)";

        Connection connection = DatabaseConfiguration.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1, nume);
            preparedStatement.setString(2, prenume);
            preparedStatement.setInt(3, nrMatricol);
            preparedStatement.setInt(4, grupa);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //READ
    public void select() {
        String selectSql = "SELECT * FROM studenti";

        Connection connection = DatabaseConfiguration.getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);
            System.out.println("Studenti: ");
            while (resultSet.next()) {
                System.out.println("\tNume: " + resultSet.getString(2));
                System.out.println("\tPrenume: " + resultSet.getString(3));
                System.out.println("\tNr. matricol: " + resultSet.getInt(4));
                System.out.println("\tGrupa: " + resultSet.getInt(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UPDATE
    public void update(int id, int grupa) {
        String updateSql = "UPDATE studenti SET grupa=? WHERE id=?";

        Connection connection = DatabaseConfiguration.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setInt(1, grupa);
            preparedStatement.setInt(2, id);
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
}


