<%-- 
    Document   : blank
    Created on : 28-feb-2020, 13:45:47
    Author     : USUARIO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">

  <head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>QueSabenDeMi</title>

    <link href="css/inicio.css" rel="stylesheet">
    <link href="css/estilos.css" rel="stylesheet">    
    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">

  </head>

  <body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

      <!-- Content Wrapper -->
      <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">
          <div class="container-fluid">

            <div class="row">
              <div class="col-12 my-5">
              </div>
            </div>
            <div class="row">
              <div class="col-12 my-5">
                <p id="titulo-app" class="text-center text-primary">Qué sabe Twitter sobre mi...</p>
              </div>
            </div>

            <div class="row d-flex flex-column">
              <div class="col-12">
                <form id="form-search" name="formulario" title="Debe aceptar la política de privacidad" class="col-xl-4 offset-xl-4 col-lg-4 offset-lg-4 col-md-6 offset-md-3 col-sm-8 offset-sm-2 col-xs-8 offset-xs-2 col-10 offset-1" action="<%= response.encodeURL("ConexionTwitter")%>" method="post">

                <c:if test="${msgError != null}">
                <div class="card mb-2" id="div-error">
                  <div class="card-body p-2">
                    <div class="row no-gutters align-items-center">
                      <div class="col mr-2">
                        <div class="text-xs" style="color:red"> * ${msgError}</div>
                      </div>
                    </div>
                  </div>
                </div>
                </c:if>

                  <div class="input-group">
                    <span class="text-primary input-txt-fixed font-weight-bolder">@</span>
                    <input type="text" maxlength="20" name="userTwitter" id="userInput" disabled="true" style="padding-left: 29px" class="form-control border-0 small" placeholder="Introduce usuario Twitter..." aria-label="Search" aria-describedby="basic-addon2">
                    <div class="input-group-append">
                      <button class="btn btn-primary" type="submit" id="botonConsulta" disabled="true" onclick="modalSpinner();">
                        <i class="fas fa-search fa-sm" id="lupa"></i>
                      </button>
                    </div>
                  </div>
                </form>
                <div class="form-check text-center col-12 my-3">
                  <input type="checkbox" class="form-check-input" name="check" id="check" onChange="aceptPrivacity(this);"/>
                  <label class="form-check-label" for="defaultCheck1">
                    He leído y acepto la <a href="politica-privacidad.jsp" target="_blank">política de privacidad</a>
                  </label>
                </div>                    
              </div>
            </div>

          </div>
        </div>
        <!-- End of Main Content -->

        <!-- Footer -->
        <footer id="footer" class="sticky-footer bg-white">
          <div class="container my-auto">
            <div class="copyright text-center my-auto">
              <span>UCAM - QueSabenDeMi 2020</span>
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

    <div id="modalspinner" class="modal fade bd-example-modal-lg" data-backdrop="static" data-keyboard="false" tabindex="-1">
      <div class="modal-dialog modal-sm">
        <div class="modal-content text-center text-white">
          <span class="fa fa-spinner fa-spin fa-3x"></span>
          <div class="my-2">Cargando . . .</div>
        </div>
      </div>
    </div>

<script type="text/javascript">
  //Control sobre el checkbox obligatorio de privacidad y el campo de texto
  function aceptPrivacity(obj) {
    if (obj.checked) {
      document.getElementById('userInput').disabled = false;
      document.getElementById('form-search').title = "";
      document.getElementById('botonConsulta').disabled = false;
    } else {
      document.getElementById('userInput').disabled = true;
      document.getElementById('userInput').value = "";
      document.getElementById('form-search').title = "Debe aceptar la política de privacidad";
      document.getElementById('botonConsulta').disabled = true;
    }
  }

  //Muestra una ventana modal con indicador de carga al pulsar el boton de buscar
  function modalSpinner() {
    $('#modalspinner').modal('show');
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
