package com.example.demo.controller;

import com.example.demo.NativeSql;
import com.example.demo.model.District;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import com.example.demo.repository.MapRepository;
import com.example.demo.repository.TestRepository;
import org.hibernate.annotations.Parameter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @GetMapping(path = "/getDistrict")
    public District loadDistrict(@RequestParam(value="point")String point) {
        NativeSql na = new NativeSql();
        return na.listDistrict(point);
    }
    
}
