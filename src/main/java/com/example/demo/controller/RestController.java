package com.example.demo.controller;

import com.example.demo.NativeSql;
import com.example.demo.model.District;
import com.example.demo.model.Tree;
import com.example.demo.model.Village;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.hibernate.annotations.Parameter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @GetMapping(path = "/getXa")
    public Village loadXa(@RequestParam(value="point")String point) {
        NativeSql na = new NativeSql();
        return na.listVillage(point);
    }
    @GetMapping(path = "/getDistrictOrVillageByid")
    public Object getDistrictOrVillageByid(@RequestParam(value="gid")String gid) {
        NativeSql na = new NativeSql();
        return na.getDistrictOrVillageByid(gid);
    }
    
    @GetMapping(path = "/getTree")
    public List<Tree> getTree() {
        NativeSql na = new NativeSql();
        return na.getTree();
    }
    
    
    @GetMapping(path = "/getXaTouch")
    public List<Village> loadXaTouch(@RequestParam(value="point")String point) {
        NativeSql na = new NativeSql();
        return na.listVillageGeo(point);
    }
}
