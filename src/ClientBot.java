import java.awt.*;

public class ClientBot
{
	ClientPlayer player;
	//all of these PixelGroups do not change based on champion
	//these are protected because they are used in ChampionRoleBot files
	protected PixelGroup championSelect;
	protected PixelGroup runesTab;
	protected PixelGroup runesLocked;
	protected PixelGroup championSearchBox;
	protected PixelGroup acceptMatchButton;
	protected PixelGroup startQueueButton;
	private PixelGroup runesFailedToSave;
	private PixelGroup loadScreen;
	private PixelGroup honorSelect;
	private PixelGroup playAgainButton;
	private PixelGroup cannotCreateCustomRunes;
	private PixelGroup dailyReward;
	private PixelGroup levelUp;
	private PixelGroup missions;
	private PixelGroup genericNotificationBox;
	private PixelGroup notificationBoxDarkenedBackground;

	public ClientBot() throws AWTException
	{
		player = new ClientPlayer();

		championSelect = new PixelGroup(new Pixel(1148, 749, 3, 58, 70));
		runesTab = new PixelGroup(new Pixel(1179, 860, 90, 89, 85));
		runesLocked = new PixelGroup(new Pixel(512, 397, 170, 170, 170));
		championSearchBox = new PixelGroup(new Pixel(1146, 264, 3, 8, 8));
		acceptMatchButton = new PixelGroup(new Pixel(994, 361, 33, 77, 98));
		startQueueButton = new PixelGroup(new Pixel(635, 585, 26, 55, 32));
		runesFailedToSave = new PixelGroup(new Pixel(855, 497, 93, 94, 89));
		loadScreen = new PixelGroup(new Pixel(955, 578, 57, 53, 50));
		honorSelect = new PixelGroup(new Pixel(882, 216, 225, 230, 210));
		playAgainButton = new PixelGroup(new Pixel(1128, 731, 9, 32, 40));
		cannotCreateCustomRunes = new PixelGroup(new Pixel(1074, 503, 132, 130, 119));
		dailyReward = new PixelGroup(new Pixel(323, 296, 50, 40, 30));
		levelUp = new PixelGroup(new Pixel(1017, 314, 238, 228, 208));
		missions = new PixelGroup(new Pixel(1263, 353, 86, 66, 35));
		genericNotificationBox = new PixelGroup(new Pixel(1014, 548, 1, 10, 19));
		notificationBoxDarkenedBackground = new PixelGroup(new Pixel(702, 842, 2, 6, 9));
	}
	
	public void tick()
	{
		if(acceptMatchButton.isVisible())
		{
			System.out.println("accept match");
			
			player.acceptMatch();
		}
		else if(startQueueButton.isVisible())
		{
			System.out.println("start queue");
			
			player.startQueue();
		}
		else if(playAgainButton.isVisible())
		{
			System.out.println("play again");
			
			player.playAgain();
		}
		else if(honorSelect.isVisible())
		{
			System.out.println("honor teammate");
			
			player.honorTeammate();
		}
		else if(levelUp.isVisible())
		{
			System.out.println("level up");
			
			player.dismissLevelUp();
		}
		else if(dailyReward.isVisible())
		{
			System.out.println("daily reward");
			
			player.acceptDailyReward();
		}
		else if(missions.isVisible())
		{
			System.out.println("mission complete");
			
			player.dismissMissions();
		}
		else if(runesFailedToSave.isVisible())
		{
			System.out.println("rune save failed");
			
			player.declineSaveRunePageRequest();
			//decline because it's likely the bot messed up and
			//left rune slots open, better to go with a filled in but
			//possibly non-optimal rune page than an incomplete one
			
			//this will almost never run, edge case
		}
		else if(cannotCreateCustomRunes.isVisible())
		{
			System.out.println("cannot create custom runes");
			
			player.dismissCannotCreateCustomRunes();
			//only runs if a different part of the LoL-Bot fails
			//runs when champion select sees wrong pixels
			
			//this will almost never run, edge case
		}
		else if(genericNotificationBox.isVisible() || notificationBoxDarkenedBackground.isVisible())
		{
			System.out.println("unknown notification / text box");
			
			//hopefully will click on the correct button(s) by chance
			//random num ranges are about the center of the client's dimensions
			player.leftClickAt(player.randomInt(792, 1180), player.randomInt(396, 644));
		}
		else if(loadScreen.isVisible())
		{
			//player.cursorDance();
			System.out.println("load screen");
		}
		else
		{
			System.out.println("nothing visible");
		}
	}
}