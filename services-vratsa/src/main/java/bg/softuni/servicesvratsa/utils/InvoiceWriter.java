package bg.softuni.servicesvratsa.utils;

import bg.softuni.servicesvratsa.model.view.InvoiceModelView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class InvoiceWriter {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static void writeToJsonFile(List<InvoiceModelView> invoices, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            GSON.toJson(invoices, writer);
            System.out.println("JSON файл създаден успешно.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToJsonFileNoOrders(String message, String filePath) {

        try (FileWriter writer = new FileWriter(filePath)) {
            GSON.toJson(message, writer);
            System.out.println("JSON файл създаден успешно.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
