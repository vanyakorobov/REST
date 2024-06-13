package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Component
public class AddToDb {

    final UserServiceImpl userServiceImpl;
    final RoleService roleService;

    @Autowired
    public AddToDb(UserServiceImpl userServiceImpl, RoleService roleService) {
        this.userServiceImpl = userServiceImpl;
        this.roleService = roleService;
    }

    @PostConstruct
    public void init() {
        Role adminRole = roleService.findByName("ROLE_ADMIN");
        if (adminRole == null) {
            adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleService.saveRole(adminRole);
        }

        roleService.saveRole(new Role("ROLE_USER"));

        User adminUser = userServiceImpl.findByUsername("admin");
        if (adminUser == null) {
            User user = new User();
            user.setName("admin");
            user.setAge((byte) 30);
            user.setPassword("admin");
            user.setRoles(Collections.singleton(adminRole));
            userServiceImpl.saveUser(user);
        }
    }
}
