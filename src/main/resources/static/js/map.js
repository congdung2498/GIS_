
var format = 'image/png';
var map;
var minX = 102.144584655762;
var minY = 8.38135528564453;
var maxX = 109.469177246094;
var maxY = 23.3926944732666;
var cenX = (minX + maxX) / 2;
var cenY = (minY + maxY) / 2;
var mapLat = cenY;
var mapLng = cenX;
var mapDefaultZoom = 6;

function initialize_map() {
    //*
    layerBG = new ol.layer.Tile({
        source: new ol.source.OSM({})
    });
    //*/
    var adm1Style = {
        'MultiPolygon': new ol.style.Style({
            fill: new ol.style.Fill({
                color: 'orange'
            }),
            stroke: new ol.style.Stroke({
                color: 'yellow',
                width: 2
            }),
            text: new ol.style.Text({
                font: '12px Calibri,sans-serif',
//                fill: new ol.style.Fill({color: '#000'}),
//                stroke: new ol.style.Stroke({
//                    color: '#fff', width: 2
//                }),
                // get the text from the feature - `this` is ol.Feature
                // and show only under certain resolution
                text: 'acs'
            })
        })
    };
    var adm1styleFunction = function (feature) {
        return adm1Style[feature.getGeometry().getType()];
    };
    var layer_adm1 = new ol.layer.Image({
        source: new ol.source.ImageWMS({
            ratio: 1,
            url: 'http://localhost:8080/geoserver/wspaceTest/wms?',
            params: {
                'FORMAT': format,
                'VERSION': '1.1.1',
                STYLES: '',
                LAYERS: 'xa',
            }
        })
    });
    var layer_adm2 = new ol.layer.Image({
        source: new ol.source.ImageWMS({
            ratio: 2,
            url: 'http://localhost:8080/geoserver/wspaceTest/wms?',
            params: {
                'FORMAT': format,
                'VERSION': '1.1.1',
                STYLES: '',
                LAYERS: 'xa',
            }
        })
    });
    var viewMap = new ol.View({
        center: ol.proj.fromLonLat([mapLng, mapLat]),
        zoom: mapDefaultZoom
                //projection: projection
    });
    map = new ol.Map({
        target: "map",
        layers: [layerBG, layer_adm1],
        //layers: [layerCMR_adm1],
        view: viewMap
    });
    //map.getView().fit(bounds, map.getSize());

    var styles = {
        'MultiPolygon': new ol.style.Style({
            fill: new ol.style.Fill({
                color: 'orange'
            }),
            stroke: new ol.style.Stroke({
                color: 'yellow',
                width: 2
            }),
            text: new ol.style.Text({
                font: '12px Calibri,sans-serif',
//                fill: new ol.style.Fill({color: '#000'}),
//                stroke: new ol.style.Stroke({
//                    color: '#fff', width: 2
//                }),
                // get the text from the feature - `this` is ol.Feature
                // and show only under certain resolution
                text: 'acs'
            })
        })
    };
    var styleFunction = function (feature) {
        return styles[feature.getGeometry().getType()];
    };

    var vectorLayer = new ol.layer.Vector({
        //source: vectorSource,
        style: styleFunction
    });
    map.addLayer(vectorLayer);

    map.getView().on('change:resolution', function (e) {
        if (map.getView().getZoom() > 7) {
            map.getLayers().clear();
            map.addLayer(layerBG);
            map.addLayer(layer_adm2);
        } else {
            map.getLayers().clear();
            map.addLayer(layerBG);
            map.addLayer(layer_adm1);

        }
        map.addLayer(vectorLayer);

    });

    function createJsonObj(result) {
        var geojsonObject = '{'
                + '"type": "FeatureCollection",'
                + '"crs": {'
                + '"type": "name",'
                + '"properties": {'
                + '"name": "EPSG:4326"'
                + '}'
                + '},'
                + '"features": [{'
                + '"type": "Feature",'
                + '"geometry": ' + result.geometry
                + '}]'
                + '}';
        return geojsonObject;
    }
    function highLightGeoJsonObj(paObjJson) {
        var vectorSource = new ol.source.Vector({
            features: (new ol.format.GeoJSON()).readFeatures(paObjJson, {
                dataProjection: 'EPSG:4326',
                featureProjection: 'EPSG:3857'
            })
        });
        vectorLayer.setSource(vectorSource);
        /*
         var vectorLayer = new ol.layer.Vector({
         source: vectorSource
         });
         map.addLayer(vectorLayer);
         */
    }
    function highLightObj(result) {
        //alert("result: " + result);
        var strObjJson = createJsonObj(result);
        //alert(strObjJson);
        var objJson = JSON.parse(strObjJson);
        //alert(JSON.stringify(objJson));
        //drawGeoJsonObj(objJson);
        highLightGeoJsonObj(objJson);
    }
    map.on('singleclick', function (evt) {
        //alert("coordinate org: " + evt.coordinate);
        //var myPoint = 'POINT(12,5)';
        var lonlat = ol.proj.transform(evt.coordinate, 'EPSG:3857', 'EPSG:4326');
        var lon = lonlat[0];
        var lat = lonlat[1];
        var myPoint = 'POINT(' + lon + ' ' + lat + ')';
        $.ajax({
            type: "GET",
            url: "http://localhost:8089/getXa",
            dataType: 'json',
            //data: {functionname: 'reponseGeoToAjax', paPoint: myPoint},
            data: {point: myPoint},
            success: function (result, status, erro) {
                $("#info").html("Xã: "+ result.name +  "("+ "Diện tích:"+ result.acreage + " km2" + "--" + "Dân số:" + result.populartion + " người"+ ")");
                highLightObj(result);
//                displayObjInfo(result, evt.coordinate);
            },
            error: function (req, status, error) {
                alert(req + " " + status + " " + error);
            }
        });
        //*/
    });

}
