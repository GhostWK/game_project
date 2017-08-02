package client.map;

import client.utils.MyFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Created by USER on 02.08.2017.
 */
public class JaxbParser implements Parser {
    @Override
    public Object getObject(MyFile file, Class c) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(c);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return unmarshaller.unmarshal(file.getInputStream());
    }

    @Override
    public void saveObject(MyFile file, Object o) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(o.getClass());
        Marshaller marshaller = context.createMarshaller();
        try {
            marshaller.marshal(o, file.getOutputStream());
            marshaller.marshal(o, System.out);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Could not save Object");
        }

    }
}
