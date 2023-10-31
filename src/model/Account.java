package model;

import java.util.Objects;

public class Account {
    private String user;
    private String password;

    public Account(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public Account() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(user, account.user) && Objects.equals(password, account.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, password);
    }
}
