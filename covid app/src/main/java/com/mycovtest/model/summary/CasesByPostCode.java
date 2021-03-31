package com.mycovtest.model.summary;

public class CasesByPostCode {
    private String postCode;
    private int count;

    public CasesByPostCode() {
    }

    public CasesByPostCode(String postCode, long count) {
        this.postCode = postCode;
        this.count = (int) count;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
