<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">

<head>

    
    
    
<style>
  /*ZOOM DE IMAGENES DE TABLA*/
.zoom {
  /*padding: 2px;*/
  transition: transform .2s; /* Animation */
  width: 2rem;
  height: 2rem;
  margin: auto;
}

.zoom:hover {
  -ms-transform: scale(3.3); /* IE 9 */
  -webkit-transform: scale(3.3); /* Safari 3-8 */
  transform: scale(3.3); 
}

  /*ESTILOS SPINNER EN MODAL*/
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


    
    
    
    
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <meta http-equiv="Expires" content="0">
  <meta http-equiv="Last-Modified" content="0">
  <meta http-equiv="Cache-Control" content="no-cache, mustrevalidate">
  <meta http-equiv="Pragma" content="no-cache">

  <title>Qué saben de mí</title>

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
    <script type="text/javascript" src="js/areaTweet.js"></script>
    <script type="text/javascript" src="js/Twitters.js"></script>
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
<%-- 
    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

      <!-- Sidebar - Brand -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
        <div class="sidebar-brand-icon rotate-n-15">
          <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3">SB Admin <sup>2</sup></div>
      </a>

      <!-- Divider -->
      <hr class="sidebar-divider my-0">

      <!-- Nav Item - Dashboard -->
      <li class="nav-item active">
        <a class="nav-link" href="index.html">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Dashboard</span></a>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Heading -->
      <div class="sidebar-heading">
        Interface
      </div>

      <!-- Nav Item - Pages Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
          <i class="fas fa-fw fa-cog"></i>
          <span>Components</span>
        </a>
        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Custom Components:</h6>
            <a class="collapse-item" href="buttons.html">Buttons</a>
            <a class="collapse-item" href="cards.html">Cards</a>
          </div>
        </div>
      </li>

      <!-- Nav Item - Utilities Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities" aria-expanded="true" aria-controls="collapseUtilities">
          <i class="fas fa-fw fa-wrench"></i>
          <span>Utilities</span>
        </a>
        <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Custom Utilities:</h6>
            <a class="collapse-item" href="utilities-color.html">Colors</a>
            <a class="collapse-item" href="utilities-border.html">Borders</a>
            <a class="collapse-item" href="utilities-animation.html">Animations</a>
            <a class="collapse-item" href="utilities-other.html">Other</a>
          </div>
        </div>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Heading -->
      <div class="sidebar-heading">
        Addons
      </div>

      <!-- Nav Item - Pages Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages" aria-expanded="true" aria-controls="collapsePages">
          <i class="fas fa-fw fa-folder"></i>
          <span>Pages</span>
        </a>
        <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Login Screens:</h6>
            <a class="collapse-item" href="login.html">Login</a>
            <a class="collapse-item" href="register.html">Register</a>
            <a class="collapse-item" href="forgot-password.html">Forgot Password</a>
            <div class="collapse-divider"></div>
            <h6 class="collapse-header">Other Pages:</h6>
            <a class="collapse-item" href="404.html">404 Page</a>
            <a class="collapse-item" href="blank.html">Blank Page</a>
          </div>
        </div>
      </li>

      <!-- Nav Item - Charts -->
      <li class="nav-item">
        <a class="nav-link" href="charts.html">
          <i class="fas fa-fw fa-chart-area"></i>
          <span>Charts</span></a>
      </li>

      <!-- Nav Item - Tables -->
      <li class="nav-item">
        <a class="nav-link" href="tables.html">
          <i class="fas fa-fw fa-table"></i>
          <span>Tables</span></a>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider d-none d-md-block">

      <!-- Sidebar Toggler (Sidebar) -->
      <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
      </div>

    </ul>
    <!-- End of Sidebar -->
