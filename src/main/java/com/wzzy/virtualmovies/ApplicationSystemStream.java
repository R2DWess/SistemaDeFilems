package com.wzzy.virtualmovies;

import com.sun.net.httpserver.HttpServer;
import com.wzzy.virtualmovies.usuarios.cadastrar.handler.CadastrarUserHandler;
import com.wzzy.virtualmovies.usuarios.cadastrar.services.CadastrarUserService;
import com.wzzy.virtualmovies.movie.service.MoviesService;
import com.wzzy.virtualmovies.movie.handler.MoviesHandler;
import com.wzzy.virtualmovies.usuarios.util.JpaUtil;

import javax.persistence.EntityManager;
import java.net.InetSocketAddress;

public class ApplicationSystemStream {
    public static void main(String[] args) throws Exception {
        EntityManager em = JpaUtil.getEntityManager();

        MoviesService moviesService = new MoviesService(em);
        CadastrarUserService cadastrarUserService = new CadastrarUserService(em);

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/movies", new MoviesHandler(moviesService));
        server.createContext("/users", new CadastrarUserHandler(cadastrarUserService));

        server.setExecutor(null);
        server.start();

        System.out.println("Server started on port 8080");

        // Keep the server running
        while (true) {
            Thread.sleep(60 * 60 * 1000); // Sleep for an hour
        }
    }
}
