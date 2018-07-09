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
@PropertySource("classpath:ban.properties")
@ConfigurationProperties(prefix = "ban")
public class BanProperties
{
    private String urlSearch;
    private String urlSearchLimitParam;
    private String searchResultLimit;
    private String urlSearchQueryParam;
    private String urlSearchCityCodeParam;
}
