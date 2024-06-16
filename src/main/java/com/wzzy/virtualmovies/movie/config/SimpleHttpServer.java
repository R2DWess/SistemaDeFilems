package com.wzzy.virtualmovies.movie.config;

import com.sun.net.httpserver.HttpServer;
import com.wzzy.virtualmovies.movie.controller.MoviesHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class SimpleHttpServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/movies", new MoviesHandler());
        server.setExecutor(null); // cria um executor default
        server.start();
        System.out.println("Server started on port 8080");
    }
}
