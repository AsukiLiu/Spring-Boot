package org.asuki.springboot.service;

import org.asuki.springboot.model.ViewData;
import org.asuki.springboot.repository.DemoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    private DemoDAO dao;

    @Override
    public List<ViewData> findData() {
        return dao.findData();
    }
}
