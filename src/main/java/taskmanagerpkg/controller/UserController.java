package taskmanagerpkg.controller;

import org.springframework.http.HttpMethod;
import taskmanagerpkg.constantmodel.RoleEnum;
import taskmanagerpkg.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taskmanagerpkg.service.UserService;

import java.util.List;
/*
    todo : authentications
    todo : PUT-all update calls should only update name-ifNotExists/role-byHigherUser/password
    todo : GET-wild card search
    todo : POST-multi-create, DELETE-batch-delete, PUT-batch-update

Return type/value for HTTP methods
    https://tools.ietf.org/html/rfc7231#section-4.3
    http://shengwangi.blogspot.com/2016/02/response-for-get-post-put-delete-in-rest.html
*/
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //GET ----------------------------------------------------------------------------------
    @RequestMapping(value = "/users", method = RequestMethod.GET, params = "role")
    //@GetMapping("/users/?role=")
    public ResponseEntity<List<UserDTO>> getUsersByRole(@RequestParam String role) {
        List<UserDTO> result = userService.getUsersByRole(RoleEnum.valueOf(role.toUpperCase()));
        return ResponseEntity.ok().body(result);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET, params = "startswith")
    //@GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsersByUsernameStartswith(@RequestParam String startswith) {
        // todo wild card search
        return null;
    }

    @GetMapping("/users/{name}")
    public ResponseEntity<UserDTO> getUsersByUsername(@PathVariable String name) {
        UserDTO result = userService.getUsersByUsername(name);
        if (result==null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok().body(result);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> result = userService.getAllUsers();
        return ResponseEntity.ok().body(result);
    }

    //PUT ----------------------------------------------------------------------------------

    @PutMapping("/users/{name}")
    public ResponseEntity<Boolean> updateUsersByUsername(@PathVariable String name,
                                                    @RequestBody UserDTO user) {

       if (userService.updateUsersByUsername(name, user))
           return ResponseEntity.ok(true);
       else
           return ResponseEntity.ok(false);

    }

    //POST ----------------------------------------------------------------------------------
    @PostMapping("/users")
    public ResponseEntity<UserDTO> createUsers(@RequestBody UserDTO user) {
        UserDTO result = userService.createUsers(user);
        return ResponseEntity.ok().body(result);
    }

    //DELETE ----------------------------------------------------------------------------------
    @DeleteMapping("/users/{name}")
    public ResponseEntity<Boolean> deleteUsersByUsername(@PathVariable(value = "name") String name) {
        if(userService.deleteUsersByUsername(name))
            return ResponseEntity.ok(true);
        else
            return ResponseEntity.ok(false);
    }

    //customized urls & return values
    /*@RequestMapping(value ="/book3", produces =MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Book> bookInfo3() {
        Book book = new Book();
        book.setBookName("Ramayan");
        book.setWriter("Valmiki");
        return ResponseEntity.accepted().body(book);
    }*/
}
