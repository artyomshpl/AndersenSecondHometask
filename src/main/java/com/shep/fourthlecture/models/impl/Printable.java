package com.shep.fourthlecture.models.impl;

public interface Printable {
    default void print() {
        System.out.println(this);
    }
}