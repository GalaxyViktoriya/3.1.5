package ru.kata.spring.boot_security.demo.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Init {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public Init(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @PostConstruct
    public void initializedDataBase() {
        roleService.addRole(new Role("ROLE_USER"));
        roleService.addRole(new Role("ROLE_ADMIN"));

        Set<Role> adminRole = new HashSet<>();
        Set<Role> userRole = new HashSet<>();
        Set<Role> allRoles = new HashSet<>();

        adminRole.add(roleService.getRoleById(2L));
        userRole.add(roleService.getRoleById(1L));
        allRoles.add(roleService.getRoleById(1L));
        allRoles.add(roleService.getRoleById(2L));

        userService.saveUser(new User("Руслан", "Гарманов", (byte) 21, "kata@mail.ru",
                "admin", "admin", adminRole));
        userService.saveUser(new User("Александр", "Белов", (byte) 22, "sasha@mail.ru",
                "user", "user", userRole));
        userService.saveUser(new User("Дмитрий", "Антонов", (byte) 42, "dimon@mail.ru",
                "all", "all", allRoles));
    }
}