--%>
    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Topbar -->
        <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

          <!-- Sidebar Toggle (Topbar) -->
          <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
            <i class="fa fa-bars"></i>
          </button>

          <!-- Topbar Search -->
          <form id="form-search" name="formulario" class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search" action="<%= response.encodeURL("ConexionTwitter")%>" method="post">
            <div class="input-group">
              <input type="text" name="userTwitter" class="form-control bg-light border-0 small" placeholder="Introduce usuario Twitter..." aria-label="Search" aria-describedby="basic-addon2">
              <div class="input-group-append">
                <button class="btn btn-primary" type="submit" onclick="modalSpinner();" id="btnFetch___________">
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

            <!-- Nav Item - Alerts -->
            <li class="nav-item dropdown no-arrow mx-1">
              <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-bell fa-fw"></i>
                <!-- Counter - Alerts -->
                <span class="badge badge-danger badge-counter">3+</span>
              </a>
              <!-- Dropdown - Alerts -->
              <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="alertsDropdown">
                <h6 class="dropdown-header">
                  Alerts Center
                </h6>
                <a class="dropdown-item d-flex align-items-center" href="#">
                  <div class="mr-3">
                    <div class="icon-circle bg-primary">
                      <i class="fas fa-file-alt text-white"></i>
                    </div>
                  </div>
                  <div>
                    <div class="small text-gray-500">December 12, 2019</div>
                    <span class="font-weight-bold">A new monthly report is ready to download!</span>
                  </div>
                </a>
                <a class="dropdown-item d-flex align-items-center" href="#">
                  <div class="mr-3">
                    <div class="icon-circle bg-success">
                      <i class="fas fa-donate text-white"></i>
                    </div>
                  </div>
                  <div>
                    <div class="small text-gray-500">December 7, 2019</div>
                    $290.29 has been deposited into your account!
                  </div>
                </a>
                <a class="dropdown-item d-flex align-items-center" href="#">
                  <div class="mr-3">
                    <div class="icon-circle bg-warning">
                      <i class="fas fa-exclamation-triangle text-white"></i>
                    </div>
                  </div>
                  <div>
                    <div class="small text-gray-500">December 2, 2019</div>
                    Spending Alert: We've noticed unusually high spending for your account.
                  </div>
                </a>
                <a class="dropdown-item text-center small text-gray-500" href="#">Show All Alerts</a>
              </div>
            </li>

            <!-- Nav Item - Messages -->
            <li class="nav-item dropdown no-arrow mx-1">
              <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-envelope fa-fw"></i>
                <!-- Counter - Messages -->
                <span class="badge badge-danger badge-counter">7</span>
              </a>
              <!-- Dropdown - Messages -->
              <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="messagesDropdown">
                <h6 class="dropdown-header">
                  Message Center
                </h6>
                <a class="dropdown-item d-flex align-items-center" href="#">
                  <div class="dropdown-list-image mr-3">
                    <img class="rounded-circle" src="https://source.unsplash.com/fn_BT9fwg_E/60x60" alt="">
                    <div class="status-indicator bg-success"></div>
                  </div>
                  <div class="font-weight-bold">
                    <div class="text-truncate">Hi there! I am wondering if you can help me with a problem I've been having.</div>
                    <div class="small text-gray-500">Emily Fowler · 58m</div>
                  </div>
                </a>
                <a class="dropdown-item d-flex align-items-center" href="#">
                  <div class="dropdown-list-image mr-3">
                    <img class="rounded-circle" src="https://source.unsplash.com/AU4VPcFN4LE/60x60" alt="">
                    <div class="status-indicator"></div>
                  </div>
                  <div>
                    <div class="text-truncate">I have the photos that you ordered last month, how would you like them sent to you?</div>
                    <div class="small text-gray-500">Jae Chun · 1d</div>
                  </div>
                </a>
                <a class="dropdown-item d-flex align-items-center" href="#">
                  <div class="dropdown-list-image mr-3">
                    <img class="rounded-circle" src="https://source.unsplash.com/CS2uCrpNzJY/60x60" alt="">
                    <div class="status-indicator bg-warning"></div>
                  </div>
                  <div>
                    <div class="text-truncate">Last month's report looks great, I am very happy with the progress so far, keep up the good work!</div>
                    <div class="small text-gray-500">Morgan Alvarez · 2d</div>
                  </div>
                </a>
                <a class="dropdown-item d-flex align-items-center" href="#">
                  <div class="dropdown-list-image mr-3">
                    <img class="rounded-circle" src="https://source.unsplash.com/Mv9hjnEUHR4/60x60" alt="">
                    <div class="status-indicator bg-success"></div>
                  </div>
                  <div>
                    <div class="text-truncate">Am I a good boy? The reason I ask is because someone told me that people say this to all dogs, even if they aren't good...</div>
                    <div class="small text-gray-500">Chicken the Dog · 2w</div>
                  </div>
                </a>
                <a class="dropdown-item text-center small text-gray-500" href="#">Read More Messages</a>
              </div>
            </li>

            <div class="topbar-divider d-none d-sm-block"></div>

            <!-- Nav Item - User Information -->
            <li class="nav-item dropdown no-arrow">
                <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="mr-2 d-none d-lg-inline text-gray-600 small">${usrBean.name} - ${usrBean.screenName}</span>
                <img class="img-profile rounded-circle" src="${usrBean.urlImgProfile}" >
              </a>
              <!-- Dropdown - User Information -->
              <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" style="z-index: 1100;" aria-labelledby="userDropdown">
                <img class="img-fluid rounded py-2 px-3"  src="${usrBean.urlImgProfileBig}" >
                <div class="dropdown-divider"></div>
                  <a class="dropdown-item" href="https://twitter.com/${usrBean.screenName}" target="_blank">
                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                    URL usuario: ${usrBean.screenName}
                  </a>
                  <a class="dropdown-item" href="#" target="">
                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                    Amigos: ${usrBean.numFriends}
                  </a>
                  <a class="dropdown-item" href="#" target="">
                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                    Seguidores: ${usrBean.numFollowers}
                  </a>
                  <a class="dropdown-item" href="#" target="">
                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                    Tweets: ${usrBean.numTweets}
                  </a>
                  <a class="dropdown-item" href="#" target="">
                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                    Me gustas: ${usrBean.numFavoritos}
                  </a>
                  <a class="dropdown-item" href="#" target="">
                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                    Listas: ${usrBean.numListas}
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

            <!-- Earnings (Monthly) Card Example -->
            <div class="col-xl-2 col-lg-2 col-md-3 col-sm-4 col-6 mb-4">
              <div class="card border-left-primary shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">Tweets</div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800">${usrBean.numTweets}</div>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-calendar fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <!-- Earnings (Monthly) Card Example -->
            <div class="col-xl-2 col-lg-2 col-md-3 col-sm-4 col-6 mb-4">
              <div class="card border-left-success shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-success text-uppercase mb-1">Followers</div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800">${usrBean.numFollowers}</div>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <!-- Earnings (Monthly) Card Example -->
            <div class="col-xl-2 col-lg-2 col-md-3 col-sm-4 col-6 mb-4">
              <div class="card border-left-info shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-info text-uppercase mb-1">Amigos</div>
                      <div class="row no-gutters align-items-center">
                        <div class="col-auto">
                          <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">${usrBean.numFriends}</div>
                        </div>
                        <div class="col">
                          <div class="progress progress-sm mr-2">
                            <div class="progress-bar bg-info" role="progressbar" style="width: 50%" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <!-- Pending Requests Card Example -->
            <div class="col-xl-2 col-lg-2 col-md-3 col-sm-4 col-6 mb-4">
              <div class="card border-left-warning shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">Me gustas</div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800">${usrBean.numFavoritos}</div>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-comments fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <div class="col-xl-2 col-lg-2 col-md-3 col-sm-4 col-6 mb-4">
              <div class="card border-left-primary shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">Listas</div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800">${usrBean.numListas}</div>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-calendar fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <div class="col-xl-2 col-lg-2 col-md-3 col-sm-4 col-6 mb-4">
              <div class="card border-left-success shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-success text-uppercase mb-1">Perfil protegido</div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800"><c:if test="${usrBean.protegido}">Si</c:if><c:if test="${not usrBean.protegido}">No</c:if></div>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-calendar fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>
                    
          </div>



        <!-- Content Row -->
        <div class="row">

          <!-- DataTales Example -->
          <div class="col-xl-6 col-lg-6">

          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">Éstos parecen tus mejores amigos de Twitter</h6>
            </div>
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered table-sm" id="dataTable" width="100%" cellspacing="0" >
                  <thead>
                    <tr>
                      <th style="text-align: center">Imagen</th>
                      <th>Nombre</th>
                      <th>Screen name</th>
                    </tr>
                  </thead>
                  <tfoot>
                    <tr>
                      <th style="text-align: center">Imagen</th>
                      <th>Nombre</th>
                      <th>Screen name</th>
                    </tr>
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
                        <a href="https://twitter.com/${amg.value.screenName}" target="_blank">${amg.value.screenName}</a>                              
                      </td>
                    </tr>
                  </c:forEach>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
          </div>

          <div class="col-xl-6 col-lg-6">
                <div class="card shadow mb-4">
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
            <div class="col-xl-8 col-lg-7">
              <div class="card shadow mb-4">
                <!-- Card Header - Dropdown -->
                <div class="card-header p-0 d-flex flex-row align-items-center justify-content-between">
                  <h6 class="ml-3 mt-2 font-weight-bold text-primary">Cuándo usas Twitter</h6>
                  <ul class="nav nav-tabs mr-3 mt-2 border-bottom-0">
                      <li id="prueba" class="nav-item"><a id="time-horas" class="nav-link px-2" onclick="selectTime(1);return false;" href="#">Horas</a></li>
                      <li class="nav-item"><a id="time-dias" class="nav-link active px-2" onclick="selectTime(2);return false;" href="#">Días</a></li>
                      <li class="nav-item"><a id="time-meses" class="nav-link px-2" onclick="selectTime(3);return false;" href="#">Meses</a></li>
                      <li class="nav-item"><a id="time-annios" class="nav-link px-2" onclick="selectTime(4);return false;" href="#">Años</a></li>
                  </ul>


