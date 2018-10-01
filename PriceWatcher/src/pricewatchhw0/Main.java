package pricewatchhw0;

import javax.swing.*;

public class Main extends JFrame {

	private UI ui;
	private Item item;
	
	
	public Main(){
		item = new Item();
		ui = new UI(item);
	}
	public static void main(String[] args){
		new Main().run();	
	}
	public void run() {
		// show a welcome message (ui job)
		ui.showMessage("Welcome to pricewatcher!");
		
		// show/print the item
		ui.showItem();
		ui.promptUser();
		// repeat until the user quit
		// get user selection

		int sel =ui.readSelection();
		while (sel != -1){
			/*switch(sel){
			case 1:
				//get new price
				break;
			case 2:
				//view page of item
				break;
			}*/

			ui.promptUser();
			sel =ui.readSelection();
		}
		//hi
		//hi2
		// process user selection
		// print bye message
		ui.showMessage("Bye");
	}
	

}