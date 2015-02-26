package com.tao;

public enum EnumExample {
    Apple("my name is Jack"), Orange("My name is Tom");

    String description;

    EnumExample(String description) {
        this.description = description;
    }
}
