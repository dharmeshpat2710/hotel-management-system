package com.mycompany.project;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginPage extends Application {

    @Override
    public void start(Stage stage) {

        
        StackPane root = new StackPane();
        root.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);

        
        Image bgImage = new Image(getClass().getResource("/images/login1.jpg").toExternalForm());
        ImageView bgView = new ImageView(bgImage);
        bgView.setPreserveRatio(false);
        bgView.fitWidthProperty().bind(stage.widthProperty());
        bgView.fitHeightProperty().bind(stage.heightProperty());

      
        Rectangle overlay = new Rectangle();
        overlay.setFill(Color.rgb(0, 0, 0, 0.4));
        overlay.widthProperty().bind(stage.widthProperty());
        overlay.heightProperty().bind(stage.heightProperty());

       
        Image sideImg = new Image(getClass().getResource("/images/bed.jpg").toExternalForm());
        ImageView sideView = new ImageView(sideImg);
        sideView.setFitWidth(250);
        sideView.setFitHeight(350);

     
        Label title = new Label("THE GRAND PARADISE");
        title.setStyle("-fx-font-size: 28px; -fx-text-fill: black; -fx-font-weight: bold;-fx-font-family: 'Times New Roman';");

        Label emailLabel = new Label("Email");
        TextField emailField = new TextField();
        emailField.setPrefWidth(250);

        Label passLabel = new Label("Password");
        PasswordField passField = new PasswordField();
        passField.setPrefWidth(250);
        
        title.setStyle("-fx-text-fill: white; -fx-font-size: 24px; -fx-font-weight: bold;");
        emailLabel.setStyle("-fx-text-fill: white;");
        passLabel.setStyle("-fx-text-fill: white;");

        Button loginBtn = new Button("Login");
        Label message = new Label();
        
        Button backBtn = new Button("Back");
            backBtn.setOnAction(e -> {
            Stage newStage = new Stage();
            new Homepage().start(newStage);
            newStage.setMaximized(stage.isMaximized()); 
            stage.close();
});
        
        Button signupBtn = new Button("Sign Up");
        signupBtn.setOnAction(e -> {
        Stage newStage = new Stage();
        new SignupPage().start(newStage);
        newStage.setMaximized(stage.isMaximized());
        stage.close();
});
        
        loginBtn.setOnAction(e -> {
        String email = emailField.getText();
        String password = passField.getText();
        
        if (email.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setContentText("Please enter email and password");
            alert.getButtonTypes().add(ButtonType.OK);
            alert.initOwner(loginBtn.getScene().getWindow());

            alert.setHeaderText(null);
            alert.setTitle("");
            alert.showAndWait();
            return;
}

      try {
            java.sql.Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM users WHERE email=? AND password=?";
            java.sql.PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            java.sql.ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");

                UserSession.setUsername(name);
                UserSession.setEmail(email);

                Stage newStage = new Stage();
                new Homepage().start(newStage);
                newStage.setMaximized(stage.isMaximized());
                stage.close();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid Email or Password");
                alert.show();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
});
        
        loginBtn.setStyle("-fx-background-color: #00bfff; -fx-text-fill: white;");
        signupBtn.setStyle("-fx-background-color: #00bfff; -fx-text-fill: white;");
        backBtn.setStyle("-fx-background-color: #00bfff; -fx-text-fill: white;");

        HBox btnBox = new HBox(20, loginBtn, signupBtn, backBtn);
        btnBox.setAlignment(Pos.CENTER);

        VBox formBox = new VBox(15, title, emailLabel, emailField, passLabel, passField, btnBox, message);
        formBox.setAlignment(Pos.CENTER);
        formBox.setStyle("-fx-background-color: transparent; -fx-padding: 20;");

        
        HBox mainBox = new HBox(20, sideView, formBox);
        mainBox.setAlignment(Pos.CENTER);
        mainBox.setMaxWidth(Double.MAX_VALUE);
        mainBox.setMaxHeight(Double.MAX_VALUE);

       
        root.getChildren().addAll(bgView, overlay, mainBox);

        Scene scene = new Scene(root, 1200, 700);

        stage.setTitle("Login Page");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}