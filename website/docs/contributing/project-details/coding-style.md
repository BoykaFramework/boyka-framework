---
title: 💄 Coding style
sidebar_position: 5
---

## Java coding style

### IntelliJ IDEA Code style setup

We have defined a common coding style which we follow while writing Java code. The defined code formatter can be found at `core-java/code-formatter` directory. You need to import the formatter from the mentioned directory in your IntelliJ IDEA properties.

![IntelliJ IDEA](/img/docs/contributing/intellij-preferences.png)

1. Open IntelliJ IDEA preferences window, go to `Editor` -> `Code Style` and click on 3 dots.
2. Select `Import Schema` -> `IntelliJ IDEA code style XML` from the options and select the `boyka-formatter.xml` file from the path mentioned above.
3. Now click on `Apply` and `OK` buttons.

### Save Action plugin settings

In your IntelliJ IDEA, add `Save Actions` plugin to your project. This plugin is used to format code files on save when working on the Java project.

To configure the plugin, open IntelliJ IDEA preferences window and expand `Other Settings` and select `Save Actions`.

A window similar to the following will be displayed, select all the options demonstrated here in the following screenshots.

![Save Actions First page](/img/docs/contributing/save-action-1.png)

Now after scrolling, select the following options shown in this screenshot.

![Save Actions First page](/img/docs/contributing/save-action-2.png)

Now click on `Apply` and `OK` buttons.
