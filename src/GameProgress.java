import java.io.*;
import java.sql.SQLOutput;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class GameProgress implements Serializable {
    private static final long serialVersionUID = 1L;

    private int health;
    private int weapon;
    private int lvl;
    private double distance;

    public GameProgress(int health, int weapon, int lvl, double distance) {
        this.health = health;
        this.weapon = weapon;
        this.lvl = lvl;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "GameProgress{" +
                "health=" + health +
                ", weapons=" + weapon +
                ", lvl=" + lvl +
                ", distance=" + distance +
                "}";
    }

    public static void saveGame(String pathSave, GameProgress save) {
        ObjectOutputStream fos = null;
        try {
            fos = new ObjectOutputStream(new FileOutputStream(pathSave));
            fos.writeObject(save);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void delSave(String path) {
        File file = new File(path);
        if(file.delete()) {
            //void
        } else {
            System.out.println("Файл не найден!");
        }
    }

    public static void zipFiles(String pathZip, List<String> saves) {
        ZipOutputStream zop = null;
        try {
            zop = new ZipOutputStream(new FileOutputStream(pathZip));
            for (int i = 0; i < saves.size(); i++) {
                String saveName = saves.get(i);
                String[] parts = saveName.split("/");
                String name = null;
                for (int j = 0; j < parts.length; j++) {
                    if (parts[j].contains(".dat")) {
                        name = parts[j];
                    }
                }
                if (name != null) {
                    ZipEntry entry = new ZipEntry(name);
                    zop.putNextEntry(entry);
                    FileInputStream fis = null;
                    try {
                        fis = new FileInputStream(saveName);
                        byte[] bytes = new byte[fis.available()];
                        fis.read(bytes);
                        zop.write(bytes);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (fis != null) {
                            fis.close();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (zop != null) {
                try {
                    zop.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
