package main.java.app.util;

import lombok.*;

public class Path {

    // The @Getter methods are needed in order to access
    // the variables from Velocity Templates
    public static class Web {
        @Getter public static final String INDEX = "/index/";
        @Getter public static final String LOGIN = "/login/";
        @Getter public static final String LOGOUT = "/logout/";
        @Getter public static final String OBRAS = "/obras/";
        @Getter public static final String ONE_OBRA = "/obras/:id/";
    }

    public static class Template {
        public final static String INDEX = "/velocity/index/index.vm";
        public final static String LOGIN = "/velocity/login/login.vm";
        public final static String OBRAS_ALL = "/velocity/obra/all.vm";
        public static final String OBRAS_ONE = "/velocity/obra/one.vm";
        public static final String NOT_FOUND = "/velocity/notFound.vm";
    }

}
