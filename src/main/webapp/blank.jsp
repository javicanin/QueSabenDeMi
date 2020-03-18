<%-- 
    Document   : blank
    Created on : 28-feb-2020, 13:45:47
    Author     : USUARIO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

  <head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Qué saben de mí</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">

    <style>
      .bd-example-modal-lg .modal-dialog{
        display: table;
        position: relative;
        margin: 0 auto;
        top: calc(50% - 24px);
      }

      .bd-example-modal-lg .modal-dialog .modal-content{
        background-color: transparent;
        border: none;
      }
    </style>

  </head>

  <body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">



      <!-- Content Wrapper -->
      <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">
          <div class="container-fluid bg-white">

            <div class="row">
              <div class="col-12 my-5">
                <h1 class="h1 text-center text-primary">Qué sabe Twitter de mi...</h1>
              </div>
            </div>

            <div class="row">
              <div class="col-12 my-5">
                <form id="form-search" name="formulario" class="col-xl-4 offset-xl-4 col-lg-4 offset-lg-4 col-md-6 offset-md-3 col-sm-8 offset-sm-2 col-xs-8 offset-xs-2 col-10 offset-1" action="<%= response.encodeURL("ConexionTwitter")%>" method="post">
                  <div class="input-group">
                    <input type="text" name="userTwitter" class="form-control bg-light border-0 small" placeholder="Introduce usuario Twitter..." aria-label="Search" aria-describedby="basic-addon2">
                    <div class="input-group-append">
                      <button class="btn btn-primary" type="submit" onclick="modalSpinner();">
                        <i class="fas fa-search fa-sm" id="lupa"></i>
                      </button>
                    </div>
                  </div>
                </form>
              </div>
            </div>

            <!--                      <button class="btn btn-primary" type="submit" onclick="modalSpinner();" id="btn">
            spinner
                                  </button>
            -->



          </div>
        </div>
        <!-- End of Main Content -->


        <!-- Footer -->
        <footer class="sticky-footer bg-white">
          <div class="container my-auto">
            <div class="copyright text-center my-auto">
              <span>Copyright &copy; QueSabenDeMi  2020</span>
            </div>
          </div>
        </footer>
        <!-- End of Footer -->

      </div>
      <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->



    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
      <i class="fas fa-angle-up"></i>
    </a>

    <div class="modal fade bd-example-modal-lg" data-backdrop="static" data-keyboard="false" tabindex="-1">
      <div class="modal-dialog modal-sm">
        <div class="modal-content text-center text-white">
          <span class="fa fa-spinner fa-spin fa-3x"></span>
          <div class="my-2">Cargando . . .</div>
        </div>
      </div>
    </div>

    <script type="text/javascript">
      function modalSpinner() {
        $('.modal').modal('show');
        /*       setTimeout(function () {
         console.log('hejsan');
         $('.modal').modal('hide');
         }, 300000);*/
      }

    </script>    

    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>
    <script src="js/sb-admin-2.js"></script>


  </body>

</html>
