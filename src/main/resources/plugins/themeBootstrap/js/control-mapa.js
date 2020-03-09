        var map = L.map('map',{center: [40.418889, -3.691944], zoom: 6});          
        var osm = L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'});        

        var cap_prov = L.geoJson(capitales,{onEachFeature:function(feature, layer){ layer.bindPopup ("<h2>"+feature.properties.Provincia+" </h2> <h4>"+feature.properties.capital+"</h4> <figure> <img src =Datos/"+feature.properties.foto+"> </figure> <h5> <a href="+feature.properties.link+" target=_blank>Más información <a></h5>"); } });
		
        function ventana(feature,layer){layer.bindPopup("<h3>Provincia:"+feature.properties.NOMBRE+"</h3>");};
        
        //Función para dar estilo al polígono de cada provincia en función de la propiedad nombre del archivo GeoJSON
        function funcionestilo(feature) {
        switch (feature.properties.NOMBRE) {
            case 'Baetica': return {fillColor: "#1b9e77","color":"black","opacity": 0.7, "weight": 3, "dashArray": '7', "fillOpacity": 0.5};
            case 'Tarraconensis': return {fillColor: "#d95f02", "color":"black", "opacity": 0.7, "weight": 3, "dashArray": '7', "fillOpacity": 0.5};
            case 'Lusitania': return {fillColor: "#7570b3","color":"black","opacity": 0.7, "weight": 3, "dashArray": '7', "fillOpacity": 0.5};
			case 'tweetensis': return {fillColor: "#FFFFFF","color":"black","opacity": 0.7, "weight": 3, "dashArray": '7', "fillOpacity": 0.5};
            }
        };

        var marcadores = L.markerClusterGroup();
		var marcadores2 = L.markerClusterGroup();
        var cap_tweets = marcadores2.addLayer(L.geoJSON(tweets,{onEachFeature:function(feature, layer){ layer.bindPopup ("<h2>"+feature.properties.Nombre+" </h2> <h4>"+feature.properties.Tipo+"</h4> <h4>"+feature.properties.Pais+"</h4>"); } }));
        var capa_yacimientos = marcadores.addLayer(L.geoJSON(datos, {onEachFeature: function (feature, layer) {
            layer.bindPopup("<h3> Nombre del yacimiento:" +feature.properties.Nombre+" </h3><li> Tipo: "+feature.properties.Tipo+"</li><li> Tipo de Provincia: "+feature.properties.Tip_Prov+"</li><li>Provincia :"+feature.properties.Provincia+"</li>"); } }));
        
        //Creamos nuevas capas ESRI para ver el resultado de la organización de capas mediante styledLayerControl
        var ESRI_NG = L.esri.basemapLayer('NationalGeographic');
        var ESRI_T = L.esri.basemapLayer('Topographic');
        var ESRI_DG = L.esri.basemapLayer('DarkGray');
        
        var ESRI = L.esri.basemapLayer('Imagery').addTo(map);
        var rom_prov = L.geoJSON(provincias, {onEachFeature:ventana, style:funcionestilo}).addTo(map);
		var area_tweet = L.geoJSON(areaTweet, {onEachFeature:ventana, style:funcionestilo}).addTo(map);
                
        //Creamos los grupos de capas para el nuevo control de capas mediante styledLayerControl
        var overlays = [ {
            groupName : "Yacimientos Romanos",
            layers : { "Capitales de provincias": cap_prov, "Yacimientos" : capa_yacimientos, "tweets": cap_tweets
        }},
		{
            groupName : "Areas tweets",
            layers : { "tweets": area_tweet
        }}];
        
        var baseMaps = [ {
            groupName : "Mapa Base OSM",
            layers : {"Open Stret Map" : osm
        }},
        {
            groupName : "Mapa Base ESRI",
            layers : { "NationalGeographic" : ESRI_NG, "Topographic" : ESRI_T, "Imagery" : ESRI, "DarkGary": ESRI_DG
        }
        },];
        
        //Variable para dar formato al control de capas
        var options = {
            container_width : "250px",
            group_maxHeight : "80px",
            exclusive : false,
            collapsed : true,
            position: 'topright'
        };
        //Añadimos el nuevo control de capas mediante el siguiente código:
        var capas= L.Control.styledLayerControl(baseMaps, overlays, options);
        map.addControl(capas);        

        //Creamos y añadimos el objeto buscar_marcador. Será un buscador de capa_yacimientos
        var buscar_marcador = new L.Control.Search({
            position: 'topleft',
            initial: false,
            textPlaceholder: 'Buscar yacimiento',
            layer: capa_yacimientos,
            propertyName: 'Nombre',
            circleLocation:true,
            collapsed: true
        });
        map.addControl(buscar_marcador);        
        
        //Escala
        L.control.scale({position:'bottomleft',metric: true,imperial: false, maxWidth: 100}).addTo(map);        