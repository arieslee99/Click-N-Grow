package persistence;

import model.SavedItems;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

//Writes garden objects into JSON representation
public class JsonWriter {
    private PrintWriter writer;
    private String destination;

    //EFFECTS: constructs a json writer
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    //MODIFIES: this
    //EFFECTS: opens destination file;
    // throws FileNotFoundException if destination file can't be opened
    public void openFile() throws FileNotFoundException {
        writer = new PrintWriter(destination);
    }

    //MODIFIES: this
    //EFFECTS: writes object into JSON representation
    public void write(SavedItems savedItems) {
        JSONObject json = savedItems.toJson();
        saveToFile(json.toString());
    }

    //EFFECTS: closes destination file
    public void close() {
        writer.close();
    }

    //MODIFIES: this
    //EFFECTS: saves string to file
    private void saveToFile(String json) {
        writer.print(json);
    }

}
