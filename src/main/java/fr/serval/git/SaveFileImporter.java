package fr.serval.git;

import fr.serval.GlobalKeys;
import fr.serval.launcher.LauncherKeys;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.CharBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class SaveFileImporter {
    private final File userHomeDir;
    private final File servalHomeDir;
    private final File saveFile;

    public SaveFileImporter() {
        this.userHomeDir = new File(System.getProperty(LauncherKeys.USER_HOME_DIR));
        this.servalHomeDir = new File(userHomeDir + String.valueOf(File.separator) + GlobalKeys.SERVAL);
        this.saveFile = new File(servalHomeDir + String.valueOf(File.separator) + "save.json");

        if (!isUserHomeDirPresent()) {
            displayAlertError(LauncherKeys.ERROR_NO_HOME_DIR);
        } else {
            if (!isServalDirPresent() && !createServalDir()) {
                displayAlertError(LauncherKeys.ERROR_CAN_NOT_CREATE + this.servalHomeDir.getAbsolutePath());
            }

            if (!isPluginFileListPresent() && !createPluginFileList()) {
                displayAlertError(LauncherKeys.ERROR_CAN_NOT_CREATE + this.saveFile.getAbsolutePath());
            }
        }
    }

    private void displayAlertError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle(LauncherKeys.ERROR_PLUGIN_IMPORTER);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public JSONObject getSaveJSON() {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(this.saveFile); FileReader tmpReader = new FileReader(this.saveFile))
        {
            if(tmpReader.read() != -1){
                return (JSONObject) jsonParser.parse(reader);
            }
        }
        catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setSaveFileContent(JSONObject json) {
        try {
            FileWriter fileWriter = new FileWriter(this.saveFile);
            fileWriter.write(json.toJSONString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteSaveFile() {
        try {
            Path fileToDeletePath = Paths.get(String.valueOf(this.saveFile));
            Files.deleteIfExists(fileToDeletePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isUserHomeDirPresent() {
        return this.userHomeDir.exists();
    }

    private boolean isServalDirPresent() {
        return this.servalHomeDir.exists();
    }

    private boolean createServalDir() {
        return this.servalHomeDir.mkdir();
    }

    private boolean isPluginFileListPresent() {
        return this.saveFile.exists();
    }

    private boolean createPluginFileList() {
        try {
            if (!this.saveFile.createNewFile()) {
                throw new IOException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return isPluginFileListPresent();
    }
}
