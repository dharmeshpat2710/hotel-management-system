/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class BookingInfoPage extends Application {

    @Override
    public void start(Stage stage) {

        VBox root = new VBox(15);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);

        root.setStyle("-fx-background-color: #1e2a38;");

        Label title = new Label("Booking Information");
        title.setStyle("-fx-font-size: 22px; -fx-text-fill: white; -fx-font-weight: bold;");

        Label room = new Label("Room: " + UserSession.getRoomType());
        Label plan = new Label("Plan: " + UserSession.getPlan());
        Label guests = new Label("Guests: " + UserSession.getGuests());
        Label checkIn = new Label("Check-in: " + UserSession.getCheckIn());
        Label checkOut = new Label("Check-out: " + UserSession.getCheckOut());
        Label total = new Label("Total: ₹" + UserSession.getTotal());

        String style = "-fx-text-fill: white; -fx-font-size: 14px;";
        room.setStyle(style);
        plan.setStyle(style);
        guests.setStyle(style);
        checkIn.setStyle(style);
        checkOut.setStyle(style);
        total.setStyle(style);

        Button openPdf = new Button("Download Receipt");

        openPdf.setOnAction(e -> {
            try {
                String path = UserSession.getReceiptPath();

                if(path != null) {
                    java.awt.Desktop.getDesktop().open(new java.io.File(path));
                } else {
                    Alert a = new Alert(Alert.AlertType.WARNING);
                    a.setContentText("No receipt found!");
                    a.show();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button back = new Button("Back");
        back.setOnAction(e -> {
            new Homepage().start(stage);
        });

        root.getChildren().addAll(
                title,
                room, plan, guests, checkIn, checkOut, total,
                openPdf
        );

        Scene scene = new Scene(root, 400, 450);
        stage.setScene(scene);
        stage.setTitle("Booking Info");
        stage.show();
    }
}
