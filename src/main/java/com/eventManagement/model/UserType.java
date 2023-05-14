package com.eventManagement.model;

public enum UserType {

	UNIVERSITY_STUDENTS,
	SCHOLARSHIP_STUDENTS,
	DP_EMPLOYEE,
	GUEST_USER,
	ALL;
	
	 public static UserType fromValue(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }
        switch (value) {
            case "University Students":
                return UNIVERSITY_STUDENTS;
            case "Scholarship Students":
                return SCHOLARSHIP_STUDENTS;
            case "DP Employee":
                return DP_EMPLOYEE;
            case "Guest User":
                return GUEST_USER;
            case "All":
                return ALL;
            default:
                throw new IllegalArgumentException("Invalid value: " + value);
        }
    }
}
