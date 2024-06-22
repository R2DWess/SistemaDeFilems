package com.wzzy.virtualmovies.Cors;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.List;

public class CORSFilter extends Filter {
    private final HttpHandler handler;

    public CORSFilter(HttpHandler handler) {
        this.handler = handler;
    }

    @Override
    public void doFilter(HttpExchange exchange, Chain chain) throws IOException {
        List<String> origin = exchange.getRequestHeaders().get("Origin");
        if (origin != null && !origin.isEmpty()) {
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", origin.get(0));
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type,Authorization");
            exchange.getResponseHeaders().add("Access-Control-Allow-Credentials", "true");
        }
        if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(204, -1);
            return;
        }
        chain.doFilter(exchange);
    }

    @Override
    public String description() {
        return "Handles CORS requests by adding the necessary headers";
    }
}
