package cl.gerardomascayano.appfiestas.model;

/**
 * Created by Gerardo on 01-03-2018.
 */

public class Account {

    private String email;
    private String name;

    public Account(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
