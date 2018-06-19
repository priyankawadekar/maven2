package taskmanagerpkg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taskmanagerpkg.constantmodel.RoleEnum;
import taskmanagerpkg.constantmodel.ServiceStatusEnum;
import taskmanagerpkg.dto.ProcessDTO;
import taskmanagerpkg.service.ProcessService;

import java.util.List;

/*
    todo : PUT-all update calls should only update name/desc/status by user authentication header
    todo : GET-wild card search
    todo : POST-multi-create, DELETE-batch-delete, PUT-batch-update
    todo : jdk8 date-time improvements
*/

@RestController
public class ProcessController {
    @Autowired
    private ProcessService processService;

    //GET ----------------------------------------------------------------------------------
    @GetMapping("/processes")
    public ResponseEntity<List<ProcessDTO>> getAllProcesses() {
        List<ProcessDTO> result = processService.getAllProcesses();
        return ResponseEntity.ok().body(result);
    }

    @RequestMapping(value = "/processes", method = RequestMethod.GET, params = "rolename")
    public ResponseEntity<List<ProcessDTO>> getProcessesByRole(@RequestParam String rolename) {
        List<ProcessDTO> result = processService.getProcessesByRole(RoleEnum.valueOf(rolename.toUpperCase()));
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/processes/createdon/{dd-mm-yyyy}")
    public ResponseEntity<List<ProcessDTO>> getProcessesByCreatedOn(@PathVariable(value = "dd-mm-yyyy") String ddmmyyyy) {
        List<ProcessDTO> result = processService.getProcessesByCreatedOn(ddmmyyyy);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/processes/updatedon/{dd-mm-yyyy}")
    public ResponseEntity<List<ProcessDTO>> getProcessesByUpdatedOn(@PathVariable(value = "dd-mm-yyyy") String ddmmyyyy) {
        List<ProcessDTO> result = processService.getProcessesByUpdatedOn(ddmmyyyy);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/processes/createdafter/{dd-mm-yyyy}")
    public ResponseEntity<List<ProcessDTO>> getProcessesByCreatedAfter(@PathVariable(value = "dd-mm-yyyy") String ddmmyyyy) {
        List<ProcessDTO> result = processService.getProcessesByCreatedAfter(ddmmyyyy);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/processes/updatedafter/{dd-mm-yyyy}")
    public ResponseEntity<List<ProcessDTO>> getProcessesByUpdatedAfter(@PathVariable(value = "dd-mm-yyyy") String ddmmyyyy) {
        List<ProcessDTO> result = processService.getProcessesByUpdatedAfter(ddmmyyyy);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/processes/createdbefore/{dd-mm-yyyy}")
    public ResponseEntity<List<ProcessDTO>> getProcessesByCreatedBefore(@PathVariable(value = "dd-mm-yyyy") String ddmmyyyy) {
        List<ProcessDTO> result = processService.getProcessesByCreatedBefore(ddmmyyyy);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/processes/updatedbefore/{dd-mm-yyyy}")
    public ResponseEntity<List<ProcessDTO>> getProcessesByUpdatedBefore(@PathVariable(value = "dd-mm-yyyy") String ddmmyyyy) {
        List<ProcessDTO> result = processService.getProcessesByUpdatedBefore(ddmmyyyy);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/processes/year/{yyyy}")
    public ResponseEntity<List<ProcessDTO>> getProcessesByYear(@PathVariable int yyyy) {
        List<ProcessDTO> result = processService.getProcessesByYear(yyyy);
        return ResponseEntity.ok().body(result);
    }

    @RequestMapping(value = "/processes", method = RequestMethod.GET, params = "status")
    public ResponseEntity<List<ProcessDTO>> getProcessesByStatus(@RequestParam String status) {
        List<ProcessDTO> result = processService.getProcessesByStatus(ServiceStatusEnum.valueOf(status.toUpperCase()));
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/users/{username}/processes")
    public ResponseEntity<List<ProcessDTO>> getProcessesByUsername(@PathVariable String username) {
        List<ProcessDTO> result = processService.getProcessesByUsername(username);
        return ResponseEntity.ok().body(result);
    }

    @RequestMapping(value = "/users/{username}/processes", method = RequestMethod.GET, params = "status")
    //@GetMapping("/users/{username}/processes?status=status}")
    public ResponseEntity<List<ProcessDTO>> getProcessesByUserAndStatus(@PathVariable String username,
                                                                        @RequestParam String status) {
        List<ProcessDTO> result = processService.getProcessesByUserAndStatus(username,
                ServiceStatusEnum.valueOf(status.toUpperCase()));
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/processes/{processid}")
    public ResponseEntity<ProcessDTO> getProcessesByProcessid(@PathVariable int processid) {
       ProcessDTO result = processService.getProcessesByProcessid(processid);
        return ResponseEntity.ok().body(result);
    }

    //PUT ----------------------------------------------------------------------------------
    @PutMapping("/processes/{processid}")
    public ResponseEntity<ProcessDTO> updateProcessesByProcessid(@PathVariable int processid,
                                                            @RequestBody ProcessDTO process){
        ProcessDTO result = processService.updateProcessesByProcessid(processid, process);
        return ResponseEntity.ok().body(result);
    }

    @RequestMapping(value = "/users/{username}/processes", method = RequestMethod.PUT)
    //@PutMapping("/users/{username}/processes?status=status}")
    public ResponseEntity<String> updateProcessesStatusByUsername(@PathVariable String username,
                                                                  @RequestParam String status){
        return ResponseEntity.ok(processService.updateProcessesStatusByUsername(username,
                ServiceStatusEnum.valueOf(status.toUpperCase())));
    }

    //POST ----------------------------------------------------------------------------------
    @PostMapping("/users/{username}/processes")
    public ResponseEntity<ProcessDTO> createProcesses(@PathVariable String username,
                                                   @RequestBody ProcessDTO process){
        ProcessDTO result = processService.createProcesses(username, process);
        return ResponseEntity.ok().body(result);
    }

    //DELETE ----------------------------------------------------------------------------------
    @DeleteMapping("/users/{username}/processes/{processid}")
    public ResponseEntity<Boolean> deleteProcessesByProcessid(@PathVariable String username,
                                                   @PathVariable int processid){
        return ResponseEntity.ok(processService.deleteProcessesByOwnernameAndProcessid(username, processid));
    }

    @DeleteMapping("/users/{username}/processes")
    public ResponseEntity<String> deleteAllProcesses(@PathVariable String username){
        return ResponseEntity.ok(processService.deleteAllProcessesByOwnername(username));
    }
}
