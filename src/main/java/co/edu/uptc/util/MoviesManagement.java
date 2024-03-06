package co.edu.uptc.util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import co.edu.uptc.model.Movie;
import com.google.gson.*;

public class MoviesManagement {

    // "Type safety: The method put(Object, Object) belongs to the raw type HashMap.
    // References to generic type HashMap<K,V> should be parameterizedJava"
    public void addMovie(Movie movie) {
        JsonObject atributes = new JsonObject();

        atributes.addProperty("id", movie.getId());
        atributes.addProperty("name", movie.getName());
        atributes.addProperty("author", movie.getAuthor());
        atributes.addProperty("description", movie.getDescription());
        atributes.addProperty("duration", movie.getDuration());

        JsonObject movieObject = new JsonObject();
        movieObject.add("movie", atributes);

        // Reads if JSON exists
        @SuppressWarnings("deprecation")
        JsonParser JsonParser = new JsonParser();
        try (FileReader reader = new FileReader("src/main/java/co/edu/uptc/persistence/files/administratorFile/movies.json")) {
            @SuppressWarnings("deprecation")
            JsonObject currentJSON = JsonParser.parse(reader).getAsJsonObject();
            JsonArray moviesList = currentJSON.get("movies").getAsJsonArray();
            moviesList.add(movieObject);
            currentJSON.add("movies", moviesList);

            Gson gson = new Gson(); 
            try (FileWriter file = new FileWriter("src/main/java/co/edu/uptc/persistence/files/administratorFile/movies.json")) {
                file.write(gson.toJson(currentJSON));
                file.flush();
            } catch (IOException e) {
                e.printStackTrace(System.err);
            }

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public ArrayList<Movie> getMovies() {
        ArrayList<Movie> moviesArray = new ArrayList<>();
        @SuppressWarnings("deprecation")
        JsonParser JsonParser = new JsonParser();
        JsonObject JsonObject = new JsonObject();

        try (FileReader reader = new FileReader("src/main/java/co/edu/uptc/persistence/files/administratorFile/movies.json")) {
            @SuppressWarnings("deprecation")
            Object obj = JsonParser.parse(reader);
            JsonObject = (JsonObject) obj;
        } catch (Exception e) {
            System.out.println("Sumn went wrong");
        }

        JsonArray movies = JsonObject.get("movies").getAsJsonArray();
        for (JsonElement movie : movies) {
            JsonObject ma = movie.getAsJsonObject().get("movie").getAsJsonObject();
            int id = ma.get("id").getAsInt();
            String name = ma.get("name").getAsString();
            String author = ma.get("author").getAsString();
            String description = ma.get("description").getAsString();
            int duration = ma.get("duration").getAsInt();
            moviesArray.add(new Movie(id, name, author, description, duration));
        }
        return moviesArray;
    }

    public void updateMovie(Movie movieToUpdate, Movie movieUpdated) {
        JsonObject atributesMovieToUpdate = new JsonObject();
        atributesMovieToUpdate.addProperty("id", movieToUpdate.getId());
        atributesMovieToUpdate.addProperty("name", movieToUpdate.getName());
        atributesMovieToUpdate.addProperty("description", movieToUpdate.getDescription());
        atributesMovieToUpdate.addProperty("duration", movieToUpdate.getDuration());
        atributesMovieToUpdate.addProperty("author", movieToUpdate.getAuthor());

        JsonObject movieToUpdateObject = new JsonObject();
        movieToUpdateObject.add("movie", atributesMovieToUpdate);

        @SuppressWarnings("deprecation")
        JsonParser JsonParser = new JsonParser();
        try (FileReader reader = new FileReader("src/main/java/co/edu/uptc/persistence/files/administratorFile/movies.json")) {
            @SuppressWarnings("deprecation")
            Object objAux = JsonParser.parse(reader);
            JsonObject currentJSON = (JsonObject) objAux;

            JsonArray moviesList = (JsonArray) currentJSON.get("movies");
            moviesList.remove(movieToUpdateObject);

            JsonObject movieUpdatedAtributes = new JsonObject();

            movieUpdatedAtributes.addProperty("id", movieUpdated.getId());
            movieUpdatedAtributes.addProperty("name", movieUpdated.getName());
            movieUpdatedAtributes.addProperty("author", movieUpdated.getAuthor());
            movieUpdatedAtributes.addProperty("description", movieUpdated.getDescription());
            movieUpdatedAtributes.addProperty("duration", movieUpdated.getDuration());
            currentJSON.add("movies", moviesList);

            JsonObject movieUpdatedObject = new JsonObject();
            movieUpdatedObject.add("movie", movieUpdatedAtributes);

            moviesList.add(movieUpdatedObject);
            currentJSON.add("movies", moviesList);

            try (FileWriter file = new FileWriter("src/main/java/co/edu/uptc/persistence/files/administratorFile/movies.json")) {
                file.write(new Gson().toJson(currentJSON));
                file.flush();
            } catch (IOException e) {
                e.printStackTrace(System.err);
            }

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public void removeMovie(Movie movieToRemove) {
        JsonObject atributesMovieToRemove = new JsonObject();

        atributesMovieToRemove.addProperty("id", movieToRemove.getId());
        atributesMovieToRemove.addProperty("name", movieToRemove.getName());
        atributesMovieToRemove.addProperty("author", movieToRemove.getAuthor());
        atributesMovieToRemove.addProperty("description", movieToRemove.getDescription());
        atributesMovieToRemove.addProperty("duration", movieToRemove.getDuration());

        JsonObject movieToRemoveObject = new JsonObject();
        movieToRemoveObject.add("movie", atributesMovieToRemove);

        @SuppressWarnings("deprecation")
        JsonParser JsonParser = new JsonParser();
        try (FileReader reader = new FileReader("src/main/java/co/edu/uptc/persistence/files/administratorFile/movies.json")) {
            @SuppressWarnings("deprecation")
            Object objAux = JsonParser.parse(reader);
            JsonObject currentJSON = (JsonObject) objAux;

            JsonArray moviesList = (JsonArray) currentJSON.get("movies");
            moviesList.remove(movieToRemoveObject);

            try (FileWriter file = new FileWriter("src/main/java/co/edu/uptc/persistence/files/administratorFile/movies.json")) {
                file.write(new Gson().toJson(currentJSON));
                file.flush();
            } catch (IOException e) {
                e.printStackTrace(System.err);
            }

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
