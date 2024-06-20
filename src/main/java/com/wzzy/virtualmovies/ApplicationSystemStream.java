package com.wzzy.virtualmovies;

import java.net.InetSocketAddress;

import javax.persistence.EntityManager;

import com.sun.net.httpserver.HttpServer;
import com.wzzy.virtualmovies.Cors.CORSFilter;
import com.wzzy.virtualmovies.movie.handler.MoviesHandler;
import com.wzzy.virtualmovies.movie.service.MoviesService;
import com.wzzy.virtualmovies.usuarios.cadastrar.handler.CadastrarUserHandler;
import com.wzzy.virtualmovies.usuarios.cadastrar.services.CadastrarUserService;
import com.wzzy.virtualmovies.usuarios.login.handler.LoginUserHandler;
import com.wzzy.virtualmovies.usuarios.login.services.LoginUserService;
import com.wzzy.virtualmovies.usuarios.util.JpaUtil;

public class ApplicationSystemStream {
    public static void main(String[] args) throws Exception {
        EntityManager em = JpaUtil.getEntityManager();

        MoviesService moviesService = new MoviesService(em);
        CadastrarUserService cadastrarUserService = new CadastrarUserService(em);
        LoginUserService loginUserService = new LoginUserService(em);

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/movies", new CORSFilter(new MoviesHandler(moviesService)));
        server.createContext("/users", new CORSFilter(new CadastrarUserHandler(cadastrarUserService)));
        server.createContext("/login", new CORSFilter(new LoginUserHandler(loginUserService)));

        server.setExecutor(null);
        server.start();

        System.out.println("Server started on port 8080");

        while (true) {
            Thread.sleep(60 * 60 * 1000);
        }
    }
}
