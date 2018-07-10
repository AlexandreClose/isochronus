$(function () {
    map = L.map('map').setView([48.859903, 2.342780], 12.51);
    var markersLayer = new L.LayerGroup();

        L.tileLayer('https://{s}.tile.openstreetmap.de/tiles/osmde/{z}/{x}/{y}.png', {
                maxZoom: 18,
                attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
        }).addTo(map);

//    L.tileLayer('http://{s}.tile.cloudmade.com/e7b61e61295a44a5b319ca0bd3150890/997/256/{z}/{x}/{y}.png', 
//        {
//            attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery Â© <a href="http://cloudmade.com">CloudMade</a>',
//            maxZoom: 18
//        }).addTo(map);

    $('#color_picker_begin,#color_picker_end').colorpicker();
    
    $('#address_search').autocomplete(
{
    autofocus : true,
    minLength : 2,  
    source: function(request, response) 
    {
        $.ajax(
        {
            url: 'address/autocomplete',
            dataType: "json",
            method: 'post',
            data: 
            {
                "q" : $("#address_search").val(),
            },
            success: function(data) {
                response(data);
            }
        });
    },
    select: function( event, ui ) 
    {
        $.ajax(
        {
            url: 'address/picked',
            dataType: "json",
            contentType: 'application/json',
            method: 'post',
            data: JSON.stringify(ui.item),
            success: function( data ) 
            {
                markersLayer.clearLayers();
                var x = data.geometry.point.x;
                var y = data.geometry.point.y;
                var marker = L.marker([y,x]);
                markersLayer.addLayer(marker); 
                
                $.ajax(
                {
                    url: 'isochronus/compute?min_duration='+$("#min_duration").val()+'&max_duration='+$("#max_duration").val()+'&nb_isochrones='+$("#nb_isochrones").val(),
                    dataType: "json",
                    contentType: 'application/json',
                    method: 'post',
                    data: JSON.stringify(ui.item),
                    success: function(data)
                    {
                        var colorBegin = $('#begin_color').val();
                        var colorEnd = $('#end_color').val();
                        var colors = styleSteps( colorBegin, colorEnd, data.length );
                        //Returns a list of isochrons as geojson; readable for leaflet
                        data.forEach( function( value, index)
                        {
                            var isochroneStyle = {
                                "color": colors[index],
                                "weight": data.length-index,
                                "opacity": 0.15,
                                "fillOpacity":0.5
                                
                            };
                            var isochroneJson = JSON.parse( value );
                            
                            markersLayer.addLayer(L.geoJSON(isochroneJson.isochrones[0].geojson,{style: isochroneStyle}));
                        });
                        markersLayer.addTo(map);
                    }
                });
            }
        });
    }
});
});


