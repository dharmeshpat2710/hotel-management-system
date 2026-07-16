package com.mycompany.project;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.util.Duration;

public class FinalBookingPage extends Application {

    private String roomType;
    private String price;
    private String plan;
    private String checkInDate;
    private String checkOutDate;
    private int guestCount;

    public void setData(String roomType, String price, String plan,
                        String checkInDate, String checkOutDate, int guestCount) {

        this.roomType = roomType;
        this.price = price;
        this.plan = plan;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.guestCount = guestCount;
    }

    @Override
    public void start(Stage stage) {

        int priceValue = Integer.parseInt(price.replace("₹", ""));

        int extra = (guestCount - 1) * 500;
        if(extra < 0) extra = 0;

        int finalPricePerNight = priceValue + extra;

        java.time.LocalDate in = java.time.LocalDate.parse(checkInDate);
        java.time.LocalDate out = java.time.LocalDate.parse(checkOutDate);

        long days = java.time.temporal.ChronoUnit.DAYS.between(in, out);
        if(days <= 0) days = 1;
        final long finalDays = days;

        int total = (int) days * finalPricePerNight;

        final int[] finalTotal = { total };
        final boolean[] applied = { false };

        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #1e2a38, #0f1720);");

        VBox card = new VBox(20);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(30));
        card.setMaxWidth(420);

        card.setStyle(
    "-fx-background-color: rgba(255,255,255,0.08);" +
    "-fx-background-radius: 20;" +
    "-fx-border-radius: 20;" +
    "-fx-border-color: rgba(255,255,255,0.1);" +
    "-fx-padding: 25;"
);

        Label heading = new Label("Booking Summary");
        heading.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: white;");

        ImageView roomImage = new ImageView();
        roomImage.setFitWidth(250);
        roomImage.setPreserveRatio(true);

        if(roomType.equals("Single")) {
            roomImage.setImage(new Image(getClass().getResource("/images/single.jpg").toExternalForm()));
        } else if(roomType.equals("Double")) {
            roomImage.setImage(new Image(getClass().getResource("/images/double.jpg").toExternalForm()));
        } else {
            roomImage.setImage(new Image(getClass().getResource("/images/suite.jpg").toExternalForm()));
        }
        
        VBox infoBox = new VBox(10);

        Label roomLabel = new Label("🏨 Room: " + roomType);
        Label planLabel = new Label("📋 Plan: " + plan);
        Label priceLabel = new Label("💰 Price/Night: ₹" + finalPricePerNight);
        Label guestLabel = new Label("👤 Guests: " + guestCount);
        Label dateLabel = new Label("📅 " + checkInDate + " → " + checkOutDate);
        roomLabel.setStyle("-fx-text-fill: white;");
        planLabel.setStyle("-fx-text-fill: white;");
        priceLabel.setStyle("-fx-text-fill: white;");
        guestLabel.setStyle("-fx-text-fill: white;");
        dateLabel.setStyle("-fx-text-fill: white;");

        infoBox.getChildren().addAll(roomLabel, planLabel, priceLabel, guestLabel, dateLabel);

        Label totalLabel = new Label("Total: ₹" + total + " (" + finalDays + " nights)");
        totalLabel.setStyle(
        "-fx-font-size: 22px;" +
        "-fx-font-weight: bold;" +
        "-fx-text-fill: #FFD700;"
);

   
        Button confirmBtn = new Button("Confirm Booking");

        confirmBtn.setStyle(
            "-fx-background-color: #27ae60;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 14px;" +
            "-fx-padding: 12 25;" +
            "-fx-background-radius: 10;" +
            "-fx-cursor: hand;"
        );
        confirmBtn.setOnMouseEntered(e ->
    confirmBtn.setStyle(
        "-fx-background-color: #2ecc71;" +
        "-fx-text-fill: white;" +
        "-fx-font-size: 14px;" +
        "-fx-padding: 12 25;" +
        "-fx-background-radius: 10;" +
        "-fx-effect: dropshadow(gaussian, rgba(255,255,255,0.6), 10, 0, 0, 0);"
    )
);

        confirmBtn.setOnMouseExited(e ->
    confirmBtn.setStyle(
        "-fx-background-color: #27ae60;" +
        "-fx-text-fill: white;" +
        "-fx-font-size: 14px;" +
        "-fx-padding: 12 25;" +
        "-fx-background-radius: 10;"
    )
);

    confirmBtn.setOnAction(e -> {
        
        UserSession.setBookingData(
        roomType,
        plan,
        checkInDate,
        checkOutDate,
        guestCount,
        total
    );


    try {
         System.out.println("Saving booking...");

        java.sql.Connection conn = DBConnection.getConnection();

        String sql = "INSERT INTO bookings(user_email, room_type, plan, check_in, check_out, guests, total) VALUES (?, ?, ?, ?, ?, ?, ?)";
        java.sql.PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, UserSession.getEmail());
        ps.setString(2, roomType);
        ps.setString(3, plan);
        ps.setString(4, checkInDate);
        ps.setString(5, checkOutDate);
        ps.setInt(6, guestCount);
        ps.setInt(7, total);

        ps.executeUpdate();

        System.out.println("Booking saved!");

    } catch (Exception ex) {
        ex.printStackTrace();
    }

    String user = UserSession.getUsername();
    if(user == null || user.isEmpty()) user = "Guest";

    String path = ReceiptGenerator.generateReceipt(
        user,
        roomType,
        plan,
        checkInDate,
        checkOutDate,
        guestCount,
        total
    );

    UserSession.setReceiptPath(path);

    boolean sent = EmailSender.sendReceipt(UserSession.getEmail(), path);

    if(sent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Receipt emailed successfully!");
        alert.showAndWait();
    } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Failed to send email!");
        alert.showAndWait();
    }

    Label success = new Label("Booking Confirmed!");
    success.setStyle("-fx-font-size: 28px; -fx-text-fill: #2ecc71; -fx-font-weight: bold;");

    Label thankYou = new Label(
        "Your room has been successfully booked.\nWe look forward to welcoming you!"
    );

    thankYou.setStyle("-fx-font-size: 15px; -fx-text-fill: #dddddd;");
    thankYou.setWrapText(true);
    thankYou.setAlignment(Pos.CENTER);

    Button homeBtn = new Button("Go to Home");

    homeBtn.setOnAction(ev -> {
        boolean isMax = stage.isMaximized();

        java.util.List<javafx.stage.Window> windows =
                new java.util.ArrayList<>(javafx.stage.Window.getWindows());

        for (javafx.stage.Window window : windows) {
            if (window instanceof Stage) {
                ((Stage) window).close();
            }
        }

        javafx.application.Platform.runLater(() -> {
            Stage newStage = new Stage();
            new Homepage().start(newStage);
            newStage.setMaximized(isMax);
            });
        });
    VBox successBox = new VBox(25, success, thankYou, homeBtn);
    successBox.setAlignment(Pos.CENTER);

    card.getChildren().clear();
    card.getChildren().add(successBox);

});
      card.getChildren().addAll(
    heading,
    roomImage,
    infoBox,
    totalLabel,
    confirmBtn
);

        root.getChildren().add(card);

        Scene scene = new Scene(root, 500, 500);

        stage.setScene(scene);
        stage.setTitle("Final Booking");
        stage.setMaximized(false);
        
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}