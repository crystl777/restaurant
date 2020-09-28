package ru.crystl.restaurant;

public class Profiles {

    public static final String HSQL_DB = "hsqldb";

    public static final String DATAJPA = "datajpa";

    public static final String REPOSITORY_IMPLEMENTATION = "datajpa";

    public static String getActiveDbProfile() {
        try {
            Class.forName("org.postgresql.Driver");
            return HSQL_DB;
        } catch (ClassNotFoundException ex) {
            try {
                Class.forName("org.hsqldb.jdbcDriver");
                return Profiles.HSQL_DB;
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Could not find DB driver");
            }
        }
    }
}
