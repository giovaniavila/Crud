package resource.provajuliana;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection(){
        try{
            return DriverManager.getConnection("jdbc:mysql://localhost/juliana","root","");
        }
        catch(SQLException erro){
            throw new RuntimeException(erro);
        }
    }
}
