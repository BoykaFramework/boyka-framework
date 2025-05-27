---
title: 3️⃣ Create MacOS Page Action class
sidebar_position: 3
---

Using the page object for the Calculator application, we will create an Action class that will expose methods to perform user flows specific to the MacOS platform.

Let's check out the following action class example below for our `CalculatorPage` class we created:

```java
import static io.github.boykaframework.actions.elements.ClickableActions.withMouse;
import static io.github.boykaframework.actions.elements.ElementActions.onElement;
import static io.github.boykaframework.testng.ui.mac.pages.CalculatorPage.calculatorPage;

public final class CalculatorActions {
    public static void verifyAdd (final int a, final int b) {
        final var expected = a + b;

        withMouse (calculatorPage ().getNumber (a)).click ();
        withMouse (calculatorPage ().getAdd ()).click ();
        withMouse (calculatorPage ().getNumber (b)).click ();
        withMouse (calculatorPage ().getEquals ()).click ();

        onElement (calculatorPage ().getInput ()).verifyText ()
            .endsWith (Integer.toString (expected));
    }

    private CalculatorActions () {
        // Utility class.
    }
}
```
