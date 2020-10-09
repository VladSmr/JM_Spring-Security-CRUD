package web.service;

import web.model.Role;
import web.model.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    List<User> getAllUsers();

    void deleteUser(long id);

    User findUser(long id);

    User getUserByName(String name);

    Role findRoleByName(String roleName);

    void updateUser(User user);
}