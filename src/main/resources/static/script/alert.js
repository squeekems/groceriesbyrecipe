$(function()
{
  if ($('.alert') != null && $('.alert').innerHTML !== null)
  {
    setTimeout(() => 
    {
      $('.alert').alert('close');
    }, 3000);
  }
});