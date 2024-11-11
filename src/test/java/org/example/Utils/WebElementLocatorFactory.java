package org.example.Utils;

import org.openqa.selenium.WebDriver;

import java.lang.reflect.Method;
import java.util.Objects;


public class WebElementLocatorFactory extends BaseClass {

    public String getLocator(Class c, String webElementName) {

        Method m = null;
        try {

            m = c.getDeclaredMethod(webElementName);
            m.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        String locator = null;

        if (m.isAnnotationPresent(WebElementLocator.class)) {
            WebElementLocator ta = m.getAnnotation(WebElementLocator.class);
            switch (Objects.requireNonNull(getPlatform())) {
                case WEB_DESKTOP:
                    locator = ta.webDesktop();
                    break;
                case WEB_PHONE:
                    locator = ta.webPhone();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + getPlatform());
            }
        }

        return locator;
    }

}






