/**
 * @package
 */
package com.jackrkern.groceriesbyrecipe.util;

/**
 * @imports
 */
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 *  @author Jack Kern <jackrkern@gmail.com>
 */
@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {}
}
