import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> mainDirs = Arrays.asList("src", "res", "savegames", "temp");
        List<String> srcDirs = Arrays.asList("main", "test");
        List<String> srcFiles = Arrays.asList("Main.java", "Utils.java");
        List<String> resDirs = Arrays.asList("drawables", "vectors", "icons");
        List<String> tempFiles = Arrays.asList("temp.txt");
        //Создаём StringBuilder
        StringBuilder sb = new StringBuilder();
        //Изначально создаём все директории
        for (String dir : mainDirs) {
            new File("D:/Games/" + dir).mkdirs();
            log(sb, dir, "dir");
        }
        for (String dir : srcDirs) {
            new File("D:/Games/src/" + dir).mkdirs();
            log(sb, dir, "dir");
        }
        for (String dir : resDirs) {
            new File("D:/Games/res/" + dir).mkdirs();
            log(sb, dir, "dir");
        }
        //Далее по уже знакомым расположениям создаём файлы в каталогах
        for (String file : srcFiles) {
            File srcFile = new File("D:/Games/src/main/" + file);
            try {
                srcFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            log(sb, file, "file");
        }
        for (String file : tempFiles) {
            File tempFile = new File("D:/Games/temp/" + file);
            try {
                tempFile.createNewFile();
                FileWriter fw = new FileWriter(tempFile);
                fw.write(sb.toString());
                fw.flush();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            log(sb, file, "file");
        }
        //Создаём лист с сейвами
        ArrayList<String> savePath = new ArrayList<>();
        List<GameProgress> savesList = Arrays.asList(
                new GameProgress(10, 2, 5, 423.364),
                new GameProgress(580, 34, 35, 1788.234),
                new GameProgress(12490, 99, 99, 89334.632));
        for (int i = 0; i < savesList.toArray().length; i++) {
            GameProgress.saveGame("D:/Games/savegames/save" + i + ".dat", savesList.get(i));
            savePath.add("D:/Games/savegames/save" + i + ".dat");

        }
            GameProgress.zipFiles("D:/Games/savegames/saves.zip", savePath);
        for (int i = 0; i < savePath.toArray().length; i++) {
            GameProgress.delSave(savePath.get(i));
        }
    }

    //И метод saveGame


    public static void log(StringBuilder log, String name, String format) {
        if (format.equals("dir")) {
            log.append("Каталог " + name + " Создан успешно\n");
        } else if (format.equals("file")) {
            log.append("Файл " + name + " Создан успешно\n");
        }
    }
}
