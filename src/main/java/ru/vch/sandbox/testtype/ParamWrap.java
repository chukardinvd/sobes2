package ru.vch.sandbox.testtype;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ParamWrap {
    private int p1;
    private Long p2;
    private String p3;
    private LocalDate p4;
}
