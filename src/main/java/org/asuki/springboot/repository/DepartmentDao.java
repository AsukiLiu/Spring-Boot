package org.asuki.springboot.repository;

import org.asuki.springboot.model.Department;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = Department.class, idClass = Long.class)
public interface DepartmentDao extends BaseDao<Department> {
}
