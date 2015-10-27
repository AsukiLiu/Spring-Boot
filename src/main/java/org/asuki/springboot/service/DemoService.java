package org.asuki.springboot.service;

import org.asuki.springboot.model.ViewData;

import java.util.List;

public interface DemoService {
    List<ViewData> findData();
}