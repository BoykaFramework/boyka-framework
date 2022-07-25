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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.IntStream;
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
    private static final Logger LOGGER = getLogger ();

    @Override
    public void generateReport (final List<XmlSuite> xmlSuites, final List<ISuite> suites,
        final String outputDirectory) {
        final String reportTemplate = initReportTemplate ();
        final String body = suites.stream ()
            .flatMap (suiteToResults ())
            .collect (joining ());
        saveReportTemplate (outputDirectory, reportTemplate.replaceFirst ("</tbody>", format ("{0}</tbody>", body)));
    }

    private List<String> generateReportRows (final String testName, final String suiteName,
        final Set<ITestResult> allTestResults) {
        final var results = new ArrayList<> (allTestResults);
        return IntStream.range (1, allTestResults.size ())
            .boxed ()
            .map (i -> testResultToResultRow (testName, suiteName, results.get (i), i))
            .collect (toList ());
    }

    private String initReportTemplate () {
        final byte[] reportTemplate;
        String template = null;
        try {
            reportTemplate = Files.readAllBytes (Paths.get ("src/test/resources/reportTemplate.html"));
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
                new BufferedWriter (new FileWriter (new File (outputDirectory, "my-report.html"))))) {
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

    private String testResultToResultRow (final String testName, final String suiteName, final ITestResult testResult,
        final int index) {
        final String ROW_TEMPLATE = "<tr class=\"{0}\"><td>{1}</td><td>{2}</td><td>{3}</td><td>{4}</td><td>{5}</td><td>{6}</td><td>{7}</td><td>{8}</td></tr>";
        final var testClass = testResult.getTestClass ()
            .getRealClass ();
        switch (testResult.getStatus ()) {
            case ITestResult.FAILURE:
                return format (ROW_TEMPLATE, "danger", index, suiteName, testName, testClass.getPackageName (),
                    testClass.getSimpleName (), testResult.getName (), "FAILED", "NA");

            case ITestResult.SUCCESS:
                return format (ROW_TEMPLATE, "success", index, suiteName, testName, testClass.getPackageName (),
                    testClass.getSimpleName (), testResult.getName (), "PASSED",
                    String.valueOf (testResult.getEndMillis () - testResult.getStartMillis ()));

            case ITestResult.SKIP:
                return format (ROW_TEMPLATE, "warning", index, suiteName, testName, testClass.getPackageName (),
                    testClass.getSimpleName (), testResult.getName (), "SKIPPED", "NA");

            default:
                return "";
        }
    }
}
