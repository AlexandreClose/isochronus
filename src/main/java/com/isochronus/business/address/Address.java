/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isochronus.business.address;

import lombok.Data;

@Data
public class Address 
{
    private String id;
    private String label;
    private String postcode;
    private String citycode;
    private double score;
    private String name;
    private String city;
    private Geometry geometry;
}
