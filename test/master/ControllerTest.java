/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package master;

import java.io.IOException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Artjom
 */
public class ControllerTest {
    @Test
    public void testReadFromFile() throws IOException {
        
        Controller c = Controller.getInstance();
     List<String> l = c.readFromFile();
        System.out.println(l);
     assertEquals(9, l.size());
    }

    @Test
    public void testSplitDictionary(){
        
    }
    
    @Before
    public void setUp() {
    }

  
}