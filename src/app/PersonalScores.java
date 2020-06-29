package app;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.DBConnection;
import utils.Language;
import utils.Session;

public class PersonalScores 
{
	private String fullName = Session.getFullName();
	private int totalScore, ones, twos, threes, fours, fives, sixes, noOfGames;
	private Label player = Language.getLabel("label15");
	private Label fullNameTxt = new Label(player.getText() + fullName);
	private Label totalScoreLbl = new Label(Integer.toString(totalScore));
	private Label onesLbl = new Label(Integer.toString(ones));
	private Label twosLbl = new Label(Integer.toString(twos));
	private Label threesLbl = new Label(Integer.toString(threes));
	private Label foursLbl = new Label(Integer.toString(fours));
	private Label fivesLbl = new Label(Integer.toString(fives));
	private Label sixesLbl = new Label(Integer.toString(sixes));
	private Label noOfGamesLbl = new Label(Integer.toString(noOfGames));
	private Label totalScoreTxt = Language.getLabel("label20"); 
	private Label onesTxt = Language.getLabel("label21");
	private Label twosTxt = Language.getLabel("label22");
	private Label threesTxt = Language.getLabel("label23");
	private Label foursTxt = Language.getLabel("label24");
	private Label fivesTxt = Language.getLabel("label25");
	private Label sixesTxt = Language.getLabel("label26");
	private Label noOfGamesTxt = Language.getLabel("label27"); 
	private Label personalScores;

	public void getStage3()
	{
		String query = "SELECT * "
					 + "FROM player_statistics p "
					 + "INNER JOIN users u ON u.idusers = p.userId "
					 + "WHERE p.userId = " + Session.id;
		try {
			PreparedStatement stmnt = DBConnection.getConnection().prepareStatement(query);
			ResultSet result = stmnt.executeQuery();
			result.next();
			totalScore = result.getInt("totalScore");
			ones = result.getInt("ones");
			twos = result.getInt("twos");
			threes = result.getInt("threes");
			fours = result.getInt("fours");
			fives = result.getInt("fives");
			sixes = result.getInt("sixes");
			noOfGames = result.getInt("noOfGames");
			totalScoreLbl.setText(totalScoreTxt.getText() + String.valueOf(totalScore));
			onesLbl.setText(onesTxt.getText() + String.valueOf(ones));
			twosLbl.setText(twosTxt.getText()  + String.valueOf(twos));
			threesLbl.setText(threesTxt.getText()  + String.valueOf(threes));
			foursLbl.setText(foursTxt.getText()  + String.valueOf(fours));
			fivesLbl.setText(fivesTxt.getText()  + String.valueOf(fives));
			sixesLbl.setText(sixesTxt.getText()  + String.valueOf(sixes));
			noOfGamesLbl.setText(noOfGamesTxt.getText() + String.valueOf(noOfGames));
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		personalScores = Language.getLabel("label19");
		BorderPane pane = new BorderPane();
		StatusBar statusBar = new StatusBar();
		VBox vbox = new VBox(10);
		vbox.getChildren().addAll(fullNameTxt, totalScoreLbl, onesLbl, twosLbl, threesLbl, foursLbl, fivesLbl, sixesLbl, noOfGamesLbl);
		vbox.setAlignment(Pos.CENTER);
		Stage stage3 = new Stage();  
		
		pane.setCenter(vbox);
		pane.setBottom(statusBar.getStatusBar());
			
		stage3.setScene(new Scene(pane, 500, 500));
		stage3.setTitle(personalScores.getText());
				
		stage3.show();
	}
}