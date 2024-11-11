package org.example.Utils;

public enum Platform {

    WEB_DESKTOP("webDesktop"),
    WEB_PHONE("webPhone");

    private String value;

    Platform(String value){this.value = value;}

    @Override
    public String toString() {
        return value;
    }
}
