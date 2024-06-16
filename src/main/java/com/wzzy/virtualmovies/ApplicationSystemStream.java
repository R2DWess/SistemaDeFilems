package com.wzzy.virtualmovies;

import com.sun.net.httpserver.HttpServer;
import com.wzzy.virtualmovies.movie.controller.MoviesHandler;
import com.wzzy.virtualmovies.movie.service.MoviesService;
import com.wzzy.virtualmovies.usuarios.cadastrar.controller.CadastrarUserHandler;
import com.wzzy.virtualmovies.usuarios.login.controller.LoginUserHandler;
import com.wzzy.virtualmovies.usuarios.util.JpaUtil;
import com.wzzy.virtualmovies.usuarios.login.services.LoginUserService;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.net.InetSocketAddress;

public class ApplicationSystemStream {

    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        MoviesService moviesService = new MoviesService(em);
        LoginUserService loginUserService = new LoginUserService(em);

        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

            // Configure context paths and handlers
            server.createContext("/movies", new MoviesHandler(moviesService));
            server.createContext("/users", new CadastrarUserHandler(em));
            server.createContext("/login", new LoginUserHandler(loginUserService));

            server.setExecutor(null); // Creates a default executor
            server.start();

            System.out.println("Server started on port 8080");

            // Keep the application running
            while (true) {
                Thread.sleep(60 * 60 * 1000); // Sleep for an hour
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            JpaUtil.close();
        }
    }
}
