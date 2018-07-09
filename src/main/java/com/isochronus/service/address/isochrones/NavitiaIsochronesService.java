/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isochronus.service.address.isochrones;

import com.isochronus.business.address.Address;
import com.isochronus.util.NavitiaProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class NavitiaIsochronesService implements IIsochronesService
{
    @Autowired
    private NavitiaProperties navitiaProperties;
    
    public String getIsochrones( Address address, int nMinSec, int nMaxSec ) 
    {
        double x = address.getGeometry().getPoint().getX();
        double y = address.getGeometry().getPoint().getY();
        
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl( navitiaProperties.getUrlApi( ) )
            .pathSegment(navitiaProperties.getCoveragePath( ) )
            .pathSegment( navitiaProperties.getCoverage())
            .pathSegment( navitiaProperties.getIsochronesApi( ) )
            .queryParam( navitiaProperties.getFromParam(), x+";"+y)
            .queryParam( navitiaProperties.getMinDurationParam(), nMinSec)
            .queryParam( navitiaProperties.getMaxDurationParam(), nMaxSec);
        
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add( "Authorization", navitiaProperties.getSecurityToken( ) );
        
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<String> response = restTemplate.exchange( builder.toUriString(), HttpMethod.GET, request , String.class);
        return response.getBody( );
    }
}
