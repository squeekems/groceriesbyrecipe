document.addEventListener("DOMContentLoaded", () =>
{
  const inputField = document.getElementById("search-input");
  const tblItems = document.getElementById("tblItems");
  const tableRows = tblItems.querySelectorAll("tbody tr");
  inputField?.addEventListener("input", () =>
  {
    const searchQuery = inputField.value.toLowerCase();
    console.log(searchQuery);
    for (const tableCell of (tableRows))
    {
      const row = tableCell.closest("tr");
      const value = tableCell.textContent.toLowerCase().replace(",","");
      row.style.visibility = null;
      if (value.search(searchQuery) === -1)
      {
        row.style.visibility = "collapse";
      }
      console.log(inputField.value.toLowerCase());
    }
  });
});
$('#lstItemsInStore').on('click', '.clickable-row', function(event) {
  $(this).toggleClass('bg-info').siblings().removeClass('bg-info');
});
$('#addItemModal').on('show.bs.modal', function (event) {
  var button = $(event.relatedTarget) // Button that triggered the modal
  var recipient = button.data('whatever') // Extract info from data-* attributes
  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
  var modal = $(this)
});
console.log("This is running");