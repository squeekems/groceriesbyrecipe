package com.jackrkern.groceriesbyrecipe.util;

/* @author "Jack Kern" */
public class AppConstants
{
	public static String strMapping(String templateMapping)
	{
		return templateMapping.substring(1);
	}

	public static String strQuote(String s)
	{
		return String.format("\"%s\"", s);
	}

	public static String strSpace(String s)
	{
		s = s.replaceAll("([^_])([A-Z])", "$1 $2").toLowerCase();
		return s;
	}

	public static String strPlural(String s)
	{
		if (s.substring(s.length() - 1).equals("s") || s.substring(s.length() - 2).equals("sh")
			|| s.substring(s.length() - 2).equals("ch") || s.substring(s.length() - 1).equals("x")
			|| s.substring(s.length() - 1).equals("z"))
			return s + "es";
		else
			return s + "s";
	}

	public static String strPast(String s)
	{
		if (s.substring(s.length() - 1).equals("e"))
			return s + "d";
		else if (s.length() > 2)
		{ // If the word ends in a Consonant + Vowel + Consonant, we double the final consonant and add ED.
			if (!isVowel(s.substring(s.length() - 3, s.length() - 2))
				&& isVowel(s.substring(s.length() - 2, s.length() - 1)) && !isVowel(s.substring(s.length() - 1)))
			{
				if (s.substring(s.length() - 1).toLowerCase().equals("w")
					|| s.substring(s.length() - 1).toLowerCase().equals("x")
					|| s.substring(s.length() - 1).toLowerCase().equals("y"))
					return s + "ed";
				else
					return s + s.substring(s.length() - 1) + "ed";
			} else
				return s + "ed";
		} else
			return s + "ed";
	}

	private static boolean isVowel(char ch)
	{
		switch (ch)
		{
			case 'a':
			case 'e':
			case 'i':
			case 'o':
			case 'u':
				return true;
			default:
				return false;
		}
	}

	private static boolean isVowel(String s)
	{
		return isVowel(s.toCharArray()[0]);
	}

	////////////////////////// ModelAttributes
	public static final String ACTIVEPAGE = "activePage";
	public static final String SUCCESS = "success";
	public static final String SCROLLVALUE = "scrollValue";

	////////////////////////// Error Codes

	////////////////////////// Error Messages

	////////////////////////// Template Maps
	public static final String ADD = "/add";
	public static final String ADDTOSHOPPINGLIST = "/addToShoppingList";
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
	public static final String INDEX = "/";
	public static final String INGREDIENT = "/ingredient";
	public static final String ITEM = "/item";
	public static final String ITEMS = "/items";
	public static final String LIST = "/list";
	public static final String LOGIN = "/login";
	public static final String RECIPE = "/recipe";
	public static final String RECIPES = "/recipes";
	public static final String REGISTER = "/register";
	public static final String REMOVE = "/remove";
	public static final String SAVE = "/save";
	public static final String SETTINGS = "/settings";
	public static final String SHOPPINGLIST = "/shoppingList";
	public static final String SHOPPINGLISTITEM = "/shoppingListItem";
	public static final String UNITSOFMEASUREMENT = "/unitsOfMeasurement";
	public static final String UNITOFMEASUREMENT = "/unitOfMeasurement";
	public static final String UPDATE = "/update";
	public static final String USER = "/user";

	////////////////////////// Path Variables
	public static final String PVMAISLEID = "/{aisleID}";
	public static final String PVVAISLEID = "aisleID";
	public static final String PVMAMOUNTID = "/{amountID}";
	public static final String PVVAMOUNTID = "amountID";
	public static final String PVMINGREDIENTID = "/{ingredientID}";
	public static final String PVVINGREDIENTID = "ingredientID";
	public static final String PVMITEMID = "/{itemID}";
	public static final String PVVITEMID = "itemID";
	public static final String PVMRECIPEID = "/{recipeID}";
	public static final String PVVRECIPEID = "recipeID";
	public static final String PVMSHOPPINGLISTITEMID = "/{shoppingListItemID}";
	public static final String PVVSHOPPINGLISTITEMID = "shoppingListItemID";
	public static final String PVMUNITOFMEASUREMENTID = "/{unitOfMeasurementID}";
	public static final String PVVUNITOFMEASUREMENTID = "unitOfMeasurementID";

	////////////////////////// Request Parameters
	public static final String CHKADDTOSHOPPINGLIST = "chkAddToShoppingList";
	public static final String CMBADDAISLE = "cmbAddAisle";
	public static final String CMBADDITEM = "cmbAddItem";
	public static final String CMBADDUNITOFMEASUREMENT = "cmbAddUnitOfMeasurement";
	public static final String CMBEDITAISLE = "cmbEditAisle";
	public static final String CMBEDITAMOUNT = "cmbEditAmount";
	public static final String CMBEDITITEM = "cmbEditItem";
	public static final String CMBEDITUNITOFMEASUREMENT = "cmbEditUnitOfMeasurement";
	public static final String NUMCO = "numCo";
	public static final String NUMCOUNT = "numCount";
	public static final String TXTADDAMOUNT = "cmbAddAmount";
	public static final String TXTADDINGREDIENTRECIPEID = "txtAddIngredientRecipeID";
	public static final String TXTADDTOSHOPPINGLISTSCROLLVALUE = "txtAddToShoppingListScrollValue";
	public static final String TXTEDITID = "txtEditID";
	public static final String TXTEDITINGREDIENTRECIPEID = "txtEditIngredientRecipeID";
	public static final String TXTID = "txtID";
	public static final String TXTINSTRUCTIONS = "txtInstructions";
	public static final String TXTNAME = "txtName";
	public static final String TXTREMOVEINGREDIENTRECIPEID = "txtRemoveIngredientRecipeID";
	public static final String TXTSAVERECIPERECIPEID = "txtSaveRecipeRecipeID";

