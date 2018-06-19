package taskmanagerpkg.model;

import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import taskmanagerpkg.constantmodel.ServiceStatusEnum;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Process implements  Comparable<Process>{

    @SequenceGenerator(name = "processsystemsequence",
            sequenceName = "process_system_sequence",
            initialValue = 1001,
            allocationSize = 100)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "processsystemsequence")
    private long proc_syst_id;
    //@Column(columnDefinition = "integer auto_increment") - throws exception as we already have @Id auto-generated
    @Column(name="process_id")
    /*@ColumnTransformer(
            read="uuid_from_bin(process_id)",
            write="uuid_to_bin(?)")*/
    private int process_id; // id for customer end
    @Column
    private String process_name;
    @Column
    private String process_description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private User process_owner;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date process_createdOn;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date process_updatedOn;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ServiceStatusEnum process_status;

    public Process() {
    }

    public Process(String process_name,
                   String process_description,
                   User process_owner,
                   Date process_createdOn,
                   Date process_updatedOn) {

        this.process_name = process_name;
        this.process_description = process_description;
        this.process_owner = process_owner;
        this.process_createdOn = process_createdOn;
        this.process_updatedOn = process_updatedOn;
        this.process_status = ServiceStatusEnum.RUNNING;
    }

    public long getProc_syst_id() {
        return proc_syst_id;
    }

    private void setProc_syst_id(long proc_syst_id) {
        this.proc_syst_id = proc_syst_id;
    }

    public int getProcess_id() {
        return process_id;
    }

    private void setProcess_id() {
        this.process_id = (int)proc_syst_id - 1000;
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

    public User getProcess_owner() {
        return process_owner;
    }

    public void setProcess_owner(User process_owner) {
        this.process_owner = process_owner;
    }

    public Date getProcess_createdOn() {
        return process_createdOn;
    }

    public void setProcess_createdOn(Date process_createdOn) {
        this.process_createdOn = process_createdOn;
    }

    public Date getProcess_updatedOn() {
        return process_updatedOn;
    }

    public void setProcess_updatedOn(Date process_updatedOn) {
        this.process_updatedOn = process_updatedOn;
    }

    public ServiceStatusEnum getProcess_status() {
        return process_status;
    }

    public void setProcess_status(ServiceStatusEnum process_status) {
        this.process_status = process_status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Process process = (Process) o;
        return getProcess_id() == process.getProcess_id() &&
                Objects.equals(getProc_syst_id(), process.getProc_syst_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProc_syst_id(), getProcess_id()) + 2018 ;
    }

    @Override
    public String toString() {
        return "Process{" +
                "process_id=" + process_id +
                ", process_name='" + process_name + '\'' +
                ", process_description='" + process_description + '\'' +
                ", process_owner=" + process_owner.getUser_name() +
                '}';
    }

    @Override
    public int compareTo(Process o) {
        return (int)(this.proc_syst_id-o.proc_syst_id);
    }
}
