<!DOCTYPE html>
<html>
    <head>
        <title>Isochronus</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
         <link rel="stylesheet" href="/leaflet/leaflet.css"/>
         <link rel="stylesheet" href="/jquery-ui-1.12.1/jquery-ui.min.css" />
         <link rel="stylesheet" href="/bootstrap-3.3.7/css/bootstrap.min.css"/>
         <link rel="stylesheet" href="/colorpicker/bootstrap-colorpicker.css" >
         <link rel="stylesheet" href="/css/isochronus.css" />
    </head>
    <body>
        <div id="viewport" class="row justify-content-md-center justify-content-sm-center">
            <div id="content" class="col-sm-10">
                <div class="form-group">
                    <label for="address_search">Adresse</label>
                    <input class="form-control" type="text" id="address_search" name="address_search" />
                </div>
                <div class="form-row">
                    <div class="col-sm-4">
                        <label for="min_duration">Durée min. du trajet</label>
                        <input class="form-control" type="text" id="min_duration" name="min_duration" value="1000" />
                    </div>
                    <div class="col-sm-4">
                        <label for="max_duration">Durée max. du trajet</label>
                        <input class="form-control" type="text" id="max_duration" name="max_duration" value="2000"/>
                    </div>
                    <div class="col-sm-4">
                        <label for="nb_isochrones">Nombre d'isochrones</label>
                        <input class="form-control" type="text" id="nb_isochrones" name="nb_isochrones" value="4"/>
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-sm-6">
                        <!--<label for="begin_color">Couleur départ</label>-->
                        <div id="color_picker_begin" class="input-group colorpicker-component">
                            <input type="text" class="form-control input-lg" id="begin_color" name="begin_color" value="#73ba48"/>
                            <span class="input-group-addon"><i></i></span>
                          </div>
                    </div>
                    <div class="col-sm-6">
                        <!--<label for="end_color">Couleur fin</label>-->
                        <div id="color_picker_end" class="input-group colorpicker-component">
                            <input type="text" class="form-control input-lg" id="end_color" name="end_color" value="#ff0000"/>
                            <span class="input-group-addon"><i></i></span>
                        </div>
                    </div>
                    <div class="form-control" id="map"></div>
                </div>
            </div>
        </div>
        <script src="/jquery-3.3.1.min.js"></script>
        <script src="/jquery-ui-1.12.1/jquery-ui.js"></script>
        <script src="/colorpicker/bootstrap-colorpicker.js"></script>
        <script src="/leaflet/leaflet.js"></script>
        <script src="/js/colors.js"></script>
        <script src="/js/isochronus.js"></script>
    </body>
</html>
