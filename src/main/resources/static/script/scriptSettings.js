$(function()
{
  // Onclick Remove Aisle
  $('#cmdRemoveAisle').on('click', function(event)
  {
    let blnDelete = confirm('Are you sure you want to delete Aisle "' + $('#cmbAisle option:selected').html() + '"?');
    if (blnDelete)
    {
      const href = "/settings/remove/aisle/" + $('#cmbAisle').val();
      window.location.href = href;
    }
  });

  // Onclick Remove Unit of Measurement
  $('#cmdRemoveUnitOfMeasurement').on('click', function(event)
  {
    let blnDelete = confirm('Are you sure you want to delete Unit of Measurement "' + $('#cmbUnitsOfMeasurement option:selected').html() + '"?');
    if (blnDelete)
    {
      const href = "/settings/remove/unitOfMeasurement/" + $('#cmbUnitsOfMeasurement').val();
      window.location.href = href;
    }
  });

  // Onclick Remove Amount
  $('#cmdRemoveAmount').on('click', function(event)
  {
    let blnDelete = confirm('Are you sure you want to delete Amount "' + $('#cmbAmount option:selected').html() + '"?');
    if (blnDelete)
    {
      const href = "/settings/remove/amount/" + $('#cmbAmount').val();
      window.location.href = href;
    }
  });
  
  // Populate Edit Aisle Modal
  $('#editAisleModal').on('show.bs.modal', function (event)
  {
	$('#txtEditAisleID').val($('#cmbAisle option:selected').val());
	$('#txtEditAisleName').val($('#cmbAisle option:selected').html());
	$('#txtEditAisleName').html($('#cmbAisle option:selected').html());
    
    $('#editAisleModal').modal();
  });
  
  // Populate Edit Unit of Measurement Modal
  $('#editUnitOfMeasurementModal').on('show.bs.modal', function (event)
  {
	$('#txtEditUnitOfMeasurementID').val($('#cmbUnitsOfMeasurement option:selected').val());
	$('#txtEditUnitOfMeasurementName').val($('#cmbUnitsOfMeasurement option:selected').html());
	$('#txtEditUnitOfMeasurementName').html($('#cmbUnitsOfMeasurement option:selected').html());
    
    $('#editUnitOfMeasurementModal').modal();
  });
  
  // Populate Edit Amount Modal
  $('#editAmountModal').on('show.bs.modal', function (event)
  {
	$('#txtEditAmountID').val($('#cmbAmount option:selected').val());
	$('#txtEditAmountValue').val($('#cmbAmount option:selected').html());
	$('#txtEditAmountValue').html($('#cmbAmount option:selected').html());
    
    $('#editAmountModal').modal();
  });
});