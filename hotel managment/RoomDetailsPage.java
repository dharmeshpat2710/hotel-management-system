package com.mycompany.project;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class RoomDetailsPage extends Application {
    private String checkInDate;
    private String checkOutDate;
    private String roomType;
    private int guestCount;

    public RoomDetailsPage() {}

    public void setRoomType(String type) {
        this.roomType = type;
    }
    public void setDates(String in, String out) {
    this.checkInDate = in;
    this.checkOutDate = out;
}
    public void setGuests(int count) {
    this.guestCount = count;
}
    @Override
    public void start(Stage stage) {

        if(roomType == null) roomType = "Single";

        VBox main = new VBox(25);
        main.setPadding(new Insets(25));
       main.setStyle(
    "-fx-background-color: linear-gradient(to bottom, #1e2a38, #0f1720);"
);

    
        ImageView roomImage = new ImageView();
        roomImage.setFitWidth(420);
        roomImage.setPreserveRatio(true);
        roomImage.setOnMouseEntered(e -> {
    roomImage.setScaleX(1.05);
    roomImage.setScaleY(1.05);
    roomImage.setStyle("-fx-cursor: hand;");
});

roomImage.setOnMouseExited(e -> {
    roomImage.setScaleX(1);
    roomImage.setScaleY(1);
});
        roomImage.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 20, 0, 0, 8);");

        Label title = new Label();
        Label desc = new Label();
        Label features = new Label();
        Label rating = new Label("⭐⭐⭐⭐☆ 4.5 (120 reviews)");
        rating.setStyle("-fx-text-fill: gold; -fx-font-size: 14px;");
        Label info = new Label(
    "Guests: " + guestCount +
    "   |   Check-in: " + checkInDate +
    "   |   Check-out: " + checkOutDate
);

info.setStyle("-fx-text-fill: #bbbbbb; -fx-font-size: 13px;");
        title.setStyle("-fx-font-size: 30px; -fx-text-fill: white; -fx-font-weight: bold;");
desc.setStyle("-fx-font-size: 15px; -fx-text-fill: #cccccc;");
        desc.setWrapText(true);
        
        features.setStyle(
    "-fx-background-color: rgba(255,255,255,0.1);" +
    "-fx-padding: 10;" +
    "-fx-background-radius: 10;" +
    "-fx-text-fill: white;"
);

        if(roomType.equals("Single")) {
            title.setText("Single Room Standard");
            desc.setText("Comfortable single room with Wi-Fi, TV, and breakfast.");
            features.setText("✔ WiFi   ✔ AC   ✔ Breakfast   ✔ TV");

            roomImage.setImage(new Image(getClass().getResource("/images/single.jpg").toExternalForm()));
        }
        else if(roomType.equals("Double")) {
            title.setText("Double Room Deluxe");
            desc.setText("Perfect for couples with extra comfort.");
            features.setText("✔ WiFi   ✔ AC   ✔ Breakfast   ✔ Balcony");

            roomImage.setImage(new Image(getClass().getResource("/images/double.jpg").toExternalForm()));
        }
        else {
            title.setText("Suite Room Luxury");
            desc.setText("Luxury suite with premium facilities.");
            features.setText("✔ WiFi   ✔ AC   ✔ Breakfast   ✔ Jacuzzi");

            roomImage.setImage(new Image(getClass().getResource("/images/suite.jpg").toExternalForm()));
        }

        VBox textBox = new VBox(12, title, rating, desc, features, info);
        HBox top = new HBox(30, roomImage, textBox);

        
        VBox priceList = new VBox(20);

        if(roomType.equals("Single")) {

    priceList.getChildren().add(createRow(
            "Non-refundable (Breakfast included)",
            "₹5000",
            "#f39c12",
            stage
    ));

    priceList.getChildren().add(createRow(
            "Free cancellation (Breakfast included)",
            "₹6000",
            "#27ae60",
            stage
    ));

}
else if(roomType.equals("Double")) {

    priceList.getChildren().add(createRow(
            "Non-refundable (Breakfast included)",
            "₹10000",
            "#f39c12",
            stage
    ));

    priceList.getChildren().add(createRow(
            "Free cancellation (Breakfast included)",
            "₹11000",
            "#27ae60",
            stage
    ));

}
else { 

    priceList.getChildren().add(createRow(
            "Luxury Suite (Breakfast included)",
            "₹15000",
            "#f39c12",
            stage
    ));

    priceList.getChildren().add(createRow(
            "Premium Suite (Free cancellation)",
            "₹20000",
            "#27ae60",
            stage
    ));
}

       Label highlightsTitle = new Label("Why choose this room?");
        highlightsTitle.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");

Label h1 = new Label("✔ Spacious Interior");
Label h2 = new Label("✔ Sea View");
Label h3 = new Label("✔ Free WiFi");
Label h4 = new Label("✔ 24/7 Support");

String style = "-fx-text-fill: #00ffcc; -fx-font-size: 14px; -fx-font-weight: bold;";

h1.setStyle(style);
h2.setStyle(style);
h3.setStyle(style);
h4.setStyle(style);

HBox highlights = new HBox(40, h1, h2, h3, h4);

highlights.setStyle("-fx-text-fill: #cccccc; -fx-font-size: 13px;");
highlights.setAlignment(Pos.CENTER);

VBox bottomSection = new VBox(15, highlightsTitle, highlights);
bottomSection.setAlignment(Pos.CENTER);

main.getChildren().addAll(top, priceList, bottomSection);

        Scene scene = new Scene(main, 1000, 600);
        stage.setScene(scene);
        stage.setTitle("Room Details");
        stage.setMaximized(false);
        stage.show();
    }

    private HBox createRow(String title, String price, String color,  Stage stage) {

        Label t = new Label(title);
        t.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: white;");

        Label p = new Label(price);
        p.setStyle("-fx-font-size: 20px; -fx-text-fill: #FFD700; -fx-font-weight: bold;");

        Button btn = new Button("Book Now");
       btn.setOnAction(e -> {
        FinalBookingPage page = new FinalBookingPage();
        page.setData(roomType, price, title, checkInDate, checkOutDate, guestCount);
        Stage newStage = new Stage();
        newStage.setMaximized(false); 
        page.start(newStage);         
        
});
        btn.setStyle(
            "-fx-background-color: " + color + ";" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 10;" +
            "-fx-padding: 10 18;" +
            "-fx-cursor: hand;"
        );
        btn.setOnMouseEntered(e ->
        btn.setStyle(
        "-fx-background-color: " + color + ";" +
        "-fx-text-fill: white;" +
        "-fx-font-weight: bold;" +
        "-fx-background-radius: 10;" +
        "-fx-padding: 10 18;" +
        "-fx-effect: dropshadow(gaussian, rgba(255,255,255,0.6), 10, 0, 0, 0);"
    )
);

        btn.setOnMouseExited(e ->
        btn.setStyle(
        "-fx-background-color: " + color + ";" +
        "-fx-text-fill: white;" +
        "-fx-font-weight: bold;" +
        "-fx-background-radius: 10;" +
        "-fx-padding: 10 18;"
    )
);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox row = new HBox(20, t, spacer, p, btn);
        row.setOnMouseEntered(e -> {
    row.setStyle(
        "-fx-background-color: rgba(255,255,255,0.12);" +
        "-fx-background-radius: 15;" +
        "-fx-padding: 15;" +
        "-fx-border-radius: 15;" +
        "-fx-border-color: rgba(255,255,255,0.2);" +
        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.7), 15, 0, 0, 8);"
    );
});

row.setOnMouseExited(e -> {
    row.setStyle(
    "-fx-background-color: rgba(255,255,255,0.08);" +
    "-fx-background-radius: 15;" +
    "-fx-padding: 15;" +
    "-fx-border-radius: 15;" +
    "-fx-border-color: rgba(255,255,255,0.1);" +
    "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 5);"
    );
});
        row.setPadding(new Insets(15));
        row.setStyle(
    "-fx-background-color: rgba(255,255,255,0.08);" +
    "-fx-background-radius: 15;" +
    "-fx-padding: 15;" +
    "-fx-border-radius: 15;" +
    "-fx-border-color: rgba(255,255,255,0.1);" +
    "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 5);"
);

        return row;
    }

    public static void main(String[] args) {
        launch();
    }
}