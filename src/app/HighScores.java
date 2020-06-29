package app;
import java.util.List;

public class HighScores
{
	private Label highScores;
	private Label highScores2;
	private TableColumn score;
	private TableView table = new TableView();
	

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