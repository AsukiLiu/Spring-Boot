package org.asuki.springboot;

import org.asuki.springboot.model.*;
import org.asuki.springboot.repository.DepartmentDao;
import org.asuki.springboot.repository.EmployeeDao;
import org.asuki.springboot.repository.ProjectDao;
import org.asuki.springboot.repository.TeamDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.Date;

import static java.util.Collections.singletonList;
import static org.asuki.springboot.model.EmployeeProfile.SEX.MALE;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
public class MainTest {

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Test
    @Transactional
    public void test() {
        /*
        CREATE
         */

        Project project = createProject();
        projectDao.save(project);

        Team team = createTeam();
        teamDao.save(team);

        Employee employee = createEmployee();
        employee.setTeam(team);
        employee.setProjectList(singletonList(project));

        Department department = createDepartment();
        department.setEmployeeList(singletonList(employee));

        employee.setDepartment(department);

        departmentDao.save(department);

        /*
        GET
         */

        Department dbDepartment = departmentDao.getById(department.getId());
        Employee dbEmployee = dbDepartment.getEmployeeList().get(0);
        Project dbProject = dbEmployee.getProjectList().get(0);

        assertThat(dbDepartment.getName(), is(department.getName()));
        assertThat(dbEmployee.getName(), is(employee.getName()));
        assertThat(dbEmployee.getTeam().getName(), is(team.getName()));
        assertThat(dbEmployee.getEmployeeProfile().getSex(), is(MALE));
        assertThat(dbProject.getName(), is(project.getName()));

        dbEmployee = employeeDao.getById(employee.getId());
        dbDepartment = dbEmployee.getDepartment();

        assertThat(dbEmployee.getEmployeeProfile().getId(), is(employee.getId()));
        assertThat(dbDepartment.getName(), is(department.getName()));

        /*
        DELETE
         */

        departmentDao.delete(department.getId());

        assertThat(departmentDao.count(), is(0L));
        assertThat(employeeDao.count(), is(0L));
        assertThat(teamDao.count(), is(1L));
        assertThat(projectDao.count(), is(1L));
    }

    private Project createProject() {
        Project project = new Project();
        project.setName("ProjectX");
        project.setCreateTime(new Date());
        project.setUpdateTime(new Date());
        return project;
    }

    private Team createTeam() {
        Team team = new Team();
        team.setName("TeamX");
        team.setCreateTime(new Date());
        team.setUpdateTime(new Date());
        return team;
    }

    private Department createDepartment() {
        Department department = new Department();
        department.setName("Dev");
        department.setDescription("Creating product.");
        department.setCreateTime(new Date());
        department.setUpdateTime(new Date());
        return department;
    }

    private Employee createEmployee() {
        EmployeeProfile profile = createEmployeeProfile();

        Employee employee = new Employee();
        employee.setName("Member101");
        employee.setCreateTime(new Date());
        employee.setUpdateTime(new Date());
        employee.setEmployeeProfile(profile);
        return employee;
    }

    private EmployeeProfile createEmployeeProfile() {
        EmployeeProfile profile = new EmployeeProfile();
        profile.setSex(MALE);
        profile.setCreateTime(new Date());
        profile.setUpdateTime(new Date());
        return profile;
    }
}
