package taskmanagerpkg.service.implementation;

import org.springframework.http.HttpStatus;
import taskmanagerpkg.constantmodel.RoleEnum;
import taskmanagerpkg.dto.UserDTO;
import taskmanagerpkg.exception.ObjectConflictException;
import taskmanagerpkg.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import taskmanagerpkg.repository.UserRepository;
import taskmanagerpkg.service.UserService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
todo : ignore username-case sensitivity
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Bean
    private static ModelMapper modelmapper() {
        return new ModelMapper();
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> userList = userRepo.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        userList.forEach(user -> userDTOList.add(entityToDto(user)));
        return userDTOList;
    }


    @Override
    public UserDTO getUsersByUsername(String user_name) {

        List<User> userList = userRepo.findUsersByUsername(user_name.toUpperCase());
        if (!userList.isEmpty())
            return entityToDto(userList.get(0));
        else
            return null;
    }

    @Override
    public List<UserDTO> getUsersByRole(RoleEnum role) {
        //String roleStr = role.name().toUpperCase();
        List<User> userList = userRepo.findUsersByRole(role);
        List<UserDTO> userDTOList = new ArrayList<>();
        userList.forEach(user -> userDTOList.add(entityToDto(user)));
        return userDTOList;
    }

    @Override
    public boolean updateUsersByUsername(String uname, UserDTO userdto) {

        List<User> userList = userRepo.findUsersByUsername(uname.toUpperCase());
        if (userList.size()!=1)
            throw new ObjectConflictException("ID conflict. No users or multiple users found with same user_name."
                    + userList,
                    HttpStatus.CONFLICT);
        User user = userList.get(0);

        //in case of public key modification, detach existing entity and save new.
        //otherwise org.hibernate.HibernateException: identifier of an instance xx altered from x0 to x1
        user.setUser_name(userdto.getUser_name().toUpperCase());
        user.setUser_password(userdto.getUser_password());
        user.setUser_role(userdto.getUser_role());
        userRepo.delete(user);
        userRepo.save(user);
        return userRepo.existsById(user.getUser_id());
    }

    @Override
    public UserDTO createUsers(UserDTO userdto) {

        List<User> userList = userRepo.findUsersByUsername(userdto.getUser_name().toUpperCase());
        if(userList.size()>0)
            throw new ObjectConflictException("ID conflict. No users or multiple users found with same user_name."
                    + userList,
                    HttpStatus.CONFLICT);
        User user = new User(userdto.getUser_name().toUpperCase(),
                userdto.getUser_password(),
                userdto.getUser_role(),
                new Date());
        return entityToDto(userRepo.save(user));
    }

    @Override
    public boolean deleteUsersByUsername(String uname) {
        List<User> userList = userRepo.findUsersByUsername(uname.toUpperCase());
        if(userList.size()!=1)
            throw new ObjectConflictException("ID conflict. No users or multiple users found with same user_name."
                    + userList,
                    HttpStatus.CONFLICT);

        userRepo.delete(userList.get(0));
        return !userRepo.existsById(userList.get(0).getUser_id());
    }

    @Override
    public long deleteAllUsers() {
        int entities = (int)userRepo.count();
        userRepo.deleteAll();
        return entities;
    }

    private User dtoToEntity(UserDTO userdto) throws ParseException {
        return modelmapper().map(userdto, User.class);
    }

    private UserDTO entityToDto(User user) {
        //UserDTO userdto = modelmapper().map(user, UserDTO.class); - not mapping Date field
        return new UserDTO(user.getUser_name(), null, user.getUser_role(), user.getUser_createdOn());
    }
}
