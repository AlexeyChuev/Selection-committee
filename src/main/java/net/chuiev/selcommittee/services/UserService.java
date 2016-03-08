package net.chuiev.selcommittee.services;

import net.chuiev.selcommittee.entity.RoleTypeEnum;
import net.chuiev.selcommittee.entity.User;
import net.chuiev.selcommittee.repository.UserRepository;

/**
 * Created by Alex on 3/7/2016.
 */
public class UserService {
    private static UserRepository userRepository = new UserRepository();

    public static void registrationUserInSystem(String email, String password, String roleType) {
        User user = new User();
        user.setPassword(password);
        user.setEmail(email);
        //1 - Admin
        //2 - Client
        if (RoleTypeEnum.ADMIN.getName().equalsIgnoreCase(roleType))
            user.setRole(1);
        else user.setRole(2);

        userRepository.create(user);
    }
}
