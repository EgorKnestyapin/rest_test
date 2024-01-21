package de.aittr.g_31_2_rest.repositories;

import de.aittr.g_31_2_rest.domain.Parrot;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ParrotRepository implements CrudRepository<Parrot> {

    private final String DB_DRIVER_PATH = "com.mysql.cj.jdbc.Driver";
    private final String DB_ADDRESS = "jdbc:mysql://localhost:3306/";
    private final String DB_NAME = "31_2_parrots";
    private final String DB_USERNAME = "root";
    private final String DB_PASSWORD = "rootroot";

    private final String ID = "id";
    private final String COLOR = "color";
    private final String WEIGHT = "weight";

    // Метод, который подключается к БД и возвращает объект подключения
    private Connection getConnection() {
        try {
            Class.forName(DB_DRIVER_PATH);
            // jdbc:mysql://localhost:3306/31_2_parrots?user=root&password=77777
            String dbUrl = String.format("%s%s?user=%s&password=%s",
                    DB_ADDRESS, DB_NAME, DB_USERNAME, DB_PASSWORD);
            return DriverManager.getConnection(dbUrl);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Parrot save(Parrot parrot) {
        String color = parrot.getColor();
        double weight = parrot.getWeight();
        try (Connection connection = getConnection()) {
            String query = String.format("INSERT INTO `31_2_parrots`.`parrot` (`color`, `weight`) VALUES ('%s', '%.0f');",
                    color, weight);
            Statement statement = connection.createStatement();
            boolean rs = statement.execute(query, Statement.RETURN_GENERATED_KEYS);

            ResultSet resultSet = statement.getGeneratedKeys();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                parrot = new Parrot(id, color, weight);
            }

            return parrot;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Parrot> getAll() {
        try (Connection connection = getConnection()) {
            String query = "select * from parrot;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Parrot> parrots = new ArrayList<>();

            while (resultSet.next()) {
//                int id = resultSet.getInt(1); вариант 1 - передать номер колонки
                int id = resultSet.getInt(ID); // вариант 2 - передать название колонки
                String color = resultSet.getString(COLOR);
                double weight = resultSet.getDouble(WEIGHT);
                Parrot parrot = new Parrot(id, color, weight);
                parrots.add(parrot);
            }

            return parrots;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Parrot getById(int id) {
        try (Connection connection = getConnection()) {

            String query = String.format("select * from parrot where id = %d;", id);
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            Parrot parrot = null;

            while (resultSet.next()) {
                String color = resultSet.getString(COLOR);
                double weight = resultSet.getDouble(WEIGHT);
                parrot = new Parrot(id, color, weight);
            }

            return parrot;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Parrot parrot) {
        int id = parrot.getId();
        String color = parrot.getColor();
        double weight = parrot.getWeight();
        try (Connection connection = getConnection()) {
            String query = String.format("UPDATE `31_2_parrots`.`parrot` SET `color` = '%s', `weight` = '%.0f' WHERE (`id` = '%d');",
                    color, weight, id);
            connection.createStatement().executeQuery(query);
            System.out.println("Successful update!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {
        try (Connection connection = getConnection()) {
            String query = String.format("DELETE FROM `31_2_parrots`.`parrot` WHERE (`id` = '%d');", id);
            connection.createStatement().execute(query);
            System.out.println("Successful deletion!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}