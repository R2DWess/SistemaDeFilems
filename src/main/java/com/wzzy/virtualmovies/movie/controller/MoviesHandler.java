package com.wzzy.virtualmovies.movie.controller;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.wzzy.virtualmovies.movie.Movie;
import com.wzzy.virtualmovies.movie.service.MoviesService;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

public class MoviesHandler implements HttpHandler {
    private MoviesService moviesService;
    private Gson gson = new Gson();

    public MoviesHandler(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            String path = exchange.getRequestURI().getPath();
            String method = exchange.getRequestMethod();

            if (path.startsWith("/movies") && "GET".equals(method)) {
                handleGet(exchange, path);
            } else if (path.startsWith("/movies") && "POST".equals(method)) {
                handlePost(exchange);
            } else if (path.startsWith("/movies") && "DELETE".equals(method)) {
                handleDelete(exchange, path);
            } else {
                sendResponse(exchange, 404, "Not Found");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Adicione esta linha para logar a exceção
            sendResponse(exchange, 500, "Internal Server Error: " + e.getMessage());
        }
    }


    private void handleGet(HttpExchange exchange, String path) throws IOException {
        if ("/movies".equals(path)) {
            List<Movie> movies = moviesService.findAll();
            String jsonResponse = gson.toJson(movies);
            sendResponse(exchange, 200, jsonResponse);
        } else if (path.matches("/movies/\\w+")) {
            UUID id = UUID.fromString(path.split("/")[2]);
            Movie movie = moviesService.findById(id);
            if (movie != null) {
                sendResponse(exchange, 200, gson.toJson(movie));
            } else {
                sendResponse(exchange, 404, "Movie Not Found");
            }
        }
    }

    private void handlePost(HttpExchange exchange) throws IOException {
        InputStreamReader reader = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
        Movie movie = gson.fromJson(reader, Movie.class);
        movie = moviesService.save(movie);
        sendResponse(exchange, 201, gson.toJson(movie));
    }

    private void handleDelete(HttpExchange exchange, String path) throws IOException {
        UUID id = UUID.fromString(path.split("/")[2]);
        boolean success = moviesService.deleteById(id);
        if (success) {
            sendResponse(exchange, 204, "");
        } else {
            sendResponse(exchange, 404, "Movie Not Found");
        }
    }

    private void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(statusCode, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}
