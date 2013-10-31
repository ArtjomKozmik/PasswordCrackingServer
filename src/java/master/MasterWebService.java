/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package master;

import com.sun.xml.rpc.streaming.Stream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.jws.Oneway;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Artjom
 */
@WebService(serviceName = "MasterWebService")
public class MasterWebService {
    final ArrayList<List> arrayOfWords = Controller.getInstance().arrayOfWords;

    int counter = 0;
    ArrayList<String> results = new ArrayList<>();
    

    public MasterWebService() {
//        Controller.getInstance();
    }

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {


        return "Hello " + txt + " !";

    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "printWords")
    public String printWords() {
        //TODO write your implementation code here:
        return Controller.getInstance().words.get(2);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getWords")
    public List getWords() {
        List<String> partOfDictionary= null;
        if(counter<arrayOfWords.size()){
        partOfDictionary = arrayOfWords.get(counter);
        }
        
        counter = counter + 1;
        return partOfDictionary;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getPasswords")
    public List<UserInfo> getPasswords() {


        List<UserInfo> password = Controller.getInstance().passwords;
        return password;
    }

    

    /**
     * Web service operation
     */
    @WebMethod(operationName = "sendResult2")
    @Oneway
    public void sendResult2(@WebParam(name = "result") String result) {
        System.out.println(result);
        results.add(result);
        if(counter == arrayOfWords.size()){
            System.out.println("wrote file");
        Controller.writeFile(results);
        }
    }
    
    
}
