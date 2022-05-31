package ro.catalog.dao.repositories;

import ro.catalog.dao.configuration.DatabaseConfiguration;
import ro.catalog.entitati.Materie;
import ro.catalog.entitati.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public List<Materie> select() {
        String selectSql = "SELECT * FROM materii";

        Connection connection = DatabaseConfiguration.getConnection();

        List<Materie> materii = new ArrayList<Materie>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);
            while (resultSet.next()) {
                materii.add(new Materie(resultSet.getString(2), resultSet.getInt(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materii;
    }

    // UPDATE
    public void update(Integer credite, String nume) {
        String updateSql = "UPDATE materii SET nrCredite=? WHERE denumire=?";

        Connection connection = DatabaseConfiguration.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setInt(1, credite);
            preparedStatement.setString(2, nume);
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

    public void deleteAll() {
        String deleteSql = "DELETE FROM materii";

        Connection connection = DatabaseConfiguration.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
