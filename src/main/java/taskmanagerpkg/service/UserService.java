package taskmanagerpkg.service;

import taskmanagerpkg.constantmodel.RoleEnum;
import taskmanagerpkg.dto.UserDTO;

import java.util.List;

// Note - Keeping same method names as that of controllers;
// as services returning java-entities and controllers returning ResponseEntity
// so for testing/customer-side-api-consuming we would focus on Service entities.

// Note - Repository method signatures designed as per existing methods.

//todo : spring+jpa bulk insert,update

public interface UserService {

    public List<UserDTO> getAllUsers();
    public List<UserDTO> getUsersByRole(RoleEnum role);
    public UserDTO getUsersByUsername(String uname);

    public boolean updateUsersByUsername(String uname, UserDTO user);

    public UserDTO createUsers(UserDTO user);

    public boolean deleteUsersByUsername(String uname);
    public long deleteAllUsers();
}
