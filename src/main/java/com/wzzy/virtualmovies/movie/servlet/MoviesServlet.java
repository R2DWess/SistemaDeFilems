//package com.wzzy.virtualmovies.movie.servlet;
//
//import com.wzzy.virtualmovies.database.DatabaseUtil;
//import com.wzzy.virtualmovies.movie.Movie;
//import com.wzzy.virtualmovies.movie.service.MoviesService;
//import com.wzzy.virtualmovies.usuarios.cadastrar.model.CadastrarUserModel;
//import com.wzzy.virtualmovies.usuarios.cadastrar.services.CadastrarUserService;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//import java.util.UUID;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//@WebServlet("/movies/*")
//public class MoviesServlet extends HttpServlet {
//
//    private MoviesService moviesService;
//    private CadastrarUserService cadastrarUserService;
//
//    @Override
//    public void init() throws ServletException {
//        DatabaseUtil databaseUtil = new DatabaseUtil(); // Cria a instância do DatabaseUtil
//        moviesService = new MoviesService(databaseUtil); // Passa a instância para o MoviesService
//        cadastrarUserService = new CadastrarUserService(databaseUtil); // Passa a instância para o CadastrarUserService
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String pathInfo = request.getPathInfo();
//        if (pathInfo == null || pathInfo.equals("/")) {
//            List<Movie> movies = moviesService.findAll();
//            writeResponse(response, movies, HttpServletResponse.SC_OK);
//        } else {
//            String[] splits = pathInfo.split("/");
//            try {
//                switch (splits[1]) {
//                    case "category":
//                        List<Movie> movies = moviesService.findByCategory(splits[2]);
//                        writeResponse(response, movies, HttpServletResponse.SC_OK);
//                        break;
//                    case "id":
//                        Movie movie = moviesService.findById(UUID.fromString(splits[2]));
//                        writeResponse(response, movie, HttpServletResponse.SC_OK);
//                        break;
//                    case "user":
//                        CadastrarUserModel user = cadastrarUserService.findById(UUID.fromString(splits[3])).orElse(null);
//                        if (user != null) {
//                            if (splits[2].equals("list")) {
//                                writeResponse(response, user.getMovieList(), HttpServletResponse.SC_OK);
//                            } else if (splits[2].equals("favorites")) {
//                                writeResponse(response, user.getFavoriteMovies(), HttpServletResponse.SC_OK);
//                            }
//                        } else {
//                            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//                        }
//                        break;
//                    case "title":
//                        movies = moviesService.findByTitulo(splits[2]);
//                        writeResponse(response, movies, HttpServletResponse.SC_OK);
//                        break;
//                    case "generos":
//                        List<String> genres = moviesService.findAllGenres();
//                        writeResponse(response, genres, HttpServletResponse.SC_OK);
//                        break;
//                    default:
//                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//                }
//            } catch (Exception e) {
//                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            }
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String pathInfo = request.getPathInfo();
//        if (pathInfo != null) {
//            String[] splits = pathInfo.split("/");
//            if (splits.length == 3 && splits[1].equals("favorite")) {
//                String socialName = splits[1];
//                String titulo = splits[2];
//                boolean result = moviesService.favoriteMovieBySocialNameAndTitle(socialName, titulo);
//                if (result) {
//                    response.getWriter().write("Movie favorited successfully");
//                    response.setStatus(HttpServletResponse.SC_OK);
//                } else {
//                    response.getWriter().write("User or Movie not found");
//                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//                }
//            }
//        } else {
//            try {
//                Movie movie = objectMapper.readValue(request.getInputStream(), Movie.class);
//                movie = moviesService.save(movie);
//                writeResponse(response, movie, HttpServletResponse.SC_CREATED);
//            } catch (Exception e) {
//                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            }
//        }
//    }
//
//    @Override
//    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String pathInfo = request.getPathInfo();
//        if (pathInfo != null) {
//            String[] splits = pathInfo.split("/");
//            if (splits.length == 3 && splits[1].equals("title")) {
//                moviesService.deleteByTitulo(splits[2]);
//                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
//            }
//        }
//    }
//
//    private void writeResponse(HttpServletResponse response, Object obj, int status) throws IOException {
//        response.setContentType("application/json");
//        response.getWriter().write(objectMapper.writeValueAsString(obj));
//        response.setStatus(status);
//    }
//}
