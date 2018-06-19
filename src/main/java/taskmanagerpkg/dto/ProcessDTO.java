package taskmanagerpkg.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import org.springframework.stereotype.Component;
import taskmanagerpkg.constantmodel.ServiceStatusEnum;

import java.io.Serializable;
import java.util.Date;

@Component
public class ProcessDTO implements Serializable {

    private int process_id; // id for customer end
    private String process_name;
    private String process_description;
    private UserDTO process_owner;
    private Date process_createdOn;
    private Date process_updatedOn;
    private ServiceStatusEnum process_status;

    protected  ProcessDTO(){}
    public ProcessDTO(String process_name,
                      String process_description,
                      UserDTO process_owner,
                      Date process_createdOn) {
        this.process_name = process_name;
        this.process_description = process_description;
        this.process_owner = process_owner;
        this.process_createdOn = process_createdOn;
        this.process_updatedOn = process_createdOn;
    }

    public int getProcess_id() {
        return process_id;
    }

    public String getProcess_name() {
        return process_name;
    }

    public void setProcess_name(String process_name) {
        this.process_name = process_name;
    }

    public String getProcess_description() {
        return process_description;
    }

    public void setProcess_description(String process_description) {
        this.process_description = process_description;
    }

    public UserDTO getProcess_owner() {
        return process_owner;
    }

    public void setProcess_owner(UserDTO process_owner) {
        this.process_owner = process_owner;
    }

    public Date getProcess_createdOn() {
        return process_createdOn;
    }

    private void setProcess_createdOn(Date process_createdOn) {
        this.process_createdOn = process_createdOn;
    }

    public Date getProcess_updatedOn() {
        return process_updatedOn;
    }

    private void setProcess_updatedOn(Date process_updatedOn) {
        this.process_updatedOn = process_updatedOn;
    }

    public ServiceStatusEnum getProcess_status() {
        return process_status;
    }

    public void setProcess_status(ServiceStatusEnum process_status) {
        this.process_status = process_status;
    }
}
