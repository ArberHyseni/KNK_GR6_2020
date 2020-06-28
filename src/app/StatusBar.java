package app;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import utils.Language;
import utils.Session;

public class StatusBar extends HBox
{
	private Label player;

	public HBox getStatusBar()
	{
		player = Language.getLabel("label15");
		HBox statusBar = new HBox(10);
		Label userInfo = new Label();
		userInfo.setPadding(new Insets(5,20,5,5));
		String fullName = Session.getFullName();
		userInfo.setText(player.getText() + fullName);
		userInfo.setStyle("-fx-text-fill:black;");
		statusBar.getChildren().add(userInfo);
		statusBar.setStyle("-fx-background-color:linear-gradient(#c2b6b6, #576574);");

		return statusBar;
	}
}