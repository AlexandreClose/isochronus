/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isochronus.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource("classpath:navitia.properties")
@ConfigurationProperties(prefix = "navitia")
public class NavitiaProperties
{
    private String urlApi;
    private String coverage;
    private String coveragePath;
    private String isochronesApi;
    private String securityToken;
    private String fromParam;
    private String minDurationParam;
    private String maxDurationParam;
}
