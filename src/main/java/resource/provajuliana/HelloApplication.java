package resource.provajuliana;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


public class HelloApplication extends Application {
    private static Stage stage;
    private static Scene main;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        Parent fxmlMain = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        main = new Scene(fxmlMain);

        primaryStage.setScene(main);
        primaryStage.show();
    }

    public static void main(String[] args)throws SQLException {
        Connection connection = new ConnectionFactory().getConnection();
        System.out.println("Conexão aberta");
        connection.close();
        launch(args);
    }
}