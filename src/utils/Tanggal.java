package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tanggal {
    public static String konversiFormat(String tanggal, String formatLama, String formatBaru) {
        try {
            SimpleDateFormat dt = new SimpleDateFormat(formatLama); 
            Date date = dt.parse(tanggal); 
            SimpleDateFormat dt1 = new SimpleDateFormat(formatBaru);
            
            return dt1.format(date);
        } catch (Exception e) {
            return "";
        }
    }
    
    public static String konversiFormat(String tanggal, String formatLama) {
        try {
            SimpleDateFormat dt = new SimpleDateFormat(formatLama);
            Date date = dt.parse(tanggal);
            SimpleDateFormat dt1 = new SimpleDateFormat("d M yyyy");
            
            return dt1.format(date);
        } catch (Exception e) {
            return "";
        }
    }
    
    public static String konversiFormat(String tanggal) {
        try {
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd"); 
            Date date = dt.parse(tanggal); 
            SimpleDateFormat dt1 = new SimpleDateFormat("d MMMM yyyy");
            
            return dt1.format(date);
        } catch (Exception e) {
            return "";
        }
    }
}
