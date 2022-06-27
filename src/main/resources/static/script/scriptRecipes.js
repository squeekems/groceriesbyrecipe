let selectedRecipeID = -1;

$(function()
{
  // Makes the table selectable and sets the value of selectedItemID
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
});