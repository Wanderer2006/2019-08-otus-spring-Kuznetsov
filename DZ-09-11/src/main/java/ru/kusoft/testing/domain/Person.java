package ru.kusoft.testing.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Person {
    private final String firstName;
    private final String secondName;
}
