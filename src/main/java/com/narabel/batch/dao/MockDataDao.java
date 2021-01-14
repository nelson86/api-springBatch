package com.narabel.batch.dao;

import com.narabel.batch.entity.MockData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface MockDataDao extends CrudRepository<MockData, Long> {

}
