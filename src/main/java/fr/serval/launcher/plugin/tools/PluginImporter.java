package fr.serval.launcher.plugin.tools;

import fr.serval.GlobalKeys;
import fr.serval.Main;
import fr.serval.launcher.LauncherKeys;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PluginImporter {
    private final File userHomeDir;
    private final File servalHomeDir;
    private final File pluginFileList;

    public PluginImporter() {
        this.userHomeDir = new File(System.getProperty(LauncherKeys.USER_HOME_DIR));
        this.servalHomeDir = new File(userHomeDir + String.valueOf(File.separator) + GlobalKeys.SERVAL);
        this.pluginFileList = new File(servalHomeDir + String.valueOf(File.separator) + "plugins.lst");

        if (!isUserHomeDirPresent()) {
            JOptionPane.showMessageDialog(null, LauncherKeys.ERROR_NO_HOME_DIR, LauncherKeys.ERROR_PLUGIN_IMPORTER, JOptionPane.ERROR_MESSAGE);
        } else {
            if (!isServalDirPresent() && !createServalDir()) {
                JOptionPane.showMessageDialog(null, LauncherKeys.ERROR_CAN_NOT_CREATE + this.servalHomeDir.getAbsolutePath(), LauncherKeys.ERROR_PLUGIN_IMPORTER, JOptionPane.ERROR_MESSAGE);
            }

            if (!isPluginFileListPresent() && !createPluginFileList()) {
                JOptionPane.showMessageDialog(null, LauncherKeys.ERROR_CAN_NOT_CREATE + this.pluginFileList.getAbsolutePath(), LauncherKeys.ERROR_PLUGIN_IMPORTER, JOptionPane.ERROR_MESSAGE);
            }

            if (isServalDirPresent() && isPluginFileListPresent()) {
                printFileContent(this.pluginFileList);
            }
        }
    }

    private void printFileContent(File file) {
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                JarFile jarFile = new JarFile(this.servalHomeDir + String.valueOf(File.separator) + data);
                Enumeration<JarEntry> e = jarFile.entries();

                URL[] urls = { new URL("jar:file:" + this.servalHomeDir + File.separator + data+"!/") };
                URLClassLoader cl = URLClassLoader.newInstance(urls);

                while (e.hasMoreElements()) {
                    JarEntry je = e.nextElement();
                    if(je.isDirectory() || !je.getName().endsWith(".class")){
                        continue;
                    }
                    // -6 because of .class
                    String className = je.getName().substring(0,je.getName().length()-6);
                    className = className.replace('/', '.');
                    Class c = cl.loadClass(className);

                    Method m = c.getMethod("main");
                    m.setAccessible(true);
                    m.invoke(null);
                }
            }
            myReader.close();
        } catch (FileNotFoundException | ClassNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
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
        return this.pluginFileList.exists();
    }

    private boolean createPluginFileList() {
        try {
            this.pluginFileList.createNewFile();
        } catch (IOException e) {
            System.err.println(e);
        }

        return isPluginFileListPresent();
    }
}
