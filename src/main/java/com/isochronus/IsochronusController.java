package com.isochronus;

import com.isochronus.business.address.Address;
import com.isochronus.service.address.isochrones.IIsochronesService;
import com.isochronus.util.IsochronusProperties;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IsochronusController {

    @Autowired
    private IIsochronesService isochronesService;
    
    @Autowired
    private IsochronusProperties isochronusProperties;
    
    @RequestMapping("/isochronus")
    public String isochronus( Model model) 
    {
        return "isochronus";
    }
    
    @RequestMapping(value = "/isochronus/compute", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity computeIsochrones (
            @RequestBody Address address, 
            @RequestParam( name = "min_duration") int nMinDuration, 
            @RequestParam( name = "max_duration") int nMaxDuration  ) 
    {
        List<String> listIsochrones = new ArrayList<>();
        if ( !address.getLabel().isEmpty() )
        {
            int nbIsochrones = Integer.parseInt( isochronusProperties.getNumberIsochrones( ) );
            int nIterateMinDuration = nMinDuration;
            int nIterateMaxDuration = nMinDuration;
            int nAugmMaxDuration = ( nMaxDuration - nMinDuration )/nbIsochrones;
            for ( int i = 1 ; i <= nbIsochrones ; i++ )
            {
                nIterateMinDuration = nIterateMaxDuration;
                nIterateMaxDuration += nAugmMaxDuration;
                String strIsochrones = isochronesService.getIsochrones(address, nIterateMinDuration, nIterateMaxDuration);
                listIsochrones.add( strIsochrones );
            }
            
            return new ResponseEntity( listIsochrones, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity( HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }

}