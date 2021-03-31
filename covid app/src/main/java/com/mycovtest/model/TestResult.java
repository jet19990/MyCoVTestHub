package com.mycovtest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class TestResult {

    private @Id
    @GeneratedValue
    Long id;
    private String fullName;
    private String email;
    private int age;
    private Gender gender;
    private String address;
    private String postCode;
    private String ttn;
    private ResultType testResult;

    public TestResult() {
    }

    public TestResult(String fullName, String email, int age, Gender gender, String address, String postCode, String ttn, ResultType testResult) {
        this.fullName = fullName;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.postCode = postCode;
        this.ttn = ttn;
        this.testResult = testResult;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getTtn() {
        return ttn;
    }

    public void setTtn(String ttn) {
        this.ttn = ttn;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public ResultType getTestResult() {
        return testResult;
    }

    public void setTestResult(ResultType testResult) {
        this.testResult = testResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestResult that = (TestResult) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
