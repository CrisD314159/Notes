package model;

import lists.ListaSimple;

import java.util.Objects;

public class Process {
    private String id;
    private String name;
    private ListaSimple<Activity> activitiesList = new ListaSimple<Activity>();
    private Integer size;

    public Process(String id, String name) {
        this.size = activitiesList.getSize();
        this.id = id;
        this.name = name;
    }

    public Process() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ListaSimple<Activity> getActivitiesList() {
        return activitiesList;
    }

    public void setActivitiesList(ListaSimple<Activity> activitiesList) {
        this.activitiesList = activitiesList;
    }


    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Process process = (Process) o;
        return Objects.equals(id, process.id) && Objects.equals(name, process.name) && Objects.equals(activitiesList, process.activitiesList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, activitiesList);
    }

    @Override
    public String toString() {
        return "Process{" +
                "activitiesList=" + activitiesList +
                '}';
    }
}
