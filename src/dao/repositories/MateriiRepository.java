package dao.repositories;

import dao.configuration.DatabaseConfiguration;

import java.sql.*;

public class MateriiRepository {
    private static MateriiRepository materiiRepository;

    private MateriiRepository() {
    }

    public static MateriiRepository getMateriiRepository() {
        if (materiiRepository == null) {
            materiiRepository = new MateriiRepository();
        }
        return materiiRepository;
    }

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS materii" +
                "(id int PRIMARY KEY AUTO_INCREMENT, denumire varchar(30), nrCredite varchar(30))";

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
    public void insert(String denumire, int nrCredite) {
        String insertSql = "INSERT INTO materii(denumire, nrCredite) VALUES(?, ?)";

        Connection connection = DatabaseConfiguration.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1, denumire);
            preparedStatement.setInt(2, nrCredite);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //READ
    public void select() {
        String selectSql = "SELECT * FROM materii";

        Connection connection = DatabaseConfiguration.getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);
            System.out.println("Materii: ");
            while (resultSet.next()) {
                System.out.println("\tDenumire: " + resultSet.getString(2));
                System.out.println("\tNr. credite: " + resultSet.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UPDATE
    public void update(int id, int nrCredite) {
        String updateSql = "UPDATE studenti SET credite=? WHERE id=?";

        Connection connection = DatabaseConfiguration.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setInt(1, nrCredite);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void delete(int id) {
        String deleteSql = "DELETE FROM materii WHERE id=?";

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
