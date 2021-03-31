package com.mycovtest.service;

import com.mycovtest.model.ResultType;
import com.mycovtest.model.TestResult;
import com.mycovtest.model.summary.CasesByAge;
import com.mycovtest.model.summary.CasesByAgeGroup;
import com.mycovtest.model.summary.CasesByPostCode;
import com.mycovtest.model.summary.Summary;
import com.mycovtest.repository.TestResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminService {

    @Autowired
    private TestResultRepository testResultRepository;

    public Summary getSummaryReport() {
        Summary summary = new Summary();
        List<TestResult> testResults = new ArrayList<>();
        testResultRepository.findAll().forEach(testResults::add);

        addCaseSummary(summary, testResults);
        addPositiveCasesPerPostCode(summary);
        addPositiveCasesPerAgeGroup(summary);
        addCasesPerAgeGroupPerPostCode(summary);

        return summary;
    }

    private void addCasesPerAgeGroupPerPostCode(Summary summary) {
        List<CasesByPostCode> casesByPostCodeList = summary.getCasesByPostCodeList();
        List<List<Object>> result = new ArrayList<>();
        for (CasesByPostCode casesByPostCode : casesByPostCodeList) {
            String postCode = casesByPostCode.getPostCode();
            ArrayList<Object> resultRow = new ArrayList<>(Arrays.asList(postCode, 0, 0, 0, 0, 0));
            List<TestResult> byPostCode = testResultRepository.findByPostCodeAndTestResult(postCode, ResultType.POSITIVE);
            for (TestResult testResult : byPostCode) {
                if (testResult.getAge() <= 10) {
                    addToArray(resultRow, 1);
                } else if (testResult.getAge() <= 20) {
                    addToArray(resultRow, 2);
                } else if (testResult.getAge() <= 40) {
                    addToArray(resultRow, 3);
                } else if (testResult.getAge() <= 60) {
                    addToArray(resultRow, 4);
                } else {
                    addToArray(resultRow, 5);
                }
            }
            result.add(resultRow);
        }
        summary.setCasesByAgeGroupPerPostCode(result);
    }

    private void addToArray(ArrayList<Object> resultRow, int index) {
        Integer currentValue = (Integer) resultRow.get(index);
        currentValue++;
        resultRow.set(index, currentValue);
    }

    private void addPositiveCasesPerAgeGroup(Summary summary) {
        Collection<CasesByAge> casesByAges = testResultRepository.positiveCasesByAge();
        Map<String, CasesByAgeGroup> ageGroupMap = new HashMap<>();
        for (CasesByAge casesByAge : casesByAges) {
            if (casesByAge.getAge() <= 10) {
                addToCasesMap("0 - 10", ageGroupMap);
            } else if (casesByAge.getAge() <= 20) {
                addToCasesMap("10 - 20", ageGroupMap);
            } else if (casesByAge.getAge() <= 40) {
                addToCasesMap("20 - 40", ageGroupMap);
            } else if (casesByAge.getAge() <= 60) {
                addToCasesMap("40 - 60", ageGroupMap);
            } else {
                addToCasesMap(" > 60", ageGroupMap);
            }
        }
        summary.setCasesByAgeGroupsList(new ArrayList<>(ageGroupMap.values()));
    }

    private void addToCasesMap(String ageGroup, Map<String, CasesByAgeGroup> ageGroupMap) {
        CasesByAgeGroup casesByAgeGroup = ageGroupMap.getOrDefault(ageGroup, new CasesByAgeGroup(ageGroup, 0));
        casesByAgeGroup.increaseCaseCount();
        if (ageGroupMap.containsKey(ageGroup)) {
            ageGroupMap.replace(ageGroup, casesByAgeGroup);
        } else {
            ageGroupMap.put(ageGroup, casesByAgeGroup);
        }
    }

    private void addPositiveCasesPerPostCode(Summary summary) {
        Collection<CasesByPostCode> casesByPostCodes = testResultRepository.positiveCasesByPostCode();
        summary.setCasesByPostCodeList(new ArrayList<>(casesByPostCodes));
    }

    private void addCaseSummary(Summary summary, List<TestResult> testResults) {
        int positiveCases = 0;
        int negativeCases = 0;
        int inconclusiveCases = 0;

        for (TestResult testResult : testResults) {
            ResultType result = testResult.getTestResult();
            if (result == ResultType.POSITIVE) {
                positiveCases++;
            } else if (result == ResultType.NEGATIVE) {
                negativeCases++;
            }else{
                inconclusiveCases++;
            }
        }

        summary.setTotalPositiveCases(positiveCases);
        summary.setTotalNegativeCases(negativeCases);
        summary.setTotalInconclusiveCases(inconclusiveCases);
    }

}
