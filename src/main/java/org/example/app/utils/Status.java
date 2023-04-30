package org.example.app.utils;

public enum Status {
    SUCCESS("Success"), ERROR("Error");
    final String status;

    Status(String status) {
        this.status = status;
    }
}
