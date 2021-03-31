package com.mycovtest.model.summary;

public class CasesByAgeGroup {
    private String ageGroup;
    private long cases;

    public CasesByAgeGroup() {
        cases = 0;
    }

    public CasesByAgeGroup(String ageGroup, long cases) {
        this.ageGroup = ageGroup;
        this.cases = cases;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public long getCases() {
        return cases;
    }

    public void setCases(long cases) {
        this.cases = cases;
    }

    public void increaseCaseCount() {
        this.cases++;
    }
}
