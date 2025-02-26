package com.picalc;

import com.picalc.entities.BinarySplitResult;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Main {
    private final static int SCALE = 1000000;
    private final static int CHUDNOVSKY_ITERATIONS = 70514;

    public static void main(String[] args) {
        System.out.println(chudnovsky(CHUDNOVSKY_ITERATIONS).toString().substring(0, SCALE+2));
    }

    private static BigDecimal chudnovsky(int n) {
        BinarySplitResult P1nQ1nR1n = binarySplit(BigDecimal.ONE, BigDecimal.valueOf(n));
        return BigDecimal.valueOf(426880).multiply(BigDecimal.valueOf(10005).sqrt(new MathContext(SCALE+2)).multiply(P1nQ1nR1n.getQab()))
                .divide(BigDecimal.valueOf(13591409).multiply(P1nQ1nR1n.getQab()).add(P1nQ1nR1n.getRab()), SCALE+2, RoundingMode.HALF_EVEN);
    }

    private static BinarySplitResult binarySplit(BigDecimal a, BigDecimal b) {
        if (a.add(BigDecimal.ONE).equals(b)) {
            BigDecimal Pab = BigDecimal.valueOf(-1).multiply((BigDecimal.valueOf(6).multiply(a)).subtract(BigDecimal.valueOf(5)))
                    .multiply((BigDecimal.valueOf(2).multiply(a)).subtract(BigDecimal.ONE))
                    .multiply((BigDecimal.valueOf(6).multiply(a)).subtract(BigDecimal.ONE));
            BigDecimal Qab = BigDecimal.valueOf(10939058860032000L).multiply(a.pow(3));
            BigDecimal Rab = Pab.multiply((BigDecimal.valueOf(545140134).multiply(a)).add(BigDecimal.valueOf(13591409)));
            return new BinarySplitResult().setPab(Pab).setQab(Qab).setRab(Rab);
        } else {
            BigDecimal m = (a.add(b)).divide(BigDecimal.valueOf(2), 0, RoundingMode.FLOOR);
            BinarySplitResult PamQamRam = binarySplit(a, m);
            BinarySplitResult PmbQmbRmb = binarySplit(m, b);
            return new BinarySplitResult().setPab(PamQamRam.getPab().multiply(PmbQmbRmb.getPab()))
                    .setQab(PamQamRam.getQab().multiply(PmbQmbRmb.getQab()))
                    .setRab((PmbQmbRmb.getQab().multiply(PamQamRam.getRab())).add(PamQamRam.getPab().multiply(PmbQmbRmb.getRab())));
        }
    }
}
