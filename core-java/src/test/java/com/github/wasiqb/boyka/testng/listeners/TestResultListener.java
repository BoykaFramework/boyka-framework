/*
 * MIT License
 *
 * Copyright (c) 2022 Wasiq Bhamla
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.github.wasiqb.boyka.testng.listeners;

import static java.text.MessageFormat.format;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static org.apache.logging.log4j.LogManager.getLogger;
import static org.testng.ITestResult.FAILURE;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Stream;

import org.apache.logging.log4j.Logger;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

/**
 * Generate custom test report.
 *
 * @author Wasiq Bhamla
 * @since 13-Jul-2022
 */
public class TestResultListener implements IReporter {
    private static final Logger        LOGGER  = getLogger ();
    private static final AtomicInteger counter = new AtomicInteger (1);

    @Override
    public void generateReport (final List<XmlSuite> xmlSuites, final List<ISuite> suites,
        final String outputDirectory) {
        final String reportTemplate = initReportTemplate ();
        final String body = suites.stream ()
            .flatMap (suiteToResults ())
            .collect (joining ());
        saveReportTemplate (outputDirectory, reportTemplate.replaceFirst ("<body>", body));
    }

    private List<String> generateReportRows (final String testName, final String suiteName,
        final Set<ITestResult> allTestResults) {
        return allTestResults.stream ()
            .map (testResultToResultRow (testName, suiteName))
            .collect (toList ());
    }

    private String initReportTemplate () {
        final byte[] reportTemplate;
        String template = null;
        try {
            reportTemplate = Files.readAllBytes (Paths.get ("src/test/resources/report-template.md"));
            template = new String (reportTemplate, StandardCharsets.UTF_8);
        } catch (final IOException e) {
            LOGGER.error ("Problem initializing template", e);
        }
        return template;
    }

    private Function<Map.Entry<String, ISuiteResult>, Stream<? extends String>> resultsToRows (final ISuite suite) {
        return e -> {
            final ITestContext testContext = e.getValue ()
                .getTestContext ();

            final Set<ITestResult> failedTests = testContext.getFailedTests ()
                .getAllResults ();
            final Set<ITestResult> passedTests = testContext.getPassedTests ()
                .getAllResults ();
            final Set<ITestResult> skippedTests = testContext.getSkippedTests ()
                .getAllResults ();

            final String suiteName = suite.getName ();

            return Stream.of (failedTests, passedTests, skippedTests)
                .flatMap (results -> generateReportRows (e.getKey (), suiteName, results).stream ());
        };
    }

    private void saveReportTemplate (final String outputDirectory, final String reportTemplate) {
        new File (outputDirectory).mkdirs ();
        try (
            final PrintWriter reportWriter = new PrintWriter (
                new BufferedWriter (new FileWriter (new File (outputDirectory, "my-report.md"))))) {
            reportWriter.println (reportTemplate);
            reportWriter.flush ();
        } catch (final IOException e) {
            LOGGER.error ("Problem saving template", e);
        }
    }

    private Function<ISuite, Stream<? extends String>> suiteToResults () {
        return suite -> suite.getResults ()
            .entrySet ()
            .stream ()
            .flatMap (resultsToRows (suite));
    }

    private Function<ITestResult, String> testResultToResultRow (final String testName, final String suiteName) {
        final String ROW_TEMPLATE = "| {0} | {1} | {2} | {3} | {4} | {5} | {6} | {7} |\n";
        return testResult -> {
            final var testClass = testResult.getTestClass ()
                .getRealClass ();
            final var index = counter.getAndIncrement ();
            switch (testResult.getStatus ()) {
                case FAILURE:
                    return format (ROW_TEMPLATE, index, suiteName, testName, testClass.getPackageName (),
                        testClass.getSimpleName (), testResult.getName (), "❌", "NA");

                case ITestResult.SUCCESS:
                    return format (ROW_TEMPLATE, index, suiteName, testName, testClass.getPackageName (),
                        testClass.getSimpleName (), testResult.getName (), "✅",
                        String.valueOf (testResult.getEndMillis () - testResult.getStartMillis ()));

                case ITestResult.SKIP:
                    return format (ROW_TEMPLATE, index, suiteName, testName, testClass.getPackageName (),
                        testClass.getSimpleName (), testResult.getName (), "⛔", "NA");

                default:
                    return "";
            }
        };
    }
}
