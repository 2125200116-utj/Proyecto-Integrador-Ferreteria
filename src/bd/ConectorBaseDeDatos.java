package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectorBaseDeDatos {

    private static final String DRIVER_JDBC = "com.mysql.cj.jdbc.Driver";
    private static final String URL_BD = "jdbc:mysql://localhost:3306/ferreteria";
    private static final String USUARIO = "root";
    private static final String CLAVE = "";

    public static Connection conectar() throws SQLException {
        Connection conexion = DriverManager.getConnection(URL_BD, USUARIO, CLAVE);
        return conexion;
    }
   
    public static void desconectar(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try (Connection conexion = conectar()) {
            // Código para usar la conexión: consultas, actualizaciones, etc.
            System.out.println("Conexión exitosa. Base de datos: " + conexion.getMetaData().getDatabaseProductName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
