package org.svb.imc.util;

import java.sql.Timestamp;
import java.util.Date;
 
public class Utils 
{
    public static String getTimestamp()
    {
     Date date= new Date();
     Timestamp ts = new Timestamp(date.getTime());
     return ts.toString();
    }
}