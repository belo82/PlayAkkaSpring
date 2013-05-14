package configurations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Here you can define factory methods for your beans.
 *
 * @author Peter Belko
 */
@Configuration
@ComponentScan({"controllers"})
public class SpringConfiguration {
}
