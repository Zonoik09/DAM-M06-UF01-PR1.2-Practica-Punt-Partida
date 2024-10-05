package com.project;

import com.project.excepcions.IOFitxerExcepcio;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class PR120mainPersonesHashmap {
    private static String filePath = System.getProperty("user.dir") + "/data/PR120persones.dat";

    public static void main(String[] args) {
        HashMap<String, Integer> persones = new HashMap<>();
        persones.put("Anna", 25);
        persones.put("Bernat", 30);
        persones.put("Carla", 22);
        persones.put("David", 35);
        persones.put("Elena", 28);

        try {
            escriurePersones(persones);
            llegirPersones();
        } catch (IOFitxerExcepcio e) {
            System.err.println("Error en treballar amb el fitxer: " + e.getMessage());
        }
    }

    // Getter per a filePath
    public static String getFilePath() {
        return filePath;
    }

    // Setter per a filePath
    public static void setFilePath(String newFilePath) {
        filePath = newFilePath;
    }

    // Mètode per escriure les persones al fitxer
    public static void escriurePersones(HashMap<String, Integer> persones) throws IOFitxerExcepcio {
        try (FileOutputStream fos = new FileOutputStream(filePath);
             DataOutputStream dos = new DataOutputStream(fos)) {

            dos.writeInt(persones.size());

            for (Map.Entry<String, Integer> entry : persones.entrySet()) {
                dos.writeUTF(entry.getKey());
                dos.writeInt(entry.getValue());
            }
        } catch (FileNotFoundException e) {
            throw new IOFitxerExcepcio("Error en escriure les persones al fitxer: " + filePath, e);
        } catch (IOException e) {
            throw new IOFitxerExcepcio("Error en escriure les persones al fitxer: " + filePath, e);
        }
    }

    // Mètode per llegir les persones des del fitxer
    public static void llegirPersones() throws IOFitxerExcepcio {
        try (FileInputStream fis = new FileInputStream(filePath);
             DataInputStream dis = new DataInputStream(fis)) {

            int size = dis.readInt();
            HashMap<String, Integer> persones = new HashMap<>();

            for (int i = 0; i < size; i++) {
                String nom = dis.readUTF();
                int edat = dis.readInt();
                persones.put(nom, edat);
            }

            for (Map.Entry<String, Integer> entry : persones.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + " anys");
            }

        } catch (FileNotFoundException e) {
            throw new IOFitxerExcepcio("Error en llegir les persones del fitxer: " + filePath, e);
        } catch (IOException e) {
            throw new IOFitxerExcepcio("Error en llegir les persones del fitxer: " + filePath, e);
        }
    }
}
