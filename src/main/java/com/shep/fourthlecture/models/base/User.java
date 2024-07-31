package com.shep.fourthlecture.models.base;

import com.shep.fourthlecture.annotations.NotNull;
import com.shep.fourthlecture.models.impl.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class User implements Identifiable, Printable {
    @NotNull
    private String id;
    private String role;
    private String name;

    public User(String id, String role, String name) {
        this.id = id;
        this.role = role;
        this.name = name;
    }

    public User(String id, String role) {
        this.id = id;
        this.role = role;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public void printRole() {
        System.out.println("Role: " + role);
    }

}



