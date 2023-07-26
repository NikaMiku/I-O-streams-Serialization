import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UTFDataFormatException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        List<String> mainDirs = Arrays.asList("src", "res", "savegames", "temp");
        List<String> srcDirs = Arrays.asList("main", "test");
        List<String> srcFiles = Arrays.asList("Main.java", "Utils.java");
        List<String> resDirs = Arrays.asList("drawables", "vectors", "icons");
        List<String> tempFiles = Arrays.asList("temp.txt");
        dirCreate("D:/Games/", mainDirs, sb);
        dirCreate("D:/Games/src/", srcDirs, sb);
        dirCreate("D:/Games/res/", resDirs, sb);
        fileCreate("D:/Games/src/main/", srcFiles, sb);
        fileCreate("D:/Games/temp/", tempFiles, sb);
        log(sb, "D:/Games/temp/","temp.txt");
        ArrayList<String> savePath = new ArrayList<>();
        //Create list GameProgress
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


    public static void log(StringBuilder sb,String path, String file) {
        try {
            FileWriter fw = new FileWriter(path + file);
            fw.write(sb.toString());
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void dirCreate(String path, List<String> dirs, StringBuilder sb) {
        for (String dir : dirs) {
            if (new File(path + dir).mkdirs()) {
                sb.append("Папка " + dir + " по пути " + path + " создана!\n");
            } else {
                sb.append("Папка " + dir + " не была создана!\n");
            }
        }
    }

    public static void fileCreate(String path, List<String> files, StringBuilder sb) {
        for (String file : files) {
            File file1 = new File(path, file);
            try {
                if (file1.createNewFile()) {
                    sb.append("Файл " + file + " по пути " + path + " создан!\n");
                } else {
                    sb.append("Файл " + file + " не был создан!\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
