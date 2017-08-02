package jaxb;

import client.map.JaxbParser;
import client.map.Map;
import client.map.Parser;
import client.utils.MyFile;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by USER on 02.08.2017.
 */
public class JaxbParserTest {
    private Parser parser;
    private MyFile file;

    @Before
    public void setUp()throws Exception{
        parser = new JaxbParser();
        file = new MyFile("maps","map.xml");
    }
    @Ignore
    @Test
    public void testGetObject() throws Exception{
        Map map = (Map) parser.getObject(file, Map.class);
        System.out.println(map.toString());
    }

    @Test
    public void testSaveObject()throws Exception{
        Map map = new Map(new int[][]{{1,2,3},{5,5,6},{7,8,9}});
        parser.saveObject(file, map);
    }

    @After
    public void shutDown()throws Exception{
        file.getOutputStream().close();

    }

}
