package git.stella.app;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    private Main main;

    @FXML
    public JFXButton loginButton;

    @FXML
    public TextField userField;

    @FXML
    public PasswordField passwordField;

    public void onClick() throws Exception {
        System.out.println(userField.getText() + " " + passwordField.getText());
        main.showHome(userField.getText(), passwordField.getText());
    }

    public void init() {
        userField.textProperty().addListener(((observable, oldValue, newValue) -> {
            if (userField.getText().length() == 0 || passwordField.getText().length() < 4) {
                loginButton.setDisable(true);
                loginButton.setDefaultButton(true);
            } else {
                loginButton.setDisable(false);
                loginButton.setDefaultButton(false);
            }
        }));

        passwordField.textProperty().addListener(((observable, oldValue, newValue) -> {
            if (passwordField.getText().length() < 4 || userField.getText().length() == 0) {
                loginButton.setDisable(true);
            } else {
                loginButton.setDisable(false);
            }
        }));
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
