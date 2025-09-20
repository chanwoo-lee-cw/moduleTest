package com.example.api.controller.city;


import com.example.common.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/city")
public class CityController {

    @GetMapping("/{id}")
    public ApiResponse<Void> getCityById(@PathVariable Long id) {
        return "city id " + id;
    }
}
