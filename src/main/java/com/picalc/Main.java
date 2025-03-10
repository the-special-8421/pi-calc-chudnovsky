package com.picalc;

import com.picalc.entities.BinarySplitResult;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Main {
    private static int DIGITS_OF_PI = 10000;

    public static void main(String[] args) throws IOException {
        if (args.length > 0 && !args[0].isEmpty()) DIGITS_OF_PI = Integer.parseInt(args[0]);
        try (FileWriter writer = new FileWriter("pi_" + DIGITS_OF_PI + ".txt")) {
            writer.write(chudnovsky(BigDecimal.valueOf(DIGITS_OF_PI).divide(
                    BigDecimal.valueOf(14.18164), 0, RoundingMode.CEILING)).toString().substring(0, DIGITS_OF_PI+5));
        }
    }

    private static BigDecimal chudnovsky(BigDecimal n) {
        BinarySplitResult P1nQ1nR1n = binarySplit(BigDecimal.ONE, n);
        return BigDecimal.valueOf(426880).multiply(BigDecimal.valueOf(10005).sqrt(new MathContext(DIGITS_OF_PI+5)).multiply(P1nQ1nR1n.getQab()))
                .divide(BigDecimal.valueOf(13591409).multiply(P1nQ1nR1n.getQab()).add(P1nQ1nR1n.getRab()), DIGITS_OF_PI+5, RoundingMode.HALF_EVEN);
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