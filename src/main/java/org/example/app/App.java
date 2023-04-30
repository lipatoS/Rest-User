package org.example.app;

import org.example.app.controllers.RestController;

public class App {

    public static void main(String[] args) {
        RestController controller = new RestController();
        controller.runApp();
    }
}
