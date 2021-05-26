package git.stella.app;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXSlider;
import git.stella.model.generator.Generator;
import git.stella.model.key.KeyContainer;
import git.stella.model.user.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class HomeController {

    private Main main;
    private String username;
    private String password;
    private User user;
    private Generator generator;
    private KeyContainer keyContainer;

    @FXML
    public Label welcomeLabel;

    @FXML
    public JFXButton generateButton, logoutButton;

    @FXML
    public TextField serviceText;

    @FXML
    public PasswordField passwordArea;

    @FXML
    public JFXCheckBox optLower, optUpper, optNum, optSym;

    @FXML
    public JFXSlider lengthSlider, numSlider;

    @FXML
    public AnchorPane optPane;


    public void setMain(Main main) {
        this.main = main;
    }

    public void logout() throws IOException {
        main.showLogin();
    }

    public void generate() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();

        if (generateButton.getText().equals("Copy")) {
            content.putString(passwordArea.getText());
            clipboard.setContent(content);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Password Copied");
            alert.setContentText("Password has been copied to clipboard.");
            alert.showAndWait();
            return;
        }

        KeyContainer keyContainer = new KeyContainer(optLower.isSelected(), optUpper.isSelected(), optNum.isSelected(), optSym.isSelected());
        int len = (int) lengthSlider.getValue();
        int num = (int) numSlider.getValue();

        User user = new User(this.username, this.password);
        Generator generator = new Generator(user);
        passwordArea.setText(generator.getPassword(serviceText.getText(), keyContainer, len, num));
        System.out.println("Password generated is: " + passwordArea.getText());


        content.putString(passwordArea.getText());
        clipboard.setContent(content);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Password Generated");
        alert.setContentText("Password has been copied to clipboard.");
        alert.showAndWait();

        generateButton.setText("Copy");
    }

    public void init(String username, String password) {
        passwordArea.setEditable(false);
        passwordArea.setPromptText("Password Generated Here...");
        this.username = username;
        this.password = password;

        welcomeLabel.setText("Hi, " + username);
        numSlider.setBlockIncrement(1);
        numSlider.setMajorTickUnit(1);
        numSlider.setMinorTickCount(0);
        numSlider.setSnapToTicks(true);
        numSlider.setMin(1);
        numSlider.setMax(10);
        numSlider.setValue(1);

        lengthSlider.setBlockIncrement(1);
        lengthSlider.setMajorTickUnit(1);
        lengthSlider.setMinorTickCount(0);
        lengthSlider.setSnapToTicks(true);
        lengthSlider.setMin(4);
        lengthSlider.setMax(64);
        lengthSlider.setValue(16);

        serviceText.textProperty().addListener(((observable, oldValue, newValue) -> {
            validate();
        }));

        optLower.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                validate();
            }
        });

        optUpper.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                validate();
            }
        });

        optNum.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                validate();
            }
        });

        optSym.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                validate();
            }
        });

        lengthSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                validate();
            }
        });


        numSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                validate();
            }
        });
    }

    private void validate() {
        if (!serviceText.getText().stripTrailing().stripLeading().equals("") && (optLower.isSelected() ||
                optUpper.isSelected() || optNum.isSelected() || optSym.isSelected())) {
            generateButton.setDisable(false);
        } else {
            generateButton.setDisable(true);
        }

        if (!generateButton.getText().equals("Generate"))
            generateButton.setText("Generate");
    }
}
