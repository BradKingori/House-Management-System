package housemanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Central database configuration for the House Management System.
 * <p>
 * All screens use {@link #connect()} so you only need to change the connection
 * details in one place. Update {@link #USER} and {@link #PASSWORD} to match your
 * local MySQL installation.
 */
public final class Database {

    /** Host name of the MySQL server (usually "localhost"). */
    public static final String HOST = "localhost";
    /** Port MySQL is listening on (3306 is the default). */
    public static final int PORT = 3306;
    /** Name of the database (created from sql.txt). */
    public static final String DATABASE = "apartmentmanager";
    /** MySQL user name. */
    public static final String USER = "root";
    /** MySQL password. Set this if your "root" account has a password. */
    public static final String PASSWORD = "";

    /** JDBC connection URL. */
    public static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE
            + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    private Database() {
        // Utility class: not meant to be instantiated.
    }

    /**
     * Opens a new connection to the configured MySQL database.
     *
     * @return a live JDBC {@link Connection}
     * @throws SQLException if the connection cannot be established
     */
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
