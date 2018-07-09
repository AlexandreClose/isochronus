/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isochronus.business.address;

import lombok.Data;

@Data
public class Geometry {
    private String type;
    private Point2D point;
}
