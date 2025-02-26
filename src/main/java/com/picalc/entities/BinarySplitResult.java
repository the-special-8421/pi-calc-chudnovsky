package com.picalc.entities;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class BinarySplitResult {
    private BigDecimal Pab, Qab, Rab;
}
