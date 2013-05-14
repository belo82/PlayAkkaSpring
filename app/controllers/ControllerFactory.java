package controllers;

import global.Global;

/**
 * Controller factory.<br/>
 * <br/>
 * Used by <code>routes</code> file to access spring defined controllers.<br/>
 * <br/>
 * Example of use in <code>routes</code> file:<br/>
 * <code>controllers.ControllerFactory.application.index()</code>
 *
 * @author Peter Belko
 */
public class ControllerFactory {

    private ControllerFactory() {
        // to prevent instantiation, it's just a damn stupid factory
    }

    public static Application application() {
        return Global.getBean(Application.class);
    }
}
