package com.mowitnow.mower.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;

@EqualsAndHashCode
@AllArgsConstructor
@Value
public class MowerData {
    Mower mower;
    List<Command> commands;
}
