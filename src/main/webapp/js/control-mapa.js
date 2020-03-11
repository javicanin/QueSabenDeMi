

        var map = L.map('map',{center: [40.418889, -3.691944], zoom: 4, fullscreenControl: true, fullscreenControlOptions: {position: 'topleft'}});          
        var osm = L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'}).addTo(map);        

        
		
/*        function ventana(feature,layer){layer.bindPopup("<h3>Provincia:"+feature.properties.NOMBRE+"</h3>");};
        
        //Funci�n para dar estilo al pol�gono de cada provincia en funci�n de la propiedad nombre del archivo GeoJSON
        function funcionestilo(feature) {
        switch (feature.properties.NOMBRE) {
            case 'Baetica': return {fillColor: "#1b9e77","color":"black","opacity": 0.7, "weight": 3, "dashArray": '7', "fillOpacity": 0.5};
            case 'Tarraconensis': return {fillColor: "#d95f02", "color":"black", "opacity": 0.7, "weight": 3, "dashArray": '7', "fillOpacity": 0.5};
            case 'Lusitania': return {fillColor: "#7570b3","color":"black","opacity": 0.7, "weight": 3, "dashArray": '7', "fillOpacity": 0.5};
			case 'tweetensis': return {fillColor: "#FFFFFF","color":"black","opacity": 0.7, "weight": 3, "dashArray": '7', "fillOpacity": 0.5};
            }
        }; */

        var marcadores = L.markerClusterGroup();
	var marcadores2 = L.markerClusterGroup();
        /*var cap_prov = L.geoJson(capitales,{onEachFeature:function(feature, layer){ layer.bindPopup ("<h2>"+feature.properties.Provincia+" </h2> <h4>"+feature.properties.capital+"</h4> <figure> <img src =Datos/"+feature.properties.foto+"> </figure> <h5> <a href="+feature.properties.link+" target=_blank>M�s informaci�n <a></h5>"); } });
        var capa_yacimientos = marcadores.addLayer(L.geoJSON(datos, {onEachFeature: function (feature, layer) {
            layer.bindPopup("<h3> Nombre del yacimiento:" +feature.properties.Nombre+" </h3><li> Tipo: "+feature.properties.Tipo+"</li><li> Tipo de Provincia: "+feature.properties.Tip_Prov+"</li><li>Provincia :"+feature.properties.Provincia+"</li>"); } }));*/
        var capa_tweets = marcadores2.addLayer(L.geoJSON(tweets,{onEachFeature:function(feature, layer){ layer.bindPopup ("<center><h6 class='m-1 font-weight-light text-primary'>"+feature.properties.nombre +" </h6> <h6 class='m-1 small font-weight-normal'>"+feature.properties.pais+"</h6> <h6 class='m-1 font-weight-light text-gray-600 small'>"+feature.properties.fechaTweet+"</h6n></center>"); } }));
        //Creamos nuevas capas ESRI para ver el resultado de la organizaci�n de capas mediante styledLayerControl
        var ESRI_NG = L.esri.basemapLayer('NationalGeographic');
        var ESRI_T = L.esri.basemapLayer('Topographic');
        //var ESRI_DG = L.esri.basemapLayer('DarkGray');
        var ESRI = L.esri.basemapLayer('Imagery');
/*        var rom_prov = L.geoJSON(provincias, {onEachFeature:ventana, style:funcionestilo}).addTo(map);
		var area_tweet = L.geoJSON(areaTweet, {onEachFeature:ventana, style:funcionestilo}).addTo(map);*/
        //Creamos los grupos de capas para el nuevo control de capas mediante styledLayerControl
        var overlays = [ {
            groupName : "Marcadores",
            layers : {"tweets": capa_tweets
        }}];

        map.addLayer(capa_tweets);
        var baseMaps = [ {
            groupName : "Tipo de Mapa",
            layers : {"Open Stret Map" : osm, "NationalGeographic" : ESRI_NG, "Topographic" : ESRI_T, "Imagery" : ESRI
        }}];
        
        //Variable para dar formato al control de capas
        var options = {
            container_width : "175px",
            group_maxHeight : "80px",
            exclusive : false,
            collapsed : false,
            position: 'topright',
        };
        //Añadimos el nuevo control de capas mediante el siguiente código:
        var capas= L.Control.styledLayerControl(baseMaps, overlays, options);
        map.addControl(capas);        

        //Creamos y añadimos el objeto buscar_marcador. 
        /*var buscar_marcador = new L.Control.Search({
            position: 'topleft',
            initial: false,
            textPlaceholder: 'Buscar yacimiento',
            layer: capa_yacimientos,
            propertyName: 'Nombre',
            circleLocation:true,
            collapsed: true
        });
        map.addControl(buscar_marcador);*/
        
        //Escala
        L.control.scale({position:'bottomleft',metric: true,imperial: false, maxWidth: 100}).addTo(map);
        // events are fired when entering or exiting fullscreen.
        map.on('enterFullscreen', function(){
            console.log('entered fullscreen');
        });

        map.on('exitFullscreen', function(){
            console.log('exited fullscreen');
        });        
        