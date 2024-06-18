//package com.wzzy.virtualmovies.usuarios.util;
//
//import com.sun.net.httpserver.HttpServer;
//
//import java.io.IOException;
//import java.net.InetSocketAddress;
//
//public class ServerApp {
//    public static void main(String[] args) throws IOException {
//        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
//        server.createContext("/api/data", new SomeApiHandler());
//        server.setExecutor(null); // creates a default executor
//        server.start();
//    }
//}
