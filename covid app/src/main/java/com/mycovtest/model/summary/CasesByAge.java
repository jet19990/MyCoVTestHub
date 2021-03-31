package com.mycovtest.model.summary;

public class CasesByAge {
    private int age;
    private long cases;

    public CasesByAge() {
    }

    public CasesByAge(int age, long cases) {
        this.age = age;
        this.cases = cases;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getCases() {
        return cases;
    }

    public void setCases(long cases) {
        this.cases = cases;
    }
}
