package com.mycovtest.controller;

import com.mycovtest.model.summary.Summary;
import com.mycovtest.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "admin", produces = "application/json")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("summary")
    public ResponseEntity<Summary> getSummary() {
        return new ResponseEntity<>(adminService.getSummaryReport(), HttpStatus.OK);
    }

}
