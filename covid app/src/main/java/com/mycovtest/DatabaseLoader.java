package com.mycovtest;

import com.mycovtest.model.IssuedTTNNumber;
import com.mycovtest.repository.IssuedTTNNumberRepository;
import com.mycovtest.repository.TestResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseLoader implements CommandLineRunner {


    @Autowired
    private TestResultRepository testResultRepository;
    @Autowired
    private IssuedTTNNumberRepository ttnNumberRepository;

    @Override
    public void run(String... strings) throws Exception {
        addIssuedTTNNumbers();
    }

    private void addIssuedTTNNumbers() {
        List<String> issuedTTnNumbers = Arrays.asList("MM2874Z6", "FEQQ6UUG", "34GC829B", "CB8FBCCM", "8RL4ENTK",
                "57UBS5J6", "4F7YKH9G", "R9KZ2NXL", "YBQUVXHL", "CCZTQ8K");
        for (String issuedTTnNumber : issuedTTnNumbers) {
            if (this.ttnNumberRepository.findByTtnNumber(issuedTTnNumber).isEmpty()) {
                this.ttnNumberRepository.save(new IssuedTTNNumber(issuedTTnNumber));
            }
        }
    }
}