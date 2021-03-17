import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Student
 *
 *         This class tests the functions and methods of CoinSorter class. This
 *         class is running on command line
 */
public class testCoinSorter {

	private static CoinSorter coinSorter;

	/**
	 * Main method
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		testCoinSorter sorter = new testCoinSorter();
		coinSorter = new CoinSorter();
		Scanner sc = new Scanner(System.in);
		int command = -1;
		// until user press quit action, main menu will prompt to user
		do {
			try {
				// prints main menu option
				System.out.println("***Coin Sorter - Main Menu***\r\n" + "1 - Coin calculator\r\n"
						+ "2 - Multiple coin calculator\r\n" + "3 - Print coin list\r\n" + "4 - Set details\r\n"
						+ "5 - Display program configurations\r\n" + "6 - Quit the program\r\n");
				command = sc.nextInt();
				// runs main menu commands
				sorter.runMainMenuCommands(command, sc);
			} catch (InputMismatchException ex) {
				System.out.println("Main menu command has to be an integer, invalid command:" + sc.nextLine());
			}

		} while (command != 6);
	}

	/**
	 * runs main method commands
	 *
	 * @param command
	 * @param scanner
	 */
	private void runMainMenuCommands(int command, Scanner sc) {
		// command run decision point
		switch (command) {
		case 1:
			calculateCoin(sc);
			break;
		case 2:
			calculateMultiCoin(sc);
			break;
		case 3:
			printCoinList();
			break;
		case 4:
			goToSubMenu(sc);
			break;
		case 5:
			displayProgramConfigs();
			break;
		case 6:
			System.out.println("Quited");
			break;
		default:
			System.out.println("Command is not valid :" + command);
			break;
		}

	}

	/**
	 * calculates the coins
	 *
	 * @param scanner
	 */
	private void calculateCoin(Scanner sc) {
		// title
		System.out.println("------------------------------");
		System.out.println("1: Coin Calculator ");
		System.out.println("------------------------------");
		System.out.println(
				"You can exchange total amount of coins with the maximum number of coins of the input coin type that can be exchanged");
		int totalAmount = -1;
		int coinType = -1;
		// will prompt to user until total amount and currency type is inserted
		do {
			try {
				System.out.println("Total Amount : ");
				totalAmount = sc.nextInt();
				if (totalAmount > coinSorter.getMaxCoinIn()) {
					System.out.println("Total amount can not be bigger than " + coinSorter.getMaxCoinIn());
				} else if (totalAmount < coinSorter.getMinCoinIn()) {
					System.out.println("Total amount can not be less than " + coinSorter.getMinCoinIn());
				} else if (totalAmount > coinSorter.getMaxCoinIn()) {
					System.out.println("Total amount can not be bigger than " + coinSorter.getMaxCoinIn());
				} else {
					// if total amount is valid go and ask currency type
					do {
						try {
							System.out.println("Coin Type : ");
							coinType = sc.nextInt();
						} catch (InputMismatchException ex) {
							// if currency type is not valid format as integer them prompt error
							System.out.println("Coin type has to be an integer, invalid value " + sc.nextLine());
						}
					} while (coinType < 0);
				}
			} catch (InputMismatchException ex) {
				// if total amount is not valid format as integer them prompt error
				System.out.println("Total amount has to be an integer, invalid value " + sc.nextLine());
			}
		} while (totalAmount < 0);

		// calculates the result and return
		String result = coinSorter.coinCalculator(totalAmount, coinType);
		System.out.println("Result : " + result);
		System.out.println();
	}

	/**
	 * calculates multiple coins
	 *
	 * @param scanner
	 */
	private void calculateMultiCoin(Scanner sc) {
		// title
		System.out.println("------------------------------");
		System.out.println("2: Multi Coin Calculator ");
		System.out.println("------------------------------");
		System.out.println("You can exchange total amount of coins by excluding with the input of coin type");
		int totalAmount = -1;
		int coinType = -1;
		// will prompt to user until total amount and currency type is inserted
		do {
			try {
				System.out.println("Total Amount : ");
				totalAmount = sc.nextInt();
				if (totalAmount > coinSorter.getMaxCoinIn()) {
					System.out.println("Total amount can not be bigger than " + coinSorter.getMaxCoinIn());
				} else if (totalAmount < coinSorter.getMinCoinIn()) {
					System.out.println("Total amount can not be less than " + coinSorter.getMinCoinIn());
				} else if (totalAmount > coinSorter.getMaxCoinIn()) {
					System.out.println("Total amount can not be bigger than " + coinSorter.getMaxCoinIn());
				} else {
					// if total amount is valid go and ask currency type
					do {
						try {
							System.out.println("Excluded Coin Type : ");
							coinType = sc.nextInt();
						} catch (InputMismatchException ex) {
							// if currency type is not valid format as integer them prompt error
							System.out
									.println("Excluded Coin type has to be an integer, invalid value " + sc.nextLine());
						}
					} while (coinType < 0);
				}
			} catch (InputMismatchException ex) {
				// if total amount is not valid format as integer them prompt error
				System.out.println("Total amount has to be an integer, invalid value " + sc.nextLine());
			}
		} while (totalAmount < 0);

		// calculates multiple coin sort then returns result
		String result = coinSorter.multiCoinCalculator(totalAmount, coinType);
		System.out.println("Result : " + result);
		System.out.println();
	}

