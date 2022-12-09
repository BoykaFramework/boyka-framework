---
title: VerifyDropDownActions
sidebar_position: 8
---

## `verifySelectedItem (locator)` {#verify-selected-item-locator}

This method will verify the selected item from the drop down.

```java
import static com.github.wasiqb.boyka.actions.VerifyDropDownActions.verifySelectedItem;
. . .
verifySelectedItem (locator).isEqualTo ("Batman");
```

## `verifySelectedItems (locator)` {#verify-selected-items-locator}

This method will verify the list of selected items from the drop down.

```java
import static com.github.wasiqb.boyka.actions.VerifyDropDownActions.verifySelectedItems;
. . .
verifySelectedItems (locator).containsExactly ("The Avengers", "Batman", "Black Panther");
```
