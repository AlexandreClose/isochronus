/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isochronus.service.address;

import com.isochronus.business.address.Address;
import com.isochronus.business.address.Geometry;
import com.isochronus.business.address.Point2D;
import com.isochronus.util.BanProperties;
import com.isochronus.util.IsochronusProperties;
import com.vividsolutions.jts.geom.Coordinate;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.geotools.feature.FeatureCollection;
import org.geotools.geojson.feature.FeatureJSON;
import org.opengis.feature.Feature;
import org.opengis.feature.GeometryAttribute;
import org.opengis.feature.Property;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.vividsolutions.jts.geom.Point;

@Service
/**
 * BAN Address Suggestion service
 */
public class BanAddressSuggestionService implements IAddressSuggestionService
{
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private BanProperties banProperties;
    @Autowired
    private IsochronusProperties isochronusProperties;

    @Override
    public List<Address> getAddressListFromQuery(String strQuery)
    {
        strQuery = strQuery.replace(' ', '+');
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl( banProperties.getUrlSearch() )
        .queryParam( banProperties.getUrlSearchLimitParam(), isochronusProperties.getSearchResultLimit() )
        .queryParam( banProperties.getUrlSearchQueryParam(), strQuery)
        .queryParam( banProperties.getUrlSearchCityCodeParam(), isochronusProperties.getFilteredByInseeCityCode());
        
        RestTemplate restTemplate = new RestTemplate();
        String featureJson = restTemplate.getForObject( builder.toUriString() , String.class);
        FeatureJSON io = new FeatureJSON();
    
        try
        {
            List<Address> listSugg = new ArrayList<>();
            FeatureCollection features = io.readFeatureCollection( featureJson );
            for ( Object obj : features.toArray( ) )
            {
                Feature feature = (Feature)obj;
                Address address =  new Address();
                for ( Property prop : feature.getProperties( ) )
                {
                    switch ( prop.getName().toString() )
                    {
                        case "label":
                            address.setLabel( (String)prop.getValue() );
                            break;
                        case "id":
                            address.setId((String)prop.getValue() );
                            break;
                        case "citycode":
                            address.setCitycode((String)prop.getValue() );
                            break;
                        case "postcode":
                            address.setPostcode((String)prop.getValue() );
                            break;
                        case "name":
                            address.setName((String)prop.getValue() );
                            break;   
                        case "score":
                            address.setScore( (double)prop.getValue() );
                            break; 
                            
                    }
                }
                
                //Get geometry
                GeometryAttribute geomAttr = feature.getDefaultGeometryProperty();
                Point point = (Point)geomAttr.getValue();
                Coordinate coord = point.getCoordinate(); 
                
                Geometry geometry = new Geometry();
                geometry.setType( geomAttr.getType().toString( ) );
                
                Point2D point2D = new Point2D();
                point2D.setX( coord.x );
                point2D.setY( coord.y );
                geometry.setPoint( point2D );
                
                address.setGeometry( geometry );
                
                listSugg.add( address );

            }
            return listSugg;
        }
        catch ( IOException e )
        {
            log.error( "Unable to contact BAN WS");
        }
        return null;
    }
}
