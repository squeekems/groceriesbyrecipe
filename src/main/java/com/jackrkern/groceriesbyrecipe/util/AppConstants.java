/**
 * @package
 */
package com.jackrkern.groceriesbyrecipe.util;

/**
 * @imports
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author Jack Kern <jackrkern@gmail.com>
 *   <p>There are often times where literals can be used in several locations of an app. This class
 *   should be used to store such literals and functions to operate on those literals.</p>
 */
public class AppConstants {

	/**
	 * variables
	 */
	public static DateTimeFormatter dateTimeFormatter =
			DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

	////////////////////////// Column Names
	public static final String CN_AISLE_ID = "aisleID";
	public static final String CN_AMOUNT_ID = "amountID";
	public static final String CN_INGREDIENT_ID = "ingredientID";
	public static final String CN_ITEM_ID = "itemID";
	public static final String CN_RECIPE_ID = "recipeID";
	public static final String CN_UNIT_OF_MEASUREMENT_ID = "unitOfMeasurementID";
	public static final String CN_USER_ID = "userID";

	////////////////////////// Conjunctions
	public static final String AND = "and";

	////////////////////////// Defaults Values
	public static final String DEFAULT_AISLE_FORMAT = "%02d";
	public static final String ONE_FOURTH = "¼";
	public static final String ONE_THIRD = "⅓";
	public static final String ONE_HALF = "½";
	public static final String TWO_THIRDS = "⅔";
	public static final String THREE_FOURTHS = "¾";
	public static final String EMPTY_UNIT_OF_MEASUREMENT = "";
	public static final String TSP = "tsp";
	public static final String TBS = "TBS";
	public static final String OUNCE = "oz.";
	public static final String POUND = "lb.";
	public static final String CUP = "Cup";
	public static final String SLICE = "Slice";

	////////////////////////// Error Codes
	public static final String S_IN_USE = "%sInUse";

	////////////////////////// Error Messages
	public static final String INVALID_CREDENTIALS = "Invalid Credentials";
	public static final String S_IS_ALREADY_IN_USE = "%s is already in use";

	////////////////////////// Model Attributes
	public static final String ACTIVE_PAGE = "activePage";
	public static final String DANGER = "danger";
	public static final String SCROLL_VALUE = "scrollValue";
	public static final String SUCCESS = "success";

	////////////////////////// Nouns
	public static final String ID = "ID";
	public static final String SOMEONE = "someone";
	public static final String STORE_ITEMS = "Store Items";

	////////////////////////// Other
	public static final String NOUN_Q_NOUN_Q_VERB = "%s \"%s\" %s";
	public static final String NOUN_VERB_PREPOSITION_NOUN = "%s %s %S %s";
	public static final String S_S = "%s %s";
	public static final String WELCOME_S = "Welcome, %s!";

	////////////////////////// Path Variables
	public static final String PVM_AISLE_ID = "/{aisleID}";
	public static final String PVV_AISLE_ID = "aisleID";
	public static final String PVM_AMOUNT_ID = "/{amountID}";
	public static final String PVV_AMOUNT_ID = "amountID";
	public static final String PVM_INGREDIENT_ID = "/{ingredientID}";
	public static final String PVV_INGREDIENT_ID = "ingredientID";
	public static final String PVM_ITEM_ID = "/{itemID}";
	public static final String PVV_ITEM_ID = "itemID";
	public static final String PVM_RECIPE_ID = "/{recipeID}";
	public static final String PVV_RECIPE_ID = "recipeID";
	public static final String PVM_SHOPPING_LIST_ITEM_ID = "/{shoppingListItemID}";
	public static final String PVV_SHOPPING_LIST_ITEM_ID = "shoppingListItemID";
	public static final String PVM_UNIT_OF_MEASUREMENT_ID = "/{unitOfMeasurementID}";
	public static final String PVV_UNIT_OF_MEASUREMENT_ID = "unitOfMeasurementID";

	////////////////////////// Prepositions
	public static final String BY = "by";
	public static final String FROM = "from";
	public static final String IS = "is";
	public static final String OF = "of";
	public static final String ON = "on";
	public static final String TO = "to";

	////////////////////////// Pronouns
	public static final String IT = "it";
	public static final String THEIR = "their";

	////////////////////////// Queries
	public static final String FIND_INGREDIENTS_BY_RECIPE_ID =
			"SELECT ingredientID FROM recipes_ingredients WHERE recipeID = ?1";

