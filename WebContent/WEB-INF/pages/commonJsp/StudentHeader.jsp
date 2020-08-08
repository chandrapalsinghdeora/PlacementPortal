<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="css/bootstrap.css" rel="stylesheet">

    <!-- Custom -->
    <link href="css/custom.css" rel="stylesheet">

    <link rel="stylesheet" href="css/font-awesome.css">

    <!-- CSS STYLE-->
    <link rel="stylesheet" type="text/css" href="css/style.css" media="screen" />
    <link rel="stylesheet" type="text/css" href="css/dataTables.bootstrap.min.css" media="screen" />
</head>
<body>
	 <header>

        <div class="top-menu-bar">
            <div class="col-sm-3 col-md-3 pull-right">

                <ul>
                    
                    <li> <a href="logout"><img src="images/avatar.jpg" class="img-circle" alt=""> ${sessionBean.name} Log OUT |</a></li>
                    

                </ul>

            </div>
            <div class="col-sm-3 col-md-3 pull-right">
                <form class="navbar-form" role="search">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Search" name="q">
                        <div class="input-group-btn">
                            <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                        </div>
                    </div>
                </form>
            </div>

        </div>
        <div class="bottom-nav-menu">
            <!--<a href="#" id="logo"> <img src="images/logo.png"> </a> -->

            <div class="navbar-wrapper">
                <div class="container-fluid">
                    <nav class="navbar navbar-default">
                        <div class="container">
                            <div class="navbar-header">
                                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                                    <span class="sr-only">Toggle navigation</span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                </button>
                                <a class="navbar-brand" href="https://www.iima.ac.in/web/iima" id="logo" target="_blank"><img src="images/logo.png"></a>
                            </div>
                            <div id="navbar" class="navbar-collapse collapse">
                                <ul class="nav navbar-nav">
                                    <li class="active"><a href="#" class="">Inbox</a></li>
                                    <li class=" dropdown">
                                        <a href="#" class="dropdown-toggle " data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Companys <span class="caret"></span></a>
                                    </li>
                                      <li class=" dropdown">
                                        <a href="#" class="dropdown-toggle " data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">OfferStatus <span class="caret"></span></a>
                                    </li>
                                     <li class=" dropdown">
                                        <a href="#" class="dropdown-toggle " data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Resource <span class="caret"></span></a>
                                    </li>
                                    <li class=" dropdown">
                                        <a href="#" class="dropdown-toggle " data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Profile <span class="caret"></span></a>
                                    </li>
                                    <li class=" dropdown">
                                        <a href="#" class="dropdown-toggle " data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Form <span class="caret"></span></a>
                                    </li>
                                    </ul>

                            </div>
                        </div>
                    </nav>
                </div>
            </div>

        </div>

    </header>
     <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>

    <script>
        $(document).ready(function() {
            $('#main-fourum').DataTable({

                "bFilter": false,
                "bInfo": false,
                "bPaginate": true,

            });
            $("#main-fourum_length").hide();

        });
    </script>

    <!-- Latest compiled JavaScript -->
    <script type="text/javascript" src="js/bootstrap.js"></script>
</body>
</html>