package main.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * FileUtils class to handle saving and restoring the state of a {@link Object}
 */
public class FileUtils {
    /**
     * Serializes and saves a given object to the given file
     *
     * @param objToSave    Object to save
     * @param fileToWrite  file to write to
     * @param appendObject whether or not to make the ObjectOutputStream appendable
     * @param writeHeader  whether or not to make the FileOutputStream appendable
     */
    public boolean saveState(Object objToSave,
                             String fileToWrite,
                             boolean appendObject,
                             boolean writeHeader) {
        System.out.println("Saving " + objToSave.getClass().toString() + " state");
        try (FileOutputStream fileOutputStream
                     = new FileOutputStream(fileToWrite, writeHeader)) {
            ObjectOutputStream objectOutputStream = appendObject ? new AppendingObjectOutputStream(fileOutputStream) :
                    new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(objToSave);
            objectOutputStream.flush();
            objectOutputStream.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error while serializing Object: " + e.getMessage());
        }
        return false;
    }

    /**
     * Returns a list of all deserialized objects in a given file
     *
     * @param fileToRead file to read from
     * @return list of deserialized Objects
     */
    public List<Object> restoreState(String fileToRead) {
        List<Object> objects = new ArrayList<>();
        if (new File(fileToRead).exists()) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileToRead))) {
                while (true) {
                    objects.add(objectInputStream.readObject());
                }
            } catch (EOFException ignored) {
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error while deserializing Object: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return objects;
    }
}
