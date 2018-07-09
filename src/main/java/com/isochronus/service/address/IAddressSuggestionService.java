/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isochronus.service.address;

import com.isochronus.business.address.Address;
import java.util.List;


public interface IAddressSuggestionService 
{
    List<Address> getAddressListFromQuery( String strQuery );
}
