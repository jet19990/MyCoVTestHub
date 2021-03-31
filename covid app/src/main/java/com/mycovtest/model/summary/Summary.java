package com.mycovtest.model.summary;

import java.util.List;

public class Summary {
    private int totalPositiveCases;
    private int totalNegativeCases;
    private int totalInconclusiveCases;
    private List<CasesByPostCode> casesByPostCodeList;
    private List<CasesByAgeGroup> casesByAgeGroupsList;
    private List<List<Object>> casesByAgeGroupPerPostCode;

    public Summary() {
    }

    public int getTotalPositiveCases() {
        return totalPositiveCases;
    }

    public void setTotalPositiveCases(int totalPositiveCases) {
        this.totalPositiveCases = totalPositiveCases;
    }

    public int getTotalNegativeCases() {
        return totalNegativeCases;
    }

    public void setTotalNegativeCases(int totalNegativeCases) {
        this.totalNegativeCases = totalNegativeCases;
    }

    public int getTotalInconclusiveCases() {
        return totalInconclusiveCases;
    }

    public void setTotalInconclusiveCases(int totalInconclusiveCases) {
        this.totalInconclusiveCases = totalInconclusiveCases;
    }

    public List<CasesByPostCode> getCasesByPostCodeList() {
        return casesByPostCodeList;
    }

    public void setCasesByPostCodeList(List<CasesByPostCode> casesByPostCodeList) {
        this.casesByPostCodeList = casesByPostCodeList;
    }

    public List<CasesByAgeGroup> getCasesByAgeGroupsList() {
        return casesByAgeGroupsList;
    }

    public void setCasesByAgeGroupsList(List<CasesByAgeGroup> casesByAgeGroupsList) {
        this.casesByAgeGroupsList = casesByAgeGroupsList;
    }

    public List<List<Object>> getCasesByAgeGroupPerPostCode() {
        return casesByAgeGroupPerPostCode;
    }

    public void setCasesByAgeGroupPerPostCode(List<List<Object>> casesByAgeGroupPerPostCode) {
        this.casesByAgeGroupPerPostCode = casesByAgeGroupPerPostCode;
    }
}
