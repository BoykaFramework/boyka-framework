---
title: TextBoxActions
sidebar_position: 5
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
import static com.github.wasiqb.boyka.actions.elements.TextBoxActions.onTextBox;
. . .
onTextBox (locator).enterText (text);
```

### `pressKey (key)` {#press-key}

This method is used to press the given key to the given element.

```java
import static com.github.wasiqb.boyka.actions.elements.TextBoxActions.onTextBox;
. . .
onTextBox (locator).pressKey (key);
```

### `isKeyboardVisible` {#is-keyboard-visible}

This method is used to determine if the Mobile keyboard is visible.

```java
import static com.github.wasiqb.boyka.actions.elements.TextBoxActions.onTextBox;
. . .
var isKeyboardDisplayed = onTextBox (locator).isKeyboardVisible ();
```

### `hideKeyboard` {#hide-keyboard}

This method is used to hide the keyboard on the mobile.

```java
import static com.github.wasiqb.boyka.actions.elements.TextBoxActions.onTextBox;
. . .
onTextBox (locator).hideKeyboard ();
```

### `uploadFile` {#upload-file}

This method is used to enter file path on File input element to upload the file.

```java
import static com.github.wasiqb.boyka.actions.elements.TextBoxActions.onTextBox;
. . .
onTextBox (locator).uploadFile (filePath);
```
