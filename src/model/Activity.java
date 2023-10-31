package model;

import lists.Cola;
import lists.ListaDoble;

import java.util.Objects;

public class Activity {
    private String name;
    private String description;
    private boolean mustDo;
    private boolean done;
    private Cola<Task> tasksList = new Cola<Task>();

    public Activity(String name, String description, boolean mustDo, boolean done) {
        this.name = name;
        this.description = description;
        this.mustDo = mustDo;
        this.done = done;
    }

    public Activity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isMustDo() {
        return mustDo;
    }

    public void setMustDo(boolean mustDo) {
        this.mustDo = mustDo;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Cola<Task> getTasksList() {
        return tasksList;
    }

    public void setTasksList(Cola<Task> tasksList) {
        this.tasksList = tasksList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return mustDo == activity.mustDo && done == activity.done && Objects.equals(name, activity.name) && Objects.equals(description, activity.description) && Objects.equals(tasksList, activity.tasksList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, mustDo, done, tasksList);
    }

    @Override
    public String toString() {
        return "Activity{" +
                "mustDo=" + mustDo +
                ", done=" + done +
                ", tasksList=" + tasksList +
                '}';
    }
}
