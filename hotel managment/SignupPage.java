package com.mycompany.project;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.FileWriter;

public class SignupPage extends Application {

    @Override
    public void start(Stage stage) {

     
        StackPane root = new StackPane();
        root.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);  

        Image bgImage = new Image(getClass().getResource("/images/signpage1.jpg").toExternalForm());
        ImageView bgView = new ImageView(bgImage);
        bgView.setPreserveRatio(false);
        bgView.fitWidthProperty().bind(stage.widthProperty());
        bgView.fitHeightProperty().bind(stage.heightProperty());

        Label title = new Label("CREATE ACCOUNT");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 26px; -fx-font-weight: bold;");

        TextField nameField = new TextField();
        nameField.setPrefWidth(250);
        TextField emailField = new TextField();
        emailField.setPrefWidth(250);
        PasswordField passField = new PasswordField();
        passField.setPrefWidth(250);
        PasswordField confirmPassField = new PasswordField();
        confirmPassField.setPrefWidth(250);
        TextField mobileField = new TextField();
        mobileField.setPrefWidth(250);
        TextArea addressField = new TextArea();
        addressField.setPrefWidth(250);

        addressField.setPrefHeight(80);

        ComboBox<String> roleBox = new ComboBox<>();
        roleBox.getItems().addAll("Customer", "Admin");
        roleBox.setValue("Customer");

        Label nameLabel = new Label("Full Name");
        Label emailLabel = new Label("Email");
        Label passLabel = new Label("Password");
        Label confirmLabel = new Label("Confirm Password");
        Label mobileLabel = new Label("Mobile Number");
        Label addressLabel = new Label("Address");
        Label roleLabel = new Label("Role");

     
        String labelStyle = "-fx-text-fill: white; -fx-font-size: 18px;-fx-font-family: 'Times New Roman';";
        nameLabel.setStyle(labelStyle);
        emailLabel.setStyle(labelStyle);
        passLabel.setStyle(labelStyle);
        confirmLabel.setStyle(labelStyle);
        mobileLabel.setStyle(labelStyle);
        addressLabel.setStyle(labelStyle);
        roleLabel.setStyle(labelStyle);


        GridPane grid = new GridPane();
        grid.setVgap(15);
        grid.setHgap(20);

        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);

        grid.add(emailLabel, 0, 1);
        grid.add(emailField, 1, 1);

        grid.add(passLabel, 0, 2);
        grid.add(passField, 1, 2);

        grid.add(confirmLabel, 0, 3);
        grid.add(confirmPassField, 1, 3);

        grid.add(mobileLabel, 0, 4);
        grid.add(mobileField, 1, 4);

        grid.add(addressLabel, 0, 5);
        grid.add(addressField, 1, 5);

        grid.add(roleLabel, 0, 6);
        grid.add(roleBox, 1, 6);

        grid.setAlignment(Pos.CENTER);

 
        CheckBox terms = new CheckBox("I agree to Terms & Conditions");
        terms.setStyle("-fx-text-fill: white;");

        Button registerBtn = new Button("Register");
        
        registerBtn.setOnAction(e -> {

        String name = nameField.getText();     
        String email = emailField.getText();
        String password = passField.getText();
        String confirm = confirmPassField.getText();

    if (!password.equals(confirm)) {
        System.out.println("Passwords do not match!");
        return;
    }

    try {
        java.sql.Connection conn = DBConnection.getConnection();

        String sql = "INSERT INTO users (name, email, password, phone) VALUES (?, ?, ?, ?)";

        java.sql.PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, email);
        ps.setString(3, password);
        ps.setString(4, mobileField.getText());

        ps.executeUpdate();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Account created successfully!");
        alert.showAndWait();

        System.out.println("User Registered Successfully!");

        Stage newStage = new Stage();

        UserSession.setMaximized(stage.isMaximized()); 
        new LoginPage().start(newStage);
        newStage.setMaximized(UserSession.isMaximized());
        stage.close();

    } catch (Exception ex) {
        ex.printStackTrace();
    }
});
        
        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> {
        Stage newStage = new Stage();

        UserSession.setMaximized(stage.isMaximized());
        new LoginPage().start(newStage);
        newStage.setMaximized(UserSession.isMaximized());
        stage.close();
});

        HBox btnBox = new HBox(20, registerBtn, backBtn);
        btnBox.setAlignment(Pos.CENTER);

        VBox form = new VBox(20, title, grid, terms, btnBox);
        form.setAlignment(Pos.CENTER);

        form.setStyle(
            "-fx-background-color: rgba(0,0,0,0.4);" +
            "-fx-padding: 30;" +
            "-fx-background-radius: 10;"
        );
        StackPane.setAlignment(form, Pos.CENTER);

        root.getChildren().addAll(bgView, form);

        Scene scene = new Scene(root, 1200, 700);

        stage.setTitle("Sign Up");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}