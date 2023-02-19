---
title: DropDownActions
sidebar_position: 2
---

## Static methods

### `onDropDown` {#on-drop-down}

This will return `IDropDownActions` which will expose different methods to handle drop down actions.

```java
IDropDownActions dropDownActions = DropDownActions.onDropDown ();
```

## Instance methods

### `deselectAll` {#deselect-all}

This method will deselect all the options of the given multi-select element.

```java
import static com.github.wasiqb.boyka.actions.elements.DropDownActions.onDropDown;
. . .
onDropDown (multiSelectLocator).deselectAll ();
```

### `deselectByIndex (index)` {#deselect-by-index}

This method will deselect the option of the given dropdown / multi-select element by the given index.

```java
import static com.github.wasiqb.boyka.actions.elements.DropDownActions.onDropDown;
. . .
onDropDown (dropdownLocator).deselectByIndex (1);
```

### `deselectByText (text)` {#deselect-by-text}

This method will deselect the option of the given dropdown / multi-select element by the given text.

```java
import static com.github.wasiqb.boyka.actions.elements.DropDownActions.onDropDown;
. . .
onDropDown (dropdownLocator).deselectByText ("Option 1");
```

### `deselectByValue (value)` {#deselect-by-value}

This method will deselect the option of the given dropdown / multi-select element by the given value.

```java
import static com.github.wasiqb.boyka.actions.elements.DropDownActions.onDropDown;
. . .
onDropDown (dropdownLocator).deselectByValue ("val1");
```

### `selectByIndex (index)` {#select-by-index}

This method will select the option of the given dropdown / multi-select element by the given index.

```java
import static com.github.wasiqb.boyka.actions.elements.DropDownActions.onDropDown;
. . .
onDropDown (dropdownLocator).selectByIndex (2);
```

### `selectByText (text)` {#select-by-text}

This method will select the option of the given dropdown / multi-select element by the given text.

```java
import static com.github.wasiqb.boyka.actions.elements.DropDownActions.onDropDown;
. . .
onDropDown (dropdownLocator).selectByText ("Option 2");
```

### `selectByValue (value)` {#select-by-value}

This method will select the option of the given dropdown / multi-select element by the given value.

```java
import static com.github.wasiqb.boyka.actions.elements.DropDownActions.onDropDown;
. . .
onDropDown (dropdownLocator).selectByValue ("value-2");
```

### `selectedItem` {#selected-item}

This method will return the selected item text of the given dropdown / multi-select element.

```java
import static com.github.wasiqb.boyka.actions.elements.DropDownActions.onDropDown;
. . .
String itemText = onDropDown (dropdownLocator).selectedItem ();
```

### `selectedItems` {#selected-items}

This method will return the list of selected item texts of the given multi-select element.

```java
import static com.github.wasiqb.boyka.actions.elements.DropDownActions.onDropDown;
. . .
List<String> itemTexts = onDropDown (dropdownLocator).selectedItems (dropdownLocator);
```

### `verifySelectedItem` {#verify-selected-item}

This method will verify the selected item from the drop down.

```java
import static com.github.wasiqb.boyka.actions.elements.DropDownActions.onDropDown;
. . .
onDropDown (dropdownLocator).verifySelectedItem ().isEqualTo ("Batman");
```

### `verifySelectedItems` {#verify-selected-items}

This method will verify the list of selected items from the drop down.

```java
import static com.github.wasiqb.boyka.actions.elements.DropDownActions.onDropDown;
. . .
onDropDown (dropdownLocator).verifySelectedItems ().containsExactly ("The Avengers", "Batman", "Black Panther");
```
