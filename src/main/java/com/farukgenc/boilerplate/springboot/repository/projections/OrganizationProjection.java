package com.farukgenc.boilerplate.springboot.repository.projections;

import java.util.UUID;

public interface OrganizationProjection {
    UUID getId();

    String getName();

    String getEmail();

    String getPhoneNumber();

    String getType();

    String getOtherType();

    String getMissionStatement();

    String getAddress();

    String getCity();

    String getStateProvince();

    String getZipPostalCode();

    String getCountry();
}
