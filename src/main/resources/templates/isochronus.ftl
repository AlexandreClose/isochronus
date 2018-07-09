<!DOCTYPE html>
<html>
    <head>
        <title>Isochronus</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
         <link rel="stylesheet" href="https://unpkg.com/leaflet@1.3.1/dist/leaflet.css"
            integrity="sha512-Rksm5RenBEKSKFjgI3a41vrjkw4EVPlJ3+OiI65vTjIdo9brlAacEuKOiQ5OFh7cOI1bkDwLqdLw3Zg0cRJAAQ=="
            crossorigin=""/>
         <link rel="stylesheet" href="/isochronus.css" />
         <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous" />
    </head>
    <body>
        <div id="viewport" class="row justify-content-md-center justify-content-sm-center">
            <div id="content" class="col-sm-10">
                <div class="form-group">
                    <label for="address_search">Adresse</label>
                    <input class="form-control" type="text" id="address_search" name="address_search" />
                </div>
                <div class="form-row">
                    <div class="col-sm-6">
                        <label for="min_duration">Durée min. du trajet</label>
                        <input class="form-control" type="text" id="min_duration" name="min_duration" value="1000" />
                    </div>
                    <div class="col-sm-6">
                        <label for="max_duration">Durée min. du trajet</label>
                        <input class="form-control" type="text" id="max_duration" name="max_duration" value="2000"/>
                    </div>
                </div>
                <br>
                <div class="row" id="map"></div>
            </div>
        </div>
        
        
    </body>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="https://unpkg.com/leaflet@1.3.1/dist/leaflet.js"
        integrity="sha512-/Nsx9X4HebavoBvEBuyp3I7od5tA0UzAxs+j83KgC8PU0kgB4XiK4Lfe4y4cgBtaRJQEIFCW+oC506aPT2L1zw=="
        crossorigin=""></script>
    <script src="/colors.js"></script>
    <script src="/isochronus.js"></script>
</html>
