document.addEventListener("DOMContentLoaded", () =>
{
  document.querySelectorAll(".search-input").forEach(inputField => 
    {
      const tableRows = inputField.closest("table").querySelectorAll("tbody tr");
      const headerCell = inputField.closest("th");
      const otherHeaderCells = inputField.closest("tr").querySelectorAll("th");
      const columnIndex = Array.from(otherHeaderCells).indexOf(headerCell);
      const searchableCells = Array.from(tableRows).map(row => row.querySelectorAll("td")[columnIndex]);
      inputField.addEventListener("input", () =>
      {
        const searchQuery = inputField.value.toLowerCase();
        for (const tableCell of searchableCells)
        {
          const row = tableCell.closest("tr");
          const value = tableCell.textContent.toLowerCase().replace(",","");
          row.style.visibility = null;
          if (value.search(searchQuery) === -1)
          {
            row.style.visibility = "collapse";
          }
        }
      });
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
})