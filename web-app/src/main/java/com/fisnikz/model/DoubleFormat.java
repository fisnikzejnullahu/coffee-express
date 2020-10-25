package com.fisnikz.model;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Model;
import javax.inject.Named;
import java.text.DecimalFormat;

/**
 * @author Fisnik Zejnullahu
 */
@ApplicationScoped
@Named("doubleFormatter")
public class DoubleFormat {

    public static String format(double num) {
        if (num == 0) return "0.00";
        return new DecimalFormat("#.##").format(num);
    }
}
