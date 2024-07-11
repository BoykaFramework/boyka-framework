---
title: TextBoxActions
sidebar_position: 6
---

## Static methods

### `onTextBox (locator)` {#on-textbox-locator}

This will return `ITextBoxActions` which will expose different methods to handle all text box related actions.

```java
ITextBoxActions textBoxActions = TextBoxActions.onTextBox (locator);
```

## Instance methods

### `enterText (text)` {#enter-text}

This method is used to enter the given text to the given element.

```java
import static io.github.boykaframework.actions.elements.TextBoxActions.onTextBox;
. . .
onTextBox (locator).enterText (text);
```

### `focus` {#focus}

This method is used to focus the cursor on the input element.

```java
import static io.github.boykaframework.actions.elements.TextBoxActions.onTextBox;
. . .
onTextBox (locator).focus ();
```

### `inputValue` {#input-value}

This method is used to get the value from the Textbox element.

```java
import static io.github.boykaframework.actions.elements.TextBoxActions.onTextBox;
. . .
String value = onTextBox (locator).inputValue ();
```

### `verifyInputValue` {#verify-input-value}

This method is used to verify the value from the Textbox element.

```java
import static io.github.boykaframework.actions.elements.TextBoxActions.onTextBox;
. . .
onTextBox (locator).verifyInputValue ()
    .isEqualTo (userName);
```
