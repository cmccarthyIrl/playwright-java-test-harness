package com.cmccarthyirl.common;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Test;
import org.testng.ITestResult;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ExtentTests {

    private static final Map<String, ExtentTest> classTestMap = new HashMap<>();
    private static final ThreadLocal<ExtentTest> methodTest = new ThreadLocal<>();
    private static final ThreadLocal<ExtentTest> dataProviderTest = new ThreadLocal<>();

    public static ExtentTest getTest() {
        return Optional.ofNullable(dataProviderTest.get()).orElse(methodTest.get());
    }

    public static ExtentTest getTest(ITestResult result) {
        return (result.getParameters() != null && result.getParameters().length > 0)
                ? dataProviderTest.get()
                : methodTest.get();
    }

    public static void createOrUpdateTestMethod(ITestResult result, boolean createAsChild) {
        if (createAsChild) {
            createTestWithClassHierarchy(result);
            return;
        }
        createOrUpdateTestMethod(result);
    }

    private static void createTestWithClassHierarchy(ITestResult result) {
        String className = result.getTestContext().getName();
        String methodName = result.getMethod().getMethodName();
        String description = result.getMethod().getDescription();
        ExtentTest classTest = classTestMap.computeIfAbsent(className,
                key -> ExtentReporter.extent.createTest(className, description));

        Optional<Test> existingMethodTest = classTest.getModel().getChildren().stream()
                .filter(test -> test.getName().equals(methodName))
                .findFirst();

        if (result.getParameters().length > 0) {
            if (existingMethodTest.isEmpty()) {
                createTest(result, classTest);
            }
            String paramName = Arrays.toString(result.getParameters());
            ExtentTest paramTest = methodTest.get().createNode(paramName);
            dataProviderTest.set(paramTest);
        } else {
            dataProviderTest.remove();
            createTest(result, classTest);
        }

        methodTest.get();
    }

    private static void createOrUpdateTestMethod(ITestResult result) {
        String methodName = result.getMethod().getMethodName();

        if (result.getParameters().length > 0) {
            if (methodTest.get() == null || !methodTest.get().getModel().getName().equals(methodName)) {
                createTest(result, null);
            }
            String paramName = Arrays.toString(result.getParameters());
            ExtentTest paramTest = methodTest.get().createNode(paramName);
            dataProviderTest.set(paramTest);
        } else {
            dataProviderTest.remove();
            createTest(result, null);
        }

        methodTest.get();
    }

    private static void createTest(ITestResult result, ExtentTest classTest) {
        String methodName = result.getMethod().getMethodName();
        String description = result.getMethod().getDescription();

        ExtentTest test = (classTest != null)
                ? classTest.createNode(methodName, description)
                : ExtentReporter.extent.createTest(methodName, description);

        methodTest.set(test);
        assignGroups(test, result.getMethod().getGroups());
    }

    private static void assignGroups(ExtentTest test, String[] groups) {
        if (groups != null && groups.length > 0) {
            Arrays.stream(groups).forEach(group -> {
                if (group.startsWith("d:") || group.startsWith("device:")) {
                    test.assignDevice(group.replaceAll("d:|device:", ""));
                } else if (group.startsWith("a:") || group.startsWith("author:")) {
                    test.assignAuthor(group.replaceAll("a:|author:", ""));
                } else if (group.startsWith("t:") || group.startsWith("tag:")) {
                    test.assignCategory(group.replaceAll("t:|tag:", ""));
                } else {
                    test.assignCategory(group);
                }
            });
        }
    }

    private static Status mapStatus(int statusCode) {
        switch (statusCode) {
            case ITestResult.FAILURE:
                return Status.FAIL;
            case ITestResult.SUCCESS:
                return Status.PASS;
            default:
                return Status.SKIP;
        }
    }
}