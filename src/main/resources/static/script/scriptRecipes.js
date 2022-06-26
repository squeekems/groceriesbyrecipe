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
  
  // Edit Recipe
  $('#cmdEditRecipe').on('click', function (event)
  {
    if (selectedRecipeID === -1)
    {
      event.preventDefault();
      alert('You need to select a recipe to edit first.');
    }else
    {
      $.get(`/recipes/edit/${selectedRecipeID}`);
      // fetch(`/recipes/edit/${selectedRecipeID}`)
      // .then(res =>
      // {
      //   console.log(res)
      // })
      // .catch(err =>
      // {
      //   console.log(err)
      // });
    }
  });
});