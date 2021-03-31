package com.mycovtest.controller;

import com.mycovtest.model.TestResult;
import com.mycovtest.service.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "test-results", produces = "application/json")
public class TestResultController {

    @Autowired
    private TestResultService testResultService;

    @GetMapping
    public ResponseEntity<List<TestResult>> getAllTestResults() {
        return new ResponseEntity<>(testResultService.getAllTests(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TestResult> addNewTestResult(@RequestBody TestResult testResult) {
        return new ResponseEntity<>(testResultService.addNewTestResult(testResult), HttpStatus.ACCEPTED);
    }
}
