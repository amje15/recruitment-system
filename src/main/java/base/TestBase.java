package base;

import utility.constants;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

    public static Properties prop;
    protected final static String staging_host="http://repair-sm-stage.bounce.bike";

    public TestBase() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(constants.config_file_path);
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } }}
