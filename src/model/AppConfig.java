/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

public class AppConfig {

private static AppConfig instance;

private final String applicationName = "PawCare";
private final String version = "1.0";

private AppConfig() {
}

public static synchronized AppConfig getInstance() {
    if (instance == null) {
        instance = new AppConfig();
    }

    return instance;
}

public String getApplicationName() {
    return applicationName;
}

public String getVersion() {
    return version;
}


}
