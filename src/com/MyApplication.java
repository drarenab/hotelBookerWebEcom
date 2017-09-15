package com;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.controllers.MainController;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by AZ PC on 08/09/2017.
 */

@ApplicationPath("/")
public class MyApplication extends Application {
	/*
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add(MainController.class);
        return h;
    }*/
}
