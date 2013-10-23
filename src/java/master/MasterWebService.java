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

    ArrayList<String> words = new ArrayList<>();
    ArrayList<ArrayList> arrayOfWords = new ArrayList<>();

    public MasterWebService() {
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

    
        private void splitDictionary()
        {
            FileReader fileReader = null;
            boolean isFinished = false;
        try {
            try {
                fileReader = new FileReader("webster-dictionary.txt");
                final BufferedReader dictionary = new BufferedReader(fileReader);
                while (isFinished == true) {
                    final String dictionaryEntry = dictionary.readLine();
                    for (int i = 0; i < 12; i++) {
                        for (int y = 0; i < 9999; i++) {
                            words.add(dictionaryEntry);
                            if (dictionaryEntry == null) {
                                isFinished = true;
                                break;
                            }
                            arrayOfWords.add(words);
                        }
                    }
                }
            } finally {
                if (fileReader != null) {
                    fileReader.close();
                }
            }
        } catch (IOException ex) {//empty};
        }
    }
}
