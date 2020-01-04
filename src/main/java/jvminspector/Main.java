
package jvminspector;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

public class Main {

    public static final Logger logger = Logger.getLogger("");

    private static String getJDKLibPath(File javaHome, String libName) {
        return javaHome.getName().equalsIgnoreCase("jre") ? "../lib/" + libName : "lib/" + libName;
    }

    private void loadDependencies() throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        File javaHome = new File(System.getProperty("java.home"));
        String toolsPath = getJDKLibPath(javaHome, "tools.jar");
        String saJdiPath = getJDKLibPath(javaHome, "sa-jdi.jar");

        ArrayList<URL> urls = new ArrayList<URL>(Arrays.asList(((URLClassLoader) getClass().getClassLoader()).getURLs()));
        urls.add(new File(javaHome, toolsPath).getCanonicalFile().toURI().toURL());
        urls.add(new File(javaHome, saJdiPath).getCanonicalFile().toURI().toURL());

        URLClassLoader loader = new URLClassLoader(urls.toArray(new URL[0]), null);
        Class<?> utilityClass = loader.loadClass("jvminspector.Main");

        utilityClass.getMethod("showWindow").invoke(null);
    }

    public static void main(String[] args) throws Exception {
        new Main().loadDependencies();
    }

    public static void showWindow() {
        EventQueue.invokeLater(
                () ->
                {
                    MainWindow window = new MainWindow();
                    final Toolkit toolkit = Toolkit.getDefaultToolkit();
                    final Dimension screenSize = toolkit.getScreenSize();
                    final int x = (screenSize.width - window.getWidth()) / 2;
                    final int y = (screenSize.height - window.getHeight()) / 2;
                    window.setLocation(x, y);
                    window.setVisible(true);
                }
        );
    }
}
