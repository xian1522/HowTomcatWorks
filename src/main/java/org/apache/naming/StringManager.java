package org.apache.naming;


import java.util.Hashtable;
import java.util.ResourceBundle;

public class StringManager {

    private ResourceBundle resourceBundle;

    private static Hashtable<String, StringManager> managers = new Hashtable();

    private StringManager(String packageName) {
        String boundleName = packageName + ".LocalStrings";
        resourceBundle = ResourceBundle.getBundle(boundleName);
    }

    public synchronized static StringManager getManager(String packageName){
        StringManager stringManager = managers.get(packageName);
        if(stringManager == null) {
            stringManager = new StringManager(packageName);
            managers.put(packageName, stringManager);
        }
        return stringManager;
    }
}
