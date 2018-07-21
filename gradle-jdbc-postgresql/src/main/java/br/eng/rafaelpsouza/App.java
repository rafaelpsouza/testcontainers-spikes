package br.eng.rafaelpsouza;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/localdb?user=postgres&password=mysecretpassword");

        connection.prepareStatement("CREATE TABLE IF NOT EXISTS public.person (id serial NOT NULL, " +
                "name character varying(60), age integer, " +
                "CONSTRAINT person_pkey PRIMARY KEY (id))").executeUpdate();

        PersonDao personDao = new PersonDao(connection);

        personDao.save(new Person("Messi", 30));
        personDao.save(new Person("Ronaldo", 32));
        personDao.save(new Person("Neymar", 26));

        personDao.listAll().stream().forEach(person -> {
            System.out.println(person);
        });
    }
}
