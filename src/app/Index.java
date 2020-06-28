package app;

import java.util.Locale;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import utils.Language;

public class Index extends VBox {
	public static Scene logInScene, signInScene;
	private Button login;
	private Button signup;
	private Button playasguest;
	private Label welcome;

	public VBox getIndex() {
		VBox pane = new VBox(5);
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(20, 20, 20, 20));

		Login form1 = new Login();
        SignUp form2 = new SignUp();
        
		logInScene = new Scene(form1.getLogIn(), 400, 400);
		signInScene = new Scene(form2.getSignUp(), 400, 400);
		
		HBox buttonPane = new HBox(13);
		buttonPane.setAlignment(Pos.CENTER);
		
		HBox buttonPane1 = new HBox(13);
		buttonPane1.setAlignment(Pos.CENTER);
		buttonPane1.setPadding(new Insets(5, 5, 5, 5));

		login = Language.getButton("Button3");
		signup = Language.getButton("Button4");
		playasguest = Language.getButton("Button5");
		buttonPane.getChildren().add(playasguest);
		buttonPane1.getChildren().addAll(login, signup);
		
		login.setOnAction(e -> {
			(Main.window).setScene(logInScene);
			(Main.window).setTitle(Language.getLabel("Button3").getText());
		});
		signup.setOnAction(e -> {
			(Main.window).setScene(signInScene);
			(Main.window).setTitle(Language.getLabel("Button4").getText());
		});
		playasguest.setOnAction(e -> {
			PlayAsGuest form3 = new PlayAsGuest("Guest"+(int)(Math.random()*100000));
			Scene gameScene = form3.guest();
			(Main.window).setScene(gameScene);
			(Main.window).setResizable(false);
			(Main.window).setTitle("Game");
		});
		
		HBox languageButtons = new HBox(13);
		
		Button alBtn = new Button();
		Button enBtn = new Button();
		
		Image alImg = new Image("img/al1.png");
		Image enImg = new Image("img/en1.png");
		alBtn.setGraphic(new ImageView(alImg));
		enBtn.setGraphic(new ImageView(enImg));
		
		HBox.setMargin(alBtn, new Insets(5, 1, 5, 4));
		HBox.setMargin(enBtn, new Insets(5, 5, 5, -3));
		
		alBtn.setOnAction(e -> {
			Language.setLocale(new Locale("al"));
		});
		
		enBtn.setOnAction(e -> {
			Language.setLocale(new Locale("en"));
		});
		
		languageButtons.getChildren().addAll(alBtn, enBtn);
		languageButtons.setAlignment(Pos.CENTER);
		
		welcome = Language.getLabel("label14");
		welcome.setPadding(new Insets(3, 3, 3, 3));
		welcome.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 20));

		pane.getChildren().addAll(welcome, buttonPane, buttonPane1, languageButtons);
		return pane;
	}
}