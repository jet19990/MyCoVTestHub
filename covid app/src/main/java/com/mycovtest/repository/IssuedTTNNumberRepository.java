package com.mycovtest.repository;

import com.mycovtest.model.IssuedTTNNumber;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IssuedTTNNumberRepository extends CrudRepository<IssuedTTNNumber, Long> {

    List<IssuedTTNNumber> findByTtnNumber(String ttnNumber);
}
