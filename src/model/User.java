package model;

import lists.ListaSimple;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String name;
    private String id;
    private Account account;
    private ListaSimple<Process> processList = new ListaSimple<Process>();

    public User(String name, String id, Account account) {
        this.name = name;
        this.id = id;
        this.account = account;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public ListaSimple<Process> getProcessList() {
        return processList;
    }

    public void setProcessList(ListaSimple<Process> processList) {
        this.processList = processList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(id, user.id) && Objects.equals(account, user.account) && Objects.equals(processList, user.processList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, account, processList);
    }

    @Override
    public String toString() {
        return "User{" +
                "account=" + account +
                ", processList=" + processList +
                '}';
    }

    public boolean deleteProcess(Process selectedProcess) {
        for (int i = 0; i < getProcessList().getSize(); i++) {
            getProcessList().eliminar(selectedProcess);
            return true;
        }
        return false;
    }

    public boolean verifyProcess(String id) {
        Process aux;
        for (int i = 0; i < getProcessList().getSize(); i++) {
            aux = getProcessList().getNodeValue(i);
            if(aux.getId().equals(id)) return true;
        }
        return false;
    }
}
