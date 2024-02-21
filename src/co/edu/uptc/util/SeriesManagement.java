package co.edu.uptc.util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import co.edu.uptc.model.MultimediaContent;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;
import com.google.gson.*;

public class SeriesManagement {


    public void addSerie(Serie serie) {

        JsonObject atributes = new JsonObject();

        atributes.addProperty("id", serie.getId());
        atributes.addProperty("name", serie.getName());
        atributes.addProperty("author", serie.getAuthor());
        atributes.addProperty("description", serie.getDescription());

        JsonArray seasonsList = new JsonArray();

        for (Season i : serie.getSeasons()) {
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

        atributes.add("seasons", seasonsList);

        JsonObject serieObject = new JsonObject();
        serieObject.add("serie", atributes);

        // Reads if JSON exists
        @SuppressWarnings("deprecation")
        JsonParser JsonParser = new JsonParser();
        try (FileReader reader = new FileReader("src/main/java/co/edu/uptc/persistence/files/administratorFile/series.json")) {
            @SuppressWarnings("deprecation")
            Object objAux = JsonParser.parse(reader);
            JsonObject currentJSON = (JsonObject) objAux;

            JsonArray seriesList = (JsonArray) currentJSON.get("series");

            seriesList.add(serieObject);

            currentJSON.add("series", seriesList);

            try (FileWriter file = new FileWriter("src/main/java/co/edu/uptc/persistence/files/administratorFile/series.json")) {
                file.write(new Gson().toJson(currentJSON));
                file.flush();
            } catch (IOException e) {
                e.printStackTrace(System.err);
            }

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public ArrayList<Serie> getSeries() {
        ArrayList<Serie> seriesArray = new ArrayList<>();
        @SuppressWarnings("deprecation")
        JsonParser JsonParser = new JsonParser();
        JsonObject JsonObject = new JsonObject();

        try (FileReader reader = new FileReader("src/main/java/co/edu/uptc/persistence/files/administratorFile/series.json")) {
            @SuppressWarnings("deprecation")
            Object obj = JsonParser.parse(reader);
            JsonObject = (JsonObject) obj;
        } catch (Exception e) {
            System.out.println("Sumn went wrong");
        }

        JsonArray series = (JsonArray) JsonObject.get("series");
        for (JsonElement serie : series) {
            JsonObject serieObjectAux = serie.getAsJsonObject().get("serie").getAsJsonObject();
            JsonArray seasonsList = serieObjectAux.get("seasons").getAsJsonArray();
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
                    chapters.add(new MultimediaContent((int) durationChap, nameChap, descriptionChap));
                }

                String nameSea = seasonObjectAux.get("seasonName").getAsString();
                seasons.add(new Season(nameSea, chapters));
            }

            int idSerie =  serieObjectAux.get("id").getAsInt();
            String nameSerie = serieObjectAux.get("name").getAsString();
            String authorSerie = serieObjectAux.get("author").getAsString();
            String descriptionSerie = serieObjectAux.get("description").getAsString();
            seriesArray.add(new Serie(idSerie, nameSerie, authorSerie, descriptionSerie, seasons));

        }

        return seriesArray;
    }

    public void updateSerie(ArrayList<Serie> seriesArray) {
        JsonArray series = new JsonArray();
        for (Serie serieAux : seriesArray) {
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

        // Reads if JSON exists
        @SuppressWarnings("deprecation")
        JsonParser JsonParser = new JsonParser();
        try (FileReader reader = new FileReader("src/main/java/co/edu/uptc/persistence/files/administratorFile/series.json")) {
            @SuppressWarnings("deprecation")
            Object objAux = JsonParser.parse(reader);
            JsonObject currentJSON = (JsonObject) objAux;

            currentJSON.add("series", series);

            try (FileWriter file = new FileWriter("src/main/java/co/edu/uptc/persistence/files/administratorFile/series.json")) {
                file.write(new Gson().toJson(currentJSON));
                file.flush();
            } catch (IOException e) {
                e.printStackTrace(System.err);
            }

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public void removeSerie(Serie serieToRemove) {
        
        @SuppressWarnings("deprecation")
        JsonParser JsonParser = new JsonParser();
        try (FileReader reader = new FileReader("src/main/java/co/edu/uptc/persistence/files/administratorFile/series.json")) {
            @SuppressWarnings("deprecation")
            Object objAux = JsonParser.parse(reader);
            JsonObject currentJSON = (JsonObject) objAux;

            JsonArray seriesList = (JsonArray) currentJSON.get("series");
            Iterator<JsonElement> iterator = seriesList.iterator();

            while (iterator.hasNext()) {
                Object s = iterator.next();
                JsonObject sObj = (JsonObject) s;
                JsonObject sObjAux = (JsonObject) sObj.get("serie");

                if (sObjAux.get("id").getAsInt() == serieToRemove.getId()) {
                    iterator.remove(); 
                    break;
                }
            }

            try (FileWriter file = new FileWriter("src/main/java/co/edu/uptc/persistence/files/administratorFile/series.json")) {
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