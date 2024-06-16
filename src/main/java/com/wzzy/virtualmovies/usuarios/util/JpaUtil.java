package com.wzzy.virtualmovies.usuarios.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
    private static final EntityManagerFactory EM_FACTORY;

    static {
        EM_FACTORY = Persistence.createEntityManagerFactory("systemMoviesUnit");
    }

    public static EntityManager getEntityManager() {
        return EM_FACTORY.createEntityManager();
    }

    public static void close() {
        EM_FACTORY.close();
    }
}