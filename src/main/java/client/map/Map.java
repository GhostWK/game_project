package client.map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by USER on 02.08.2017.
 */
@XmlRootElement
public class Map {
    private int[][] array;

    public Map() {
    }

    public Map(int[][] array) {
        this.array = array;
    }

    public int[][] getArray() {
        return array;
    }
    @XmlElement
    @XmlElementWrapper
    public void setArray(int[][] array) {
        this.array = array;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");
        for(int i = 0; i < array.length; i++){
            builder.append("\t{");
            for (int j = 0; j < array[i].length; j++) {
                builder.append(array[i][j]).append(", ");
            }
            builder.append("}\n");
        }
        builder.append("}");
        return builder.toString();

    }
}
