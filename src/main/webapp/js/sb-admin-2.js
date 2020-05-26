(function ($) {
  "use strict"; // Start of use strict

  // Toggle the side navigation
  $("#sidebarToggle, #sidebarToggleTop").on('click', function (e) {
    $("body").toggleClass("sidebar-toggled");
    $(".sidebar").toggleClass("toggled");
    if ($(".sidebar").hasClass("toggled")) {
      $('.sidebar .collapse').collapse('hide');
    }
    ;
  });

  // Close any open menu accordions when window is resized below 768px
  $(window).resize(function () {
    if ($(window).width() < 768) {
      $('.sidebar .collapse').collapse('hide');
    }
    ;
  });

  // Prevent the content wrapper from scrolling when the fixed side navigation hovered over
  $('body.fixed-nav .sidebar').on('mousewheel DOMMouseScroll wheel', function (e) {
    if ($(window).width() > 768) {
      var e0 = e.originalEvent,
              delta = e0.wheelDelta || -e0.detail;
      this.scrollTop += (delta < 0 ? 1 : -1) * 30;
      e.preventDefault();
    }
  });

  // Scroll to top button appear
  $(document).on('scroll', function () {
    var scrollDistance = $(this).scrollTop();
    if (scrollDistance > 100) {
      $('.scroll-to-top').fadeIn();
    } else {
      $('.scroll-to-top').fadeOut();
    }
  });

  // Smooth scrolling using jQuery easing
  $(document).on('click', 'a.scroll-to-top', function (e) {
    var $anchor = $(this);
    $('html, body').stop().animate({
      scrollTop: ($($anchor.attr('href')).offset().top)
    }, 1000, 'easeInOutExpo');
    e.preventDefault();
  });


  $(document).ready(function () {
    $('#btnFetch').on('click', function () {
      // disable button
      $(this).prop('disabled', true);
      $('#form-search').submit();
    });

    
    //Funcion para hacer un scroll más lento de la página al pulsar href
    $("a.scrollLink").click(function (event) {
      event.preventDefault();
      $("html, body").animate({scrollTop: $($(this).attr("href")).offset().top}, 500);
    });
  });

  $('#file-imagen').on({
    'click': function () {
      if (urlFileMultimedia !== '') {
        $('#fileMultimedia').attr('src', urlFileMultimedia);
        $('#tweetMultimedia').attr('href', urlTweetMultimedia);
        $('#txtMultimedia').text('Tu imagen más comentada en Twitter. Retweets: ' + numMultimTw + ' Me gusta: ' + numMultimFv);
        $('#linkMultim').show();
      } else {
        $('#txtMultimedia').text('Parece que tus imágenes no son muy populares.');
        $('#linkMultim').hide();
        $('#fileMultimedia').attr('src', "images/logo-multimedia-tw.png");
      }

    }
  });
  $('#file-gif').on({
    'click': function () {
      if (urlFileMultimedia !== '') {
        $('#fileMultimedia').attr('src', urlFileMultimedia);
        $('#tweetMultimedia').attr('href', urlTweetMultimedia);
        $('#txtMultimedia').text('Tu gif más comentado en Twitter. Retweets: ' + numMultimTw + ' Me gusta: ' + numMultimFv);
        $('#linkMultim').show();
      } else {
        $('#txtMultimedia').text('Parece que tus gifs no son muy populares.');
        $('#linkMultim').hide();
        $('#fileMultimedia').attr('src', "images/logo-multimedia-tw.png");
      }
    }
  });
  $('#file-video').on({
    'click': function () {
      if (urlFileMultimedia !== '') {
        $('#fileMultimedia').attr('src', urlFileMultimedia);
        $('#tweetMultimedia').attr('href', urlTweetMultimedia);
        $('#txtMultimedia').text('Tu vídeo más comentado en Twitter. Retweets: ' + numMultimTw + ' Me gusta: ' + numMultimFv);
        $('#linkMultim').show();
      } else {
        $('#txtMultimedia').text('Parece que tus vídeos no son muy populares.');
        $('#linkMultim').hide();
        $('#fileMultimedia').attr('src', "images/logo-multimedia-tw.png");
      }
    }
  });



})(jQuery); // End of use strict
