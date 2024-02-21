package co.edu.uptc.util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import co.edu.uptc.model.Category;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.MultimediaContent;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;
import com.google.gson.*;

public class CategoryManagement {
    public void addCategory(Category category) {

        JsonObject atributes = new JsonObject();

        atributes.addProperty("name", category.getName());

        JsonArray movies = new JsonArray();
        JsonArray series = new JsonArray();

        atributes.add("movies", movies);
        atributes.add("series", series);

        JsonObject categoryObj = new JsonObject();
        categoryObj.add("category", atributes);

        // Reads if JSON exists
        @SuppressWarnings("deprecation")
        JsonParser JsonParser = new JsonParser();
        try (FileReader reader = new FileReader("src/main/java/co/edu/uptc/persistence/files/administratorFile/categories.json")) {
            @SuppressWarnings("deprecation")
            Object objAux = JsonParser.parse(reader);
            JsonObject currentJSON = (JsonObject) objAux;
            JsonArray categories = (JsonArray) currentJSON.get("categories");
            categories.add(categoryObj);
            currentJSON.add("categories", categories);
            try (FileWriter file = new FileWriter("src/main/java/co/edu/uptc/persistence/files/administratorFile/categories.json")) {
                file.write(new Gson().toJson(currentJSON));
                file.flush();
            } catch (IOException e) {
                e.printStackTrace(System.err);
            }

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public void updateCategories(ArrayList<Category> categoriesArray) {
        JsonArray categories = new JsonArray();

        for (Category c : categoriesArray) {
            JsonObject atributes = new JsonObject();

            atributes.addProperty("name", c.getName());

            JsonArray movies = new JsonArray();

            for (Movie movieAux : c.getMovies()) {
                JsonObject movieAtributes = new JsonObject();
                movieAtributes.addProperty("id", (long) movieAux.getId());
                movieAtributes.addProperty("duration", (long) movieAux.getDuration());
                movieAtributes.addProperty("name", movieAux.getName());
                movieAtributes.addProperty("description", movieAux.getDescription());
                movieAtributes.addProperty("author", movieAux.getAuthor());

                JsonObject movieObj = new JsonObject();
                movieObj.add("movie", movieAtributes);
                movies.add(movieObj);
            }

            JsonArray series = new JsonArray();

            for (Serie serieAux : c.getSeries()) {
                JsonObject serieAtributes = new JsonObject();

                serieAtributes.addProperty("id", serieAux.getId());
                serieAtributes.addProperty("name", serieAux.getName());
                serieAtributes.addProperty("author", serieAux.getAuthor());
                serieAtributes.addProperty("description", serieAux.getDescription());

                JsonArray seasonsList = new JsonArray();

                for (Season i : serieAux.getSeasons()) {
                    JsonArray chapterList = new JsonArray();

                    JsonObject seasonAtributes = new JsonObject();
                    seasonAtributes.addProperty("seasonName", i.getSeasonName());

                    for (MultimediaContent m : i.getSeasonMultimediaContent()) {
                        JsonObject chapterAtributes = new JsonObject();
                        chapterAtributes.addProperty("duration", (long) m.getDuration());
                        chapterAtributes.addProperty("name", m.getName());
                        chapterAtributes.addProperty("description", m.getDescription());

                        JsonObject chapterObject = new JsonObject();
                        chapterObject.add("chapter", chapterAtributes);
                        chapterList.add(chapterObject);
                    }

                    seasonAtributes.add("chapters", chapterList);
                    JsonObject seasonObject = new JsonObject();
                    seasonObject.add("season", seasonAtributes);
                    seasonsList.add(seasonObject);
                }

                serieAtributes.add("seasons", seasonsList);
                JsonObject serieObj = new JsonObject();
                serieObj.add("serie", serieAtributes);
                series.add(serieObj);
            }

            atributes.add("movies", movies);
            atributes.add("series", series);

            JsonObject categoryObj = new JsonObject();
            categoryObj.add("category", atributes);
            categories.add(categoryObj);

        }

        // Reads if JSON exists
        @SuppressWarnings("deprecation")
        JsonParser JsonParser = new JsonParser();
        try (FileReader reader = new FileReader("src/main/java/co/edu/uptc/persistence/files/administratorFile/categories.json")) {
            @SuppressWarnings("deprecation")
            Object objAux = JsonParser.parse(reader);
            JsonObject currentJSON = (JsonObject) objAux;

            currentJSON.add("categories", categories);

            try (FileWriter file = new FileWriter("src/main/java/co/edu/uptc/persistence/files/administratorFile/categories.json")) {
                file.write(new Gson().toJson(currentJSON));
                file.flush();
            } catch (IOException e) {
                e.printStackTrace(System.err);
            }

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

    }

    public ArrayList<Category> getCategories() {
        ArrayList<Category> categoriesArray = new ArrayList<>();
        @SuppressWarnings("deprecation")
        JsonParser JsonParser = new JsonParser();
        JsonObject JsonObject = new JsonObject();

        try (FileReader reader = new FileReader("src/main/java/co/edu/uptc/persistence/files/administratorFile/categories.json")) {
            @SuppressWarnings("deprecation")
            Object obj = JsonParser.parse(reader);
            JsonObject = (JsonObject) obj;

        } catch (Exception e) {
            System.out.println("Sumn went wrong");
        }

        JsonArray categories = (JsonArray) JsonObject.get("categories");
        for (JsonElement c : categories) {
            JsonObject catObj = c.getAsJsonObject().get("category").getAsJsonObject();
            String name = catObj.get("name").getAsString();
            JsonArray movies = catObj.get("movies").getAsJsonArray();
            ArrayList<Movie> moviesArray = new ArrayList<>();

            // Movies
            for (JsonElement movie : movies) {
                JsonObject movieObjAux = movie.getAsJsonObject().get("movie").getAsJsonObject();
                int idMovie =  movieObjAux.get("id").getAsInt();
                int durationMovie = movieObjAux.get("duration").getAsInt();
                String nameMovie = movieObjAux.get("name").getAsString();
                String authorMovie = movieObjAux.get("author").getAsString();
                String descriptionMovie = movieObjAux.get("description").getAsString();
                moviesArray.add(new Movie(idMovie, nameMovie, authorMovie, descriptionMovie, durationMovie));
            }

            ArrayList<Serie> seriesArray = new ArrayList<>();
            JsonArray series = (JsonArray) catObj.get("series");
            for (JsonElement serie : series) {
                JsonObject serieObjectAux = serie.getAsJsonObject().get("serie").getAsJsonObject();
                JsonArray seasonsList = (JsonArray) serieObjectAux.get("seasons");
                ArrayList<Season> seasons = new ArrayList<>();
                for (JsonElement season : seasonsList) {
                    JsonObject seasonObjectAux = season.getAsJsonObject().get("season").getAsJsonObject();
                    JsonArray chaptersList = (JsonArray) seasonObjectAux.get("chapters");
                    ArrayList<MultimediaContent> chapters = new ArrayList<>();

                    for (JsonElement chapter : chaptersList) {
                        JsonObject chapterObjectAux = chapter.getAsJsonObject().get("chapter").getAsJsonObject();
                        int durationChap = chapterObjectAux.get("duration").getAsInt();
                        String nameChap = chapterObjectAux.get("name").getAsString();
                        String descriptionChap = chapterObjectAux.get("description").getAsString();
                        chapters.add(new MultimediaContent(durationChap, nameChap,descriptionChap));
                    }
                    String nameSea = seasonObjectAux.get("seasonName").getAsString();
                    seasons.add(new Season(nameSea, chapters));
                }

                int idSerie = serieObjectAux.get("id").getAsInt();
                String nameSerie = serieObjectAux.get("name").getAsString();
                String authorSerie = serieObjectAux.get("author").getAsString();
                String descriptionSerie = serieObjectAux.get("description").getAsString();
                seriesArray.add(new Serie(idSerie, nameSerie, authorSerie, descriptionSerie, seasons));

            }

            Category newCategory = new Category(name);
            newCategory.setMovies(moviesArray);
            newCategory.setSeries(seriesArray);
            categoriesArray.add(newCategory);

        }
        return categoriesArray;
    }
}

