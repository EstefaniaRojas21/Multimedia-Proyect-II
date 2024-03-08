package co.edu.uptc.util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import co.edu.uptc.model.Movie;
import co.edu.uptc.model.MultimediaContent;
import co.edu.uptc.model.PlayList;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;
import co.edu.uptc.model.UserRegistered;
import co.edu.uptc.model.UserSubscription;
import com.google.gson.*;

public class UserRegisteredManagement {


    public void addUser(UserRegistered user) {

        JsonObject atributes = new JsonObject();

        atributes.addProperty("id", user.getId());
        atributes.addProperty("firstName", user.getFirstName());
        atributes.addProperty("lastName", user.getLastName());
        atributes.addProperty("user", user.getUser());
        atributes.addProperty("password", user.getPassword());

        if (user.getSub() != null) {
            JsonObject sub = new JsonObject();
            sub.addProperty("name", user.getSub().getName());
            sub.addProperty("description", user.getSub().getDescription());
            sub.addProperty("duration", user.getSub().getDuration());
            sub.addProperty("price", user.getSub().getPrice());
            sub.addProperty("startTime", user.getSub().getStartTime());
            sub.addProperty("endTime", user.getSub().getEndTime());
            atributes.add("subscription", sub);
        } else {
            atributes.add("subscription", null);
        }
        JsonArray playLists = new JsonArray();

        atributes.add("playLists", playLists);

        JsonObject userObject = new JsonObject();
        userObject.add("user", atributes);

        // Reads if JSON exists
        @SuppressWarnings("deprecation")
        JsonParser JsonParser = new JsonParser();
        try (FileReader reader = new FileReader("src\\main\\java\\co\\edu\\uptc\\persistence\\files\\userFile\\users.json")) {
            @SuppressWarnings("deprecation")
            JsonObject currentJSON = JsonParser.parse(reader).getAsJsonObject();
            JsonArray userList = (JsonArray) currentJSON.get("users");
            userList.add(userObject);
            currentJSON.add("users", userList);

            try (FileWriter file = new FileWriter("src\\main\\java\\co\\edu\\uptc\\persistence\\files\\userFile\\users.json")) {
                file.write(new Gson().toJson(currentJSON));
                file.flush();
            } catch (IOException e) {
                e.printStackTrace(System.err);
            }

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public ArrayList<UserRegistered> getUsers() {
        ArrayList<UserRegistered> userArray = new ArrayList<>();
        @SuppressWarnings("deprecation")
        JsonParser JsonParser = new JsonParser();
        JsonObject JsonObject = new JsonObject();

        try (FileReader reader = new FileReader("src\\main\\java\\co\\edu\\uptc\\persistence\\files\\userFile\\users.json")) {
            @SuppressWarnings("deprecation")
            Object obj = JsonParser.parse(reader);
            JsonObject = (JsonObject) obj;

        } catch (Exception e) {
            System.out.println("Sumn went wrong");
        }

        JsonArray users = (JsonArray) JsonObject.get("users");
        for (JsonElement user : users) {
            JsonObject uo = user.getAsJsonObject().get("user").getAsJsonObject();
            long id = uo.get("id").getAsInt();
            String firstName = uo.get("firstName").getAsString();
            String lastName = uo.get("lastName").getAsString();
            String userString = uo.get("user").getAsString();
            String password = uo.get("password").getAsString();

            // PLayList--Sumn coulda went wrong
            ArrayList<PlayList> playListArray = new ArrayList<>();
            for (JsonElement playList : uo.get("playLists").getAsJsonArray()) {
                JsonObject playListObjAux = playList.getAsJsonObject().get("playList").getAsJsonObject();
                JsonArray movies = playListObjAux.get("movies").getAsJsonArray();
                ArrayList<Movie> moviesArray = new ArrayList<>();

                // Movies
                for (JsonElement movie : movies) {
                    JsonObject movieObjAux = movie.getAsJsonObject().get("movie").getAsJsonObject();

                    int idMovie = movieObjAux.get("id").getAsInt();
                    int durationMovie = movieObjAux.get("duration").getAsInt();
                    String nameMovie = movieObjAux.get("name").getAsString();
                    String authorMovie = movieObjAux.get("author").getAsString();
                    String descriptionMovie = movieObjAux.get("description").getAsString();
                    moviesArray.add(new Movie(idMovie, nameMovie, authorMovie, descriptionMovie, durationMovie));
                }

                // Series--Sum coulda went wrong
                ArrayList<Serie> seriesArray = new ArrayList<>();
                for (JsonElement serie : playListObjAux.get("series").getAsJsonArray()) {
                    JsonObject serieObjectAux = serie.getAsJsonObject().get("serie").getAsJsonObject();
                    JsonArray seasonsList = serieObjectAux.get("seasons").getAsJsonArray();
                    ArrayList<Season> seasons = new ArrayList<>();

                    for (JsonElement season : seasonsList) {
                        JsonObject seasonObjectAux = season.getAsJsonObject().get("season").getAsJsonObject();
                        JsonArray chaptersList = seasonObjectAux.get("chapters").getAsJsonArray();
                        ArrayList<MultimediaContent> chapters = new ArrayList<>();

                        for (JsonElement chapter : chaptersList) {
                            JsonObject chapterObjectAux = chapter.getAsJsonObject().get("chapter").getAsJsonObject();

                            int durationChap = chapterObjectAux.get("duration").getAsInt();
                            String nameChap = chapterObjectAux.get("name").getAsString();
                            String descriptionChap = chapterObjectAux.get("description").getAsString();


                            chapters.add(new MultimediaContent(durationChap, nameChap, descriptionChap));
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

                PlayList p = new PlayList(playListObjAux.get("name").getAsString());
                p.setMovies(moviesArray);
                p.setSeries(seriesArray);
                playListArray.add(p);
            }


            UserRegistered ur = new UserRegistered(firstName, lastName, (int) id, userString, password);
            JsonObject sub = (JsonObject) uo.get("subscription");
            if (sub != null) {
                int durationSub = sub.get("duration").getAsInt();
                String nameSub = sub.get("name").getAsString();
                String descriptionSub = sub.get("description").getAsString();
                double priceSub = sub.get("price").getAsDouble();
                int startTimeSub = sub.get("startTime").getAsInt();
                int endTimeSub = sub.get("endTime").getAsInt();
                ur.setSub(new UserSubscription(nameSub, durationSub, descriptionSub, priceSub, startTimeSub, endTimeSub));
            }

            ur.setplayList(playListArray);
            userArray.add(ur);
        }
        return userArray;
    }

    public void updateUsers(ArrayList<UserRegistered> allusers) {
        JsonArray users = new JsonArray();

        for (UserRegistered user : allusers) {
            JsonObject atributes = new JsonObject();

            atributes.addProperty("id", user.getId());
            atributes.addProperty("firstName", user.getFirstName());
            atributes.addProperty("lastName", user.getLastName());
            atributes.addProperty("user", user.getUser());
            atributes.addProperty("password", user.getPassword());


            JsonObject subAtributes = new JsonObject();
            if (user.getSub() != null) {
                subAtributes.addProperty("name", user.getSub().getName());
                subAtributes.addProperty("description", user.getSub().getDescription());
                subAtributes.addProperty("duration", user.getSub().getDuration());
                subAtributes.addProperty("price", user.getSub().getPrice());
                subAtributes.addProperty("startTime", user.getSub().getStartTime());
                subAtributes.addProperty("endTime", user.getSub().getEndTime());

                atributes.add("subscription", subAtributes);
            } else {
                atributes.add("subscription", null);
            }


            JsonArray playLists = new JsonArray();

            for (PlayList playList : user.getplayList()) {
                JsonObject playListsAtributes = new JsonObject();
                JsonArray movies = new JsonArray();

                for (Movie movieAux : playList.getMovies()) {
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

                for (Serie serieAux : playList.getSeries()) {
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

                playListsAtributes.add("movies", movies);
                playListsAtributes.add("series", series);
                playListsAtributes.addProperty("name", playList.getName());

                JsonObject playListObj = new JsonObject();
                playListObj.add("playList", playListsAtributes);
                playLists.add(playListObj);
            }
            atributes.add("playLists", playLists);

            JsonObject userObj = new JsonObject();
            userObj.add("user", atributes);
            users.add(userObj);
        }

        // Reads if JSON exists
        @SuppressWarnings("deprecation")
        JsonParser JsonParser = new JsonParser();
        try (FileReader reader = new FileReader("src\\main\\java\\co\\edu\\uptc\\persistence\\files\\userFile\\users.json")) {
            @SuppressWarnings("deprecation")
            Object objAux = JsonParser.parse(reader);
            JsonObject currentJSON = (JsonObject) objAux;

            currentJSON.add("users", users);

            try (FileWriter file = new FileWriter("src\\main\\java\\co\\edu\\uptc\\persistence\\files\\userFile\\users.json")) {
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