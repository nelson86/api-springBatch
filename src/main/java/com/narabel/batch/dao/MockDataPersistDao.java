package com.narabel.batch.dao;

import com.narabel.batch.entity.MockDataPersist;
import org.springframework.data.repository.CrudRepository;

public interface MockDataPersistDao extends CrudRepository<MockDataPersist, Long> {
}
