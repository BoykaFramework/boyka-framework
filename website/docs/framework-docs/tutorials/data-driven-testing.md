---
sidebar_position: 5
title: ✅ Data Driven Testing
---

## Introduction

Data Driven Testing (DDT) is a testing methodology that allows you to run the same test case multiple times with different sets of data. This approach is particularly useful when you want to validate the same functionality with various inputs and expected outputs.

In this tutorial, we will explore how to implement Data Driven Testing in our test cases using the Boyka Framework.

## Step 1: Configure the Test Data

To implement Data Driven Testing in the Boyka Framework, you need to configure the test data. The framework supports reading test data from Excel files.

In the `boyka-config.json` file, you need to specify the path to the Excel file and the sheet name that contains the test data.

```json
{
  "data": {
    "path": "data/excel"
  }
}
```

:::info
Check out more details about the Test data configuration in the [Configuration](/docs/guides/config/configuration#data-config) section.
:::

## Step 2: Create a Test Case

First, you need to create the test case that you want in an Excel file in the folder mentioned in the config file.

For this example, we will create a simple test case for Restful Booking API.

Following is the Excel file structure:

| SrNo | FirstName | LastName | TotalPrice | DepositPaid | CheckInDate | CheckOutDate | AdditionalNeeds | Enabled |
|------|-----------|----------|------------|-------------|--------------|---------------|-----------------|--------|
| 1 | Wasiq | Bhamla | 1000 | Yes | 2023-12-01 | 2023-12-03 | No needs | TRUE |
| 2 | Faisal | Khatri | 2000 | No | 2023-12-03 | 2023-12-05 | Breakfast | TRUE |
| 3 | John | Doe | 3000 | Yes | 2023-12-03 | 2023-12-05 | Lunch | TRUE |
| 4 | George | Gunner | 6700 | No | 2023-12-05 | 2023-12-11 | Dinner | FALSE |

## Step 3: Create a Test data class

Create a test data class that will hold the data for each test case. This class will be used to read the data from the Excel file and pass it to the test case.

Following is the sample test data class:

```java
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class BookingTestData {
  private String  additionalNeeds;
  private String  checkInDate;
  private String  checkOutDate;
  private String  depositPaid;
  private Boolean enabled;
  private String  firstName;
  private String  lastName;
  private Double  srNo;
  private Double  totalPrice;
}
```

:::warning[Important]
Since we are using `@Data` annotation from Lombok, it will create getters and setters for all the fields in the class.

For example, `srNo` will be converted to `getSrNo()` and `setSrNo(Double srNo)` methods.

You need to make sure that the column name in the Excel file matches the field setter method name (**without the prefix**) in the class.

Example, `SrNo` in the Excel file will be converted to `setSrNo(Double srNo)` method in the class.
:::

## Approach 1: Using POJO class object

In this approach, we will use the POJO class object to read the data from the Excel file and pass it to the test case.

### Step 4: Create a Test data provider Class

In this step, we will create a test data provider class that will read the data from the Excel file and return it as a list of `BookingTestData` objects.

Following is the sample test data provider class:

```java
import static io.github.boykaframework.actions.data.TestDataAction.withData;

import java.util.Iterator;

import io.github.boykaframework.actions.interfaces.data.ITestDataAction;
import io.github.boykaframework.testng.api.restful.pojo.BookingTestData;
import org.testng.annotations.DataProvider;

public final class BookingDataProviders {
  private static final ITestDataAction DATA;

  static {
    // The Excel file should be placed in the `src/test/resources` folder.
    // highlight-next-line
    DATA = withData ("BookingData").inBlock ("Bookings");
  }

  @DataProvider
  public static Iterator<Object[]> getBookingDataObject () {
    // Here, we get the data from the Excel file and filter it based on the `Enabled` column.
    // highlight-next-line
    final var rows = DATA.get (BookingTestData.class);
    return rows.stream ()
      .filter (BookingTestData::getEnabled)
      .map (d -> new Object[] { d })
      .toList ()
      .iterator ();
  }
}
```

Here, we are using the `withData` method to read the data from the Excel file. The `inBlock` method is used to specify the sheet name in the Excel file.

The `get` method is used to read the data from the Excel file and convert it to a list of `BookingTestData` objects.

### Step 5: Create a Test Class

In this step, we will create a test class that will use the test data provider class to run the test cases.

Following is the sample test class:

```java
import static io.github.boykaframework.actions.api.ApiActions.withRequest;
import static io.github.boykaframework.enums.PlatformType.API;
import static io.github.boykaframework.manager.ParallelSession.clearSession;
import static io.github.boykaframework.manager.ParallelSession.createSession;

import java.text.DecimalFormat;

import io.github.boykaframework.actions.interfaces.data.IDataRow;
import io.github.boykaframework.builders.ApiRequest;
import io.github.boykaframework.enums.RequestMethod;
import io.github.boykaframework.testng.api.restful.data.BookingDataProviders;
import io.github.boykaframework.testng.api.restful.pojo.BookingData;
import io.github.boykaframework.testng.api.restful.pojo.BookingDates;
import io.github.boykaframework.testng.api.restful.pojo.BookingTestData;
import lombok.SneakyThrows;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DataDrivenBookingTest {
  @BeforeClass
  public void setupClass () {
    createSession (API, "test_restfulbooker");
  }

  @AfterClass
  public void tearDownTestClass () {
    clearSession ();
  }

  // Here, we are using the `dataProvider` attributes to specify the test data provider class and method.
  // highlight-next-line
  @Test (dataProviderClass = BookingDataProviders.class, dataProvider = "getBookingDataObject")
  public void testBookingObject (final BookingTestData bookingTestData) {
    final var depositPaid = bookingTestData.getDepositPaid ()
      .equalsIgnoreCase ("yes");
    final var bookingData = BookingData.builder ()
      .firstname (bookingTestData.getFirstName ())
      .lastname (bookingTestData.getLastName ())
      .totalprice (bookingTestData.getTotalPrice ()
        .intValue ())
      .depositpaid (depositPaid)
      .bookingdates (BookingDates.builder ()
        .checkin (bookingTestData.getCheckInDate ())
        .checkout (bookingTestData.getCheckOutDate ())
        .build ())
      .additionalneeds (bookingTestData.getAdditionalNeeds ())
      .build ();

    testBooking (bookingData);
  }

  private void testBooking (final BookingData bookingData) {
    final var request = ApiRequest.createRequest ()
      .method (RequestMethod.POST)
      .header ("Accept", "application/json")
      .path ("/booking")
      .bodyObject (bookingData)
      .create ();

      final var response = withRequest (request).execute ();

      response.verifyStatusCode ()
        .isEqualTo (200);
      response.verifyStatusMessage ()
        .isEqualTo ("OK");
      response.verifySchema ("create-booking-schema.json");
      response.verifyTextField ("bookingid")
        .isNotNull ();
      response.verifyTextField ("booking.firstname")
        .isEqualTo (bookingData.getFirstname ());
      response.verifyBooleanField ("booking.depositpaid")
        .isEqualTo (bookingData.isDepositpaid ());
      response.verifyHeader ("Content-Type")
        .isEqualTo ("application/json; charset=utf-8");
  }
}
```

## Approach 2: Using IDataRow interface object

In this approach, we will use the `IDataRow` interface object to read the data from the Excel file and pass it to the test case.

### Step 4: Create a Test data provider method

In this step, we will create a test data provider method that will read the data from the Excel file and return it as a list of `IDataRow` objects.

Following is the sample test data provider method:

```java
  . . .
  @DataProvider
  public static Iterator<Object[]> getBookingData () {
    // Here, we get the data from the Excel file using the `rows` method.
    // highlight-next-line
    final var rows = DATA.rows ();
    return rows.stream ()
      .map (d -> new Object[] { d })
      .toList ()
      .iterator ();
  }
  . . .
```

Here, we are using the `rows` method to read the data from the Excel file and convert it to a list of `IDataRow` objects.

### Step 5: Create a Test Class using IDataRow interface

In this step, we will create a test class that will use the test data provider method to run the test cases.

Following is the sample test class:

```java
. . .
@SneakyThrows
@Test (dataProviderClass = BookingDataProviders.class, dataProvider = "getBookingData")
public void testBooking (final IDataRow row) {
  final var depositPaid = row.cell ("DepositPaid")
    .toString ()
    .equalsIgnoreCase ("yes");
  final var formatter = new DecimalFormat ("#0.0");
  final var bookingData = BookingData.builder ()
    .firstname (row.cell ("FirstName"))
    .lastname (row.cell ("LastName"))
    .totalprice (formatter.parse (row.cell ("TotalPrice")
      .toString ())
      .intValue ())
    .depositpaid (depositPaid)
    .bookingdates (BookingDates.builder ()
      .checkin (row.cell ("CheckInDate")
        .toString ())
      .checkout (row.cell ("CheckOutDate")
        .toString ())
      .build ())
    .additionalneeds (row.cell ("AdditionalNeeds"))
    .build ();

  testBooking (bookingData);
}
. . .
```

Here, we are using the `cell` method to read the data from the Excel file for a specific column based on the column name.

## Conclusion

In this tutorial, we have explored how to implement Data Driven Testing in the Boyka Framework using Excel files. We have seen two approaches to implement DDT: using POJO class objects and using `IDataRow` interface objects.

Both approaches are valid and can be used based on your preference. The Boyka Framework provides a flexible way to implement DDT, making it easy to validate the same functionality with different sets of data.

You can now use Data Driven Testing in your test cases to improve the efficiency and coverage of your tests. Happy testing!
