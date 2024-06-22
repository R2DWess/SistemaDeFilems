package com.wzzy.virtualmovies.movie.handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.wzzy.virtualmovies.movie.Movie;
import com.wzzy.virtualmovies.movie.service.MoviesService;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class MoviesHandler implements HttpHandler {
    private final MoviesService moviesService;
    private final Gson gson = new Gson();

    public MoviesHandler(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        String[] segments = path.split("/");

        try {
            switch (method) {
                case "GET":
                    if (segments.length == 3) {
                        handleGetById(exchange, Long.parseLong(segments[2]));
                    } else {
                        handleGetAll(exchange);
                    }
                    break;
                case "POST":
                    handlePost(exchange);
                    break;
                case "PUT":
                    if (segments.length == 3) {
                        handlePut(exchange, Long.parseLong(segments[2]));
                    }
                    break;
                case "DELETE":
                    if (segments.length == 3) {
                        handleDelete(exchange, Long.parseLong(segments[2]));
                    }
                    break;
                default:
                    exchange.sendResponseHeaders(405, -1);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            exchange.sendResponseHeaders(500, -1);
        }
    }

    private void handleGetAll(HttpExchange exchange) throws IOException {
        List<Movie> movies = moviesService.getAllMovies();
        String response = gson.toJson(movies);
        sendResponse(exchange, response, 200);
    }

    private void handleGetById(HttpExchange exchange, Long id) throws IOException {
        Movie movie = moviesService.getMovieById(id);
        if (movie != null) {
            String response = gson.toJson(movie);
            sendResponse(exchange, response, 200);
        } else {
            exchange.sendResponseHeaders(404, -1);
        }
    }

    private void handlePost(HttpExchange exchange) throws IOException {
        Movie movie = gson.fromJson(new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8), Movie.class);
        Movie savedMovie = moviesService.save(movie);
        String response = gson.toJson(savedMovie);
        sendResponse(exchange, response, 201);
    }

    private void handlePut(HttpExchange exchange, Long id) throws IOException {
        Movie updatedMovie = gson.fromJson(new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8), Movie.class);
        Movie savedMovie = moviesService.updateById(id, updatedMovie);
        if (savedMovie != null) {
            String response = gson.toJson(savedMovie);
            sendResponse(exchange, response, 200);
        } else {
            exchange.sendResponseHeaders(404, -1);
        }
    }

    private void handleDelete(HttpExchange exchange, Long id) throws IOException {
        boolean success = moviesService.deleteById(id);
        if (success) {
            exchange.sendResponseHeaders(204, -1);
        } else {
            exchange.sendResponseHeaders(404, -1);
        }
    }

    private void sendResponse(HttpExchange exchange, String response, int statusCode) throws IOException {
        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(statusCode, bytes.length);
        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();
    }
}
