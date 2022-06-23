let selectedShoppingListItemID = -1;

$(function()
{
  // Makes the table selectable and sets the value of selectedItemID
  $('#tblShoppingListItems').on('click', '.clickable-row', function(event)
  {
    $(this).toggleClass('bg-info').siblings().removeClass('bg-info');
    if ($('tr.bg-info').prop("classList") != undefined)
    {
      $('.shoppingListItem-buttons').removeClass('disabled');
      selectedShoppingListItemID = document.getElementsByClassName('bg-info')[0].firstChild.nextSibling.innerHTML;
      console.log(selectedShoppingListItemID);
    }else
    {
      $('.shoppingListItem-buttons').addClass('disabled');
      selectedShoppingListItemID = -1;
      console.log(selectedShoppingListItemID);
    }
  });
  
  // Onclick Remove item
  $('#cmdRemoveShoppingListItem').on('click', null, selectedShoppingListItemID, function(event)
  {
    if (selectedShoppingListItemID === -1)
    {
      event.preventDefault();
      alert('You need to select an item to remove first.');
    }else
    {
      let blnDelete = confirm('Are you sure you want to delete this item?');
      if (blnDelete)
      {
        fetch(`/list/remove/${selectedShoppingListItemID}`)
          .then(res =>
          {
            console.log(res)
            location.reload();
          })
          .catch(err =>
          {
            console.log(err)
          });
      }
    }
  });
  
  // Onclick Clear
  $('#cmdClearShoppingList').on('click', null, null, function(event)
  {
    let blnDelete = confirm('Are you sure you want to clear your entire shopping list?');
    if (blnDelete)
    {
      fetch(`/list/clear`)
        .then(res =>
        {
          console.log(res)
          location.reload();
        })
        .catch(err =>
        {
          console.log(err)
        });
    }
  });
});