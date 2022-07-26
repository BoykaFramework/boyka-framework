package com.github.wasiqb.boyka.testng.web.pages;

import static com.github.wasiqb.boyka.builders.Locator.buildLocator;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

import com.github.wasiqb.boyka.builders.Locator;
import lombok.Getter;

@Getter
public class MouseEvent {

    public static MouseEvent mouseEvent () {
        return new MouseEvent ();
    }

    private final Locator dragAndDropLink   = buildLocator ().web (
            xpath ("//div[@id='content']//a[text()='Drag and Drop']"))
        .build ();
    private final Locator dragAndDropResult = buildLocator ().web (xpath ("//div[@id='column-b']/header"))
        .build ();
    private final Locator draggable         = buildLocator ().web (id ("column-a"))
        .build ();
    private final Locator droppable         = buildLocator ().web (id ("column-b"))
        .build ();
    private final Locator hoverImage        = buildLocator ().web (xpath ("(//div[@class='figure'])[1]"))
        .build ();
    private final Locator hoverLink         = buildLocator ().web (xpath ("//div[@id='content']//a[text()='Hovers']"))
        .build ();
    private final Locator hoverResult       = buildLocator ().web (xpath ("(//div[@class='figure'])[1]//h5"))
        .build ();

    private MouseEvent () {

    }
}
