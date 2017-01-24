package gameProject;

import java.awt.Graphics;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class AIPlayer extends MovingObject {

	public AIPlayer(double x, double y, int left, int right, int top, int bottom) {
		super(y, y, bottom, bottom, bottom, bottom);
		// TODO Auto-generated constructor stub
	}

	public static int AITroopOneCurrent = -1;
	public static int AITroopTwoCurrent = -1;
	public static int AITroopThreeCurrent = -1;

	static ArrayList<AITroopOne> AItroopOne = new ArrayList<AITroopOne>();
	static ArrayList<AITroopTwo> AItroopTwo = new ArrayList<AITroopTwo>();
	static ArrayList<AITroopThree> AItroopThree = new ArrayList<AITroopThree>();
	static ArrayList<AITroopOne> AItroopOneHealth = new ArrayList<AITroopOne>();
	static ArrayList<AITroopTwo> AItroopTwoHealth = new ArrayList<AITroopTwo>();
	static ArrayList<AITroopThree> AItroopThreeHealth = new ArrayList<AITroopThree>();
	static int width = 1350;
	static int height = 700;

	public static void main(String[] args) 
	{	
		int AIMoney = 0;
		int AIChoice = (int) (Math.random() * 10);
		int AICharacterChoice = (int) (Math.random() * 3);

		if (AIChoice != 5)
		{
			if (AICharacterChoice == 1 && AIMoney >= 30)
			{
				AIMoney = AIMoney - 30;
				AITroopOneCurrent = AITroopOneCurrent +1;
				AItroopOne.add(new AITroopOne(0, 465, 0, width, 0, height));
				AItroopOne.get(AItroopOne.size()-1).setXSpeed(5);
				AItroopOne.get(AItroopOne.size()-1).setYSpeed(0);

				if (AItroopOne.get(AItroopOne.size()-1).getX() == 1200) {
					AItroopOne.remove(AItroopOne.size()-1);
				}

			}
			else if (AICharacterChoice == 2 && AIMoney >= 80)
			{
				AIMoney = AIMoney - 80;
				AITroopTwoCurrent = AITroopTwoCurrent +1;
				AItroopTwo.add(new AITroopTwo(0, 465, 0, width, 0, height));
				AItroopTwo.get(AItroopTwo.size()-1).setXSpeed(5);
				AItroopTwo.get(AItroopTwo.size()-1).setYSpeed(0);

				if (AItroopTwo.get(AItroopTwo.size()-1).getX() == 1200) {
					AItroopTwo.remove(AItroopTwo.size()-1);
				}

			} 
			else if (AICharacterChoice == 3 && AIMoney >= 250)
			{
				AIMoney = AIMoney - 250;
				AITroopThreeCurrent = AITroopThreeCurrent +1;
				AItroopThree.add(new AITroopThree(0, 465, 0, width, 0, height));
				AItroopThree.get(AItroopThree.size()-1).setXSpeed(5);
				AItroopThree.get(AItroopThree.size()-1).setYSpeed(0);

				if (AItroopThree.get(AItroopThree.size()-1).getX() == 1200) {
					AItroopThree.remove(AItroopThree.size()-1);
				}
			}
		}
		else 
		{
			if (AICharacterChoice == 1 && AIMoney >= 100)
			{
				AIMoney = AIMoney - 100;

			}
			else if (AICharacterChoice == 2 && AIMoney >= 200)
			{
				AIMoney = AIMoney - 200;

			}
			else if (AICharacterChoice == 3 && AIMoney >= 300)
			{
				AIMoney = AIMoney - 300;

			}
		}
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void animateOneStep() {
		// TODO Auto-generated method stub

	}
}