package io.loop.testAI;

import io.loop.utilities.ChatGPTClient;
import org.apache.poi.ss.formula.functions.T;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestAI {
    public static void main(String[] args) {
        String prompt = "Generate me scenario for www.google.com in Gherkin language, make sure to add negative ones as well"; // you can read it from somewhere else as well
        String aiResponse = ChatGPTClient.getResponseFromPrompt(prompt);


        System.out.println("\n======================AI Generated Suggestions Start ============================\n");
        System.out.println(aiResponse);
        System.out.println("\n======================AI Generated Suggestions End   ============================\n");

        // Folder path
        String folderPath = "src/test/resources/ai_suggestions/";

        // dynamic naming
        LocalDateTime now = LocalDateTime.now();
        String timeStamp = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        String fileName = "ai_suggestions_" + timeStamp + ".txt";

        try {
            //Create folder if it not exist
            Files.createDirectories(Paths.get(folderPath));

            //write ai output to the file
            PrintWriter out = new PrintWriter(folderPath + fileName);
            out.println(aiResponse);
            out.close();

            System.out.println("Output saved to " + folderPath + fileName);

        } catch (IOException e) {
            System.out.println("Failed to save output to file" );
            e.printStackTrace();
        }



    }
}
