package org.asuki.springboot.repository;

import org.asuki.springboot.model.ViewData;

import java.util.List;

public interface DemoDAO {
    List<ViewData> findData();
}
