/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package master;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Artjom
 */
@WebService(serviceName = "MasterWebService")
public class MasterWebService {

    static ArrayList<String> words = new ArrayList<>();
    static ArrayList<List> arrayOfWords = new ArrayList<>();

    public MasterWebService() {
    }

    private static void readFromFile() throws IOException {

        FileReader fileReader = null;
        try {
            fileReader = new FileReader("webster-dictionary.txt");

            final BufferedReader dictionary = new BufferedReader(fileReader);
            while (true) {
                final String dictionaryEntry = dictionary.readLine();

                if (dictionaryEntry == null) {
                    break;
                }
                words.add(dictionaryEntry);
            }
        } finally {
            if (fileReader != null) {
                fileReader.close();
            }
        }
    }


    private static void splitDictionary() {
        int startPoint = 0;
        int lastIndex = words.size();
        int endPoint = 25000;
        int jump = endPoint;
        int numberOfSplits = 0;
        double size = words.size();
        double splitSize = endPoint;
        if (lastIndex % endPoint > endPoint / 2) {
            numberOfSplits = lastIndex / endPoint;
        } else {
            numberOfSplits = lastIndex / endPoint + 1;
        }

        for (int i = 0; i < numberOfSplits; i++) {
            arrayOfWords.add(words.subList(startPoint, endPoint));


            startPoint = startPoint + jump;
            endPoint = endPoint + jump;
            if (endPoint > lastIndex) {
                endPoint = lastIndex;

            }

        }
    }
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {

        System.out.println(words.size());
        System.out.println(arrayOfWords.size());
        System.out.println(words.toString().indexOf(2000));
        System.out.println(arrayOfWords);
        return "Hello " + txt + " !";

    }

}
