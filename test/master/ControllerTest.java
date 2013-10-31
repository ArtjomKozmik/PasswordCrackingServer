/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package master;

import java.io.IOException;
import java.util.ArrayList;
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
        List<String> l = c.words;
        assertEquals(311141, l.size());
    }

    @Test
    public void testReadPasswords() {
        Controller c = Controller.getInstance();
        List<UserInfo> l = Controller.getInstance().readPasswords();

        assertEquals(8, l.size());
    }

    @Test
    public void testSplitDictionary() {
        Controller c = Controller.getInstance();
        ArrayList<List> list = c.arrayOfWords;
        list.size();
        assertEquals(13, list.size());
        assertEquals(25000, list.get(0).size());
    }

    @Before
    public void setUp() {
    }
}