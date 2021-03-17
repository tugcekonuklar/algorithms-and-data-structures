import java.util.List;

/**
 * @author Student
 *
 * This class is to sort coins
 */
public class CoinSorter {

	private String currency;
	private int minCoinIn;
	private int maxCoinIn;
	private List<Integer> coinList;

	public CoinSorter() {
		this.currency = "sterlin";
		this.minCoinIn = 0;
		this.maxCoinIn = 10000;
		this.coinList = List.of(200, 100, 50, 20, 10);
	}

	public CoinSorter(String currency, int minCoinIn, int maxCoinIn, List<Integer> coinList) {
		this.currency = currency;
		this.minCoinIn = minCoinIn;
		this.maxCoinIn = maxCoinIn;
		this.coinList = coinList;
	}

	/**
	 * Gets currency
	 * 
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * Sets Currency
	 * 
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * Gets Minimum Coin Value
	 * 
	 * @return the minCoinIn
	 */
	public int getMinCoinIn() {
		return minCoinIn;
	}

	/**
	 * Sets Minimum Coin Value
	 * 
	 * @param minCoinIn the minCoinIn to set
	 */
	public void setMinCoinIn(int minCoinIn) {
		this.minCoinIn = minCoinIn;
	}

	/**
	 * Gets Maximum Coin Value
	 * 
	 * @return the maxCoinIn
	 */
	public int getMaxCoinIn() {
		return maxCoinIn;
	}

	/**
	 * Sets Minimum Coin Value
	 * 
	 * @param maxCoinIn the maxCoinIn to set
	 */
	public void setMaxCoinIn(int maxCoinIn) {
		this.maxCoinIn = maxCoinIn;
	}

	/**
	 * Prints all coin list
	 * 
	 * @return
	 */
	public String printCoinList() {
		String coins = "";
		for (int type : coinList) {
			coins += String.valueOf(type);
			if(type != coinList.get(coinList.size()-1)) {
				coins += ", ";
			}
		}
		return String.format("The current coin denominations are in circulation: %s", coins);
	}

	/**
	 * Calculate coins with coin type
	 * 
	 * @param totalValue
	 * @param coinType
	 * @return result of the calculation
	 */
	public String coinCalculator(int totalValue, int coinType) {
		String errorMessage = validate(totalValue, coinType);
		if (!errorMessage.isEmpty()) {
			return errorMessage;
		}
		int division = totalValue / coinType;
		int remainder = totalValue % coinType;
		return String.format("A total of %s x %sp coins can be exchanged, with a remainder of %sp", division, coinType,
				remainder);
	}

	/**
	 * Calculate coin without excluded coin type
	 * 
	 * @param totalValue
	 * @param excludedCoinType
	 * @return result of the calculation
	 */
	public String multiCoinCalculator(int totalValue, int excludedCoinType) {
		String errorMessage = validate(totalValue, excludedCoinType);
		if (!errorMessage.isEmpty()) {
			return errorMessage;
		}
		String result = "The coins exchanged are: ";
		int remainder = totalValue;
		for (int type : coinList) {
			if (type == excludedCoinType) {
				result += String.format("0 x %sp, ", excludedCoinType);
			}else {
				int division = remainder / type;
				remainder = remainder % type;
				result += String.format("%s x %sp, ", division, type);
			}
			
			if (type == coinList.get(coinList.size() - 1)) {
				result += String.format("with a remainder of %sp", remainder);
				break;
			}
		}
		return result;
	}

	/**
	 * @return Program config items
	 */
	public String displayProgramConfigs() {
		return String.format("The current currency %s and the current minimum %s and maximum value %s accepted as input.",
				this.getCurrency(), this.getMinCoinIn(), this.getMaxCoinIn());
	}

	/**
	 * Validates the inputs
	 * @param totalValue
	 * @param coinType
	 * @return error message
	 */
	private String validate(int totalValue, int coinType) {
		String errorMessage = "";
		if (totalValue < getMinCoinIn()) {
			errorMessage += String.format("Total amount can not be less than %s", getMinCoinIn());
		} else if (totalValue > getMaxCoinIn()) {
			errorMessage += String.format("Total amount can not be bigger than %s", getMaxCoinIn());
		} else if (!coinList.contains(coinType)) {
			errorMessage += String.format("Coin Type is not valid : %s", coinType);
		}
		return errorMessage;
	}
}
