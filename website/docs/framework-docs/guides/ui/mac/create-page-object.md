---
title: 2️⃣ Create MacOS Page Object
sidebar_position: 2
---

## Example Page Object class

In the following example, I have used the MacOS Calculator app to demonstrate how to create a Page Object class using Boyka Framework.

```java
import static io.appium.java_client.AppiumBy.iOSClassChain;
import static io.appium.java_client.AppiumBy.iOSNsPredicateString;
import static java.text.MessageFormat.format;

import io.github.boykaframework.builders.Locator;
import lombok.Getter;

@Getter
public class CalculatorPage {
    private static final CalculatorPage CALCULATOR_PAGE = new CalculatorPage ();

    public static CalculatorPage calculatorPage () {
        return CALCULATOR_PAGE;
    }

    private final Locator add    = Locator.buildLocator ()
        .name ("Add")
        .mac (iOSNsPredicateString ("label == \"Add\""))
        .build ();
    private final Locator equals = Locator.buildLocator ()
        .name ("Equals")
        .mac (iOSNsPredicateString ("label == \"Equals\""))
        .build ();
    private final Locator input  = Locator.buildLocator ()
        .name ("Input")
        .mac (iOSClassChain ("**/XCUIElementTypeScrollView[`label == \"Input\"`]/XCUIElementTypeStaticText"))
        .build ();

    private CalculatorPage () {
        // Singleton class.
    }

    public Locator getNumber (final int number) {
        if (number < 0 || number > 9) {
            throw new IllegalArgumentException ("Number must be between 0 and 9");
        }
        return Locator.buildLocator ()
            .name (format ("Number {0}", number))
            .mac (iOSNsPredicateString (format ("label == \"{0}\"", number)))
            .build ();
    }
}
```
