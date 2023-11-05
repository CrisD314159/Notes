package model;

import java.util.Objects;

public class Task {
private String description;
private boolean mustDo;
private String duration;
private boolean done;

    public Task(String description, boolean mustDo, String duration, boolean done) {
        this.description = description;
        this.mustDo = mustDo;
        this.duration = duration;
        this.done = done;
    }

    public Task() {
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return mustDo == task.mustDo && done == task.done && Objects.equals(description, task.description) && Objects.equals(duration, task.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, mustDo, duration, done);
    }

    @Override
    public String toString() {
        return "Task{" +
                "mustDo=" + mustDo +
                ", duration='" + duration + '\'' +
                ", done=" + done +
                '}';
    }
}
