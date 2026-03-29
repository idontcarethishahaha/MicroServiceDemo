package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController{

    @GetMapping("select/{id}")
    public Map select(@PathVariable("id") Integer id) {
         return Map.of(id,120,"title","华为Mate60 Pro");
    }
}