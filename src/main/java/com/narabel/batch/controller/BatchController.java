package com.narabel.batch.controller;

import com.narabel.batch.dao.MockDataDao;
import com.narabel.batch.entity.MockData;
import com.narabel.batch.service.ExecuteBath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BatchController {

    @Autowired
    ExecuteBath executeBath;

    @Autowired
    MockDataDao dao;

    @GetMapping("/bath_1")
    public ResponseEntity<?> proceso() {
        executeBath.runBatch();
        return ResponseEntity.ok().build();
    }

}
