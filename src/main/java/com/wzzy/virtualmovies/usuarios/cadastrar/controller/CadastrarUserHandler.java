package com.wzzy.virtualmovies.usuarios.cadastrar.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.wzzy.virtualmovies.usuarios.cadastrar.model.CadastrarUserModel;
import com.wzzy.virtualmovies.usuarios.cadastrar.services.CadastrarUserService;
import com.wzzy.virtualmovies.usuarios.util.LocalDateAdapter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CadastrarUserHandler implements HttpHandler {
    private CadastrarUserService userService;
    private Gson gson;

    public CadastrarUserHandler(CadastrarUserService userService) {
        this.userService = userService;
        this.gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        try {
            switch (method) {
                case "GET":
                    handleGet(exchange, path);
                    break;
                case "POST":
                    handlePost(exchange, path);
                    break;
                case "DELETE":
                    handleDelete(exchange, path);
                    break;
                default:
                    sendResponse(exchange, 405, "Method Not Allowed");
            }
        } catch (Exception e) {
            sendResponse(exchange, 500, "Internal Server Error");
        }
    }

    private void handlePost(HttpExchange exchange, String path) throws IOException {
        if ("/users".equals(path)) {
            InputStreamReader reader = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
            CadastrarUserModel user = gson.fromJson(reader, CadastrarUserModel.class);
            CadastrarUserModel savedUser = userService.save(user);
            sendResponse(exchange, 201, gson.toJson(savedUser));
        }
    }

    private void handleGet(HttpExchange exchange, String path) throws IOException {
        if ("/users".equals(path)) {
            List<CadastrarUserModel> users = userService.findAll();
            sendResponse(exchange, 200, gson.toJson(users));
        } else {
            String userId = path.substring(path.lastIndexOf("/") + 1);
            Optional<CadastrarUserModel> user = userService.findById(userId.toString());
            if (user.isPresent()) {
                sendResponse(exchange, 200, gson.toJson(user.get()));
            } else {
                sendResponse(exchange, 404, "User Not Found");
            }
        }
    }

    private void handleDelete(HttpExchange exchange, String path) throws IOException {
        String userId = path.substring(path.lastIndexOf("/") + 1);
        userService.deleteById(userId.toString());
        sendResponse(exchange, 204, "");
    }

    private void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(statusCode, response.getBytes(StandardCharsets.UTF_8).length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes(StandardCharsets.UTF_8));
        }
    }
}