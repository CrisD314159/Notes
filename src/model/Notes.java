package model;

import exceptions.UsuarioException;
import lists.ListaSimple;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Notes implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    ArrayList<User> usersList = new ArrayList<User>();
    ListaSimple<Process> processList = new ListaSimple<Process>();

    public Notes() {
        inicializarData();
    }

    private void inicializarData() {
        Account a = new Account("pedro", "123");
        User p = new User("pedro", "12", a);
        Process p1 = new Process("01", "desayuno");
        p.getProcessList().addToEnd(p1);

        getUsersList().add(p);
    }

    public ArrayList<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(ArrayList<User> usersList) {
        this.usersList = usersList;
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
        Notes notes = (Notes) o;
        return Objects.equals(usersList, notes.usersList) && Objects.equals(processList, notes.processList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usersList, processList);
    }

    @Override
    public String toString() {
        return "Notes{" +
                "usersList=" + usersList +
                ", processList=" + processList +
                '}';
    }



    //CRUD AND DATA ADMIN METHODS
    public User buscarVendeor(String id) {
        return null;
    }

    public boolean verifyAccount(String user, String password) {
        for (User userAux:usersList) {
            Account auxAccount = userAux.getAccount();
            if (auxAccount.getUser().equals(user) && auxAccount.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    public User getUserByAccount(String user, String password) {
        User signedUser = new User();
        for (User userAux:usersList) {
            Account auxAccount = userAux.getAccount();
            if (auxAccount.getUser().equals(user) && auxAccount.getPassword().equals(password)){
                signedUser = userAux;
            }
        }
        return signedUser;
    }

    public boolean verifyUser(String id, String user) {
        System.out.println(usersList.toString());
        for (User userAux: usersList) {
            Account auxAccount = userAux.getAccount();
            if (userAux.getId().equals(id) && auxAccount.getUser().equals(user)){
                return true;
            }

        }
        return false;
    }

    public boolean createUser(String name, String id, String user, String password) {
        User newUser = new User();
        Account account = new Account(user, password);
        try {
            if (verifyUser(id, user)){
                throw  new UsuarioException("El usuario ya existe");
            }else {
                newUser.setName(name);
                newUser.setId(id);
                newUser.setAccount(account);
                getUsersList().add(newUser);
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteUserProcess(User signedUser, Process selectedProcess) {
        boolean trigger = false;
        if (verifyUser(signedUser.getId(), signedUser.getAccount().getUser())){
            trigger = signedUser.deleteProcess(selectedProcess);
        }
        return trigger;
    }

    public boolean verifyprocess(User signedUser, String id) {
        if (verifyUser(signedUser.getId(), signedUser.getAccount().getUser())){
            return signedUser.verifyProcess(id);
        }
        return false;
    }

    public Process createprocess(User signedUser, String id, String name) {
        Process process = new Process(id, name);
        if (verifyUser(signedUser.getId(), signedUser.getAccount().getUser())){
            signedUser.getProcessList().addToEnd(process);
            return process;
        }
        return null;
    }
}
