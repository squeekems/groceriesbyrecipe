let selectedItemID = -1;

// Search Table Text
const inputField = document.getElementById("search-input"); // Find the search text
const tblItems = document.getElementById("tblItems"); // Find the table
const tableRows = tblItems.querySelectorAll("tbody tr"); // Find the rows on the table

inputField.addEventListener("input", () =>
{
  const searchQuery = inputField.value.toLowerCase(); // Set query based on the search text
  for (const tableCell of (tableRows)) // Iterate through all the rows
  {
    const row = tableCell.closest("tr"); // Find the current row
    const value = tableCell.textContent.toLowerCase().replace(",",""); //Set the value of the row
    row.style.visibility = null; // Reset the visibility of the row
    if (value.search(searchQuery) === -1) // If the row does not have any match on the row
    {
      row.style.visibility = "collapse"; // Hide the row
    }
  }
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

$(function()
{
  // Makes the table selectable and sets the value of selectedItemID
  $('#tblItems').on('click', '.clickable-row', function(event)
  {
    $(this).toggleClass('bg-info').siblings().removeClass('bg-info');
    if ($('tr.bg-info').prop("classList") != undefined)
    {
      $('.item-buttons').removeClass('disabled');
      selectedItemID = document.getElementsByClassName('bg-info')[0].firstChild.nextSibling.innerHTML;
      console.log(selectedItemID);
    }else
    {
      $('.item-buttons').addClass('disabled');
      selectedItemID = -1;
      console.log(selectedItemID);
    }
  });
  
  // Populate Edit Modal
  $('#editItemModal').on('show.bs.modal', function (event)
  {
    if (selectedItemID === -1)
    {
      event.preventDefault();
      alert('You need to select an item to edit first.');
    }else
    {
      const href = "/items/getItemByID/" + selectedItemID;
      $.get(href, function(item, status)
      {
        $('#txtEditID').val(item.itemID);
        $('#cmbEditAisle').val(item.aisle.aisleID);
        $('#txtEditDescription').val(item.description);
      });
      
      $('#editItemModal').modal();
    }
  });
  
  // Onclick Remove item
  $('#cmdRemoveItem').on('click', null, function(event)
  {
    if (selectedItemID === -1)
    {
      event.preventDefault();
      alert('You need to select an item to remove first.');
    }else
    {
      let blnDelete = confirm('Are you sure you want to delete this item?');
      if (blnDelete)
      {
        fetch(`/items/remove/${selectedItemID}`)
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
  
  // Onclick Add to Shopping List
  $('#cmdAddToShoppingList').on('click', null, selectedItemID, function (event)
  {
    if (selectedItemID === -1)
    {
      event.preventDefault();
      alert('You need to select an item to add to the shopping list first.');
    }else
    {
      const href = "/items/getItemByID/" + selectedItemID;
      $.get(href, function(item, status)
      {
        // Table
        const table = document.getElementById('tblAddToShoppingList');

        // tr
        let row = table.insertRow();

        // td for ID
        let tdID = row.insertCell(0);
        let txtID = document.createElement('input');
        tdID.setAttribute('style', 'display: none;');
        txtID.setAttribute('id', 'txtID');
        txtID.setAttribute('name', 'txtID');
        txtID.setAttribute('value', selectedItemID);
        txtID.setAttribute('th:value', selectedItemID)

        // txtID
        tdID.appendChild(txtID);

        // td for Description
        let tdDescription = row.insertCell(1);
        tdDescription.innerHTML = item.description;

        // td for Count
        let tdCount = row.insertCell(2);
        let numCount = document.createElement('input');
        numCount.type = 'number';
        numCount.min = 1;
        numCount.max = 99;
        numCount.value = 1;
        numCount.setAttribute('id', 'numCount');
        numCount.setAttribute('name', 'numCount');
        numCount.setAttribute('class', 'text-right')

        // NumericUpDown
        tdCount.appendChild(numCount);
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