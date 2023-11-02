package model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Admin implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String name;
    private String id;
    private Account account;

    public Admin(String name, String id, Account account) {
        this.name = name;
        this.id = id;
        this.account = account;
    }


    public Admin() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(name, admin.name) && Objects.equals(id, admin.id) && Objects.equals(account, admin.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, account);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "account=" + account +
                '}';
    }
}