<!--                  <div class="dropdown no-arrow">
                    <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                      <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in" aria-labelledby="dropdownMenuLink">
                      <div class="dropdown-header">Tipo de uso:</div>
                      <a class="dropdown-item" onclick="selectTime(1);return false;" href="#">Horas</a>
                      <a class="dropdown-item" onclick="selectTime(2);return false;" href="#">Días</a>
                      <a class="dropdown-item" onclick="selectTime(3);return false;" href="#">Meses</a>
                      <a class="dropdown-item" onclick="selectTime(4);return false;" href="#">Años</a>
                      <div class="dropdown-divider"></div>
                      <a class="dropdown-item" href="#">Something else here</a>
                    </div>
                  </div>
-->

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
            <div class="col-xl-4 col-lg-5">
              <div class="card shadow mb-4">
                <!-- Card Header - Dropdown -->
                <div class="card-header p-0 d-flex flex-row align-items-center justify-content-between">
                  <h6 class="m-3 font-weight-bold text-primary">Qué tecnologías usas</h6>

<!--                  <div class="dropdown no-arrow">
                    <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                      <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in" aria-labelledby="dropdownMenuLink">
                      <div class="dropdown-header">Dropdown Header:</div>
                      <a class="dropdown-item" href="#">Action</a>
                      <a class="dropdown-item" href="#">Another action</a>
                      <div class="dropdown-divider"></div>
                      <a class="dropdown-item" href="#">Something else here</a>
                    </div>
                  </div>
