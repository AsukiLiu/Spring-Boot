package org.asuki.springboot.repository;

import org.asuki.springboot.model.ViewData;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.IntStream.rangeClosed;

@Repository
public class DemoDAOImpl implements DemoDAO {
    @Override
    public List<ViewData> findData() {
        List<ViewData> dataList = new ArrayList<>();

        rangeClosed(1, 5).forEach(i -> dataList.add(new ViewData(i, "Message" + i)));

        return dataList;
    }
}
