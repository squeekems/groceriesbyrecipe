let selectedRecipeID = -1;

$(function()
{
  // Makes the table selectable and sets the value of selectedItemID
  $('#tblShoppingListItems').on('click', '.clickable-row', function(event)
  {
    $(this).toggleClass('bg-info').siblings().removeClass('bg-info');
    if ($('tr.bg-info').prop("classList") != undefined)
    {
      $('.recipe-buttons').removeClass('disabled');
      selectedShoppingListItemID = document.getElementsByClassName('bg-info')[0].firstChild.nextSibling.innerHTML;
      console.log(selectedShoppingListItemID);
    }else
    {
      $('.recipe-buttons').addClass('disabled');
      selectedShoppingListItemID = -1;
      console.log(selectedShoppingListItemID);
    }
  });
});