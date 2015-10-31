package org.asuki.springboot.repository;

import org.asuki.springboot.model.Employee;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = Employee.class, idClass = Long.class)
public interface EmployeeDao extends BaseDao<Employee> {
}
