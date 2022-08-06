let selectedIngredientID = -1;

$(function()
{
  // Makes the table selectable and sets the value of selectedIngredientID
  $('#tblIngredients').on('click', '.clickable-row', function(event)
  {
    $(this).toggleClass('bg-info').siblings().removeClass('bg-info');
    if ($('tr.bg-info').prop("classList") != undefined)
    {
      $('.ingredient-buttons').removeClass('disabled');
      selectedIngredientID = document.getElementsByClassName('bg-info')[0].firstChild.nextSibling.innerHTML;
      console.log(selectedIngredientID);
    }else
    {
      $('.ingredient-buttons').addClass('disabled');
      selectedIngredientID = -1;
      console.log(selectedIngredientID);
    }
  });
  
  // Populate Edit Modal
  $('#editIngredientModal').on('show.bs.modal', function (event)
  {
    if (selectedIngredientID === -1)
    {
      event.preventDefault();
      alert('You need to select an item to edit first.');
    }else
    {
      const href = "/edit_recipe/get/ingredient/" + selectedIngredientID;
      $.get(href, function(ingredient, status)
      {
        $('#txtEditID').val(selectedIngredientID);
        $('#cmbEditAmount').val(ingredient.amount.amountID);
        $('#cmbEditUnitOfMeasurement').val(ingredient.unitOfMeasurement.unitOfMeasurementID);
        $('#cmbEditItem').val(ingredient.item.itemID);
      });
      
      $('#editIngredientModal').modal();
    }
  });
  
  // Populate Remove Ingredient Modal
  $('#removeIngredientModal').on('show.bs.modal', function(event)
  {
    if (selectedIngredientID === -1)
    {
      event.preventDefault();
      alert('You need to select an ingredient to remove first.');
    }else
    {
      const href = "/edit_recipe/get/ingredient/" + selectedIngredientID;
      $.get(href, function(ingredient, status)
      {
        // Table
        const table = document.getElementById('tblRemoveIngredient');

        // tr
        let row = table.insertRow();

        // td for ID
        let tdID = row.insertCell(0);
        let txtID = document.createElement('input');
        tdID.setAttribute('style', 'display: none;');
        txtID.setAttribute('id', 'txtID');
        txtID.setAttribute('name', 'txtID');
        txtID.setAttribute('value', selectedIngredientID);
        txtID.setAttribute('th:value', selectedIngredientID)

        // txtID
        tdID.appendChild(txtID);

        // td for Ingredient
        let tdIngredient = row.insertCell(1);
        tdIngredient.innerHTML = ingredient.amount.value + ' ' + ingredient.unitOfMeasurement.name + ' ' + ingredient.item.description;
      });
      
      $('#removeIngredientModal').modal();
    }
  });
});