let selectedRecipeID = -1;

$(function()
{
  // Makes the table selectable and sets the value of selectedRecipeID
  $('.list-group-item.list-group-item-action').on('click', function(event)
  {
    selectedRecipeID = $(this).attr('id');
    console.log(selectedRecipeID);
    $('.recipe-buttons').removeClass('disabled');
  });

  // Populate Edit Modal
  $('#editRecipeModal').on('show.bs.modal', function (event)
  {
    if (selectedRecipeID === -1)
    {
      event.preventDefault();
      alert('You need to select a recipe to edit first.');
    }else
    {
      const href = "/recipes/getRecipeByID/" + selectedRecipeID;
      $.get(href, function(recipe, status)
      {
        $('#txtEditID').val(recipe.recipeID);
        $('#txtEditName').val(recipe.name);
      });
      
      $('#editRecipeModal').modal();
    }
  });
  
  // Onclick Remove Recipe
  $('#cmdRemoveRecipe').on('click', function(event)
  {
    if (selectedRecipeID === -1)
    {
      event.preventDefault();
      alert('You need to select a recipe to remove first.');
    }else
    {
      let blnDelete = confirm('Are you sure you want to delete this recipe?');
      if (blnDelete)
      {
        const href = "/recipes/remove/" + selectedRecipeID;
        window.location.href = href;
      }
    }
  });
  
  // Onclick Add to Shopping List
  $('#cmdAddToShoppingList').on('click', null, selectedRecipeID, function (event)
  {
    if (selectedRecipeID === -1)
    {
      event.preventDefault();
      alert('You need to select a recipe to add to the shopping list first.');
    }else
    {
      const href = "/recipes/getRecipeByID/" + selectedRecipeID;
      $.get(href, function(recipe, status)
      {
        $('#txtJSON').attr('value', JSON.stringify(recipe))

        for (const ingredient of recipe.ingredients)
        {
          // Table
          const table = document.getElementById('tblAddToShoppingList');

          // tr
          let row = table.insertRow();

          // td for ID
          let tdID = row.insertCell(0);
          let txtID = document.createElement('input');
          tdID.setAttribute('style', 'display: none;');
          txtID.setAttribute('id', 'txtID' + ingredient.item.itemID);
          txtID.setAttribute('name', 'txtID' + ingredient.item.itemID);
          txtID.setAttribute('value', ingredient.item.itemID);
          txtID.setAttribute('th:value', ingredient.item.itemID)

          // txtID
          tdID.appendChild(txtID);

          // td for Description
          let tdDescription = row.insertCell(1);
          tdDescription.innerHTML = ingredient.item.description;

          // td for Count
          let tdCount = row.insertCell(2);
          let numCount = document.createElement('input');
          numCount.type = 'number';
          numCount.min = 0;
          numCount.max = 99;
          numCount.value = 1;
          numCount.setAttribute('id', 'numCount' + ingredient.item.itemID);
          numCount.setAttribute('name', 'numCount' + ingredient.item.itemID);
          numCount.setAttribute('class', 'text-right')

          // NumericUpDown
          tdCount.appendChild(numCount);
        }
      });
    
      $('#addToShoppingListModal').modal();
    }
  });

  //Onlick Close Add to Shopping List
  $('.close-add-to-shopping-list-modal').on('click', null, null, function(event)
  {
    const table = document.getElementById('tblAddToShoppingList');
    while (table.hasChildNodes())
    {
      table.removeChild(table.lastChild);
    }
  });
});