package cl.gerardomascayano.appfiestas.model;

import android.net.Uri;

/**
 * Created by Gerardo on 01-03-2018.
 */

public class Account {

    private String email;
    private String name;
    private String imageUrl;

    public Account(String email, String name, String imageUrl) {
        this.email = email;
        this.name = name;
        this.imageUrl = imageUrl;
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

    public String getImageUrl() {
        return imageUrl;
    }
}
