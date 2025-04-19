---
sidebar_position: 4
title: 📊 Generate Allure Report
---

## Introduction

Boyka Framework provides a built-in interfaces which you can use to log test steps with Allure.

## Install Allure

To install Allure, you can use the following command:

```bash
brew install allure
```

or you can download it from the [Allure website](https://docs.qameta.io/allure/).

## Implement Boyka Listener

Boyka framework provides a set of interfaces that you can use to log test steps with Allure. You can implement any of the following interfaces to log test steps:

:::tip
You can click on the listener link to see the full documentation of the interface.
:::

### API

- [`IApiActionsListener`](/api/actions/interfaces/listeners/api/api-actions-listener)

### Data

- [`ILanguageActionListener`](/api/actions/interfaces/listeners/data/language-action-listener)
- [`ITestDataActionsListener`](/api/actions/interfaces/listeners/data/test-data-action-listener)

### Device

- [`IAndroidDeviceActionsListener`](/api/actions/interfaces/listeners/device/android-device-actions-listener)
- [`IDeviceActionsListener`](/api/actions/interfaces/listeners/device/device-actions-listener)

### Driver

- [`IAlertActionsListener`](/api/actions/interfaces/listeners/drivers/alert-actions-listener)
- [`IContextActionsListener`](/api/actions/interfaces/listeners/drivers/context-actions-listener)
- [`ICookieActionsListener`](/api/actions/interfaces/listeners/drivers/cookie-actions-listener)
- [`IDriverActionsListener`](/api/actions/interfaces/listeners/drivers/driver-actions-listener)
- [`IFrameActionsListener`](/api/actions/interfaces/listeners/drivers/frame-actions-listener)
- [`INavigateActionsListener`](/api/actions/interfaces/listeners/drivers/navigate-actions-listener)
- [`IWindowActionsListener`](/api/actions/interfaces/listeners/drivers/window-actions-listener)

### Element

- [`IClickableActionsListener`](/api/actions/interfaces/listeners/elements/clickable-actions-listener)
- [`IDropDownActionsListener`](/api/actions/interfaces/listeners/elements/drop-down-actions-listener)
- [`IElementActionsListener`](/api/actions/interfaces/listeners/elements/element-actions-listener)
- [`IFingerActionsListener`](/api/actions/interfaces/listeners/elements/finger-actions-listener)
- [`IFingersActionsListener`](/api/actions/interfaces/listeners/elements/fingers-actions-listener)
- [`ITextBoxActionsListener`](/api/actions/interfaces/listeners/elements/text-box-actions-listener)

## Configure the listener

In the `boyka-config.json` file, you can add the listener to the `listeners` field. For example:

```json
{
  "listeners_package": "package.to.your.listeners"
  . . .
}
```

Once you have added the listener to the `listeners_package` field, the Boyka framework will automatically load the listener and use it to log test steps with Allure.

## Conclusion

In this tutorial, you learned how to generate Allure reports with Boyka framework. You can use the built-in interfaces to log test steps with Allure and generate reports. You can also customize the reports by implementing your own listeners.

Rest of the steps for generating Allure reports are the same as the ones mentioned in the [Allure documentation](https://docs.qameta.io/allure/).
