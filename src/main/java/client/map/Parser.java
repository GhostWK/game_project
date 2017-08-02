package client.map;

import client.utils.MyFile;

import javax.xml.bind.JAXBException;

/**
 * Created by USER on 02.08.2017.
 */
public interface Parser {
    Object getObject(MyFile file, Class c) throws JAXBException;
    void saveObject(MyFile file, Object o) throws JAXBException;
}
