package global;

import configurations.SpringConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import play.Application;
import play.GlobalSettings;

/**
 * Application Global object.<br/>
 * <br/>
 * responsible for handling global settings and loading spring configuration on applications start.
 *
 * @author Peter Belko
 *
 */
public class Global extends GlobalSettings {
    private static ApplicationContext applicationContext;

    @Override
    public void onStart(Application application) {
        applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
    }

    public static <T> T getBean(Class<T> beanClass) {
        if (applicationContext == null) {
            throw new IllegalStateException("application context is not initialized");
        }
        return applicationContext.getBean(beanClass);
    }
}
