package de.hmayer.solarsystemserver.model;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *  The generic class to hold identifiable items in a list
 *  the data is initialized with data from a csv file.
 * 
 *  add and delete of items is possible
 * 
 * @author Holger Mayer
 */

public class Repository<T extends Identifiable> {
    private static final Logger logger =
     LoggerFactory.getLogger("Repository<T>");
    private List<T> elements;

    public Repository(String fileName, Function<List<String>, T> recordHandler) {
        this.elements = new ArrayList<>();

        reload(fileName, recordHandler);
    }

   public void reload(String fileName, Function<List<String>, T> recordHandler) {

    this.deleteAll();
    try {
        loadCSVData(fileName, recordHandler);
        } catch (Exception e){
            logger.error(e.getMessage());
        }
   }


    public List<T> findAll() {
        return elements;
    }

    public Optional<T> findById(Integer id) {
        return this.elements.stream()
                .filter(e -> id.equals(e.getId()))
                .findAny();
    }

    public void delete(int id) {

        Optional<T> elementToRemove = findById(id);

        if (elementToRemove.isPresent()) {
            this.elements.remove(elementToRemove.get());
        }
    }

    public void deleteAll(){
        elements.clear();
    }

    public void add(T element) {
        Optional<T> elementCheck = findById(element.getId());

        if (!elementCheck.isPresent()) {
            this.elements.add(element);
        }

    }

    /**
     * the  data list is loaded during initalization from a csv file
     * we asume that the first line in the csv file is a header line 
     * 
     * @param filename the file name of the csv file to read
     * @param recordHandler the function that creates an object of type T from a row of string data
     * @throws IOException - can't read the file
     * @throws CsvValidationException - wrong csv format
     * @throws URISyntaxException - URI not valide (filename?)
     */
    protected void loadCSVData(String filename, Function<List<String>,T> recordHandler)
            throws IOException, CsvValidationException, URISyntaxException {

        URL resource = getClass().getClassLoader().getResource(filename);
        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        } else {
            Boolean isHeader = true;
            try (CSVReader csvReader = new CSVReader(new FileReader(new File(resource.toURI())))) {
                String[] values = null;
      
                while ((values = csvReader.readNext()) != null) {
                    if (Boolean.TRUE.equals(isHeader)) {
                        isHeader = false;
                    } else {
                        List<String> recordData = Arrays.asList(values);
 
                        elements.add(recordHandler.apply(recordData));
                    }

                }
            }
        }
    }
}