	////////////////////////// Request Parameters
	public static final String CHK_ADD_TO_SHOPPING_LIST = "chkAddToShoppingList";
	public static final String CMB_ADD_AISLE = "cmbAddAisle";
	public static final String CMB_ADD_AMOUNT = "cmbAddAmount";
	public static final String CMB_ADD_ITEM = "cmbAddItem";
	public static final String CMB_ADD_UNIT_OF_MEASUREMENT = "cmbAddUnitOfMeasurement";
	public static final String CMB_EDIT_AISLE = "cmbEditAisle";
	public static final String CMB_EDIT_AMOUNT = "cmbEditAmount";
	public static final String CMB_EDIT_ITEM = "cmbEditItem";
	public static final String CMB_EDIT_UNIT_OF_MEASUREMENT = "cmbEditUnitOfMeasurement";
	public static final String NUM_CO = "numCo";
	public static final String NUM_COUNT = "numCount";
	public static final String TXT_ADD_INGREDIENT_RECIPE_ID = "txtAddIngredientRecipeID";
	public static final String TXT_ADD_TO_SHOPPING_LIST_SCROLL_VALUE =
			"txtAddToShoppingListScrollValue";
	public static final String TXT_CONFIRM_PASSWORD = "txtConfirmPassword";
	public static final String TXT_EDIT_ID = "txtEditID";
	public static final String TXT_EDIT_INGREDIENT_RECIPE_ID = "txtEditIngredientRecipeID";
	public static final String TXT_ID = "txtID";
	public static final String TXT_INSTRUCTIONS = "txtInstructions";
	public static final String TXT_NAME = "txtName";
	public static final String TXT_REMOVE_INGREDIENT_RECIPE_ID = "txtRemoveIngredientRecipeID";
	public static final String TXT_SAVE_RECIPE_RECIPE_ID = "txtSaveRecipeRecipeID";

	////////////////////////// Roles
	public static final String GUEST_ROLE = "GUEST_ROLE";

	////////////////////////// Table Names
	public static final String TN_AISLES = "aisles";
	public static final String TN_AMOUNTS = "amounts";
	public static final String TN_INGREDIENTS = "ingredients";
	public static final String TN_ITEMS = "items";
	public static final String TN_RECIPES = "recipes";
	public static final String TN_RECIPES_INGREDIENTS = TN_RECIPES + "_" + TN_INGREDIENTS;
	public static final String TN_SHOPPING_LIST = "shoppingList";
	public static final String TN_UNITS_OF_MEASUREMENT = "unitsOfMeasurement";
	public static final String TN_USERS = "users";

	////////////////////////// Template Maps
	public static final String FS = "/";
	public static final String PERIOD = ".";
	public static final String ACCOUNT = "/account";
	public static final String ADD = "/add";
	public static final String ADD_TO_SHOPPING_LIST = "/addToShoppingList";
	public static final String AISLE = "/aisle";
	public static final String ALL = "/all";
	public static final String AMOUNT = "/amount";
	public static final String CLEAR = "/clear";
	public static final String CREATE = "/create";
	public static final String DELETE = "/delete";
	public static final String EDIT = "/edit";
	public static final String EDIT_RECIPE = "/edit_recipe";
	public static final String EMAIL = "/email";
	public static final String GET = "/get";
	public static final String INDEX = "/index";
	public static final String INGREDIENT = "/ingredient";
	public static final String ITEM = "/item";
	public static final String ITEMS = "/items";
	public static final String LIST = "/list";
	public static final String LOGIN = "/login";
	public static final String LOGOUT = "/logout";
	public static final String RECIPE = "/recipe";
	public static final String RECIPES = "/recipes";
	public static final String REGISTER = "/register";
	public static final String REMOVE = "/remove";
	public static final String SAVE = "/save";
	public static final String SETTINGS = "/settings";
	public static final String SHOPPING_LIST = "/shoppingList";
	public static final String SHOPPING_LIST_ITEM = "/shoppingListItem";
	public static final String UNITS_OF_MEASUREMENT = "/unitsOfMeasurement";
	public static final String UNIT_OF_MEASUREMENT = "/unitOfMeasurement";
	public static final String UPDATE = "/update";
	public static final String USER = "/user";

	////////////////////////// Verbs
	public static final String BE = "be";
	public static final String CALL = "call";
	public static final String CATCH = "catch";
	public static final String SELECT = "select";
	public static final String TRY = "try";

	////////////////////////// Functions

	/**
	 * Returns the passed String capitalized.
	 *
	 * <p>Capitalize a String, changing the first letter to upper case as per
	 * {@link Character#toUpperCase(char)} No other letters are changed.</p>
	 *
	 * @param s The String to capitalize.
	 * @return The capitalized String.
	 */
	public static String capitalize(String s) {
		if (s != null && !s.isEmpty()) {
			char baseChar = s.charAt(0);
			char updatedChar = Character.toUpperCase(baseChar);
			if (baseChar == updatedChar) {
				return s;
			} else {
				char[] chars = s.toCharArray();
				chars[0] = updatedChar;
				return new String(chars);
			}
		} else {
			return s;
		}
	}

