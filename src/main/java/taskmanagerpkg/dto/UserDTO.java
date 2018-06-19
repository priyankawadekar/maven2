package taskmanagerpkg.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;
import taskmanagerpkg.constantmodel.RoleEnum;

import java.io.Serializable;
import java.util.Date;
/*
1. Serialization
-- ref - https://stackoverflow.com/questions/4548816/when-should-we-implement-serializable-interface
-- If class doesn't need to be serializable it doesn't need to exist.
DTOs exist only to Transfer data between components that can't share "real" objects.
-- When we use DTO to decouple persistence layer and service layer; jpa-entities shouldnt be serializable.
ref - https://bvaisakh.wordpress.com/2015/03/04/do-jpa-entities-have-to-be-serializable/

2. Equals and Hashcode
https://stackoverflow.com/questions/7252731/overriding-equals-method-in-dtos
 */
@Component
public class UserDTO implements Serializable {
    String user_name;
    String user_password;
    RoleEnum user_role;
    Date user_createdOn;

    protected UserDTO(){}
    public UserDTO(String name,
                   String password,
                   RoleEnum role,
                   Date createdOn) {
        this.user_name = name;
        this.user_password = password;
        this.user_role = role;
        this.user_createdOn = createdOn;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public RoleEnum getUser_role() {
        return user_role;
    }

    public void setUser_role(RoleEnum user_role) {
        this.user_role = user_role;
    }

    public Date getUser_createdOn() {
        return user_createdOn;
    }

    private void setUser_createdOn(Date user_createdOn) {
        this.user_createdOn = user_createdOn;
    }
}
