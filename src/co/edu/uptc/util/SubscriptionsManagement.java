package co.edu.uptc.util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import co.edu.uptc.model.Subscription;
import com.google.gson.*;

public class SubscriptionsManagement {

    public void addSubscription(Subscription sub) {

        JsonObject atributes = new JsonObject();

        atributes.addProperty("name", sub.getName());
        atributes.addProperty("description", sub.getDescription());
        atributes.addProperty("price",(double) sub.getPrice());
        atributes.addProperty("duration", sub.getDuration());

        JsonObject subObject = new JsonObject();
        subObject.add("subscription", atributes);

        @SuppressWarnings("deprecation")
        JsonParser JsonParser = new JsonParser();
        try (FileReader reader = new FileReader("src/main/java/co/edu/uptc/persistence/files/administratorFile/subscriptions.json")) {
            @SuppressWarnings("deprecation")
            Object objAux = JsonParser.parse(reader);
            JsonObject currentJSON = (JsonObject) objAux;
            JsonArray subscriptionsList = (JsonArray) currentJSON.get("subscriptions");
            subscriptionsList.add(subObject);
            currentJSON.add("subscriptions", subscriptionsList);
            try (FileWriter file = new FileWriter("src/main/java/co/edu/uptc/persistence/files/administratorFile/subscriptions.json")) {
                file.write(new Gson().toJson(currentJSON));
                file.flush();
            } catch (IOException e) {
                e.printStackTrace(System.err);
            }

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public ArrayList<Subscription> getSubscriptions() {
        ArrayList<Subscription> subsArray = new ArrayList<>();
        @SuppressWarnings("deprecation")
        JsonParser JsonParser = new JsonParser();
        JsonObject JsonObject = new JsonObject();

        try (FileReader reader = new FileReader("src/main/java/co/edu/uptc/persistence/files/administratorFile/subscriptions.json")) {
            @SuppressWarnings("deprecation")
            Object obj = JsonParser.parse(reader);
            JsonObject = (JsonObject) obj;

        } catch (Exception e) {
            System.out.println("Sumn went wrong");
        }

        JsonArray subs = (JsonArray) JsonObject.get("subscriptions");
        for (JsonElement sub : subs) {
            JsonObject so = sub.getAsJsonObject().get("subscription").getAsJsonObject();
            int duration = so.get("duration").getAsInt();
            String name = so.get("name").getAsString();
            String description = so.get("description").getAsString();
            double price = so.get("price").getAsDouble();
            subsArray.add(new Subscription(name, duration, description, price));
        }
        return subsArray;
    }

    public void updateSubscription(Subscription subToUpdate, Subscription subUpdated) {
        JsonObject atributessubToUpdate = new JsonObject();
        atributessubToUpdate.addProperty("duration", subToUpdate.getDuration());
        atributessubToUpdate.addProperty("name", subToUpdate.getName());
        atributessubToUpdate.addProperty("description", subToUpdate.getDescription());
        atributessubToUpdate.addProperty("price", subToUpdate.getPrice());

        JsonObject subToUpdateObject = new JsonObject();
        subToUpdateObject.add("subscription", atributessubToUpdate);

        @SuppressWarnings("deprecation")
        JsonParser JsonParser = new JsonParser();
        try (FileReader reader = new FileReader("src/main/java/co/edu/uptc/persistence/files/administratorFile/subscriptions.json")) {
            @SuppressWarnings("deprecation")
            Object objAux = JsonParser.parse(reader);
            JsonObject currentJSON = (JsonObject) objAux;

            JsonArray subsList = (JsonArray) currentJSON.get("subscriptions");
            subsList.remove(subToUpdateObject);

            JsonObject subUpdatedAtributes = new JsonObject();

            subUpdatedAtributes.addProperty("price", subUpdated.getPrice());
            subUpdatedAtributes.addProperty("name", subUpdated.getName());
            subUpdatedAtributes.addProperty("description", subUpdated.getDescription());
            subUpdatedAtributes.addProperty("duration", subUpdated.getDuration());
            currentJSON.add("subscriptions", subsList);

            JsonObject subUpdatedObject = new JsonObject();
            subUpdatedObject.add("subscription", subUpdatedAtributes);

            subsList.add(subUpdatedObject);
            currentJSON.add("subscriptions", subsList);

            try (FileWriter file = new FileWriter("src/main/java/co/edu/uptc/persistence/files/administratorFile/subscriptions.json")) {
                file.write(new Gson().toJson(currentJSON));
                file.flush();
            } catch (IOException e) {
                e.printStackTrace(System.err);
            }

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public void removeSubscription(Subscription subToRemove){
        JsonObject atributessubToRemove = new JsonObject();

        atributessubToRemove.addProperty("duration", (long) subToRemove.getDuration());
        atributessubToRemove.addProperty("name", subToRemove.getName());
        atributessubToRemove.addProperty("description", subToRemove.getDescription());
        atributessubToRemove.addProperty("price", subToRemove.getPrice());

        JsonObject subToRemoveObject = new JsonObject();
        subToRemoveObject.add("subscription", atributessubToRemove);

        @SuppressWarnings("deprecation")
        JsonParser JsonParser = new JsonParser();
        try (FileReader reader = new FileReader("src/main/java/co/edu/uptc/persistence/files/administratorFile/subscriptions.json")) {
            @SuppressWarnings("deprecation")
            Object objAux = JsonParser.parse(reader);
            JsonObject currentJSON = (JsonObject) objAux;

            JsonArray subsList = (JsonArray) currentJSON.get("subscriptions");
            subsList.remove(subToRemoveObject);

            currentJSON.add("subscriptions", subsList);

            try (FileWriter file = new FileWriter("src/main/java/co/edu/uptc/persistence/files/administratorFile/subscriptions.json")) {
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
