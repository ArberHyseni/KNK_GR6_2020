package app;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import utils.DBConnection;
import utils.Session;

public class PlayAsGuest extends GridPane {
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	public static Scene gameScene;
	
	public PlayAsGuest(String firstName) {
		this.firstName=firstName;
		this.lastName=" ";
		this.email="noreply"+(int)(Math.random()*1000000000)+"@dicegame.com";
		this.password="asjkdjhf";
		addGuest();
		
	};

	public Scene guest() {
		
		try {
			
			String query = "SELECT * FROM users WHERE email = ?";
			
			PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
			preparedStatement.setString(1,  email);

			ResultSet resultSet = preparedStatement.executeQuery();	
			
			if(resultSet.next()) {
				
				Session.setSession(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
				Game game = new Game();
				gameScene = new Scene(game.getGameView(), 400, 400);

			} 
		} 
		
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return gameScene;
		
	}
		
	
	public void addGuest() {
		
		try {
			
			String query = "INSERT INTO users(firstName, lastName, email, password) VALUES(?, ?, ?, ?)";
			PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, this.firstName);
			preparedStatement.setString(2, this.lastName);
			preparedStatement.setString(3, this.email);
			preparedStatement.setString(4, this.password);
			
			if (preparedStatement.executeUpdate() > 0) {
				ResultSet rs = preparedStatement.getGeneratedKeys();
				rs.next();
			    String query2 = "INSERT INTO player_statistics(userId) VALUES(?)";
			    PreparedStatement preparedStatement2 = DBConnection.getConnection().prepareStatement(query2);
			    preparedStatement2.setInt(1, rs.getInt(1));
			    preparedStatement2.executeUpdate();

			}
		}
		
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
}