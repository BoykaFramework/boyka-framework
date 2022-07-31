---
title: DropDownActions
sidebar_position: 2
---

## `deselectAll (locator)` {#deselect-all}

This method will deselect all the options of the given multi-select element.

```java
import static com.github.wasiqb.boyka.actions.DropDownActions.deselectAll;
. . .
deselectAll (multiSelectLocator);
```

## `deselectByIndex (locator, index)` {#deselect-by-index}

This method will deselect the option of the given dropdown / multi-select element by the given index.

```java
import static com.github.wasiqb.boyka.actions.DropDownActions.deselectByIndex;
. . .
deselectByIndex (dropdownLocator, 1);
```

## `deselectByText (locator, text)` {#deselect-by-text}

This method will deselect the option of the given dropdown / multi-select element by the given text.

```java
import static com.github.wasiqb.boyka.actions.DropDownActions.deselectByText;
. . .
deselectByText (dropdownLocator, "Option 1");
```

## `deselectByValue (locator, value)` {#deselect-by-value}

This method will deselect the option of the given dropdown / multi-select element by the given value.

```java
import static com.github.wasiqb.boyka.actions.DropDownActions.deselectByValue;
. . .
deselectByValue (dropdownLocator, "val1");
```

## `selectByIndex (locator, index)` {#select-by-index}

This method will select the option of the given dropdown / multi-select element by the given index.

```java
import static com.github.wasiqb.boyka.actions.DropDownActions.selectByIndex;
. . .
selectByIndex (dropdownLocator, 2);
```

## `selectByText (locator, text)` {#select-by-text}

This method will select the option of the given dropdown / multi-select element by the given text.

```java
import static com.github.wasiqb.boyka.actions.DropDownActions.selectByText;
. . .
selectByText (dropdownLocator, "Option 2");
```

## `selectByValue (locator, value)` {#select-by-value}

This method will select the option of the given dropdown / multi-select element by the given value.

```java
import static com.github.wasiqb.boyka.actions.DropDownActions.selectByValue;
. . .
selectByValue (dropdownLocator, "value-2");
```

## `selectedItem (locator)` {#selected-item}

This method will return the selected item text of the given dropdown / multi-select element.

```java
import static com.github.wasiqb.boyka.actions.DropDownActions.selectedItem;
. . .
String itemText = selectedItem (dropdownLocator);
```

## `selectedItems (locator)` {#selected-items}

This method will return the list of selected item texts of the given multi-select element.

```java
import static com.github.wasiqb.boyka.actions.DropDownActions.selectedItems;
. . .
List<String> itemTexts = selectedItems (dropdownLocator);
```
