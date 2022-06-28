# Jack Kern's Groceries By Recipe
## Capstone for TEKSystems Fullstack Java Bootcamp

### User Stories
- As a user, I would like to customize my aisle labels.
- As a user, I would like to customize amounts and units of measurement.
- As a user, I would like to remove items from my store list.
- As a user, I would like to remove recipes from my recipe list.
- As a user, I would like to be able to edit items on my store list.
- As a user, I would like to be able to edit recipes on my recipe list.
- As a user, I would like to add items from my store list to my shopping list.
- As a user, I would like to add items from my recipes on my recipe list to my shopping list.
- As a user, I would like to print my shopping list.
- As a user, I would like to email my shopping list.
- As a user, I would like to clear my shopping list.
- As a user, I would like to remove items from my shopping list.
- As a user, I would like to add *new* items to my shopping list.
- As a developer, I need to complete learning processes.
- As a developer, I need to complete an ERD for the database.
- As a developer, I need to create a schema.
- As a developer, I need to look into OAuth.
- As a site visitor, I would like to read about this app.
- As s site visitor, I would like to setup an account.
- As an administrator, I would like to freely query the database.
- As a user, I would like to add items to my store list.
- As a user, I would like to add recipes to my recipe list.

### Technical Requirements:  
## Project Structure, Standardization, and Conventions  
- Project Package Structure:  
![Project Package Structure](https://user-images.githubusercontent.com/3833611/176082844-20c8c652-d73b-43cf-906d-6de2a39b1fec.png)  
## Standard Java naming conventions should be followed  
- Classes should be written in Pascal case  
  - **UnitOfMeasurement**.java  
- Variables, methods, and URLs should be written in the camel case  
  - private Long **unitOfMeasurementID**  
  - public List<Aisle> **getAisles**()
  - @RequestMapping("**/getItemByID/{itemID}**")  
- Files, including view files, should be written in snake case  
  - **edit_recipe**.html
- Packages should be written in lowercase, with each word separated by dots (.)
  - **com.jackrkern.groceriesbyrecipe**
- Packages should include the name of your project and your name (e.g., “org.johndoe.myprojectname”)
  - **com.jackrkern.groceriesbyrecipe**
## Core Java and Models  
- Utilize Java classes with constant variables (i.e., variables that never change from their initial value). The value of these variables can be requested parameters, SQL queries used in the DAO, names of HTML pages, or URL patterns to forward a request to - 2%.
  - class EditRecipeController  
    - final private String EDITRECIPE = "/edit_recipe";  
  - public interface RecipeRepository extends CrudRepository<Recipe, Long>  
    - @Query(value = "SELECT ingredientID FROM recipes_ingredients WHERE recipeID = ?1", nativeQuery = true)  
	    - Set<Long> findIngredientsByRecipeID(Long recipeID);  
- Have at least four models and corresponding tables in a relational database  
  - public class User  
    - @Table(name = "users")  
  - public class Item  
    - @Table(name = "items")  
  - public class Recipe  
    - @Table(name = "recipes")  
  - public class ShoppingListItem  
    - @Table(name = "shoppingList")  
## Database, ORM, and Hibernate  
  - Include a schema diagram of the tables and the SQL you used for the database
    - ![Entity Relationship Diagram](https://user-images.githubusercontent.com/3833611/176085339-a86b14e0-485a-42f5-85c6-b60ef6a84084.png)
    - Click [here](https://github.com/squeekems/groceriesbyrecipe/tree/main/SQL) to view my SQL files.
    - Your application should include examples for all four CRUD operations (Create, Read, Update, and Delete)
```java
// Create
@PostMapping("/add")
public RedirectView addItem(Item item, @RequestParam(value = "cmbAddAisle")
Long aisleID, RedirectAttributes redirectAttributes)
{
	item.setAisle(itemService.getAisleByID(aisleID));
	item.setUser(userService.getPrincipal());
	itemService.saveItem(item);
	redirectAttributes.addFlashAttribute("success", item.getDescription() + " Added");
	return new RedirectView("/items");
}
```
```java
// Read
@GetMapping
public String getItems(Model model)
{
	model.addAttribute("items", itemService.getItemsSortedByAisle(userService.getPrincipal()));
	model.addAttribute("item", new Item());
	model.addAttribute("activePage", "items");
	model.addAttribute("aisles", itemService.getAisles());
	model.addAttribute("shoppingList", new ShoppingListItem());
	/**/// Should be in my Settings Controller
	/**/model.addAttribute("aisles", itemService.getAisles());
	/**/model.addAttribute("unitsOfMeasurement", recipeService.getUnitsOfMeasurement());
	/**/model.addAttribute("amounts", recipeService.getAmounts());
	/**/// Should be in my Settings Controller
	return "items";
}
```
```java
// Update
@RequestMapping(value = "/edit", method = { RequestMethod.PUT, RequestMethod.GET })
public RedirectView updateItem(Item item, @RequestParam(value = "cmbEditAisle")
Long aisleID, RedirectAttributes redirectAttributes)
{
	item.setAisle(itemService.getAisleByID(aisleID));
	item.setUser(userService.getPrincipal());
	itemService.saveItem(item);
	redirectAttributes.addFlashAttribute("success", item.getDescription() + " Edited");
	return new RedirectView("/items");
}
```
```java
// Delete
@GetMapping("/remove/{itemID}")
public RedirectView deleteItem(@PathVariable(value = "itemID")
Long itemID, RedirectAttributes redirectAttributes)
{
	// create item object to use in feedback
	Item item = itemService.getItemByID(itemID);
	itemService.deleteItem(itemID);
	redirectAttributes.addFlashAttribute("success", item.getDescription() + " Removed");
	return new RedirectView("/items");
}
```
## Front-end Development  
- Use CSS to style the Web pages. Use an external CSS stylesheet (internal styling may be used along with frameworks such as Bootstrap, but you must still include and utilize a custom CSS external file)  
  - Click [here](https://github.com/squeekems/groceriesbyrecipe/tree/main/src/main/resources/static/style) to view my external CSS stylesheets.
- Your application should include six different views/pages  
  - Click [here](https://github.com/squeekems/groceriesbyrecipe/tree/main/src/main/resources/templates) to see my view templates.
- Use at least one JavaScript script linked from an external script file  
  - Click [here](https://github.com/squeekems/groceriesbyrecipe/tree/main/src/main/resources/static/script) to view my external script files.
- Include a navigation section that is included across multiple pages
  - Click [here](https://github.com/squeekems/groceriesbyrecipe/blob/main/src/main/resources/templates/fragments/nav.html) to view my navbar fragment used on most of my templates.
## Spring Framework
