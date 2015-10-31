package org.asuki.springboot.repository;

import org.asuki.springboot.model.Team;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = Team.class, idClass = Long.class)
public interface TeamDao extends BaseDao<Team> {
}
