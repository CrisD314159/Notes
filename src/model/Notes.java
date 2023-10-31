package model;

import lists.ListaSimple;

import java.util.ArrayList;
import java.util.Objects;

public class Notes {
    ArrayList<User> usersList = new ArrayList<User>();
    ListaSimple<Process> processList = new ListaSimple<Process>();

    public Notes() {
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
}
