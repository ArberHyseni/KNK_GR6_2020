package app;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.DBConnection;

public class Scores {
	private String player;
	private int score;
	
	public Scores(String player, int score) {
		this.player = player;
		this.score = score;
	}
	
	public String getPlayer() {
		return player;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setPlayer(String player) {
		this.player = player;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public static List<Scores> getScores() {
		List<Scores> scores = new ArrayList();
		
		try 
		{
			String query = "SELECT u.FirstName, u.LastName, g.score "
						 + "FROM games_played g "
						 + "INNER JOIN users u ON u.idusers = g.playerId "
						 + "ORDER BY g.score DESC";
			
			PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();	
			while(resultSet.next())
			{
				Scores score = new Scores(resultSet.getString(1) + " " + resultSet.getString(2), resultSet.getInt(3));
				scores.add(score);
			}
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
		}
		return scores;
	}
}