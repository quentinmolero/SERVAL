package fr.serval.launcher.plugin.tools;

import fr.serval.GlobalKeys;
import fr.serval.launcher.LauncherKeys;
import fr.serval.launcher.plugin.Plugin;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public class PluginImporter {
    private final File userHomeDir;
    private final File servalHomeDir;
    private final File pluginFileList;
    private List<Plugin> pluginList;

    public PluginImporter() {
        this.userHomeDir = new File(System.getProperty(LauncherKeys.USER_HOME_DIR));
        this.servalHomeDir = new File(userHomeDir + String.valueOf(File.separator) + GlobalKeys.SERVAL);
        this.pluginFileList = new File(servalHomeDir + String.valueOf(File.separator) + "plugins.lst");

        if (!isUserHomeDirPresent()) {
            displayAlertError(LauncherKeys.ERROR_PLUGIN_IMPORTER, LauncherKeys.ERROR_NO_HOME_DIR);
        } else {
            if (!isServalDirPresent() && !createServalDir()) {
                displayAlertError(LauncherKeys.ERROR_PLUGIN_IMPORTER, LauncherKeys.ERROR_CAN_NOT_CREATE + this.servalHomeDir.getAbsolutePath());
            }

            if (!isPluginFileListPresent() && !createPluginFileList()) {
                displayAlertError(LauncherKeys.ERROR_PLUGIN_IMPORTER, LauncherKeys.ERROR_CAN_NOT_CREATE + this.pluginFileList.getAbsolutePath());
            }
        }
    }

    private void displayAlertError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void printFileContent(File file) {
        try {
            JarFile jarFile = new JarFile(file);
            Enumeration<JarEntry> e = jarFile.entries();

            URL[] urls = {new URL("jar:file:" + file + "!/")};
            URLClassLoader cl = URLClassLoader.newInstance(urls);

            while (e.hasMoreElements()) {
                JarEntry je = e.nextElement();
                if (je.isDirectory() || !je.getName().endsWith("/Main.class")) {
                    continue;
                }
                // -6 because of .class
                String className = je.getName().substring(0, je.getName().length() - 6);
                className = className.replace('/', '.');
                Class c = cl.loadClass(className);

                Method m = c.getMethod("main");
                m.setAccessible(true);
                m.invoke(null);

                m = c.getMethod("getPluginController");
                m.setAccessible(true);
                System.out.println(m.invoke(null));
            }
        } catch (ClassNotFoundException | IOException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public List<Plugin> getPluginList() {
        if (this.pluginList == null) {
            this.pluginList = new ArrayList<>();
            if (isServalDirPresent() && isPluginFileListPresent()) {
                getPluginFileContent().lines().forEach(line -> {
                    String[] data = line.split(";");
                    pluginList.add(new Plugin(data[0], data[1], Boolean.parseBoolean(data[2])));
                });
            }
        }
        return pluginList;
    }

    private String getPluginFileContent() {
        StringBuilder fileContent = new StringBuilder();

        try {
            Scanner scanner = new Scanner(this.pluginFileList);
            while (scanner.hasNextLine()) {
                fileContent.append(scanner.nextLine()).append('\n');
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return fileContent.toString();
    }

    public boolean setPluginFileContent(List<Plugin> plugins) {
        try {
            FileWriter fileWriter = new FileWriter(this.pluginFileList);
            fileWriter.write(plugins.stream().map(plugin -> plugin.getName() + ";" + plugin.getFileName() + ";" + plugin.isEnabled() + '\n').collect(Collectors.joining()));
            fileWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void loadPlugins() {
        for (Plugin plugin : this.getPluginList()) {
            if (plugin.isEnabled()) {
                printFileContent(plugin.getFile());
            }
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
        return this.pluginFileList.exists();
    }

    private boolean createPluginFileList() {
        try {
            if (!this.pluginFileList.createNewFile()) {
                throw new IOException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return isPluginFileListPresent();
    }

    public File getServalHomeDir() {
        return this.servalHomeDir;
    }
}
