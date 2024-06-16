package com.wzzy.virtualmovies;


import com.sun.net.httpserver.HttpServer;
import com.wzzy.virtualmovies.movie.controller.MoviesHandler;
import com.wzzy.virtualmovies.usuarios.login.repository.LoginUserRepository;
import com.wzzy.virtualmovies.usuarios.util.JpaUtil;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.net.InetSocketAddress;

public class ApplicationSystemStream {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        LoginUserRepository userRepository = new LoginUserRepository(em);
        boolean exists = userRepository.existsBySocialname("exampleSocialName");
        System.out.println("Exists: " + exists);
        JpaUtil.close();
    }
}
