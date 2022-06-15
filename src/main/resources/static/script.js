$(function()
{
  let getSelectedRow = function()
  {
    return $('tr.bg-info');
  }

  $('#tblItems').on('click', '.clickable-row', function(event)
  {
    $(this).toggleClass('bg-info').siblings().removeClass('bg-info');

    let rows = getSelectedRow();
    console.log(rows);
    console.log(rows.prop("classList"));
    console.log(rows != undefined);
    console.log(rows != null);
    if (rows.prop("classList") != undefined)
    {
      $('.item-buttons').removeClass('disabled');
    }else
    {
      $('.item-buttons').addClass('disabled');
    }
  });

  $('#addItemModal').on('show.bs.modal', function (event) {
    const button = $(event.relatedTarget) // Button that triggered the modal
    const recipient = button.data('whatever') // Extract info from data-* attributes
    // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
    // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
    const modal = $(this)
  });
});

document.addEventListener("DOMContentLoaded", () =>
{
  const inputField = document.getElementById("search-input");
  const tblItems = document.getElementById("tblItems");
  const tableRows = tblItems.querySelectorAll("tbody tr");
  inputField?.addEventListener("input", () =>
  {
    const searchQuery = inputField.value.toLowerCase();
    for (const tableCell of (tableRows))
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

function sortTable(n)
{
  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
  table = document.getElementById("tblItems");
  switching = true;

  // Set the sorting direction to ascending:
  dir = "asc"; 

  // Make a loop that will continue until no switching has been done:
  while (switching)
  {
    // start by saying: no switching is done:
    switching = false;
    rows = table.rows;

    // Loop through all table rows (except the first, which contains table headers):
    for (i = 0; i < (rows.length - 1); i++)
    {
      // start by saying there should be no switching:
      shouldSwitch = false;

      // Get the two elements you want to compare, one from current row and one from the next:
      x = rows[i].getElementsByTagName("td")[n];
      y = rows[i + 1].getElementsByTagName("td")[n];

      // check if the two rows should switch place, based on the direction, asc or desc:
      if (dir == "asc")
      {
        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase())
        {
          // if so, mark as a switch and break the loop:
          shouldSwitch= true;
          break;
        }
      } else if (dir == "desc") {
        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase())
        {
          // if so, mark as a switch and break the loop:
          shouldSwitch = true;
          break;
        }
      }
    }
    if (shouldSwitch)
    {
      // If a switch has been marked, make the switch and mark that a switch has been done:
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;

      // Each time a switch is done, increase this count by 1:
      switchcount ++;      
    } else
    {
      // If no switching has been done AND the direction is "asc", set the direction to "desc" and run the while loop again.
      if (switchcount == 0 && dir == "asc")
      {
        dir = "desc";
        switching = true;
      }
    }
  }
}