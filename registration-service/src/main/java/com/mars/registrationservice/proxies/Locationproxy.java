package com.mars.registrationservice.proxies;

import com.mars.registrationservice.beans.LocationdetailsBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@FeignClient(name = "location-service",url = "localhost:4001")
public interface Locationproxy {
    @GetMapping(value = "/location")
    LocationdetailsBean getLocation();
}
