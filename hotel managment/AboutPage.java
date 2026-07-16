package com.mycompany.project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.animation.ScaleTransition;
import javafx.util.Duration;
import javafx.scene.control.Button;

public class AboutPage extends Application {

    @Override
    public void start(Stage stage) {

       
        VBox root = new VBox();
        root.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        root.setStyle("-fx-background-color: #2f6f5e;");
        
        Image topImg = new Image(getClass().getResource("/images/Topimage2.jpeg").toExternalForm());
        ImageView topView = new ImageView(topImg);
        topView.setFitHeight(250);
        topView.setPreserveRatio(false);
        topView.fitWidthProperty().bind(stage.widthProperty());
        Image logoImg = new Image(getClass().getResource("/images/logo6.png").toExternalForm());

        ImageView logoView = new ImageView(logoImg);
        logoView.setFitWidth(350);  
        logoView.setPreserveRatio(true);
        
    Button backBtn = new Button("← Back");

    backBtn.setStyle(
        "-fx-background-color: transparent;" +
        "-fx-text-fill: white;" +
        "-fx-font-size: 14px;" +
        "-fx-font-weight: bold;" +
        "-fx-cursor: hand;"
    );
    logoView.setOnMouseEntered(e -> {
        ScaleTransition zoomIn = new ScaleTransition(Duration.millis(200), logoView);
        zoomIn.setToX(1.1);
        zoomIn.setToY(1.1);
        zoomIn.play();
});
    
    backBtn.setOnAction(e -> {
        Stage newStage = new Stage();
        new Homepage().start(newStage);

        UserSession.setMaximized(stage.isMaximized());
        newStage.setMaximized(UserSession.isMaximized());
        stage.close();
    });

    Label titleBarText = new Label("About");

    titleBarText.setStyle(
        "-fx-text-fill: white;" +
        "-fx-font-size: 14px;" +
        "-fx-font-weight: bold;"
);
    HBox topBar = new HBox(20, backBtn, titleBarText);
    topBar.setAlignment(Pos.CENTER_LEFT);
    topBar.setPadding(new Insets(10, 20, 10, 20));
    topBar.setStyle( "-fx-backdrop-filter: blur(5px);");

logoView.setOnMouseExited(e -> {
    ScaleTransition zoomOut = new ScaleTransition(Duration.millis(200), logoView);
    zoomOut.setToX(1.0);
    zoomOut.setToY(1.0);
    zoomOut.play();
});
        

     
        Label title = new Label("ABOUT GRAND PARADISE HOTEL");
       
title.setOnMouseEntered(e -> {
    ScaleTransition zoomIn = new ScaleTransition(Duration.millis(200), title);
    zoomIn.setToX(1.1);
    zoomIn.setToY(1.1);
    zoomIn.play();
});


title.setOnMouseExited(e -> {
    ScaleTransition zoomOut = new ScaleTransition(Duration.millis(200), title);
    zoomOut.setToX(1.0);
    zoomOut.setToY(1.0);
    zoomOut.play();
});
        title.setStyle("-fx-text-fill: white; -fx-font-size: 28px; -fx-font-weight: bold;-fx-font-family: 'Times New Roman';");

       
HBox headerContent = new HBox(25, logoView, title);
headerContent.setAlignment(Pos.CENTER);


headerContent.setTranslateY(10);


StackPane header = new StackPane(topView, headerContent);      
header.setAlignment(Pos.CENTER);

        
        
        Label poolL = new Label("🏊 Swimming pool");
        Label barL = new Label("🍸 Bar");
        Label roomL = new Label("🛎 Room Service");
        Label wifiL = new Label("📶 Internet");
        Label breakfastL = new Label("🍳 Breakfast");
        Label teaL = new Label("☕ Tea & Coffee");
        Label shuttleL = new Label("🚐 Airport Shuttle");
        Label restaurantL = new Label("🍽 Restaurant");
        Label spaL = new Label("💆 Spa");


        String iconStyle = "-fx-text-fill: white; -fx-font-size: 16px;";

        poolL.setStyle(iconStyle);
        barL.setStyle(iconStyle);
        roomL.setStyle(iconStyle);
        wifiL.setStyle(iconStyle);
        breakfastL.setStyle(iconStyle);
        teaL.setStyle(iconStyle);
        shuttleL.setStyle(iconStyle);
        restaurantL.setStyle(iconStyle);
        spaL.setStyle(iconStyle);

        HBox facilities = new HBox(30,
        poolL, barL, roomL, wifiL, breakfastL, teaL, shuttleL, restaurantL, spaL
                
        );

        facilities.setAlignment(Pos.CENTER);
        facilities.setPadding(new Insets(10));
        facilities.setStyle("-fx-background-color: #2f6f5e; -fx-text-fill: white;");
        facilities.setMaxWidth(Double.MAX_VALUE);
        
       
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setMaxWidth(Double.MAX_VALUE);
        grid.setHgap(20);
        grid.setVgap(20);

       
        ImageView pool = new ImageView(new Image(getClass().getResource("/images/pool.jpg").toExternalForm()));
        pool.fitWidthProperty().bind(stage.widthProperty().divide(4));
       StackPane poolBox = new StackPane(pool);
poolBox.setStyle("-fx-border-color: white; -fx-border-width: 2;");

        ImageView banquet = new ImageView(new Image(getClass().getResource("/images/banquet.jpg").toExternalForm()));
        banquet.fitWidthProperty().bind(stage.widthProperty().divide(4));
        StackPane banquetBox = new StackPane(banquet);
banquetBox.setStyle("-fx-border-color: white; -fx-border-width: 2;");

        ImageView gym = new ImageView(new Image(getClass().getResource("/images/gym.jpg").toExternalForm()));
        gym.fitWidthProperty().bind(stage.widthProperty().divide(4));
        StackPane gymBox = new StackPane(gym);
gymBox.setStyle("-fx-border-color: white; -fx-border-width: 2;");

        ImageView game = new ImageView(new Image(getClass().getResource("/images/game.jpg").toExternalForm()));
        game.fitWidthProperty().bind(stage.widthProperty().divide(4));
        StackPane gameBox = new StackPane(game);
gameBox.setStyle("-fx-border-color: white; -fx-border-width: 2;");

        Label poolText = new Label("Our Swimming Pool provides a clean and \n" +
"refreshing space for relaxation, exercise, \n" +
"and recreation. It is maintained with \n" +
"proper hygiene and safety standards, \n" +
"making it ideal for both beginners and \n" +
"experienced swimmers.");
        Label banquetText = new Label("The Banquet Hall is a spacious and elegant venue \n" +
"designed for hosting special events such as\n" +
" weddings, parties, meetings, and celebrations. \n" +
"With modern facilities and a comfortable \n" +
"atmosphere, it is perfect for creating memorable \n" +
"moments.");
        Label gymText = new Label("The Gym & Yoga area is designed to \n" +
"promote a healthy and active lifestyle. \n" +
"Equipped with modern fitness machines \n" +
"and a peaceful yoga space, it helps \n" +
"members improve their strength, flexibility, \n" +
"and overall well-being.");
        Label gameText = new Label("Our Game Zone is a fun and exciting place \n" +
"where people of all ages can enjoy different \n" +
"games and activities. It offers a variety of \n" +
"indoor games that help visitors relax, have fun, \n" +
"and spend quality time with friends and family \n" +
"in an energetic environment.");

        poolText.setWrapText(true);
        banquetText.setWrapText(true);
        gymText.setWrapText(true);
        gameText.setWrapText(true);

        String boxStyle = "-fx-background-color: #2f6f5e; -fx-padding: 10; -fx-text-fill: white;-fx-font-family: "
                + "'Times New Roman';-fx-font-size: 14px;";

        poolText.setStyle(boxStyle);
        banquetText.setStyle(boxStyle);
        gymText.setStyle(boxStyle);
        gameText.setStyle(boxStyle);

        grid.add(poolBox, 0, 0);
        grid.add(poolText, 1, 0);

        grid.add(banquetBox, 2, 0);
        grid.add(banquetText, 3, 0);

        grid.add(gymBox, 0, 1);
        grid.add(gymText, 1, 1);

        grid.add(gameBox, 2, 1);
        grid.add(gameText, 3, 1);

       
        root.getChildren().addAll(topBar, header, facilities, grid);

        Scene scene = new Scene(root, 1200, 700);

        stage.setTitle("About Page");
        stage.setScene(scene);
        stage.setMaximized(stage.isMaximized());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
  