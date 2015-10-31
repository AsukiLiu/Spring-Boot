package org.asuki.springboot.repository;

import org.asuki.springboot.model.Project;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = Project.class, idClass = Long.class)
public interface ProjectDao extends BaseDao<Project> {
}
