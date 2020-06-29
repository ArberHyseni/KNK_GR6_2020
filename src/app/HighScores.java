package app;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Language;

public class HighScores
{
	private Label highScores;
	private Label highScores2;
	private TableColumn player;
	private TableColumn score;
	private Label label2 = new Label();
	private TableView table = new TableView();
	public void getStage2()
	{
		highScores = Language.getLabel("label16");
		highScores2 = Language.getLabel("label17");
		Stage Stage2 = new Stage();
		BorderPane pane = new BorderPane();
		VBox insidePane = new VBox(20);
		insidePane.setPadding(new Insets(20,20,20,20));
		insidePane.setAlignment(Pos.CENTER);

		StatusBar statusBar = new StatusBar();
		Image image = new Image("img/trofe.jpg");
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(150);
		imageView.setFitWidth(150);

		insidePane.getChildren().addAll(imageView,highScores2,label2);

	 	player = Language.getTblColumn("label18");
		score = Language.getTblColumn("label4");

		player.setCellValueFactory(new PropertyValueFactory("player"));
		score.setCellValueFactory(new PropertyValueFactory("score"));
		player.setPrefWidth(300);
		score.setPrefWidth(300);

		table.getColumns().addAll(player, score);
		table.setPrefHeight(300);
		table.setPrefWidth(600);


		Pane tablePane = new Pane();
		tablePane.getChildren().add(table);

		pane.setLeft(insidePane);
		pane.setRight(tablePane);
		pane.setBottom(statusBar.getStatusBar());


		Scene scene = new Scene(pane,800,400);

		Stage2.setTitle(highScores.getText());
		Stage2.setScene(scene);
		showScores();
		Stage2.show();
	}

	private void showScores()
	{
		List<Scores> scores = Scores.getScores();
		ObservableList<Scores> scoreList = FXCollections.observableArrayList();
		for (int i = 0; i < scores.size(); i++) {
			scoreList.add(scores.get(i));
		}
		table.setItems(scoreList);
	}
}