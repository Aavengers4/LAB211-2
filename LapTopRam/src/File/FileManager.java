/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import static Controler.RamManagement.listRam;
import Model.RAMItem;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class FileManager implements I_FileManager {
@Override
    public void saveToFile(String path) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            //try with resource
            oos.writeObject(listRam);

        } catch (Exception e) {
            System.out.println("saveFile Error: ");
        }
    }

    public static List<RAMItem> loadFromFile(String path) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            List<RAMItem> iList = (List<RAMItem>) ois.readObject();

            return iList;
        } catch (Exception e) {
            System.out.println("Error loading file: ");
            return null;
        }
    }
}
