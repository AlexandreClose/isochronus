$(function () {
    map = L.map('map').setView([48.859903, 2.342780], 12.51);
    
    var marker = null;
    var isochronesLayer = new L.LayerGroup();

    L.tileLayer('https://{s}.tile.openstreetmap.de/tiles/osmde/{z}/{x}/{y}.png', {
            maxZoom: 18,
            attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    }).addTo(map);

    $('#color_picker_begin,#color_picker_end').colorpicker();
    
    var pickedAddress=null;
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
                },
            });
        },
        select : function( event, ui)
        {
            pickedAddress = ui.item;
        }
     });
     
     $('#compute_isochrones').click( function(e)
     {
         if ( pickedAddress !== null)
         {
            isochronesLayer.clearLayers();
            if ( marker !== null){ map.removeLayer(marker) }; 
            $('#loading_bar_isochrones').width( '0%' ).text( '0%');
            $.ajax(
            {
                url: 'address/picked',
                dataType: "json",
                contentType: 'application/json',
                method: 'post',
                data: JSON.stringify( pickedAddress ),
                success: function( data ) 
                {
                    
                    var x = data.geometry.point.x;
                    var y = data.geometry.point.y;

                    marker = L.marker([y,x]).addTo(map)
                        .bindPopup("Chargement en cours...")
                        .openPopup();

                    var nMinDuration=$("#min_duration").val()*60;
                    var nMaxDuration=$("#max_duration").val()*60;
                    var nIterateMinDuration = nMinDuration;
                    var nIterateMaxDuration = nIterateMinDuration;
                    var nIsochrones = $("#nb_isochrones").val();
                    var nAugmMaxDuration = ( nMaxDuration - nMinDuration )/nIsochrones;
                    var nCurrentProgressBarWidth = 0;
                    var nProgressBarWidthAugm = 100/nIsochrones;
                    var colorBegin = $('#begin_color').val();
                    var colorEnd = $('#end_color').val();
                    var colors = styleSteps( colorBegin, colorEnd, nIsochrones );
                    
                    
                    for ( var nbIter = 1 ; nbIter <= nIsochrones ; nbIter++ )
                    {
                        nIterateMinDuration = nIterateMaxDuration;
                        nIterateMaxDuration =  nIterateMaxDuration + nAugmMaxDuration;
      
                        (function(i) 
                        { 
                            $.ajax(
                            {
                                url: 'isochronus/compute?min_duration='+nIterateMinDuration+'&max_duration='+nIterateMaxDuration,
                                dataType: "json",
                                contentType: 'application/json',
                                method: 'post',
                                data: JSON.stringify( pickedAddress ),
                                success: function(data)
                                {
                                    nCurrentProgressBarWidth = nCurrentProgressBarWidth+nProgressBarWidthAugm;
                                    $('#loading_bar_isochrones').width( nCurrentProgressBarWidth + '%' ).text( nCurrentProgressBarWidth + '%');
                                    var isochroneStyle = {
                                        "color": colors[i-1],
                                        "weight": nIsochrones-i+1,
                                        "opacity": 0.15,
                                        "fillOpacity":0.5
                                    };
                                    isochronesLayer.addLayer(L.geoJSON(data.isochrones[0].geojson,{style: isochroneStyle}));

                                }
                            });
                        })(nbIter);
                    }
                    $('#loading_bar_isochrones').width('100%').text( 'Complété !');
                    isochronesLayer.addTo(map);
                    marker.closePopup();
                }
            });
         }
        
    });
});


