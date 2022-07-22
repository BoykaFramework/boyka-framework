package com.restfulbooker.tests;

import static com.github.wasiqb.boyka.builders.ApiRequest.createRequest;
import static com.github.wasiqb.boyka.enums.RequestMethod.POST;
import static com.github.wasiqb.boyka.manager.ApiManager.execute;

import com.github.wasiqb.boyka.builders.ApiRequest;
import com.github.wasiqb.boyka.builders.ApiResponse;
import com.restfulbooker.requests.BookingData;
import com.restfulbooker.requests.BookingDataBuilder;
import com.restfulbooker.requests.PartialBookingData;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by Faisal Khatri on 22/07/2022
 */
public class APITests {

    private static final String             API_CONFIG_KEY = "test_restfulbooker";
    private              BookingData        newBooking;
    private              BookingData        updatedBooking;
    private              PartialBookingData partialUpdateBooking;
    private              int                bookingId;

    @BeforeTest
    public void testSetup () {
        BookingDataBuilder builder = new BookingDataBuilder ();
        newBooking = builder.bookingDataBuilder ();
        updatedBooking = builder.bookingDataBuilder ();
        partialUpdateBooking = builder.partialBookingBuilder ();
    }

    @Test
    public void createBookingTest () {
        ApiRequest createBookingRequest = createRequest ().configKey (API_CONFIG_KEY)
            .method (POST)
            .bodyObject (newBooking)
            .create ();

        ApiResponse response = execute (createBookingRequest);
        response.verifyStatusCode ()
            .isEqualTo (200);
        response.verifyTextField ("bookingid")
            .isNotNull ();
        response.verifyTextField ("booking.firstname")
            .isEqualTo (newBooking.getFirstname ());
    }

}
