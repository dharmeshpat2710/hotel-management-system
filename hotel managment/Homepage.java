 package com.mycompany.project;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;


public class Homepage extends Application {
   

    @Override
    public void start(Stage stage) {
        
      

        Image bgImage = new Image(getClass().getResource("/images/Homepage.jpeg").toExternalForm());
        ImageView bgView = new ImageView(bgImage);
        bgView.setPreserveRatio(false);
        bgView.fitWidthProperty().bind(stage.widthProperty());
        bgView.fitHeightProperty().bind(stage.heightProperty());
        
        Pane overlay = new Pane();
overlay.setStyle("-fx-background-color: rgba(0,0,0,0.4);");

overlay.prefWidthProperty().bind(stage.widthProperty());
overlay.prefHeightProperty().bind(stage.heightProperty());

        Image logoImg = new Image(getClass().getResource("/images/finallogo.png").toExternalForm());
ImageView logoView = new ImageView(logoImg);

logoView.setFitWidth(270);
logoView.setPreserveRatio(true);


VBox.setMargin(logoView, new Insets(10, 0, 10, 0));

        Label location = new Label("📍 Gaurav Path Rd, Adajan, Surat");
Label phone = new Label("📞 798 678 5678");
Label email = new Label("✉ HotelGrandParadise@gmail.com");

        
HBox leftBox = new HBox(20, location, phone);
leftBox.setAlignment(Pos.CENTER_LEFT);


Label fb = new Label("f");  
Label insta = new Label("📷");
Label twitter = new Label("🐦");

location.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
phone.setStyle("-fx-text-fill: white;-fx-font-size: 14px;");
email.setStyle("-fx-text-fill: white;-fx-font-size: 14px;");
fb.setStyle("-fx-text-fill: white;-fx-font-size: 14px;");
insta.setStyle("-fx-text-fill: white;-fx-font-size: 14px;");
twitter.setStyle("-fx-text-fill: white;-fx-font-size: 14px;");

String user = UserSession.getUsername();

MenuButton userMenu;

if(user != null && !user.isEmpty()) {
    userMenu = new MenuButton("Welcome, " + user);
} else {
    userMenu = new MenuButton("Welcome, Guest");
}

userMenu.setStyle("-fx-text-fill: white;" +
    "-fx-background-color: rgba(0,0,0,0);" +   
    "-fx-padding: 6 15;" +
    "-fx-font-weight: bold;");
userMenu.skinProperty().addListener((obs, oldSkin, newSkin) -> {
    if (newSkin != null) {
        userMenu.lookup(".label").setStyle("-fx-text-fill: white;");
    }
});

MenuItem bookingInfo = new MenuItem("Booking Info");
userMenu.getItems().add(bookingInfo);

if(user == null || user.isEmpty()) {
    bookingInfo.setDisable(true);  
}

bookingInfo.setOnAction(e -> {

    if(UserSession.getUsername() == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Please login first!");
        alert.show();
        return;
    }

    if(UserSession.getReceiptPath() == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("No booking found!");
        alert.show();
        return;
    }

    Stage newStage = new Stage();
    new BookingInfoPage().start(newStage);
});

MenuItem logoutItem = new MenuItem("Logout");
userMenu.getItems().add(logoutItem);

    logoutItem.setOnAction(e -> {

    Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
    confirm.setTitle("Confirm Logout");
    confirm.setHeaderText(null);
    confirm.setContentText("Are you sure you want to logout?");

    ButtonType yesBtn = new ButtonType("Yes");
    ButtonType noBtn = new ButtonType("No");

    confirm.getButtonTypes().setAll(yesBtn, noBtn);

    confirm.showAndWait().ifPresent(response -> {

        if (response == yesBtn) {
            UserSession.clear();

            userMenu.setText("Welcome, Guest");

            bookingInfo.setDisable(true);
        }
    });
});


HBox rightBox = new HBox(20, fb, insta, twitter, email, userMenu);
rightBox.setAlignment(Pos.CENTER_RIGHT);


BorderPane topBar = new BorderPane();
topBar.setLeft(leftBox);
topBar.setRight(rightBox);
topBar.setMaxWidth(Double.MAX_VALUE);

topBar.setStyle("-fx-padding: 10 40 10 40;");

Button aboutBtn = new Button("ABOUT");
Button roomBtn = new Button("ROOM BOOKING");
Button loginBtn = new Button("LOGIN");
Button homeBtn = new Button("HOME");
Button contactBtn = new Button("ALBUM");
       

     
    aboutBtn.setOnAction(e -> {
    Stage newStage = new Stage();
    new AboutPage().start(newStage);
    newStage.setMaximized(stage.isMaximized()); 
    stage.close();
});
    
      
    roomBtn.setOnAction(e -> {
    Stage newStage = new Stage();
    new RoomBookingPage().start(newStage);
    newStage.setMaximized(stage.isMaximized());
    stage.close();
});
      
       
  loginBtn.setOnAction(e -> {
    Stage newStage = new Stage();
    new LoginPage().start(newStage);
    newStage.setMaximized(stage.isMaximized());
    stage.close();
});
        
      
    contactBtn.setOnAction(e -> {
    Stage newStage = new Stage();
    new AlbumPage().start(newStage);
    newStage.setMaximized(stage.isMaximized());
    stage.close();
});
       
         
        String btnStyle = "-fx-background-color: transparent;" +
                            "-fx-text-fill: white;" +
                  "-fx-font-size: 14px;" +
                  "-fx-padding: 8 20 8 20;" +
                  "-fx-cursor: hand;"+
                  "-fx-font-weight: bold;"+
                  "-fx-font-family: 'Times New Roman';";
        aboutBtn.setStyle(btnStyle);
        roomBtn.setStyle(btnStyle);
        loginBtn.setStyle(btnStyle);
        homeBtn.setStyle(btnStyle);
        contactBtn.setStyle(btnStyle);

        HBox navbar = new HBox(30, aboutBtn, roomBtn, loginBtn, homeBtn, contactBtn);
        navbar.setAlignment(Pos.CENTER);
        navbar.setStyle("-fx-background-color: rgba(0, 0, 0, 0.6); -fx-padding: 15;");
        navbar.setMaxWidth(Double.MAX_VALUE);
        VBox navWrapper = new VBox(navbar);
navWrapper.setAlignment(Pos.CENTER);
navWrapper.setFillWidth(true);

        

       VBox layout = new VBox(10, topBar, logoView, navWrapper);
layout.setAlignment(Pos.TOP_CENTER);

        StackPane root = new StackPane(
    bgView,
    overlay,
    layout
);

        root.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);

        Scene scene = new Scene(root, 1200, 700);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}