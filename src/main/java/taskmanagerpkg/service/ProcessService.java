package taskmanagerpkg.service;

import taskmanagerpkg.constantmodel.RoleEnum;
import taskmanagerpkg.constantmodel.ServiceStatusEnum;
import taskmanagerpkg.dto.ProcessDTO;
import taskmanagerpkg.dto.UserDTO;

import java.util.Date;
import java.util.List;

public interface ProcessService {

        public List<ProcessDTO> getAllProcesses();
        public List<ProcessDTO> getProcessesByRole(RoleEnum role);
        //todo customized query
        //ref - https://stackoverflow.com/questions/47719439/spring-data-jpa-how-can-i-fetch-data-from-a-date-column-using-only-month-and-d?rq=1
        public List<ProcessDTO> getProcessesByCreatedOn(String ddmmyyyy);
        public List<ProcessDTO> getProcessesByUpdatedOn(String ddmmyyyy);
        public List<ProcessDTO> getProcessesByCreatedAfter(String ddmmyyyy);
        public List<ProcessDTO> getProcessesByUpdatedAfter(String ddmmyyyy);
        public List<ProcessDTO> getProcessesByCreatedBefore(String ddmmyyyy);
        public List<ProcessDTO> getProcessesByUpdatedBefore(String ddmmyyyy);
        public List<ProcessDTO> getProcessesByYear(int year);
        public List<ProcessDTO> getProcessesByStatus(ServiceStatusEnum status);
        public List<ProcessDTO> getProcessesByUsername(String username);
        public List<ProcessDTO> getProcessesByUserAndStatus(String username, ServiceStatusEnum status);
        public ProcessDTO getProcessesByProcessid(int processid);

        public ProcessDTO updateProcessesByProcessid(int processid, ProcessDTO process);
        //todo : multi-update should return no. of rows affected and proper message
        public String updateProcessesStatusByUsername(String username,
                                                       ServiceStatusEnum status);

        public ProcessDTO createProcesses(String username, ProcessDTO processDTO);

        public boolean deleteProcessesByOwnernameAndProcessid(String username, int processid);
        public String deleteAllProcessesByOwnername(String username);
}