-->                  
                  
                </div>
                <!-- Card Body -->
                <div class="card-body">
                  <div class="chart-pie">
                    <canvas id="myPieChart"></canvas>
                  </div>
<!--                  <div class="mt-4 text-center small">
                    <span class="mr-2">
                    </span>
                    <span class="mr-2">
                    </span>
                    <span class="mr-2">
                    </span>
                    
                  </div>
-->                  
<%--                  <div class="mt-4 text-center small">
                  <c:forEach items="${labelsDispositivos}" var="lbl" varStatus="it">
                    <span class="mr-2">
                        <i class="fas fa-circle text-" style="color: "></i>${lbl}
                    </span>
                  </c:forEach>
                  </div>

                  <div class="mt-4 text-center small">
                    <span class="mr-2">
                      <i class="fas fa-circle text-primary"></i> Direct
                    </span>
                    <span class="mr-2">
                      <i class="fas fa-circle text-success"></i> Social
                    </span>
                    <span class="mr-2">
                      <i class="fas fa-circle text-info"></i> Referral
                    </span>
                  </div>
                  --%>
                </div>
              </div>
            </div>
          </div>

                
                
                
                
                
          <!-- Content Row -->
          <div class="row">


            <div class="col-xl-6 col-lg-6 col-md-6">
              <!-- Illustrations -->
              <div class="card shadow mb-4">
                <div class="card-header p-0 d-flex flex-row align-items-center justify-content-between">
                  <h6 class="ml-3 mt-2 font-weight-bold text-primary">Tweets con multimedia</h6>

                  <ul class="nav nav-tabs mr-3 mt-2 border-bottom-0">
                      <li class="nav-item"><a id="file-imagen" class="nav-link active px-2" onclick="selectFile(1);return false;" href="#">Imagen</a></li>
                      <li class="nav-item"><a id="file-gif" class="nav-link px-3" onclick="selectFile(2);return false;" href="#">Gif</a></li>
                      <li class="nav-item"><a id="file-video" class="nav-link px-2" onclick="selectFile(3);return false;" href="#">Video</a></li>
                  </ul>
                  
