
import java.util.List;

/**
 * @author Student
 * 
 * Unit Tests for CoinSorter Class
 */
public class testCoinSorterUnit {

	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		testCoinSorterUnit test = new testCoinSorterUnit();
		test.run();
	}
	
	public void run() {
		shouldPrintCoinListWorkSuccessfully();
		shouldDisplayProgramConfigsWorksSuccessfully();
		shouldDisplayProgramConfigsWithCustomConstWorksSuccessfully();
		shouldGetMinInWorkSuccessfully();
		shouldSetMinInWorkSuccessfully();
		shouldGetMaxInWorkSuccessfully();
		shouldSetMaxInWorkSuccessfully();
		shouldGetCurrencyWorkSuccessfully();
		shouldSetCurrencyWorkSuccessfully();
		shouldCoinCalculatorWorkSuccessfully();
		shouldCoinCalculatorRetrieveMinValueError();
		shouldCoinCalculatorRetrieveMaxValueError();
		shouldCoinCalculatorRetrieveInvalidCoinValueError();
		shouldMultiCoinCalculatorWorkSuccessfully();
		shouldMultiCoinCalculatorRetrieveMinValueError();
		shouldMultiCoinCalculatorRetrieveMaxValueError();
		shouldMultiCoinCalculatorRetrieveInvalidCoinValueError();
	}
	
	private void shouldPrintCoinListWorkSuccessfully() {
		System.out.print("PrintCoinList() Success Test ->\t");
		String expected = "The current coin denominations are in circulation: 200, 100, 50, 20, 10";
		CoinSorter coinSorter = new CoinSorter();
		String result = coinSorter.printCoinList();
		printResult(result.equals(expected));
	}
	
	private void shouldDisplayProgramConfigsWorksSuccessfully() {
		System.out.print("DisplayProgramConfigs() Success Test ->\t");
		String expected = "The current currency sterlin and the current minimum 0 and maximum value 10000 accepted as input.";
		CoinSorter coinSorter = new CoinSorter();
		String result = coinSorter.displayProgramConfigs();
		printResult(result.equals(expected));
	}
	
	private void shouldDisplayProgramConfigsWithCustomConstWorksSuccessfully() {
		System.out.print("DisplayProgramConfigs() Success with Custom Const Test ->\t");
		String expected = "The current currency euro and the current minimum 10 and maximum value 2000 accepted as input.";
		CoinSorter coinSorter = new CoinSorter("euro",10,2000,List.of(200, 100, 50, 20, 10));
		String result = coinSorter.displayProgramConfigs();
		printResult(result.equals(expected));
	}
	
	private void shouldGetMinInWorkSuccessfully() {
		System.out.print("getMinIn() Success Test ->\t");
		int expected = 0;
		CoinSorter coinSorter = new CoinSorter();
		int result = coinSorter.getMinCoinIn();
		printResult(result == expected);
	}
	
	private void shouldSetMinInWorkSuccessfully() {
		System.out.print("setMinIn() Success Test ->\t");
		int expected = 10;
		CoinSorter coinSorter = new CoinSorter();
		coinSorter.setMinCoinIn(10);
		printResult(coinSorter.getMinCoinIn() == expected);
	}
	
	private void shouldGetMaxInWorkSuccessfully() {
		System.out.print("getMaxIn() Success Test ->\t");
		int expected = 10000;
		CoinSorter coinSorter = new CoinSorter();
		int result = coinSorter.getMaxCoinIn();
		printResult(result == expected);
	}
	
	private void shouldSetMaxInWorkSuccessfully() {
		System.out.print("setMaxIn() Success Test ->\t");
		int expected = 20;
		CoinSorter coinSorter = new CoinSorter();
		coinSorter.setMaxCoinIn(20);
		printResult(coinSorter.getMaxCoinIn() == expected);
	}
	
	private void shouldGetCurrencyWorkSuccessfully() {
		System.out.print("getCurrency() Success Test ->\t");
		String expected = "sterlin";
		CoinSorter coinSorter = new CoinSorter();
		String result = coinSorter.getCurrency();
		printResult(result.equals(expected));
	}
	
	private void shouldSetCurrencyWorkSuccessfully() {
		System.out.print("setCurrency() Success Test ->\t");
		String expected = "euro";
		CoinSorter coinSorter = new CoinSorter();
		coinSorter.setCurrency("euro");
		printResult(coinSorter.getCurrency().equals(expected));
	}
	
	private void shouldCoinCalculatorWorkSuccessfully() {
		System.out.print("coinCalculator() Success Test ->\t");
		String expected = "A total of 11 x 50p coins can be exchanged, with a remainder of 12p";
		CoinSorter coinSorter = new CoinSorter();
		String result = coinSorter.coinCalculator(562,50);
		printResult(result.equals(expected));
	}
	
	private void shouldCoinCalculatorRetrieveMinValueError() {
		System.out.print("coinCalculator() Min Validation Error Test ->\t");
		String expected = "Total amount can not be less than 0";
		CoinSorter coinSorter = new CoinSorter();
		String result = coinSorter.coinCalculator(-1,50);
		printResult(result.equals(expected));
	}
	
	private void shouldCoinCalculatorRetrieveMaxValueError() {
		System.out.print("coinCalculator() Max Validation Error Test ->\t");
		String expected = "Total amount can not be bigger than 10000";
		CoinSorter coinSorter = new CoinSorter();
		String result = coinSorter.coinCalculator(10001,50);
		printResult(result.equals(expected));
	}
	
	private void shouldCoinCalculatorRetrieveInvalidCoinValueError() {
		System.out.print("coinCalculator() Invalid Coin Type Validation Error Test ->\t");
		String expected = "Coin Type is not valid : 999";
		CoinSorter coinSorter = new CoinSorter();
		String result = coinSorter.coinCalculator(100,999);
		printResult(result.equals(expected));
	}
	
	private void shouldMultiCoinCalculatorWorkSuccessfully() {
		System.out.print("multiCoinCalculator() Success Test ->\t");
		String expected = "The coins exchanged are: 2 x 200p, 1 x 100p, 0 x 50p, 3 x 20p, 0 x 10p, with a remainder of 2p";
		CoinSorter coinSorter = new CoinSorter();
		String result = coinSorter.multiCoinCalculator(562,50);
		printResult(result.equals(expected));
	}
	
	private void shouldMultiCoinCalculatorRetrieveMinValueError() {
		System.out.print("multiCoinCalculator() Min Validation Error Test ->\t");
		String expected = "Total amount can not be less than 0";
		CoinSorter coinSorter = new CoinSorter();
		String result = coinSorter.multiCoinCalculator(-1,50);
		printResult(result.equals(expected));
	}
	
	private void shouldMultiCoinCalculatorRetrieveMaxValueError() {
		System.out.print("multiCoinCalculator() Max Validation Error Test ->\t");
		String expected = "Total amount can not be bigger than 10000";
		CoinSorter coinSorter = new CoinSorter();
		String result = coinSorter.multiCoinCalculator(10001,50);
		printResult(result.equals(expected));
	}
	
	private void shouldMultiCoinCalculatorRetrieveInvalidCoinValueError() {
		System.out.print("multiCoinCalculator() Invalid Coin Type Validation Error Test ->\t");
		String expected = "Coin Type is not valid : 999";
		CoinSorter coinSorter = new CoinSorter();
		String result = coinSorter.multiCoinCalculator(100,999);
		printResult(result.equals(expected));
	}
	
	private void printResult(boolean result) {
		System.out.print(result ? "Passed" : "Failed");
		System.out.println();
	}

}
