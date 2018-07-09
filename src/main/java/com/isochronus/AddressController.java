/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isochronus;

import com.isochronus.business.address.Address;
import com.isochronus.service.address.IAddressSuggestionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AddressController
{
    @Autowired
    private IAddressSuggestionService _addressSuggestionService;
    
/**
   *  Rest Controller for getting address suggestions
     * @param strSearch
     * @return 
   */
  @RequestMapping(value = "/address/autocomplete", method = RequestMethod.POST, produces = "application/json")
  @ResponseBody
  public List<Address> autocompleteSuggestions(@RequestParam("q") String strSearch ) 
    {
        return _addressSuggestionService.getAddressListFromQuery( strSearch );
    }
  
  @RequestMapping(value = "/address/picked", method = RequestMethod.POST, produces = "application/json")
  @ResponseBody
  public ResponseEntity addressPicked (@RequestBody Address address ) 
  {
      if ( !address.getLabel().isEmpty() )
      {
          return new ResponseEntity( address, HttpStatus.OK);
      }
      else
      {
          return new ResponseEntity( HttpStatus.INTERNAL_SERVER_ERROR );
      }
  }

}
