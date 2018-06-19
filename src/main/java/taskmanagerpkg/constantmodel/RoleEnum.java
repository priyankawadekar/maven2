package taskmanagerpkg.constantmodel;

import java.util.ArrayList;
import java.util.stream.Stream;

public enum RoleEnum {
    SYSADMIN(ServiceActionEnum.ALL),
    PROJMANAGER(ServiceActionEnum.ACTIVATE, ServiceActionEnum.START, ServiceActionEnum.STOP, ServiceActionEnum.RESTART),
    DEV(ServiceActionEnum.START, ServiceActionEnum.STOP, ServiceActionEnum.RESTART),
    QA(ServiceActionEnum.RESTART),
    USER(ServiceActionEnum.NONE);

    private ArrayList<ServiceActionEnum> actions;
    RoleEnum(ServiceActionEnum... sactions) {
        if(sactions.length!=0) {
            this.actions = new ArrayList<>();
            Stream.of(sactions).forEach(s -> this.actions.add(s));
        } else {
            actions = null;
        }
    }

    public ArrayList<ServiceActionEnum> getActions(){
        return actions;
    }
    public String getRole() { return this.name(); }
}
