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

    public MasterWebService() {
        Controller.getInstance();
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
        return Controller.words.get(2);
    }
}