	////////////////////////// Table Names
	public static final String TABLENAMEAISLES = "aisles";
	public static final String TABLENAMEAMOUNTS = "amounts";
	public static final String TABLENAMEINGREDIENT = "ingredients";
	public static final String TABLENAMEITEM = "items";
	public static final String TABLENAMERECIPE = "recipes";
	public static final String RECIPES_INGREDIENTS = TABLENAMERECIPE + "_" + TABLENAMEINGREDIENT;
	public static final String TABLENAMESHOPPINGLIST = "shoppingList";
	public static final String TABLENAMEUNITSOFMEASUREMENT = "unitsOfMeasurement";

	////////////////////////// Column Names
	public static final String AISLEID = "aisleID";
	public static final String AMOUNTID = "amountID";
	public static final String INGREDIENTID = "ingredientID";
	public static final String ITEMID = "itemID";
	public static final String RECIPEID = "recipeID";
	public static final String UNITOFMEASUREMENTID = "unitOfMeasurementID";
	public static final String USERID = "userID";
	public static final String TABLENAMEUSERS = "users";

	////////////////////////// Nouns
	public static final String SOMEONE = "someone";
	public static final String cSTOREscITEMS = "Store Items";

	////////////////////////// Prepositions
	public static final String FROM = "from";
	public static final String ON = "on";
	public static final String TO = "to";

	////////////////////////// Roles
	public static final String GUEST_ROLE = "GUEST_ROLE";

	////////////////////////// Queries
	public static final String FINDINGREDIENTSBYRECIPEID = "SELECT ingredientID FROM recipes_ingredients WHERE recipeID = ?1";

	////////////////////////// Other
	public static final String DATEPATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String NOUNqNOUNqsVERBED = "%s \"%s\" %s";
	public static final String NOUNsVERBEDsPREPOSITIONsNOUN = "%s %s %S %s";
	public static final String PERSONsCREATEDsAsNEWsNOUNsCALLEDsNOUNnl = "%s [] %s created a new %s called %s.\n";
	public static final String PERSONsLOADEDsTHEsNOUNsPAGEnl = "%s [] %s loaded the %s page.\n";
	public static final String PERSONsSELECTEDsAsNOUNsCALLEDsNOUNsTOsBEsVERBEDnl = "%s [] %s selected a %s called %s to be %s.\n";
	public static final String PERSONsTRIEDsTOsVERBnl = "%s [] %s tried to %s.\n";
	public static final String PERSONsVERBEDsALLsTHEsNOUNSsLISTEDsFORsAsNOUNsTOsTHEIRsNOUNnl = "%s %s %s all the %ss listed for a %s to their %s.\n";
	public static final String PERSONsVERBEDsANsOBJECTsCALLEDsNOUNnl = "%s [] %s %s an %s called %s.\n";
	public static final String PERSONsVERBEDsANsOBJECTsCALLEDsNOUNsANDsVERBEDsITsPREPOSITIONsTHEIRsNOUNnl = "%s [] %s %s an %s called %s and %s it %s their %s.\n";
	public static final String PERSONsVERBEDsAsNOUNsCALLEDsNOUNnl = "%s [] %s %s a %s called %s.\n";
	public static final String PERSONsVERBEDsAsNOUNsPREPOSITIONsTHEIRsNOUNnl = "%s [] %s %s a %s %s their %s.\n";
	public static final String PERSONsVERBEDsNOUNnl = "%s [] %s %s %s.\n";
	public static final String PERSONsVERBEDsNOUNsPREPOSITIONsTHEIRsNOUNsCALLEDsNOUNnl = "%s [] %s %s %s %s their %s called %s.\n";
	public static final String PERSONsVERBEDsNOUNsPREPOSITIONsTHEIRsNOUN = "%s [] %s %s %s %s their %s.\n";
	public static final String PERSONsVERBEDsTHEIRsNOUNsCALLEDsNOUNnl = "%s [] %s %s their %s called %s.\n";
	public static final String PERSONsVERBEDsTHEIRsNOUNnl = "%s [] %s %s their %s.\n";
	public static final String SERIAL = "serial";
	public static final String STRINGsSTRING = "%s %s";
	public static final String STRINGsSTRINGnl = "%s %s\n";

	////////////////////////// Defaults Values
	public static final String DEFAULTAISLEFORMAT = "%02d";
	public static final String ONEfsFOUR = "¼";
	public static final String ONEfsTHREE = "⅓";
	public static final String ONEfsTWO = "½";
	public static final String TWOfsTHREE = "⅔";
	public static final String THREEfsFOUR = "¾";
	public static final String EMPTYUNITOFMEASUREMENT = "";
	public static final String TSP = "tsp";
	public static final String TBS = "TBS";
	public static final String OUNCE = "oz.";
	public static final String POUND = "lb.";
	public static final String CUP = "Cup";
	public static final String SLICE = "Slice";

	public static void doNothing()
	{}
}