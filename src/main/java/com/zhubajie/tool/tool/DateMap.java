package com.zhubajie.tool.tool;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/6.
 */
public class DateMap {
    public Map<String, Date> getRelatedDate() {
        Map<String, Date> relatedDate = new HashMap<String, Date>();
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        try {
            Calendar date = Calendar.getInstance();
            date.setTime(now);
            date.add(Calendar.YEAR, -1);
            Date lastyear = dft.parse(dft.format(date.getTime()));
            date.setTime(now);
            date.add(Calendar.MONTH, -6);
            Date lastsixmoths = dft.parse(dft.format(date.getTime()));
            date.setTime(now);
            date.add(Calendar.MONTH, -3);
            Date lastthreemoths = dft.parse(dft.format(date.getTime()));
            date.setTime(now);
            date.add(Calendar.MONTH, -1);
            Date lastmoth = dft.parse(dft.format(date.getTime()));
            date.setTime(now);
            date.add(Calendar.WEEK_OF_MONTH, -1);
            Date lastweek = dft.parse(dft.format(date.getTime()));
            date.setTime(now);
            date.add(Calendar.DAY_OF_MONTH, -3);
            Date lastthreedays = dft.parse(dft.format(date.getTime()));
            date.setTime(now);
            date.add(Calendar.DAY_OF_MONTH, -1);
            Date lastday = dft.parse(dft.format(date.getTime()));
            date.setTime(now);
            date.add(Calendar.YEAR, 1);
            Date nextyear = dft.parse(dft.format(date.getTime()));
            date.setTime(now);
            date.add(Calendar.MONTH, 6);
            Date nextsixmoths = dft.parse(dft.format(date.getTime()));
            date.setTime(now);
            date.add(Calendar.MONTH, 3);
            Date nextthreemoths = dft.parse(dft.format(date.getTime()));
            date.setTime(now);
            date.add(Calendar.MONTH, 1);
            Date nextmoth = dft.parse(dft.format(date.getTime()));
            date.setTime(now);
            date.add(Calendar.WEEK_OF_MONTH, 1);
            Date nextweek = dft.parse(dft.format(date.getTime()));
            date.setTime(now);
            date.add(Calendar.DAY_OF_MONTH, 3);
            Date nextthreedays = dft.parse(dft.format(date.getTime()));
            date.setTime(now);
            date.add(Calendar.DAY_OF_MONTH, 1);
            Date nextday = dft.parse(dft.format(date.getTime()));
            relatedDate.put("nextyear", nextyear);
            relatedDate.put("nextsixmoths", nextsixmoths);
            relatedDate.put("nextthreemoths", nextthreemoths);
            relatedDate.put("nextmoth", nextmoth);
            relatedDate.put("nextweek", nextweek);
            relatedDate.put("nextthreedays", nextthreedays);
            relatedDate.put("nextday", nextday);
            relatedDate.put("now", now);
            relatedDate.put("lastyear", lastyear);
            relatedDate.put("lastsixmoths", lastsixmoths);
            relatedDate.put("lastthreemoths", lastthreemoths);
            relatedDate.put("lastmoth", lastmoth);
            relatedDate.put("lastweek", lastweek);
            relatedDate.put("lastthreedays", lastthreedays);
            relatedDate.put("lastday", lastday);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return relatedDate;
    }
}
