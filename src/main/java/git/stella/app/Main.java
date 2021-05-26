package git.stella.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        showLogin();
    }

    public void showLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("login.fxml"));
        Parent root = loader.load();
        LoginController loginController = loader.getController();
        loginController.init();
        loginController.setMain(this);
        window.setResizable(false);
        window.setTitle("Stella");
        window.setScene(new Scene(root, 700, 400));
        window.show();
    }

    public void showHome(String username, String password) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("home.fxml"));
        Parent root = loader.load();
        HomeController homeController = loader.getController();
        homeController.setMain(this);
        homeController.init(username, password);
        window.setTitle("Stella");
        window.setScene(new Scene(root, 700, 400));
        window.show();
    }
}
