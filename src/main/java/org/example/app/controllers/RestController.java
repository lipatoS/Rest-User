package org.example.app.controllers;

import com.google.gson.Gson;
import org.example.app.domain.User;
import org.example.app.services.UserService;
import org.example.app.services.UserServiceImpl;
import org.example.app.utils.Response;
import org.example.app.utils.Status;

import static spark.Spark.*;
import static spark.Spark.delete;

public class RestController {public void runApp() {

    final UserService service = new UserServiceImpl();

    post("/users", (request, response) -> {
        response.type("application/json");

        User user = new Gson().fromJson(request.body(), User.class);
        service.addUser(user);

        return new Gson().toJson(new Response(Status.SUCCESS));
    });

    get("/users", (request, response) -> {
        response.type("application/json");

        return new Gson().toJson(new Response(Status.SUCCESS,
                new Gson().toJsonTree(service.getUsers())));
    });

    get("/users/:id", (request, response) -> {
        response.type("application/json");

        return new Gson().toJson(new Response(Status.SUCCESS,
                new Gson().toJsonTree(service.getUser(
                        Long.valueOf(request.params(":id"))))));
    });

    put("/users/:id", (request, response) -> {
        response.type("application/json");

        User user = new Gson().fromJson(request.body(), User.class);
        User userEdit = service.editUser(user);

        if (userEdit != null) {
            return new Gson()
                    .toJson(new Response(Status.SUCCESS,
                            new Gson().toJsonTree(userEdit)));
        } else {
            return new Gson().toJson(new Response(Status.ERROR,
                    new Gson().toJson("User not found or editing error.")));
        }
    });

    delete("/users/:id", (request, response) -> {
        response.type("application/json");

        service.deleteUser(Long.valueOf(request.params(":id")));
        return new Gson().toJson(new Response(Status.SUCCESS, "Deleted successfully."));
    });

}
}
