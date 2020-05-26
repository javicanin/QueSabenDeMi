<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html lang="es">

  <head>


    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <meta http-equiv="Expires" content="0">
    <meta http-equiv="Last-Modified" content="0">
    <meta http-equiv="Cache-Control" content="no-cache, mustrevalidate">
    <meta http-equiv="Pragma" content="no-cache">

    <title>QueSabenDeDí</title>

    <link href="css/estilos.css" rel="stylesheet">
    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">
    <!-- Custom styles for this page -->
    <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">



    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.3.1/dist/leaflet.css" integrity="sha512-Rksm5RenBEKSKFjgI3a41vrjkw4EVPlJ3+OiI65vTjIdo9brlAacEuKOiQ5OFh7cOI1bkDwLqdLw3Zg0cRJAAQ=="   crossorigin=""/>
    <script src="https://unpkg.com/leaflet@1.3.1/dist/leaflet.js" integrity="sha512-/Nsx9X4HebavoBvEBuyp3I7od5tA0UzAxs+j83KgC8PU0kgB4XiK4Lfe4y4cgBtaRJQEIFCW+oC506aPT2L1zw==" crossorigin=""></script>
    <!-- GeoJSON -->
    <!-- esri -->
    <script src="https://unpkg.com/esri-leaflet@2.0.8"></script>
    <!--MarkerCluster -->
    <link rel="stylesheet" href="Plugins/MarkerCluster/MarkerCluster.css" />
    <link rel ="stylesheet" href="Plugins/MarkerCluster/MarkerCluster.Default.css"/>
    <script src="Plugins/MarkerCluster/leaflet.markercluster.js"></script>
    <!-- Control de capas -->
    <script src="Plugins/StyledLayer/src/styledLayerControl.js"></script>
    <link rel="stylesheet" href="Plugins/StyledLayer/css/styledLayerControl.css">
    <!-- Plugin FullScreen -->
    <link rel="stylesheet" href="Plugins/FullScreen/Control.FullScreen.css" />
    <script src="Plugins/FullScreen/Control.FullScreen.js"></script>


  </head>

  <body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">
      <!-- Content Wrapper -->
      <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

          <!-- Topbar -->
          <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

            <!-- Topbar Search -->
            <form id="form-search" name="formulario" class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search" action="<%= response.encodeURL("ConexionTwitter")%>" method="post">
              <div class="input-group">
                <span class="text-primary input-txt-fixed font-weight-bolder">@</span>
                <input type="text" maxlength="20" name="userTwitter" style="padding-left: 29px" class="form-control bg-light border-0 small" placeholder="Introduce usuario Twitter..." aria-label="Search" aria-describedby="basic-addon2">
                <div class="input-group-append">
                  <button class="btn btn-primary" type="submit" onclick="modalSpinner();" id="btnFetch">
                    <i class="fas fa-search fa-sm" id="lupa"></i>
                  </button>
                </div>
              </div>
            </form>

            <!-- Topbar Navbar -->
            <ul class="navbar-nav ml-auto">

              <!-- Nav Item - Search Dropdown (Visible Only XS) -->
              <li class="nav-item dropdown no-arrow d-sm-none">
                <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  <i class="fas fa-search fa-fw"></i>
                </a>
                <!-- Dropdown - Messages -->
                <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in" aria-labelledby="searchDropdown">
                  <form class="form-inline mr-auto w-100 navbar-search">
                    <div class="input-group">
                      <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
                      <div class="input-group-append">
                        <button class="btn btn-primary" type="button">
                          <i class="fas fa-search fa-sm"></i>
                        </button>
                      </div>
                    </div>
                  </form>
                </div>
              </li>


              <div class="topbar-divider d-none d-sm-block"></div>

              <!-- Nav Item - User Information -->
              <li class="nav-item dropdown no-arrow">
                <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  <span class="mr-2 d-none d-lg-inline text-gray-600 small">${usrBean.name} - ${usrBean.screenName}</span>
                  <img class="img-profile rounded-circle" src="${usrBean.urlImgProfile}">
                </a>
                <!-- Dropdown - User Information -->
                <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" style="z-index: 1100;" aria-labelledby="userDropdown">
                  <img class="rounded py-2 px-3" src="${usrBean.urlImgProfileBig}" style="width: 13rem">
                  <div class="dropdown-divider"></div>
                  <a class="dropdown-item pl-4" href="https://twitter.com/${usrBean.screenName}" target="_blank">
                    <%--<i class="fas fa-external-link fa-sm fa-fw mr-2 text-gray-400"></i>--%>
                    <i class="text-gray-400"></i>
                    <img class="img-fluid mr-1" src="images/link-40.png" style="width: 1rem" >
                    URL: <span class="text-primary">${usrBean.screenName}</span>
                  </a>
                  <a class="dropdown-item pl-4" href="#" target="">
                    <i class="text-gray-400"></i>
                    <img class="img-fluid mr-1" src="images/amigos-50.png" style="width: 1rem" >
                    Amigos: <span class="text-primary">${usrBean.numFriends}</span>
                  </a>
                  <a class="dropdown-item pl-4" href="#" target="">
                    <i class="text-gray-400"></i>
                    <img class="img-fluid mr-1" src="images/followers-40.png" style="width: 1rem" >
                    Seguidores: <span class="text-primary">${usrBean.numFollowers}</span>
                  </a>
                  <a class="dropdown-item pl-4" href="#" target="">
                    <i class="text-gray-400"></i>
                    <img class="img-fluid mr-1" src="images/twitter-40.png" style="width: 1rem" >
                    Tweets: <span class="text-primary">${usrBean.numTweets}</span>
                  </a>
                  <a class="dropdown-item pl-4" href="#" target="">
                    <i class="text-gray-400"></i>
                    <img class="img-fluid mr-1" src="images/corazon-40.png" style="width: 1rem" >
                    Me gustas: <span class="text-primary">${usrBean.numFavoritos}</span>
                  </a>
                  <a class="dropdown-item pl-4" href="#" target="">
                    <i class="text-gray-400"></i>
                    <img class="img-fluid mr-1" src="images/listas-50.png" style="width: 1rem" >
                    Listas: <span class="text-primary">${usrBean.numListas}</span>
                  </a>


                </div>
              </li>

            </ul>

          </nav>
          <!-- End of Topbar -->

          <!-- Begin Page Content -->
          <div class="container-fluid">

            <!-- Content Row -->
            <div class="row">

              <div class="col-xl-4 col-lg-4 col-md-6 col-sm-6 col-12 mb-3 px-2">
                <div class="card border-left-primary">
                  <div class="card-body p-2">
                    <div class="row no-gutters align-items-center">
                      <div class="col mr-2">
                        <div class="h6 font-weight-bold text-primary mb-1">
                          <c:if test="${usrBean.verified}">Perfil verificado</c:if>
                          <c:if test="${not usrBean.verified}">Perfil NO verificado</c:if>
                          </div>
                          <div class="text-xs font-weight-bold text-uppercase text-gray-900">
                          ${frases['VERIFICADO']}
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>


              <div class="col-xl-4 col-lg-4 col-md-6 col-sm-6 col-12 mb-3 px-2">
                <div class="card border-left-success">
                  <div class="card-body p-2">
                    <div class="row no-gutters align-items-center">
                      <div class="col mr-2">
                        <div class="h6 font-weight-bold text-success mb-1">
                          <c:if test="${usrBean.protegido}">Perfil protegido</c:if>
                          <c:if test="${not usrBean.protegido}">Perfil NO protegido</c:if>
                          </div>
                          <div class="text-xs font-weight-bold text-uppercase text-gray-900">
                          ${frases['PROTEGIDO']}
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              <div class="col-xl-4 col-lg-4 col-md-6 col-sm-6 col-12 mb-3 px-2">
                <div class="card border-left-info">
                  <div class="card-body p-2">
                    <div class="row no-gutters align-items-center">
                      <div class="col mr-2">
                        <div class="h6 font-weight-bold text-info mb-1">Cuándo Twiteas más:</div>
                        <div class="text-xs font-weight-bold text-uppercase text-gray-900"><a class="scrollLink text-xs font-weight-bold text-uppercase text-gray-900" href="#cuandoUsas">${fraseUsoGeneral}</a></div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              <div class="col-xl-4 col-lg-4 col-md-6 col-sm-6 col-12 mb-3 px-2">
                <div class="card border-left-warning">
                  <div class="card-body p-2">
                    <div class="row no-gutters align-items-center">
                      <div class="col mr-2">
                        <div class="h6 font-weight-bold text-warning mb-1">Tweets: ${usrBean.numTweets} / Me gustas: ${usrBean.numFavoritos}</div>
                        <div class="text-xs font-weight-bold text-uppercase text-gray-900">${frases['VOLUMEN-USO']}</div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              <div class="col-xl-4 col-lg-4 col-md-6 col-sm-6 col-12 mb-3 px-2">
                <div class="card border-left-primary">
                  <div class="card-body p-2">
                    <div class="row no-gutters align-items-center">
                      <div class="col mr-2">
                        <div class="h6 font-weight-bold text-primary mb-1">Amigos: ${usrBean.numFriends}</div>
                        <div class="row no-gutters align-items-center">
                          <div class="col-auto">
                            <div class="text-xs font-weight-bold text-uppercase text-gray-900">${frases['NUM-AMIGOS']}</div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              <div class="col-xl-4 col-lg-4 col-md-6 col-sm-6 col-12 mb-3 px-2">
                <div class="card border-left-success">
                  <div class="card-body p-2">
                    <div class="row no-gutters align-items-center">
                      <div class="col mr-2">
                        <div class="h6 font-weight-bold text-success mb-1">Followers: ${usrBean.numFollowers}</div>
                        <div class="text-xs font-weight-bold text-uppercase text-gray-900">${frases['NUM-SEGUIDORES']}</div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>


            <!-- Content Row -->
            <div class="row">

              <!-- DataTales Example -->
              <div class="col-xl-6 col-lg-6 px-2">

                <div class="card shadow mb-3">
                  <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">Tus mejores amigos o perfiles preferidos</h6>
                  </div>
                  <div class="card-body">
                    <div class="table-responsive">
                      <table class="table table-bordered table-sm" id="dataTable" width="100%" cellspacing="0" >
                        <thead>
                          <tr>
                            <th style="text-align: center">Imagen</th>
                            <th>Nombre</th>
                            <th>Usuario</th>
                          </tr>
                        </thead>
                        <tfoot>
                        </tfoot>
                        <tbody>
                          <c:forEach items="<%=request.getAttribute("amigos")%>" var="amg">
                            <tr>
                              <td style="text-align: center">
                                <div class="zoom">
                                  <img style="width: 2rem; height: 2rem" class="img-profile rounded" src="${amg.value.urlImgProfileBig}">
                                </div>
                              </td>
                              <td style="vertical-align: middle">
                                <a href="https://twitter.com/${amg.value.screenName}" target="_blank">${amg.value.name}</a>                              
                              </td>
                              <td style="vertical-align: middle">
                                <a class="table-sm" href="https://twitter.com/${amg.value.screenName}" target="_blank">@${amg.value.screenName}</a>
                              </td>
                            </tr>
                          </c:forEach>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>
              </div>

              <div class="col-xl-6 col-lg-6 px-2">
                <div class="card shadow mb-3">
                  <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">Dónde usas Twitter</h6>
                  </div>
                  <div class="card-body">
                    <div class="text-center">
                      <div  id="map" class="img-fluid mb-4 rounded" style="height: 25rem;" alt=""></div>
                    </div>
                    <p>En este mapa podrás observar las ubicaciones en el uso de twitter </p>
                  </div>
                </div>
              </div>


            </div>


            <!-- Content Row -->

            <div class="row">

              <!-- Area Chart -->
              <div class="col-xl-8 col-lg-7 px-2">
                <div class="card shadow mb-3" id="cuandoUsas">
                  <!-- Card Header - Dropdown -->
                  <div class="card-header p-0 d-flex flex-row align-items-center justify-content-between">
                    <h6 class="ml-3 mt-2 font-weight-bold text-primary">Cuándo usas Twitter</h6>
                    <ul class="nav nav-tabs mr-3 mt-2 border-bottom-0">
                      <li id="prueba" class="nav-item"><a id="time-horas" class="nav-link px-2" onclick="selectTime(1);return false;" href="#">Horas</a></li>
                      <li class="nav-item"><a id="time-dias" class="nav-link active px-2" onclick="selectTime(2);return false;" href="#">Días</a></li>
                      <li class="nav-item"><a id="time-meses" class="nav-link px-2" onclick="selectTime(3);return false;" href="#">Meses</a></li>
                      <li class="nav-item"><a id="time-annios" class="nav-link px-2" onclick="selectTime(4);return false;" href="#">Años</a></li>
                    </ul>

                  </div>
                  <!-- Card Body -->
                  <div class="card-body">
                    <div class="chart-area">
                      <canvas id="myAreaChart"></canvas>
                    </div>
                  </div>
                </div>
              </div>

              <!-- Pie Chart -->
              <div class="col-xl-4 col-lg-5 px-2">
                <div class="card shadow mb-3">
                  <!-- Card Header - Dropdown -->
                  <div class="card-header p-0 d-flex flex-row align-items-center justify-content-between">
                    <h6 class="m-3 font-weight-bold text-primary">Desde el teléfono, ordenador, aplicación</h6>
                  </div>
                  <!-- Card Body -->
                  <div class="card-body">
                    <div class="text-center">
                      <c:if test="${datosDispositivos!='[]'}">
                        <div class="chart-pie">
                          <canvas id="myPieChart"></canvas>
                        </div>
                      </c:if>
                      <c:if test="${datosDispositivos=='[]'}">
                        <img class="img-fluid px-3 px-sm-4 mt-3 mb-4" style="width: 17.3rem" src="images/logo-multimedia-tw.png" alt="">
                      </c:if>
                    </div>
                    <c:if test="${datosDispositivos!='[]'}">
                      <p class="pb-4"></p>
                    </c:if>
                    <c:if test="${datosDispositivos=='[]'}">
                      <p>${frases['INFO-NO-DISPONIBLE']}</p>
                    </c:if>

                  </div>
                </div>
              </div>
            </div>


            <!-- Content Row -->
            <div class="row">


              <div class="col-xl-6 col-lg-6 col-md-6 px-2">
                <!-- Illustrations -->
                <div class="card shadow mb-3">
                  <div class="card-header p-0 d-flex flex-row align-items-center justify-content-between">
                    <h6 class="ml-3 mt-2 font-weight-bold text-primary">Imágenes y vídeos populares</h6>

                    <ul class="nav nav-tabs mr-3 mt-2 border-bottom-0">
                      <li class="nav-item"><a id="file-imagen" class="nav-link active px-2" onclick="selectFile(1);return false;" href="#">Imagen</a></li>
                      <li class="nav-item"><a id="file-gif" class="nav-link px-3" onclick="selectFile(2);return false;" href="#">Gif</a></li>
                      <li class="nav-item"><a id="file-video" class="nav-link px-2" onclick="selectFile(3);return false;" href="#">Video</a></li>
                    </ul>


                  </div>
                  <div class="card-body">
                    <div class="text-center">
                      <img id="fileMultimedia" class="img-fluid px-3 px-sm-4 mt-3 mb-4" style="width: 20rem;"
                           <c:if test="${multimediaBean['photo'].urlArchivo != null}">
                             src="${multimediaBean['photo'].urlArchivo}"
                           </c:if>
                           <c:if test="${multimediaBean['photo'].urlArchivo == null}">
                             src="images/logo-multimedia-tw.png"
                           </c:if>" alt="">
                    </div>
                    <c:if test="${multimediaBean['photo'] != null}">
                      <p id="txtMultimedia">Esta es tu imagen más comentada en Twitter.
                        Retweets: <span class="font-weight-bold text-primary">${multimediaBean["photo"].numRetweets}</span>
                        Me gusta: <span class="font-weight-bold text-primary">${multimediaBean["photo"].numFavoritos}</span></p>
                      <p id="linkMultim">Pulsa
                        <a id="tweetMultimedia" target="_blank" rel="nofollow" href="${multimediaBean["photo"].urlTweet}"> aquí </a>para acceder al Tweet.
                      </p>
                    </c:if>
                    <c:if test="${multimediaBean['photo'] == null}">
                      <p id="txtMultimedia">Parece que tus imágenes no son muy populares.</p>
                      <p id="linkMultim"> <a id="tweetMultimedia" target="_blank" rel="nofollow" href=""></a> </p>
                    </c:if>

                  </div>
                </div>
              </div>




              <div class="col-xl-6 col-lg-6 col-md-6 px-2">
                <div class="card shadow mb-3">
                  <div class="card-header p-0 d-flex flex-row align-items-center justify-content-between">
                    <h6 class="m-3 font-weight-bold text-primary">Tus gustos o aficiones</h6>
                  </div>
                  <div class="card-body">
                    <p class="m-0 p-0">
                      <img id="" class="img-fluid p-1" style="width: 2.5rem;" src="images/games.png" title="Videojuegos" alt="">
                      ${frases['GUSTOS-GAMERS']}
                    </p>
                    <p class="m-0 p-0">
                      <img id="" class="img-fluid p-1" style="width: 2.5rem;" src="images/parlamento.png" title="Política" alt="">
                      ${frases['GUSTOS-POLITICA']}
                    </p>
                    <p class="m-0 p-0">
                      <img id="" class="img-fluid p-1" style="width: 2.5rem;" src="images/ball-football.png" title="Deportes" alt="">
                      ${frases['GUSTOS-DEPORTES']}
                    </p>
                  </div>
                </div>
              </div>


            </div>

          </div>
          <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->
        <!-- Footer -->
        <footer class="sticky-footer bg-white py-4">
          <div class="container my-auto">
            <div class="copyright text-center my-auto">
              <span>UCAM - QueSabenDeMi 2020</span>
                <p class="mb-0 mt-2">
                  Ver la <a href="politica-privacidad.jsp" target="_blank">política de privacidad</a>
                </p>
            </div>
          </div>
        </footer>
        <!-- End of Footer -->

      </div>
      <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <div class="modal fade bd-example-modal-lg" data-backdrop="static" data-keyboard="false" tabindex="-1">
      <div class="modal-dialog modal-sm">
        <div class="modal-content text-center text-white">
          <span class="fa fa-spinner fa-spin fa-3x"></span>
          <div class="my-2">Cargando . . .</div>
        </div>
      </div>
    </div>


    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
      <i class="fas fa-angle-up"></i>
    </a>




    <script type="text/javascript">
      /////////////// GRAFICA USO TWITTER /////////////// 
      var usoTwtBean = <%=request.getAttribute("usoTw")%>;
      var datos = usoTwtBean['dias'];
      var etiquetas = usoTwtBean['labelsDias'];
      function selectTime(tipo) {
        if (tipo === 1) {
          datos = usoTwtBean['horas'];
          etiquetas = usoTwtBean['labelsHoras'];
          myLineChart.options.scales.xAxes[0].scaleLabel.display = false;
          myLineChart.options.scales.xAxes[0].scaleLabel.labelString = "Horas";
          $('#time-horas').tab('show');
        }
        if (tipo === 2) {
          datos = usoTwtBean['dias'];
          etiquetas = usoTwtBean['labelsDias'];
          myLineChart.options.scales.xAxes[0].scaleLabel.display = false;
          $('#time-dias').tab('show');
        }
        if (tipo === 3) {
          datos = usoTwtBean['meses'];
          etiquetas = usoTwtBean['labelsMeses'];
          myLineChart.options.scales.xAxes[0].scaleLabel.display = false;
          $('#time-meses').tab('show');
        }
        if (tipo === 4) {
          datos = usoTwtBean['annios'];
          etiquetas = usoTwtBean['labelsAnnios'];
          myLineChart.options.scales.xAxes[0].scaleLabel.display = false;
          myLineChart.options.scales.xAxes[0].scaleLabel.labelString = "Años";
          $('#time-annios').tab('show');
        }

        myLineChart.data.datasets[0].data = datos;
        myLineChart.data.labels = etiquetas;
        myLineChart.update();
      }

      /////////////// GRAFICA DISPOSITIVOS ///////////////     
      var labelsDisp = <%=request.getAttribute("labelsDispositivos")%>;
      var datosDisp = <%=request.getAttribute("datosDispositivos")%>;

      ////////////////////// MAPA ///////////////////////
      var tweets = <%=request.getAttribute("geoJsonPlaces")%>;

      /////////////// TIPO DE FICHERO MULTIMEDIA ///////////////     
      var urlFileMultimedia = "${multimediaBean["photo"].urlArchivo}";
      var urlTweetMultimedia = "${multimediaBean["photo"].urlTweet}";
      var numMultimTw = "${multimediaBean["photo"].numRetweets}";
      var numMultimFv = "${multimediaBean["photo"].numFavoritos}";
      function selectFile(tipo) {
        if (tipo === 1) {
          urlFileMultimedia = "${multimediaBean["photo"].urlArchivo}";
          urlTweetMultimedia = "${multimediaBean["photo"].urlTweet}";
          numMultimTw = "${multimediaBean["photo"].numRetweets}";
          numMultimFv = "${multimediaBean["photo"].numFavoritos}";
          $('#file-imagen').tab('show');
        }
        if (tipo === 2) {
          urlFileMultimedia = "${multimediaBean["animated_gif"].urlArchivo}";
          urlTweetMultimedia = "${multimediaBean["animated_gif"].urlTweet}";
          numMultimTw = "${multimediaBean["animated_gif"].numRetweets}";
          numMultimFv = "${multimediaBean["animated_gif"].numFavoritos}";
          $('#file-gif').tab('show');
        }
        if (tipo === 3) {
          urlFileMultimedia = "${multimediaBean["video"].urlArchivo}";
          urlTweetMultimedia = "${multimediaBean["video"].urlTweet}";
          numMultimTw = "${multimediaBean["video"].numRetweets}";
          numMultimFv = "${multimediaBean["video"].numFavoritos}";
          $('#file-video').tab('show');
        }
      }

      /////////////////// SPINNER MODAL ///////////////////
      function modalSpinner() {
        $('.modal').modal('show');
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

    <!-- Page level plugins -->
    <script src="vendor/chart.js/Chart.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="js/chart-area-demo.js"></script>
    <!-- Condicion para que no cargue el grafico si no hay datos-->
    <c:if test="${datosDispositivos!='[]'}">
      <script src="js/chart-pie-demo.js"></script>
    </c:if>


    <!-- Page level plugins -->
    <script src="vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="js/datatables-demo.js"></script>


    <!-- Controles para el mapa de Tweets -->
    <script src="js/control-mapa.js"></script>
  </body>

</html>
