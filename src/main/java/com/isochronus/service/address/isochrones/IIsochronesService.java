/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isochronus.service.address.isochrones;

import com.isochronus.business.address.Address;

public interface IIsochronesService
{
    public String getIsochrones( Address address, int nMinSec, int nMaxSec ); 
}
