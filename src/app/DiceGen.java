package app;

import java.util.Random;

import javafx.scene.image.Image;

public class DiceGen {
	//gets welcome dice picture
	private Image currentFace = new Image("img/d1.png");
	public int roll() {
		Random generator = new Random(System.currentTimeMillis());
		//returns numbers from 1 to 6 randomly
		return generator.nextInt(6) + 1;
	}
	public void setFace(int num) {
		switch(num) {
		case 1:
			currentFace = new Image("img/d1.png");
			break;
		case 2:
			currentFace = new Image("img/d2.png");
			break;
		case 3:
			currentFace = new Image("img/d3.png");
			break;
		case 4:
			currentFace = new Image("img/d4.png");
			break;
		case 5:
			currentFace = new Image("img/d5.png");
			break;
		case 6:
			currentFace = new Image("img/d6.png");
			break;
		default:
			//In case of exception
			break;			
		}
	}
	
	public Image getFace() {
		return currentFace;
	}
}