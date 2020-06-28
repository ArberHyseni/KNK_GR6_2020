package app;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import utils.DBConnection;
import utils.Help;
import utils.Language;
import utils.Session;

public class Game extends BorderPane
{
	public static Scene logInScene, signInScene, gameScene;
	private int score = 0;
	private int rolls = 0;
	private DiceGen diceGen = new DiceGen();
	private ImageView face = new ImageView(diceGen.getFace());
	private int[] stats = {0, 0, 0, 0, 0, 0};
	private Menu fileMenu;
	private MenuItem newGame;
	private MenuItem highScores;
	private MenuItem personalScores;
	private Menu languageMenu;
	private Menu helpMenu;
	private MenuItem about;
	private Button rollDice;
	private Label roundsLabel1;
	private Label roundsLabel2;
	private Button signout;
	//get message
	private Label scoreLabel1 = Language.getLabel("label5");
	private Label scoreLabel5 = Language.getLabel("label6");
	private Label scoreLabel2;
	private Label scoreLabel3 = new Label("");
	private Label scoreLabel4;

	public BorderPane getGameView()
	{
		BorderPane pane= new BorderPane();
		StatusBar statusBar = new StatusBar();
		fileMenu = Language.getMenu("Menu1");
		newGame = Language.getMenuItem("MenuItem1");
		highScores = Language.getMenuItem("MenuItem2");
		personalScores = Language.getMenuItem("MenuItem3");

		newGame.setOnAction(e->
								{Game game = new Game();
								 gameScene = new Scene(game.getGameView(), 400, 400);
								 (Main.window).setScene(gameScene);
								 (Main.window).setTitle("Game");});
		highScores.setOnAction(e-> new HighScores().getStage2());
		personalScores.setOnAction(e-> new PersonalScores().getStage3());

		fileMenu.getItems().addAll(newGame, new SeparatorMenuItem(), highScores, personalScores);

		languageMenu = Language.getMenu("Menu2");
		ToggleGroup viewToggle = new ToggleGroup();
		RadioMenuItem al = new RadioMenuItem("_Shqip");
		RadioMenuItem en = new RadioMenuItem("_English");
		al.setToggleGroup(viewToggle);
		en.setToggleGroup(viewToggle);

		String lang = Language.getLocale().toString();

		if (lang.equals("al")) {
			al.setSelected(true);
		} else {
			en.setSelected(true);
		}


		languageMenu.getItems().addAll(al, en);

		al.setOnAction(e -> {
			Language.setLocale(new Locale("al"));
		});
		en.setOnAction(e -> {
			Language.setLocale(new Locale("en"));
		});



		helpMenu = Language.getMenu("Menu3");

		about = Language.getMenuItem("MenuItem4");


	  helpMenu.getItems().add(about);

	  about.setOnAction(e -> {Help.about();});

		MenuBar bar = new MenuBar();
		bar.getMenus().addAll(fileMenu,languageMenu,helpMenu);


		rollDice = Language.getButton("Button1");

		Label scoreLabel = new Label();
		roundsLabel1 = Language.getLabel("label3");
		//end of game message
		roundsLabel2 = Language.getLabel("label7");
		roundsLabel2.setVisible(false);
		rollDice.setOnAction(e -> {
			roundsLabel2.setVisible(false);
			int roll = diceGen.roll();
			score += roll;
			stats[roll-1]++;
			diceGen.setFace(roll);
			face.setImage(diceGen.getFace());
			rolls++;
			scoreLabel3.setText(scoreLabel1.getText() + " " + Integer.toString(10 - rolls) + " " + scoreLabel5.getText());
			scoreLabel3.setVisible(true);
			roundsLabel1.setVisible(false);
			scoreLabel.setText(Integer.toString(score));
			//reset stats and score
			if (rolls == 10) {
				insertScore(Session.getId(), score, stats);
				rolls = 0;
				score = 0;
				for (int i = 0; i < stats.length; i++) {
					stats[i] = 0;
				}
				roundsLabel2.setVisible(true);
				scoreLabel3.setVisible(false);
			}

		});

		signout = Language.getButton("Button2");
		//signs out on click and clears session
		signout.setOnAction(e -> {
			(Main.window).setScene(Main.indexScene);
			(Main.window).setTitle("Welcome");
			Session.clearSession();
		});

		HBox hbox = new HBox(10);
		hbox.setAlignment(Pos.TOP_RIGHT);
		hbox.getChildren().add(signout);

		StackPane labels = new StackPane();
		labels.getChildren().addAll(roundsLabel1, roundsLabel2, scoreLabel3);

		VBox vbox = new VBox(10);
		vbox.setPadding(new Insets(20,20,20,20));
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(labels, scoreLabel, rollDice);

		BorderPane box = new BorderPane();

		face.setFitHeight(150);
		face.setFitWidth(150);

		box.setTop(hbox);
		box.setCenter(face);
		box.setBottom(vbox);

		pane.setTop(bar);
		pane.setCenter(box);
		pane.setBottom(statusBar.getStatusBar());
		return pane;
	}

	public boolean insertScore(int playerId, int score, int[] stats) {
		try {
			String query = "INSERT INTO games_played(playerId, score) "
					     + "VALUES (" + playerId + ", " + score + ");"
					     + "UPDATE player_statistics "
					     + "SET totalScore = totalScore + " + score + ", "
					     + "ones = ones + " + stats[0] + ", "
						 + "twos = twos + " + stats[1] + ", "
						 + "threes = threes + " + stats[2] + ", "
						 + "fours = fours + " + stats[3] + ", "
						 + "fives = fives + " + stats[4] + ", "
						 + "sixes = sixes + " + stats[5] + ", "
						 + "noOfGames = noOfGames + 1 "
						 + "WHERE userId = " + playerId + ";";
			PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);

			return (preparedStatement.executeUpdate() > 0);
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}
}