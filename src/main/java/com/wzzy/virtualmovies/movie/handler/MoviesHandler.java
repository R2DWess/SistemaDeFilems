package com.wzzy.virtualmovies.movie.handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.wzzy.virtualmovies.movie.service.MoviesService;
import com.wzzy.virtualmovies.movie.Movie;

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
                        handleGetByTitulo(exchange, segments[2]);
                    } else {
                        handleGetAll(exchange);
                    }
                    break;
                case "POST":
                    handlePost(exchange);
                    break;
                case "PUT":
                    if (segments.length == 3) {
                        handlePut(exchange, segments[2]);
                    }
                    break;
                case "DELETE":
                    if (segments.length == 3) {
                        handleDelete(exchange, segments[2]);
                    }
                    break;
                default:
                    exchange.sendResponseHeaders(405, -1); // Método não permitido
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            exchange.sendResponseHeaders(500, -1); // Erro interno do servidor
        }
    }

    private void handleGetAll(HttpExchange exchange) throws IOException {
        List<Movie> movies = moviesService.getAllMovies();
        String response = gson.toJson(movies);
        sendResponse(exchange, response, 200);
    }

    private void handleGetByTitulo(HttpExchange exchange, String titulo) throws IOException {
        Movie movie = moviesService.getMovieByTitulo(titulo);
        if (movie != null) {
            String response = gson.toJson(movie);
            sendResponse(exchange, response, 200);
        } else {
            exchange.sendResponseHeaders(404, -1); // Não encontrado
        }
    }

    private void handlePost(HttpExchange exchange) throws IOException {
        Movie movie = gson.fromJson(new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8), Movie.class);
        moviesService.save(movie);
        exchange.sendResponseHeaders(201, -1); // Criado
    }

    private void handlePut(HttpExchange exchange, String titulo) throws IOException {
        Movie updatedMovie = gson.fromJson(new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8), Movie.class);
        boolean success = moviesService.updateByTitulo(titulo, updatedMovie);
        if (success) {
            exchange.sendResponseHeaders(204, -1); // Sem conteúdo
        } else {
            exchange.sendResponseHeaders(404, -1); // Não encontrado
        }
    }

    private void handleDelete(HttpExchange exchange, String titulo) throws IOException {
        boolean success = moviesService.deleteByTitulo(titulo);
        if (success) {
            exchange.sendResponseHeaders(204, -1); // Sem conteúdo
        } else {
            exchange.sendResponseHeaders(404, -1); // Não encontrado
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
