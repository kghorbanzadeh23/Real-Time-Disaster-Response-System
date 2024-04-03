package edu.ucalgary.oop;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


public class GenderRepository {
     // Assuming openFile is to read the file content and print or process it in some way.
     public void openFile(String filePath) {
        List<String> genders = readFile(filePath);
        if (genders != null) {
            // Process the data as needed, e.g., print or store it.
            genders.forEach(System.out::println);
        }
    }

    // Reads the content of a file and returns it as a list of strings.
    public List<String> readFile(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null; // or consider throwing an unchecked exception or a custom exception
        }
    }

    // Potentially lists distinct file content options (e.g., unique gender identifiers).
    public List<String> listFileOptions(String filePath) {
        try {
            return Files.lines(Paths.get(filePath))
                        .distinct()
                        .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
