package com.mycovtest.repository;

import com.mycovtest.model.ResultType;
import com.mycovtest.model.TestResult;
import com.mycovtest.model.summary.CasesByAge;
import com.mycovtest.model.summary.CasesByPostCode;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface TestResultRepository extends CrudRepository<TestResult, Long> {
    @Query("SELECT new com.mycovtest.model.summary.CasesByPostCode(t.postCode,COUNT(t.id)) FROM TestResult t where t.testResult=0 GROUP BY t.postCode")
    Collection<CasesByPostCode> positiveCasesByPostCode();

    @Query("SELECT new com.mycovtest.model.summary.CasesByAge(t.age,COUNT(t.id)) FROM TestResult t where t.testResult=0 GROUP BY t.age")
    Collection<CasesByAge> positiveCasesByAge();

    List<TestResult> findByPostCodeAndTestResult(String postCode, ResultType resultType);

    List<TestResult> findByTtn(String ttn);

    List<TestResult> findByEmail(String email);
}
