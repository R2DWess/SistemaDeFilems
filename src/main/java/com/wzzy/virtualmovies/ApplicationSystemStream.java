package com.wzzy.virtualmovies;

import java.net.InetSocketAddress;
import javax.persistence.EntityManager;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpContext;
import com.wzzy.virtualmovies.Cors.CORSFilter;
import com.wzzy.virtualmovies.movie.dto.MovieDataInitializer;
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

        HttpContext moviesContext = server.createContext("/movies", new MoviesHandler(moviesService));
        moviesContext.getFilters().add(new CORSFilter(moviesContext.getHandler()));

        HttpContext usersContext = server.createContext("/users", new CadastrarUserHandler(cadastrarUserService));
        usersContext.getFilters().add(new CORSFilter(usersContext.getHandler()));

        HttpContext loginContext = server.createContext("/login", new LoginUserHandler(loginUserService));
        loginContext.getFilters().add(new CORSFilter(loginContext.getHandler()));

        server.setExecutor(null);
        server.start();

        System.out.println("Server started on port 8080");
        MovieDataInitializer dataInitializer = new MovieDataInitializer();
        dataInitializer.initialize(moviesService);

        while (true) {
            Thread.sleep(60 * 60 * 1000);
        }
    }
}
