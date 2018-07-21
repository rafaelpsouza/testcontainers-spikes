package br.eng.rafaelpsouza;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class PersonDaoTest {

    static Connection connection;
    PersonDao personDao;

    @BeforeClass
    public static void setupConnection() throws SQLException {
        connection = DriverManager
                .getConnection("jdbc:tc:postgresql:9.5://hostname/testdatabase?TC_INITSCRIPT=test-init-script.sql");

    }

    @Before
    public void setup() {
        personDao = new PersonDao(connection);
    }

    @Test
    public void listAllTest() throws SQLException {
        assertEquals(2, personDao.listAll().size());
    }

    @Test
    public void saveTest() throws SQLException {
        personDao.save(new Person("Messi", 30));
        assertEquals(3, personDao.listAll().size());
        personDao.delete(new Person(1L, "Pelé", 18));
        assertEquals(2, personDao.listAll().size());
    }

}