$('#lstItemsInStore').on('click', '.clickable-row', function(event) {
  $(this).toggleClass('bg-info').siblings().removeClass('bg-info');
});