package br.eng.rafaelpsouza;

import com.google.common.collect.Lists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PersonDao {

    private Connection connection;

    public PersonDao(Connection connection) {
        this.connection = connection;
    }

    public List<Person> listAll() throws SQLException {
        ResultSet resultSet = connection.prepareStatement("SELECT * FROM person").executeQuery();
        List<Person> people = Lists.newArrayList();

        while (resultSet.next()) {
            people.add(new Person(resultSet.getLong("id"), resultSet.getString("name"),
                    resultSet.getInt("age")));
        }

        return people;
    }

    public void save(Person person) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO person (name, age) values (?,?)");
        preparedStatement.setString(1, person.getName());
        preparedStatement.setInt(2, person.getAge());
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void delete(Person person) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM person WHERE id = ?");
        preparedStatement.setLong(1, person.getId());
        preparedStatement.execute();
        preparedStatement.close();
    }


}
