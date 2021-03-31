package com.mycovtest.service;

import com.mycovtest.exception.EmailAlreadyUsedException;
import com.mycovtest.exception.InvalidParameterException;
import com.mycovtest.exception.NotIssuedTTNNumberException;
import com.mycovtest.exception.TTNAlreadyUsedException;
import com.mycovtest.model.IssuedTTNNumber;
import com.mycovtest.model.TestResult;
import com.mycovtest.repository.IssuedTTNNumberRepository;
import com.mycovtest.repository.TestResultRepository;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TestResultService {

    @Autowired
    private TestResultRepository testResultRepository;
    @Autowired
    private IssuedTTNNumberRepository ttnNumberRepository;

    public List<TestResult> getAllTests() {
        List<TestResult> testResults = new ArrayList<>();
        testResultRepository.findAll().forEach(testResults::add);
        return testResults;
    }

    public TestResult addNewTestResult(TestResult testResult) {
        checkNameValidation(testResult);
        checkPostCodeValidation(testResult);
        checkIssuedStatusOfTTNNumber(testResult);
        checkForIssuedTTNNumbers(testResult);
        checkUsedEmails(testResult);
        testResultRepository.save(testResult);
        return testResult;
    }

    private void checkPostCodeValidation(TestResult testResult) {
        String regex = "^[A-Z]{1,2}[0-9R][0-9A-Z]? [0-9][ABD-HJLNP-UW-Z]{2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(testResult.getPostCode());
        if (!matcher.matches()) {
            throw new InvalidParameterException("Invalid post code");
        }
    }

    private void checkNameValidation(TestResult testResult) {
        String fullName = testResult.getFullName();
        if (!fullName.matches("[a-zA-Z\\s]+")) {
            throw new InvalidParameterException("Name should only contain letters");
        }
    }

    private void checkUsedEmails(TestResult testResult) {
        if (!EmailValidator.getInstance().isValid(testResult.getEmail())) {
            throw new InvalidParameterException("Invalid email address");
        }
        List<TestResult> useEmails = testResultRepository.findByEmail(testResult.getEmail());
        if (!useEmails.isEmpty()) {
            throw new EmailAlreadyUsedException();
        }
    }

    private void checkForIssuedTTNNumbers(TestResult testResult) {
        List<TestResult> issuedTTN = testResultRepository.findByTtn(testResult.getTtn());
        if (!issuedTTN.isEmpty()) {
            throw new TTNAlreadyUsedException();
        }
    }

    private void checkIssuedStatusOfTTNNumber(TestResult testResult) {
        String ttnNumber = testResult.getTtn();
        List<IssuedTTNNumber> ttnNumberList = ttnNumberRepository.findByTtnNumber(ttnNumber.toUpperCase());
        if (ttnNumberList.isEmpty()) {
            throw new NotIssuedTTNNumberException();
        }
    }
}
