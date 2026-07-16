package com.mycompany.project;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RoomBookingPage extends Application {

    @Override
    public void start(Stage stage) {

        StackPane root = new StackPane();
        root.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);

        Image bgImage = new Image(getClass().getResource("/images/hotel.jpg").toExternalForm());
        ImageView bgView = new ImageView(bgImage);
        bgView.setPreserveRatio(false);
        bgView.fitWidthProperty().bind(stage.widthProperty());
        bgView.fitHeightProperty().bind(stage.heightProperty());
        
        Image logoImg = new Image(getClass().getResource("/images/logo6.png").toExternalForm());
        ImageView logoView = new ImageView(logoImg);

        logoView.setFitWidth(250); 
        logoView.setPreserveRatio(true);
        StackPane.setAlignment(logoView, Pos.TOP_LEFT);
        logoView.setTranslateX(20);
        logoView.setTranslateY(20);

        Label heading = new Label("ROOM BOOKING");
        heading.setStyle("-fx-text-fill: white; -fx-font-size: 34px; -fx-font-weight: bold;");

        TextField nameField = new TextField();
        TextField emailField = new TextField();
        TextField phoneField = new TextField();

        ComboBox<String> roomType = new ComboBox<>();
        roomType.getItems().addAll("Single", "Double", "Suite");
        roomType.setValue("Single");

        Spinner<Integer> guests = new Spinner<>(1, 10, 1);

        DatePicker checkIn = new DatePicker();
        DatePicker checkOut = new DatePicker();

        String labelStyle = "-fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 18px;";

        Label nameLabel = new Label("FULL NAME");
        Label emailLabel = new Label("EMAIL");
        Label phoneLabel = new Label("PHONE NUMBER");
        Label roomLabel = new Label("ROOM TYPE");
        Label guestLabel = new Label("GUESTS");
        Label checkInLabel = new Label("CHECK IN");
        Label checkOutLabel = new Label("CHECK OUT");

        nameLabel.setStyle(labelStyle);
        emailLabel.setStyle(labelStyle);
        phoneLabel.setStyle(labelStyle);
        roomLabel.setStyle(labelStyle);
        guestLabel.setStyle(labelStyle);
        checkInLabel.setStyle(labelStyle);
        checkOutLabel.setStyle(labelStyle);

        GridPane grid = new GridPane();
        grid.setVgap(15);
        grid.setHgap(20);

        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);

        grid.add(emailLabel, 0, 1);
        grid.add(emailField, 1, 1);

        grid.add(phoneLabel, 0, 2);
        grid.add(phoneField, 1, 2);

        grid.add(roomLabel, 0, 3);
        grid.add(roomType, 1, 3);

        grid.add(guestLabel, 0, 4);
        grid.add(guests, 1, 4);

        grid.add(checkInLabel, 0, 5);
        grid.add(checkIn, 1, 5);

        grid.add(checkOutLabel, 0, 6);
        grid.add(checkOut, 1, 6);

        
        Button bookBtn = new Button("Book Now");   
        bookBtn.setOnAction(e -> {
            
            String user = UserSession.getUsername();

    if(user == null || user.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.WARNING);

        alert.setTitle("Login Required");
        alert.setHeaderText(null);
        alert.setContentText("Please login first to book a room!");

        alert.getDialogPane().setPrefWidth(300);
        alert.getDialogPane().setPrefHeight(120);

        alert.getDialogPane().setStyle(
            "-fx-background-color: #2c3e50;" +
            "-fx-font-size: 13px;"
        );

        Label content = (Label) alert.getDialogPane().lookup(".content.label");
        content.setStyle("-fx-text-fill: white;");

        Button okBtn = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        okBtn.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");

        alert.showAndWait();
        return;
    }


             if (nameField.getText().isEmpty() ||
        emailField.getText().isEmpty() ||
        phoneField.getText().isEmpty() ||
        checkIn.getValue() == null ||
        checkOut.getValue() == null) {

        Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all required fields!");

                alert.getDialogPane().setPrefWidth(300);
                alert.getDialogPane().setPrefHeight(120);

                alert.getDialogPane().setStyle(
                    "-fx-background-color: #2c3e50;" +
                    "-fx-font-size: 13px;"
                );

                Label content = (Label) alert.getDialogPane().lookup(".content.label");
                content.setStyle("-fx-text-fill: white;");

                Button okBtn = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
                okBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");

                alert.showAndWait();
                return;
             }

        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String room = roomType.getValue();
        int guestCount = guests.getValue();
        String inDate = (checkIn.getValue() != null) ? checkIn.getValue().toString() : "";
        String outDate = (checkOut.getValue() != null) ? checkOut.getValue().toString() : "";

    try {
        java.sql.Connection conn = DBConnection.getConnection();

        String sql = "INSERT INTO bookings (user_email, room_type, plan, check_in, check_out, guests, total) VALUES (?, ?, ?, ?, ?, ?, ?)";

        java.sql.PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, email);        
        ps.setString(2, room);         
        ps.setString(3, "Standard");     
        ps.setString(4, inDate);
        ps.setString(5, outDate);
        ps.setInt(6, guestCount);
        ps.setInt(7, 0);                 

        ps.executeUpdate();

        System.out.println("Booking saved in DB");

        
       RoomDetailsPage page = new RoomDetailsPage();
        page.setRoomType(room);
        page.setDates(inDate, outDate);
        page.setGuests(guestCount); 

        Stage newStage = new Stage();
        newStage.setMaximized(false); 
        page.start(newStage);
        
            } catch (Exception ex) {
                ex.printStackTrace();
            }
});
        
        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> {
          Stage newStage = new Stage();
            newStage.setMaximized(stage.isMaximized());
            new Homepage().start(newStage);
            stage.close();
});

        HBox btnBox = new HBox(15, bookBtn, backBtn);
        btnBox.setAlignment(Pos.CENTER_LEFT);


        grid.add(btnBox, 1, 7); 

        VBox formBox = new VBox(25, heading, grid);
        formBox.setAlignment(Pos.TOP_LEFT);

        Label title = new Label("Book Your Stay,\nStress-Free");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 32px; -fx-font-weight: bold;");

        Label subtitle = new Label("Comfort, Convenience and the best rates");
        subtitle.setStyle("-fx-text-fill: #FFD700; -fx-font-size: 16px; -fx-font-weight: bold;");

        Button reserveBtn = new Button("Reserve your room today.");

        VBox rightBox = new VBox(20, title, subtitle, reserveBtn);
        rightBox.setAlignment(Pos.TOP_LEFT);

        HBox mainLayout = new HBox(100, formBox, rightBox);
       mainLayout.setAlignment(Pos.CENTER);
       mainLayout.setPadding(new Insets(60, 0, 0, 0));
       mainLayout.setMaxWidth(Double.MAX_VALUE);
       mainLayout.setMaxHeight(Double.MAX_VALUE);

        root.getChildren().addAll(bgView, mainLayout, logoView);
        StackPane.setAlignment(mainLayout, Pos.CENTER);
        Scene scene = new Scene(root, 1200, 700);

        stage.setTitle("Room Booking");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}