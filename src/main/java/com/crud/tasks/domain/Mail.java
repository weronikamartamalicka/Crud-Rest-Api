package com.crud.tasks.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder
public class Mail {

    private final String mailTo;
    private final String toCc;
    private final String subject;
    private final String message;
}
