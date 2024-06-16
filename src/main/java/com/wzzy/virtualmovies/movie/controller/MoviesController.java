//package com.wzzy.virtualmovies.movie.controller;
//
//import com.wzzy.virtualmovies.movie.Movie;
//import com.wzzy.virtualmovies.movie.service.MoviesService;
//import com.wzzy.virtualmovies.usuarios.cadastrar.model.CadastrarUserModel;
//import com.wzzy.virtualmovies.usuarios.cadastrar.services.CadastrarUserService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.UUID;
//
//@RestController
//@CrossOrigin("*")
//@RequestMapping("/movies")
//public class MoviesController {
//
//    final MoviesService moviesService;
//    final CadastrarUserService cadastrarUserService;
//
//    public MoviesController(MoviesService moviesService, CadastrarUserService cadastrarUserService) {
//        this.moviesService = moviesService;
//        this.cadastrarUserService = cadastrarUserService;
//    }
//
//    @PostMapping
//    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(moviesService.save(movie));
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Movie>> getAllMovies() {
//        return ResponseEntity.status(HttpStatus.OK).body(moviesService.findAll());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Movie> getMovieById(@PathVariable UUID id) {
//        return ResponseEntity.status(HttpStatus.OK).body(moviesService.findById(id));
//    }
//
//    @GetMapping("/category/{category}")
//    public ResponseEntity<List<Movie>> getMoviesByCategory(@PathVariable String category) {
//        return ResponseEntity.status(HttpStatus.OK).body(moviesService.findByCategory(category));
//    }
//
//    @PostMapping("/{socialName}/favorite/{titulo}")
//    public ResponseEntity<String> favoriteMovie(@PathVariable String socialName, @PathVariable String titulo) {
//        boolean result = moviesService.favoriteMovieBySocialNameAndTitle(socialName, titulo);
//        if (result) {
//            return ResponseEntity.status(HttpStatus.OK).body("Movie favorited successfully");
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or Movie not found");
//        }
//    }
//
//    @GetMapping("/{id}/watch")
//    public ResponseEntity<String> watchMovie(@PathVariable UUID id) {
//        return ResponseEntity.status(HttpStatus.OK).body(moviesService.getMovieVideoUrl(id));
//    }
//
//    @GetMapping("/user/{userId}/list")
//    public ResponseEntity<List<Movie>> getMovieList(@PathVariable UUID userId) {
//        CadastrarUserModel user = cadastrarUserService.findById(userId).orElse(null);
//        if (user != null) {
//            return ResponseEntity.status(HttpStatus.OK).body(user.getMovieList());
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//    }
//
//    @GetMapping("/user/{userId}/favorites")
//    public ResponseEntity<List<Movie>> getFavoriteMovies(@PathVariable UUID userId) {
//        CadastrarUserModel user = cadastrarUserService.findById(userId).orElse(null);
//        if (user != null) {
//            return ResponseEntity.status(HttpStatus.OK).body(user.getFavoriteMovies());
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//    }
//
//    @GetMapping("/title/{title}")
//    public ResponseEntity<List<Movie>> getMoviesByTitle(@PathVariable String title) {
//        return ResponseEntity.status(HttpStatus.OK).body(moviesService.findByTitulo(title));
//    }
//
//    @GetMapping("/generos")
//    public ResponseEntity<List<String>> getAllGenres() {
//        return ResponseEntity.status(HttpStatus.OK).body(moviesService.findAllGenres());
//    }
//
//    @DeleteMapping("/title/{title}")
//    public ResponseEntity<Void> deleteMovieByTitulo(@PathVariable String title) {
//        moviesService.deleteByTitulo(title);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//    }
//}
