$(document).ready(function() {

  $('.modal-open').click(function() {
    $('#' + $(this).attr('for')).addClass('is-active');
  });

  $('div.modal > button.modal-close').click(function() {
    $(this).parent().removeClass('is-active');
  });

  $(".date-picker").flatpickr({});

  flatpickr(".flatpickr");

});