<!--                  
                  <div class="dropdown no-arrow">
                    <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                      <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in" aria-labelledby="dropdownMenuLink">
                      <div class="dropdown-header">Tipo de multimedia:</div>
                      <a id="btnImagen" class="dropdown-item" onclick="selectFile(1);return false;" href="#">Imagen</a>
                      <a id="btnGif" class="dropdown-item" onclick="selectFile(2);return false;" href="#">Gif</a>
                      <a id="btnVideo" class="dropdown-item" onclick="selectFile(3);return false;" href="#">Video</a>
                    </div>
                  </div>
-->
                  
                </div>
                <div class="card-body">
                  <div class="text-center">
                    <img id="fileMultimedia" class="img-fluid px-3 px-sm-4 mt-3 mb-4" style="width: 25rem;" src="${multimediaBean["photo"].urlArchivo}" alt="">
                  </div>
                    <c:if test="${multimediaBean['photo'] != null}">
                      <p id="txtMultimedia">Esta es tu imagen más comentada en Twitter. Retweets: ${multimediaBean["photo"].numRetweets} Me gusta: ${multimediaBean["photo"].numFavoritos}</p>
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
                  
              
 
                  
          </div>

                
                




        </div>
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->
      <!-- Footer -->
      <footer class="sticky-footer bg-white">
        <div class="container my-auto">
          <div class="copyright text-center my-auto">
            <span>Copyright &copy; Your Website 2019</span>
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
    function selectTime(tipo){
        if (tipo===1){
            datos = usoTwtBean['horas'];
            etiquetas = usoTwtBean['labelsHoras'];
            myLineChart.options.scales.xAxes[0].scaleLabel.display = true;
            myLineChart.options.scales.xAxes[0].scaleLabel.labelString = "Horas";
            $('#time-horas').tab('show');
        }
        if (tipo===2){
            datos = usoTwtBean['dias'];
            etiquetas = usoTwtBean['labelsDias'];
            myLineChart.options.scales.xAxes[0].scaleLabel.display = false;
            $('#time-dias').tab('show');
        }
        if (tipo===3){
            datos = usoTwtBean['meses'];
            etiquetas = usoTwtBean['labelsMeses'];
            myLineChart.options.scales.xAxes[0].scaleLabel.display = false;
            $('#time-meses').tab('show');
        }
        if (tipo===4){
            datos = usoTwtBean['annios'];
            etiquetas = usoTwtBean['labelsAnnios'];
            myLineChart.options.scales.xAxes[0].scaleLabel.display = true;
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
    function selectFile(tipo){
        if (tipo===1){
            urlFileMultimedia = "${multimediaBean["photo"].urlArchivo}";
            urlTweetMultimedia = "${multimediaBean["photo"].urlTweet}";
            numMultimTw = "${multimediaBean["photo"].numRetweets}";
            numMultimFv = "${multimediaBean["photo"].numFavoritos}";
            $('#file-imagen').tab('show');
        }
        if (tipo===2){
            urlFileMultimedia = "${multimediaBean["animated_gif"].urlArchivo}";
            urlTweetMultimedia = "${multimediaBean["animated_gif"].urlTweet}";
            numMultimTw = "${multimediaBean["animated_gif"].numRetweets}";
            numMultimFv = "${multimediaBean["animated_gif"].numFavoritos}";
            $('#file-gif').tab('show');
        }
        if (tipo===3){
            urlFileMultimedia = "${multimediaBean["video"].urlArchivo}";
            urlTweetMultimedia = "${multimediaBean["video"].urlTweet}";
            numMultimTw = "${multimediaBean["video"].numRetweets}";
            numMultimFv = "${multimediaBean["video"].numFavoritos}";
            $('#file-video').tab('show');
        }
    }

/////////////// TIPO DE FICHERO MULTIMEDIA ///////////////  
function modalSpinner(){
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

  <!-- Page level plugins -->
  <script src="vendor/chart.js/Chart.min.js"></script>

  <!-- Page level custom scripts -->
  <script src="js/demo/chart-area-demo.js"></script>
  <script src="js/demo/chart-pie-demo.js"></script>

  <!-- Page level plugins -->
  <script src="vendor/datatables/jquery.dataTables.min.js"></script>
  <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

  <!-- Page level custom scripts -->
  <script src="js/demo/datatables-demo.js"></script>


  <!-- Controles para el mapa de Tweets -->
  <script src="js/control-mapa.js"></script>
</body>

</html>
