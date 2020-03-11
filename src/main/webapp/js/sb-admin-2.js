(function($) {
  "use strict"; // Start of use strict

  // Toggle the side navigation
  $("#sidebarToggle, #sidebarToggleTop").on('click', function(e) {
    $("body").toggleClass("sidebar-toggled");
    $(".sidebar").toggleClass("toggled");
    if ($(".sidebar").hasClass("toggled")) {
      $('.sidebar .collapse').collapse('hide');
    };
  });

  // Close any open menu accordions when window is resized below 768px
  $(window).resize(function() {
    if ($(window).width() < 768) {
      $('.sidebar .collapse').collapse('hide');
    };
  });

  // Prevent the content wrapper from scrolling when the fixed side navigation hovered over
  $('body.fixed-nav .sidebar').on('mousewheel DOMMouseScroll wheel', function(e) {
    if ($(window).width() > 768) {
      var e0 = e.originalEvent,
        delta = e0.wheelDelta || -e0.detail;
      this.scrollTop += (delta < 0 ? 1 : -1) * 30;
      e.preventDefault();
    }
  });

  // Scroll to top button appear
  $(document).on('scroll', function() {
    var scrollDistance = $(this).scrollTop();
    if (scrollDistance > 100) {
      $('.scroll-to-top').fadeIn();
    } else {
      $('.scroll-to-top').fadeOut();
    }
  });

  // Smooth scrolling using jQuery easing
  $(document).on('click', 'a.scroll-to-top', function(e) {
    var $anchor = $(this);
    $('html, body').stop().animate({
      scrollTop: ($($anchor.attr('href')).offset().top)
    }, 1000, 'easeInOutExpo');
    e.preventDefault();
  });


$(document).ready(function() {
    $('#btnFetch').on('click',function() {
      // disable button
      $(this).prop('disabled', true);
      // add spinner to button
      $(this).html(
        '<span class="spinner-border spinner-border-sm p-2" role="status" aria-hidden="true"></span> Cargando...'
      );
      $('#form-search').submit();
    });
});

$('#btnImagen').on({
    'click': function(){
        if(urlFileMultimedia !== ''){
            $('#fileMultimedia').attr('src',urlFileMultimedia);
            $('#tweetMultimedia').attr('href',urlTweetMultimedia);
            $('#txtMultimedia').text('Esta es tu imagen más comentada en Twitter. Retweets: ' + numMultimTw + ' Me gusta: ' + numMultimFv);
            $('#linkMultim').show()();
        }else{
            $('#txtMultimedia').text('Parece que tus imágenes no son muy populares.');
            $('#linkMultim').hide();
        }
        
    }
}); 
$('#btnGif').on({
    'click': function(){
        if(urlFileMultimedia !== ''){
            $('#fileMultimedia').attr('src',urlFileMultimedia);
            $('#tweetMultimedia').attr('href',urlTweetMultimedia);
            $('#txtMultimedia').text('Este es tu gif más comentado en Twitter. Retweets: ' + numMultimTw + ' Me gusta: ' + numMultimFv);
            $('#linkMultim').show()();
        }else{
            $('#txtMultimedia').text('Parece que tus gifs no son muy populares.');
            $('#linkMultim').hide();
        }
    }
}); 
$('#btnVideo').on({
    'click': function(){
        if(urlFileMultimedia !== ''){
            $('#fileMultimedia').attr('src',urlFileMultimedia);
            $('#tweetMultimedia').attr('href',urlTweetMultimedia);
            $('#txtMultimedia').text('Este es tu vídeo más comentado en Twitter. Retweets: ' + numMultimTw + ' Me gusta: ' + numMultimFv);
            $('#linkMultim').show()();
        }else{
            $('#txtMultimedia').text('Parece que tus vídeos no son muy populares.');
            $('#linkMultim').hide();
        }
}
}); 





})(jQuery); // End of use strict
