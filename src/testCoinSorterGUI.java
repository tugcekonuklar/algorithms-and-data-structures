import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * @author Student
 *
 *         This class tests the functions and methods of the CoinSorterGUI
 *         class. This class is a GUI application which is sorting coins.
 */
public class testCoinSorterGUI extends Application {

	private Stage window;
	private Scene mainScene;
	private Scene subMenuScene;
	private CoinSorterGUI coinSorterGUI;

	@Override
	public void start(Stage primaryStage) {
		try {
			window = primaryStage;
			window.setTitle("Coin Sorter - Main Menu");
			// initialise coinSorterGUI object
			coinSorterGUI = new CoinSorterGUI();
			// creates the root pane
			GridPane rootGrid = createMainMenuPane(coinSorterGUI);
			// initialise main scene
			mainScene = new Scene(rootGrid, 350, 300);
			window.setScene(mainScene);
			// shows main scene
			window.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * creates main menu pane
	 *
	 * @param coinSorter
	 * @return main menu grid pane
	 */
	private GridPane createMainMenuPane(CoinSorter coinSorter) {
		GridPane rootGrid = createGridPane();
		VBox titleBox = createTitleBox("Welcome", "This application was designed to help you to sort your coins");
		rootGrid.add(titleBox, 0, 0, 2, 1);
		// creates the grid pane components
		Button coinCalculatorButton = addButtonWithLabel(rootGrid, 1, "1:", "Coin Calculator");
		coinCalculatorButton.setOnAction(e -> createCoinCalculatorPane(coinSorter));

		Button multiCoinCalculatorButton = addButtonWithLabel(rootGrid, 2, "2:", "Multi Coin Calculator");
		multiCoinCalculatorButton.setOnAction(e -> createMultiCoinCalculatorPane(coinSorter));

		Button printCoinButton = addButtonWithLabel(rootGrid, 3, "3:", "Print Coin List");
		printCoinButton.setOnAction(e -> createPrintCoinListPane(coinSorter));

		Button setDetailsButton = addButtonWithLabel(rootGrid, 4, "4:", "Set Details");
		setDetailsButton.setOnAction(e -> createSubMenuPane(coinSorter));

		Button displayButton = addButtonWithLabel(rootGrid, 5, "5:", "Display Program Configurations");
		displayButton.setOnAction(e -> createDisplayConfigurationDetailsPane(coinSorter));

		Button quitButton = addButtonWithLabel(rootGrid, 6, "6:", "Quit The Program");
		quitButton.setOnAction(e -> quit());
		return rootGrid;
	}

	/**
	 * creates sub menu pane
	 *
	 * @param coinSorter
	 */
	private void createSubMenuPane(CoinSorter coinSorter) {
		GridPane grid = createGridPane();
		Text scenetitle = new Text("You can update Coin Sorter application config values from this menu");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
		scenetitle.setWrappingWidth(350);
		grid.add(scenetitle, 0, 0, 2, 1);
		// creates the grid pane components
		Button setCurrencyButton = addButtonWithLabel(grid, 1, "1:", "Set Currency");
		setCurrencyButton.setOnAction(e -> createSetCurrencyPane(coinSorter));

		Button setMinButton = addButtonWithLabel(grid, 2, "2:", "Set Minimum Coin Input Value");
		setMinButton.setOnAction(e -> createSetMinimumValuePane(coinSorter));

		Button setMaxButton = addButtonWithLabel(grid, 3, "3:", "Set Maximum Coin Input Value");
		setMaxButton.setOnAction(e -> createSetMaximumValuePane(coinSorter));

		Button quitButton = addButtonWithLabel(grid, 4, "4:", "Return to Main Menu");
		quitButton.setOnAction(e -> goMainMenu());

		subMenuScene = new Scene(grid, 400, 275);
		window.setTitle("Set Details - Sub Menu");
		showScene(window, subMenuScene);
	}

	/**
	 * create set currency pane
	 *
	 * @param coinSorter
	 */
	private void createSetCurrencyPane(CoinSorter coinSorter) {
		GridPane grid = createGridPane();
		VBox titleBox = createTitleBox("Set Currency", "You can set the application currency type");
		grid.add(titleBox, 0, 0, 2, 1);
		// creates the grid pane components
		TextField currency = addTextFieldWithLabel(grid, 1, "Currency :");
		currency.setText(coinSorter.getCurrency());
		final Text result = new Text();
		result.setWrappingWidth(250);
		grid.add(result, 0, 2, 2, 1);

		HBox hbBtn = createConfirmButtonHBox(Pos.BOTTOM_RIGHT);
		Button btnOk = (Button) hbBtn.getChildren().get(0);
		Button btnCancel = (Button) hbBtn.getChildren().get(1);
		grid.add(hbBtn, 1, 3);
		btnOk.setOnAction(e -> {
			// checks for currency text is empty or not
			if (currency.getText().isEmpty()) {
				result.setFill(Color.RED);
				result.setText("Currency can not be empty");
			} else {
				coinSorter.setCurrency(currency.getText());
				goSubMenu();
			}
		});
		btnCancel.setOnAction(e -> goSubMenu());
		moveNext(grid, 400, 200, "Set Currency");
	}

	/**
	 * creates minimum value pane
	 *
	 * @param coinSorter
	 */
	private void createSetMinimumValuePane(CoinSorter coinSorter) {
		GridPane grid = createGridPane();
		VBox titleBox = createTitleBox("Set Minimum Coin Input Value",
				"You can set the application minimum coin value");
		grid.add(titleBox, 0, 0, 2, 1);
		// creates the grid pane components
		TextField minValue = addTextFieldWithLabel(grid, 1, "Minimum Coin Value :");
		minValue.setText(String.valueOf(coinSorter.getMinCoinIn()));
		final Text result = new Text();
		result.setWrappingWidth(250);
		grid.add(result, 0, 2, 2, 1);
		HBox hbBtn = createConfirmButtonHBox(Pos.BOTTOM_RIGHT);
		Button btnOk = (Button) hbBtn.getChildren().get(0);
		Button btnCancel = (Button) hbBtn.getChildren().get(1);
		grid.add(hbBtn, 1, 3);
		btnOk.setOnAction(e -> {
			// checks for minimum value text is empty or not
			if (minValue.getText().isEmpty()) {
				result.setFill(Color.RED);
				result.setText("Minimum coin value can not be empty");
			} else {
				try {
					coinSorter.setMinCoinIn(Integer.valueOf(minValue.getText()));
					goSubMenu();
				} catch (NumberFormatException ex) {
					// if minimum value is not an integer value, retrieve an error to the user.
					result.setFill(Color.RED);
					result.setText("Minimum coin value has to be an integer");
				}
			}
		});
		btnCancel.setOnAction(e -> goSubMenu());
		moveNext(grid, 400, 200, "Set Minimum Coin Value");
	}

	/**
	 * creates set maximum value pane
	 *
	 * @param coinSorter
	 */
	private void createSetMaximumValuePane(CoinSorter coinSorter) {
		GridPane grid = createGridPane();
		VBox titleBox = createTitleBox("Set Maximum Coin Input Value",
				"You can set the application maximum coin value");
		grid.add(titleBox, 0, 0, 2, 1);
		// creates the grid pane components
		TextField maxValue = addTextFieldWithLabel(grid, 1, "Maximum Coin Value :");
		maxValue.setText(String.valueOf(coinSorter.getMaxCoinIn()));
		final Text result = new Text();
		result.setWrappingWidth(250);
		grid.add(result, 0, 2, 2, 1);

		HBox hbBtn = createConfirmButtonHBox(Pos.BOTTOM_RIGHT);
		Button btnOk = (Button) hbBtn.getChildren().get(0);
		Button btnCancel = (Button) hbBtn.getChildren().get(1);
		grid.add(hbBtn, 1, 3);
		btnOk.setOnAction(e -> {
			// checks for maximum value text is empty or not
			if (maxValue.getText().isEmpty()) {
				result.setFill(Color.RED);
				result.setText("Maximum coin value can not be empty");
			} else {
				try {
					coinSorter.setMaxCoinIn(Integer.valueOf(maxValue.getText()));
					goSubMenu();
				} catch (NumberFormatException ex) {
					// if maximum value is not an integer value, retrieve an error to the user.
					result.setFill(Color.RED);
					result.setText("Maximum coin value has to be an integer");
				}
			}
		});
		btnCancel.setOnAction(e -> goSubMenu());
		moveNext(grid, 400, 200, "Set Minimum Coin Value");
	}

	/**
	 * created coin calculator pane
	 *
	 * @param coinSorter
	 */
	private void createCoinCalculatorPane(CoinSorter coinSorter) {
		GridPane grid = createGridPane();
		VBox titleBox = createTitleBox("Coin Calculator",
				"You can exchange total amount of coins with the maximum number of coins of the input coin type that can be exchanged");
		grid.add(titleBox, 0, 0, 2, 1);
		// creates the grid pane components
		TextField totalAmount = addTextFieldWithLabel(grid, 1, "Total Amount:");
		TextField coinType = addTextFieldWithLabel(grid, 2, "Coin Type :");

		final Text result = new Text();
		result.setWrappingWidth(300);
		grid.add(result, 0, 4, 2, 1);

		HBox hbBtn = createConfirmButtonHBox(Pos.BOTTOM_RIGHT);
		Button btnOk = (Button) hbBtn.getChildren().get(0);
		btnOk.setText("Calculate");
		Button btnCancel = (Button) hbBtn.getChildren().get(1);
		btnCancel.setText("Return To Main Menu");
		grid.add(hbBtn, 1, 3);
		btnOk.setOnAction(e -> {
			// checks for total amount value text is empty or not
			if (totalAmount.getText().isEmpty()) {
				result.setFill(Color.RED);
				result.setText("Total amount field can not be empty!");
			}
			// checks for coin type value text is empty or not
			else if (coinType.getText().isEmpty()) {
				result.setFill(Color.RED);
				result.setText("Coin type field can not be empty!");
			} else {
				try {
					int tAmount = Integer.valueOf(totalAmount.getText());
					int currenyType = Integer.valueOf(coinType.getText());
					// if total amount less than minimum amount
					if (tAmount < coinSorter.getMinCoinIn()) {
						result.setFill(Color.RED);
						result.setText(String.format("Total amount has to be bigger than minimum amount : %s",coinSorter.getMinCoinIn()));
					}else if (tAmount > coinSorter.getMaxCoinIn()) {
						// if total amount bigger than maximum amount
						result.setFill(Color.RED);
						result.setText(String.format("Total amount has to be less than maximum amount : %s",coinSorter.getMaxCoinIn()));
					}else {
						result.setFill(Color.BLUEVIOLET);
						result.setText(coinSorter.coinCalculator(tAmount, currenyType));
					}
				} catch (NumberFormatException ex) {
					// if total amount and currency type format is not integer, retrieve an error
					result.setFill(Color.RED);
					result.setText(String.format("Please check your input has to be integer : %s", ex.getMessage()));
				}
			}
		});
		btnCancel.setOnAction(e -> goMainMenu());
		moveNext(grid, 400, 250, "Coin Calculate");
	}

	/**
	 * created multiple coin calculator pane
	 *
	 * @param coinSorter
	 */
	private void createMultiCoinCalculatorPane(CoinSorter coinSorter) {
		GridPane grid = createGridPane();
		VBox titleBox = createTitleBox("Multi Coin Calculator",
				"You can exchange total amount of coins by excluding with the input of coin type");
		grid.add(titleBox, 0, 0, 2, 1);
		// creates the grid pane components
		TextField totalAmount = addTextFieldWithLabel(grid, 1, "Total Amount:");
		TextField coinType = addTextFieldWithLabel(grid, 2, "Coin Type :");

		final Text result = new Text();
		result.setWrappingWidth(250);
		grid.add(result, 0, 4, 2, 1);

		HBox hbBtn = createConfirmButtonHBox(Pos.BOTTOM_RIGHT);
		Button btnOk = (Button) hbBtn.getChildren().get(0);
		btnOk.setText("Calculate");
		Button btnCancel = (Button) hbBtn.getChildren().get(1);
		btnCancel.setText("Return To Main Menu");
		grid.add(hbBtn, 1, 3);
		btnOk.setOnAction(e -> {
			// checks for total amount value text is empty or not
			if (totalAmount.getText().isEmpty()) {
				result.setFill(Color.RED);
				result.setText("Total amount field can not be empty!");
			}
			// checks for coin type value text is empty or not
			else if (coinType.getText().isEmpty()) {
				result.setFill(Color.RED);
				result.setText("Coin type field can not be empty!");
			} else {
				try {
					int tAmount = Integer.valueOf(totalAmount.getText());
					int currenyType = Integer.valueOf(coinType.getText());
					// if total amount less than minimum amount
					if (tAmount < coinSorter.getMinCoinIn()) {
						result.setFill(Color.RED);
						result.setText(String.format("Total amount has to be bigger than minimum amount : %s",coinSorter.getMinCoinIn()));
					}else if (tAmount > coinSorter.getMaxCoinIn()) {
						// if total amount bigger than maximum amount
						result.setFill(Color.RED);
						result.setText(String.format("Total amount has to be less than maximum amount : %s",coinSorter.getMaxCoinIn()));
					}else {
						result.setFill(Color.BLUEVIOLET);
						result.setText(coinSorter.multiCoinCalculator(tAmount, currenyType));
					}
				} catch (NumberFormatException ex) {
					// if total amount and currency type format is not integer, retrieve an error
					result.setFill(Color.RED);
					result.setText(String.format("Please check your input has to be integer : %s", ex.getMessage()));
				}
			}
		});
		btnCancel.setOnAction(e -> goMainMenu());
		moveNext(grid, 400, 250, "Multi Coin Calculate");
	}

	/**
	 * created print coin list pane
	 *
	 * @param coinSorter
	 */
	private void createPrintCoinListPane(CoinSorter coinSorter) {
		// creates base pane as border pane
		BorderPane bPane = createPaneWithBackButton();
		// creates child pane of the main border pane
		GridPane grid = createGridPane();
		// creates child pane title
		Text scenetitle = createPaneTitle("Coin List");
		grid.add(scenetitle, 0, 0, 2, 1);
		// gets coin list info and set as text value
		Text coinListText = new Text(String.valueOf(coinSorter.printCoinList()));
		coinListText.setWrappingWidth(300);
		grid.add(coinListText, 0, 1, 2, 1);
		bPane.setCenter(grid);
		// moves to the scene
		moveNext(bPane, 350, 250, "Print Coin List");
	}

	/**
	 * creates display configuration detail pane
	 *
	 * @param coinSorter
	 */
	private void createDisplayConfigurationDetailsPane(CoinSorter coinSorter) {
		// created base pane with back buttons
		BorderPane bPane = createPaneWithBackButton();
		GridPane grid = createGridPane();
		Text scenetitle = createPaneTitle("Program Configs");
		grid.add(scenetitle, 0, 0, 2, 1);
		// creates the grid pane components
		Label currencyLabel = new Label("Currency :");
		grid.add(currencyLabel, 0, 1);
		Text currencyText = new Text(String.valueOf(coinSorter.getCurrency()));
		grid.add(currencyText, 1, 1);

		Label minCoinLabel = new Label("Minimum Coin :");
		grid.add(minCoinLabel, 0, 2);
		Text minCoinInText = new Text(String.valueOf(coinSorter.getMinCoinIn()));
		grid.add(minCoinInText, 1, 2);

		Label maxCoinLabel = new Label("Maximum Coin :");
		grid.add(maxCoinLabel, 0, 3);
		Text maxCoinInText = new Text(String.valueOf(coinSorter.getMaxCoinIn()));
		grid.add(maxCoinInText, 1, 3);
		bPane.setCenter(grid);
		// moves to the scene
		moveNext(bPane, 350, 250, "Display Program Config");
	}

	/**
	 * created grid pane
	 *
	 * @return grid pane
	 */
	private GridPane createGridPane() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		return grid;
	}

	/**
	 * moves to the next scene
	 *
	 * @param pane
	 * @param width
	 * @param height
	 * @param title
	 */
	private void moveNext(Pane pane, int width, int height, String title) {
		Scene scene = new Scene(pane, width, height);
		window.setTitle(title);
		showScene(window, scene);
	}

	/**
	 * shows scene on the stage
	 *
	 * @param stage
	 * @param scene
	 */
	private void showScene(Stage stage, Scene scene) {
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * creates pane with back main menu button
	 *
	 * @return border pane
	 */
	private BorderPane createPaneWithBackButton() {
		BorderPane bPane = new BorderPane();
		Button btn = new Button("Return To Main Menu");
		HBox hbBtn = new HBox(10);
		hbBtn.setMinHeight(40);
		hbBtn.setAlignment(Pos.BASELINE_CENTER);
		hbBtn.getChildren().add(btn);
		btn.setOnAction(e -> goMainMenu());
		bPane.setBottom(hbBtn);
		return bPane;
	}

	/**
	 * adds text field with label
	 *
	 * @param grid
	 * @param order
	 * @param title
	 * @return text field
	 */
	private TextField addTextFieldWithLabel(GridPane panel, int order, String title) {
		Label label = new Label(title);
		panel.add(label, 0, order);
		TextField texField = new TextField();
		panel.add(texField, 1, order);
		return texField;
	}

	/**
	 * creates title vertical box
	 *
	 * @param title
	 * @param subTitle
	 * @return vertical box
	 */
	private VBox createTitleBox(String title, String subTitle) {
		VBox titleBox = new VBox(10);
		Text scenetitle = new Text(title);
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		Text sub = new Text(subTitle);
		sub.setWrappingWidth(300);
		sub.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
		titleBox.getChildren().addAll(scenetitle, sub);
		return titleBox;
	}

	/**
	 * creates confirm button horizontal Box
	 *
	 * @param position
	 * @return horizontal box
	 */
	private HBox createConfirmButtonHBox(Pos position) {
		Button btnOk = new Button("OK");
		Button btnCancel = new Button("Cancel");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(position);
		hbBtn.getChildren().addAll(btnOk, btnCancel);
		return hbBtn;
	}

	/**
	 * adds button with label
	 *
	 * @param grid
	 * @param order
	 * @param title
	 * @param buttonName
	 * @return button
	 */
	private Button addButtonWithLabel(GridPane grid, int order, String title, String buttonName) {
		Label label = new Label(title);
		grid.add(label, 0, order);
		Button button = new Button(buttonName);
		grid.add(button, 1, order);
		return button;
	}

	/**
	 * creates pane title
	 *
	 * @param title
	 * @return text
	 */
	private Text createPaneTitle(String title) {
		Text scenetitle = new Text(title);
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		return scenetitle;
	}

	/**
	 * quits the application
	 */
	private void quit() {
		Platform.exit();
	}

	/**
	 * goes main menu
	 */
	public void goMainMenu() {
		showScene(window, mainScene);
	}

	/**
	 * goes sub menu
	 */
	public void goSubMenu() {
		showScene(window, subMenuScene);
	}

}