	/**
	 * prints coin sorter list
	 */
	private void printCoinList() {
		// title
		System.out.println("------------------------------");
		System.out.println("3: Print Coin List ");
		System.out.println("------------------------------");
		// returns coin list detail
		String result = coinSorter.printCoinList();
		System.out.println("Result : " + result);
		System.out.println();
	}

	/**
	 * displays coin sorter configurations
	 */
	private void displayProgramConfigs() {
		// title
		System.out.println("------------------------------");
		System.out.println("5: Display Program Configs ");
		System.out.println("------------------------------");
		// returns coin sorter config details
		String result = coinSorter.displayProgramConfigs();
		System.out.println("Result : " + result);
		System.out.println();
	}

	/**
	 * goes to sub menu
	 *
	 * @param sc
	 */
	private void goToSubMenu(Scanner sc) {
		// title
		System.out.println("------------------------------");
		System.out.println("4: Set Details ");
		System.out.println("------------------------------");
		int command = -1;
		// prompt until user set quit command from sum menu
		do {
			try {
				System.out.println("***Set Details Sub-Menu***\r\n" + "1 - Set currency\r\n"
						+ "2 - Set minimum coin input value\r\n" + "3 - Set maximum coin input value\r\n"
						+ "4 - Return to main menu\r\n");
				command = sc.nextInt();
				// runs sub menu commands
				runSubMenuCommands(command, sc);
			} catch (InputMismatchException ex) {
				// if command is not a valid command as integer, retrieve error
				System.out.println("Sub menu command has to be an integer, invalid command:" + sc.nextLine());
			}
		} while (command != 4);

	}

	/**
	 * runs sub menu commands
	 *
	 * @param command
	 * @param scanner
	 */
	private void runSubMenuCommands(int command, Scanner sc) {
		// command run decision point
		switch (command) {
		case 1:
			setCurrency(sc);
			break;
		case 2:
			setMinCoin(sc);
			break;
		case 3:
			setMaxCoin(sc);
			break;
		case 4:
			break;
		default:
			System.out.println("Sub menu command is not valid :" + command);
			break;
		}
	}

	/**
	 * sets currency info
	 *
	 * @param scanner
	 */
	private void setCurrency(Scanner sc) {
		// title
		System.out.println("------------------------------");
		System.out.println("1: Set Currency ");
		System.out.println("------------------------------");
		System.out.println("You can set coin sorter currency");
		String currency = "";
		// prompt until user set valid input
		do {
			System.out.println("Currency : ");
			currency = sc.next();
			// checks input is empty or not
			if (currency.isEmpty()) {
				// if empty retrieve an error
				System.out.println("Currency can not be empty  ");
			}

		} while (currency.isEmpty());

		coinSorter.setCurrency(currency);
		System.out.println("Currency updated with " + currency);
		System.out.println();
	}

	/**
	 * sets minimum coin value
	 *
	 * @param scanner
	 */
	private void setMinCoin(Scanner sc) {
		// title
		System.out.println("------------------------------");
		System.out.println("2: Set Minimum Coin ");
		System.out.println("------------------------------");
		System.out.println("You can set coin sorter minimum coin value");
		int minValue = -1;
		// prompt until user set valid input
		do {
			try {
				System.out.println("Minimum Value Amount : ");
				minValue = sc.nextInt();
				// checks inserted min value can not be less than 0
				if (minValue < 0) {
					System.out.println("Minimum value can not be less then 0");
				}
			} catch (InputMismatchException ex) {
				// if input value is not valid as integer, retrieves an error
				System.out.println("Minimum value has to be an integer, invalid value: " + sc.nextLine());
			}
		} while (minValue < 0);

		// sets minimum coin value
		coinSorter.setMinCoinIn(minValue);
		System.out.println("Minimum coin value updated with " + minValue);
		System.out.println();
	}

	/**
	 * sets maximum coin value
	 *
	 * @param sc
	 */
	private void setMaxCoin(Scanner sc) {
		// title
		System.out.println("------------------------------");
		System.out.println("3: Set Maximum Coin ");
		System.out.println("------------------------------");
		System.out.println("You can set coin sorter maximum coin value");
		int maxValue = -1;
		// prompt until user set valid input
		do {
			try {
				System.out.println("Maximum Value Amount : ");
				maxValue = sc.nextInt();
				// checks inserted max value can not be less then 0
				if (maxValue < 0) {
					System.out.println("Maximum value can not be less then 0");
				}
			} catch (InputMismatchException ex) {
				// if input value is not valid as integer, retrieves an error
				System.out.println("Maximum value has to be an integer, invalid value : " + sc.nextLine());
			}

		} while (maxValue < 0);

		// sets maximum coin information
		coinSorter.setMaxCoinIn(maxValue);
		System.out.println("Maximum coin value updated with " + maxValue);
		System.out.println();
	}
}