	/**
	 * Returns the substring of the String starting from one index.
	 *
	 * <p>Convert mapping constants to simple nouns and verbs by removing the forward slash at the
	 * beginning of the String.</p>
	 *
	 * @param s The String to be demapped.
	 * @return The String starting from index 1.
	 */
	public static String demap(String s) {
		return s.substring(1);
	}

	/**
	 * Use in empty code blocks.
	 */
	public static void doNothing() {}

	/**
	 * Obtains the current date-time from the system clock in the default time-zone.
	 *
	 * <p>This method is stolen from {@link LocalDateTime}.</p>
	 *
	 * <p>Using this method will prevent the ability to use an alternate clock for testing
	 * because the clock is hard-coded.</p>
	 *
	 * @return the current date-time using the system clock and default time-zone, not null
	 */
	public static LocalDateTime now() { return LocalDateTime.now(); }

	/**
	 * Returns the passed String in its past tense form. Intended for single word Strings.
	 *
	 * <p>This function does not account for capitalization. It assumes the word being passed is all
	 * lowercase.</p>
	 *
	 * <p>This function does not account for word stressing. There are certain words that are spelt
	 * different based on the stress of the spoken word. These conditions are ignored.</p>
	 *
	 * @param s The String to be made past tense.
	 * @return The past tense of the String.
	 */
	public static String pastOf(String s) {
		if (s.toLowerCase(Locale.ROOT).equals(CATCH)) {
			return "caught";
		} else if (s.toLowerCase(Locale.ROOT).equals(BE)) {
			return s + "en";
		} else if (s.toLowerCase(Locale.ROOT).equals(demap(GET))) {
			char[] chars = s.toCharArray();
			chars[1] = 'o';
			return new String(chars);
		} else if (s.endsWith("e")) {
			return s + "d";
		} else if (s.length() > 2) {
			if (!isVowel(s.substring(s.length() - 3, s.length() - 2))
					&& isVowel(s.substring(s.length() - 2, s.length() - 1))
					&& !isVowel(s.substring(s.length() - 1))) {
				if (s.endsWith("w") || s.endsWith("x") || s.endsWith("y")) {
					return s + "ed";
				} else {
					return s + s.substring(s.length() - 1) + "ed";
				}
			} else {
				return s + "ed";
			}
		} else {
			return s + "ed";
		}
	}

	private static boolean isVowel(char ch) {
		return switch (ch) {
			case 'a', 'e', 'i', 'o', 'u' -> true;
			default -> false;
		};
	}

	private static boolean isVowel(String s)
	{
		return isVowel(s.toCharArray()[0]);
	}

	/**
	 * Returns the passed String in its plural form. Intended for single word Strings.
	 *
	 * <p>This function does not account for capitalization. It assumes the word being passed is all
	 * lowercase.</p>
	 *
	 * @param s The String to be made plural.
	 * @return The plural String.
	 */
	public static String pluralOf(String s) {
		if (s.endsWith("s") || s.endsWith("sh") || s.endsWith("ch")
				|| s.endsWith("x") || s.endsWith("z")) {
			return s + "es";
		} else {
			return s + "s";
		}
	}

	/**
	 * Returns the passed String wrapped in quotation marks.
	 *
	 * @param s The String to be wrapped.
	 * @return The String wrapped.
	 */
	public static String quote(String s) { return String.format("\"%s\"", s); }

	/**
	 * Returns the passed String replacing capital letters with a space and lowercase letters.
	 *
	 * <p>Makes use of {@link String#replaceAll(String, String)} passing "([^_])([A-Z])" as regex
	 * parameter and "$1 $2" as replacement parameter.</p>
	 * <p>If "unitOfMeasurement" is passed to this function, "unit of measurement" is returned.</p>
	 *
	 * @param s The String to be edited.
	 * @return The String edited.
	 */
	public static String space(String s) {
		return s.replaceAll("([^_])([A-Z])", "$1 $2").toLowerCase();
	}

	/**
	 * Returns a String containing all the passed args separated by a space.
	 *
	 * @param args The Strings to be included and separated by a space.
	 * @return The String containing all the args separated by a space.
	 */
	public static String space(String[] args) {
		StringBuilder s = new StringBuilder();
		for (String arg: args) {
			s.append(arg).append(" ");
		}
		return s.toString().trim();
	}
}
