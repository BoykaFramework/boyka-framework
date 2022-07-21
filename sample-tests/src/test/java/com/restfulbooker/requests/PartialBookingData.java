package com.restfulbooker.requests;

import lombok.Builder;
import lombok.Data;

/**
 * Created By Faisal Khatri on 22/07/2022
 */
@Data
@Builder
public class PartialBookingData {
    String firstname;
    int    totalprice;
}
