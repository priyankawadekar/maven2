package taskmanagerpkg.service.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import taskmanagerpkg.constantmodel.RoleEnum;
import taskmanagerpkg.constantmodel.ServiceStatusEnum;
import taskmanagerpkg.dto.ProcessDTO;
import taskmanagerpkg.exception.ObjectConflictException;
import taskmanagerpkg.exception.ObjectConversionException;
import taskmanagerpkg.model.Process;
import taskmanagerpkg.model.User;
import taskmanagerpkg.repository.ProcessRepository;
import taskmanagerpkg.repository.UserRepository;
import taskmanagerpkg.service.ProcessService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    private ProcessRepository processRepo;
    @Autowired
    private UserRepository userRepo;

    @Bean
    private static ModelMapper modelmapper() {
        return new ModelMapper();
    }

    @Override
    public List<ProcessDTO> getAllProcesses() {
        List<Process> processList = processRepo.findAll();
        List<ProcessDTO> processDTOList = new ArrayList<>();
        processList.forEach(process -> processDTOList.add(entityToDto(process)));
        return processDTOList;
    }

    @Override
    public List<ProcessDTO> getProcessesByRole(RoleEnum role) {
        List<Process> processList = processRepo.findProcessesByRole(role);
        List<ProcessDTO> processDTOList = new ArrayList<>();
        processList.forEach(process -> processDTOList.add(entityToDto(process)));
        return processDTOList;
    }

    @Override
    public List<ProcessDTO> getProcessesByCreatedOn(String ddmmyyyy) {
        List<ProcessDTO> processDTOList = new ArrayList<>();
        try {
            Date my_ddmmyyyy = validateDate(ddmmyyyy);
            List<Process> processList =  processRepo.findProcessesByCreatedOn(my_ddmmyyyy);
            processList.forEach(process -> processDTOList.add(entityToDto(process)));
        } catch (ParseException ex){
            throw new ObjectConversionException("INVALID DATE.", ex);
        }
        return processDTOList;
    }

    @Override
    public List<ProcessDTO> getProcessesByUpdatedOn(String ddmmyyyy) {
        List<ProcessDTO> processDTOList = new ArrayList<>();
        try {
            Date my_ddmmyyyy = validateDate(ddmmyyyy);
            List<Process> processList =  processRepo.findProcessesByUpdatedOn(my_ddmmyyyy);
            processList.forEach(process -> processDTOList.add(entityToDto(process)));
        } catch (ParseException ex){
            throw new ObjectConversionException("INVALID DATE.", ex);
        }
        return processDTOList;
    }

    @Override
    public List<ProcessDTO> getProcessesByCreatedAfter(String ddmmyyyy) {
        List<ProcessDTO> processDTOList = new ArrayList<>();
        try {
            Date my_ddmmyyyy = validateDate(ddmmyyyy);
            List<Process> processList =  processRepo.findProcessesByCreatedAfter(my_ddmmyyyy);
            processList.forEach(process -> processDTOList.add(entityToDto(process)));
        } catch (ParseException ex){
            throw new ObjectConversionException("INVALID DATE.", ex);
        }
        return processDTOList;
    }

    @Override
    public List<ProcessDTO> getProcessesByUpdatedAfter(String ddmmyyyy) {
        List<ProcessDTO> processDTOList = new ArrayList<>();
        try {
            Date my_ddmmyyyy = validateDate(ddmmyyyy);
            List<Process> processList =  processRepo.findProcessesByUpdatedAfter(my_ddmmyyyy);
            processList.forEach(process -> processDTOList.add(entityToDto(process)));
        } catch (ParseException ex){
            throw new ObjectConversionException("INVALID DATE.", ex);
        }
        return processDTOList;
    }

    @Override
    public List<ProcessDTO> getProcessesByCreatedBefore(String ddmmyyyy) {
        List<ProcessDTO> processDTOList = new ArrayList<>();
        try {
            Date my_ddmmyyyy = validateDate(ddmmyyyy);
            List<Process> processList =  processRepo.findProcessesByCreatedBefore(my_ddmmyyyy);
            processList.forEach(process -> processDTOList.add(entityToDto(process)));
        } catch (ParseException ex){
            throw new ObjectConversionException("INVALID DATE.", ex);
        }
        return processDTOList;
    }

    @Override
    public List<ProcessDTO> getProcessesByUpdatedBefore(String ddmmyyyy) {
        List<ProcessDTO> processDTOList = new ArrayList<>();
        try {
            Date my_ddmmyyyy = validateDate(ddmmyyyy);
            List<Process> processList =  processRepo.findProcessesByUpdatedBefore(my_ddmmyyyy);
            processList.forEach(process -> processDTOList.add(entityToDto(process)));
        } catch (ParseException ex){
            throw new ObjectConversionException("INVALID DATE.", ex);
        }
        return processDTOList;
    }

    @Override
    public List<ProcessDTO> getProcessesByYear(int year) {
        List<Process> processList =  processRepo.findProcessesByYear(year);
        List<ProcessDTO> processDTOList = new ArrayList<>();
        processList.forEach(process -> processDTOList.add(entityToDto(process)));
        return processDTOList;
    }

    @Override
    public List<ProcessDTO> getProcessesByStatus(ServiceStatusEnum status) {
        List<Process> processList =  processRepo.findProcessesByStatus(status);
        List<ProcessDTO> processDTOList = new ArrayList<>();
        processList.forEach(process -> processDTOList.add(entityToDto(process)));
        return processDTOList;
    }

    @Override
    public List<ProcessDTO> getProcessesByUsername(String username) {
        List<Process> processList =  processRepo.findProcessesByUsername(username);
        List<ProcessDTO> processDTOList = new ArrayList<>();
        processList.forEach(process -> processDTOList.add(entityToDto(process)));
        return processDTOList;
    }

    @Override
    public List<ProcessDTO> getProcessesByUserAndStatus(String username, ServiceStatusEnum status) {
        List<Process> processList =
                processRepo.findProcessesByUserAndStatus(username,status);
        List<ProcessDTO> processDTOList = new ArrayList<>();
        processList.forEach(process -> processDTOList.add(entityToDto(process)));
        return processDTOList;
    }

    @Override
    public ProcessDTO getProcessesByProcessid(int processid) {
        List<Process> processList =  processRepo.findProcessesByProcessid(processid);
        List<ProcessDTO> processDTOList = new ArrayList<>();
        processList.forEach(process -> processDTOList.add(entityToDto(process)));
        return processDTOList.get(0);
    }

    @Override
    public ProcessDTO updateProcessesByProcessid(int processid, ProcessDTO process) {
        List<Process> processList =  processRepo.findProcessesByProcessid(processid);
        if(processList.size()!= 1)
            throw new ObjectConflictException("ID conflict. No process or Multiple processes found.",
                    HttpStatus.CONFLICT);

        Process foundProcess = processList.get(0);
        foundProcess.setProcess_description(process.getProcess_description());
        foundProcess.setProcess_name(process.getProcess_name());
        foundProcess.setProcess_status(process.getProcess_status());
        foundProcess.setProcess_updatedOn(new Date());
        Process updatedFoundProcess = processRepo.save(foundProcess);
        return entityToDto(updatedFoundProcess);
    }

    @Override
    public String updateProcessesStatusByUsername(String username, ServiceStatusEnum status) {
        List<Process> processList =  processRepo.findProcessesByUserAndStatus(username, status);
        processList.forEach(process -> {
            process.setProcess_status(status);
            process.setProcess_updatedOn(new Date());
        });
        processRepo.saveAll(processList);
        return "Update all processes based on given criteria executed.";
    }

    @Override
    public ProcessDTO createProcesses(String username, ProcessDTO processDTO) {
        ProcessDTO savedProcessDTO;
        try {
            List<User> userList = userRepo.findUsersByUsername(username.toUpperCase());
            if (userList.size()!=1)
                throw new ObjectConflictException("ID conflict. No user or Multiple users found.",
                        HttpStatus.CONFLICT);

            Process process = dtoToEntity(processDTO);
            process.setProcess_updatedOn(new Date());
            process.setProcess_createdOn(new Date());
            process.setProcess_owner(userList.get(0));
            Process savedProcess = processRepo.save(process);
            savedProcessDTO = entityToDto(savedProcess);
        } catch (ParseException e) {
            throw new ObjectConversionException("INVALID PROCESS JSON",e);
        }

        return savedProcessDTO;
    }

    @Override
    public boolean deleteProcessesByOwnernameAndProcessid(String username, int processid) {
        List<Process> processList =  processRepo.findProcessesByProcessid(processid);
        if(processList.size()!= 1)
            throw new ObjectConflictException("ID conflict. No process or Multiple processes found.",
                    HttpStatus.CONFLICT);

        Process foundProcess = processList.get(0);
        if (foundProcess.getProcess_owner().getUser_name().equalsIgnoreCase(username)) {
            processRepo.delete(foundProcess);
            return true;
        }
        return false;
    }

    @Override
    public String deleteAllProcessesByOwnername(String username) {
        List<Process> processList =  processRepo.findProcessesByUsername(username.toUpperCase());
        processRepo.deleteInBatch(processList);

        return "Delete all processes based on given criteria executed.";
    }

    private Date validateDate(String ddmmyyyy) throws  ParseException{
        return new SimpleDateFormat("dd-MM-yyyy").parse(ddmmyyyy);
    }

    private Process dtoToEntity(ProcessDTO processDTO) throws ParseException {
        Process process = modelmapper().map(processDTO, Process.class);
        return process;
    }

    private ProcessDTO entityToDto(Process process) {
        ProcessDTO processDTO = modelmapper().map(process, ProcessDTO.class);
        return processDTO;
    }
}
