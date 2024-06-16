package com.wzzy.virtualmovies.usuarios.cadastrar.controller;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.wzzy.virtualmovies.usuarios.cadastrar.model.CadastrarUserModel;
import com.wzzy.virtualmovies.usuarios.cadastrar.services.CadastrarUserService;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CadastrarUserHandler implements HttpHandler {
    private CadastrarUserService userService = new CadastrarUserService(); // Implemente essa classe conforme sua lógica de negócios
    private Gson gson = new Gson();

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

    private void handleGet(HttpExchange exchange, String path) throws IOException {
        if ("/users".equals(path)) {
            List<CadastrarUserModel> users = userService.findAll();
            sendResponse(exchange, 200, gson.toJson(users));
        } else if (path.matches("/users/\\w+")) {
            UUID id = UUID.fromString(path.split("/")[2]);
            Optional<CadastrarUserModel> user = userService.findById(id);
            if (user != null) {
                sendResponse(exchange, 200, gson.toJson(user));
            } else {
                sendResponse(exchange, 404, "User Not Found");
            }
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

    private void handleDelete(HttpExchange exchange, String path) throws IOException {
        if (path.matches("/users/\\w+")) {
            UUID id = UUID.fromString(path.split("/")[2]); // Extrai o UUID do path
            boolean success = userService.deleteById(id); // Chama deleteById para tentar remover o usuário
            if (success) {
                sendResponse(exchange, 204, ""); // 204 No Content: O usuário foi deletado com sucesso
            } else {
                sendResponse(exchange, 404, "User Not Found"); // 404 Not Found: O usuário não foi encontrado
            }
        }
    }


    private void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(statusCode, response.getBytes(StandardCharsets.UTF_8).length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes(StandardCharsets.UTF_8));
        }
    }
}
