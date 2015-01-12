/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.tests;

import br.gov.ana.entities.TecnicoUsina;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author lucas.nunes
 */
public class Main {

    public Main() {
    }

    /**
     *
     * @param args
     *//*
     public static void main(String[] args) {
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
     DateTimeZone timeZone = DateTimeZone.forID("GMT");
     DateTimeZoneBuilder builder = new DateTimeZoneBuilder();
        
     DateTime dt = new DateTime(timeZone);
     System.out.println(sdf.format(dt.toDate()));
        
     TimeZone timeZone1 = new SimpleTimeZone(0, "GMT");
     Date date = new Date(dt.getMillis());
     Calendar cal = Calendar.getInstance(timeZone1);
     cal.setTimeInMillis(dt.getMillis());
     System.out.println(sdf.format(cal.getTime()));

     }*/

    public String getHoraMenosUm() {
        DateFormat gmtFormat = new SimpleDateFormat("HH:00:00");
        TimeZone gmtTime = TimeZone.getTimeZone("GMT");
        gmtFormat.setTimeZone(gmtTime);

        Calendar c = Calendar.getInstance();
        c.setTime(new Date(1386856800000L));
        c.add(Calendar.HOUR_OF_DAY, -2);
        
        return gmtFormat.format(c.getTime());
    }

    public static void main(String[] args) throws IOException {
        DateFormat gmtFormat = new SimpleDateFormat("HH:00:00");
        TimeZone gmtTime = TimeZone.getTimeZone("GMT");
        gmtFormat.setTimeZone(gmtTime);

        Calendar c = Calendar.getInstance();        
        c.add(Calendar.HOUR_OF_DAY, -2);

        System.out.println("Current DateTime in GMT : " + gmtFormat.format(c.getTime()));
    }
    /*
     public static void main(String[] args) throws IOException {
     Main test = new Main();
     Date fromDate = Calendar.getInstance().getTime();
     System.out.println("UTC Time - " + fromDate);
     System.out.println("GMT Time - " + test.cvtToGmt(fromDate));
     }

     private Date cvtToGmt(Date date) {
     TimeZone tz = TimeZone.getDefault();
     Date ret = new Date(date.getTime() - tz.getRawOffset());

     // if we are now in DST, back off by the delta.  Note that we are checking the GMT date, this is the KEY.
     if (tz.inDaylightTime(ret)) {
     Date dstDate = new Date(ret.getTime() - tz.getDSTSavings());

     // check to make sure we have not crossed back into standard time
     // this happens when we are on the cusp of DST (7pm the day before the change for PDT)
     if (tz.inDaylightTime(dstDate)) {
     ret = dstDate;
     }
     }
     return ret;
     }*/
}

class TecnicoUsinaComparable implements Comparator<TecnicoUsina> {

    @Override
    public int compare(TecnicoUsina o1, TecnicoUsina o2) {
        int valor = o1.getTusTecId().getTecNm().compareTo(o2.getTusTecId().getTecNm());
        return (valor != 0 ? valor : 1);
    }
}
/*
 BigDecimal bd = new BigDecimal("1.522");
 System.out.println(bd.toString());

 BigDecimal bd2 = new BigDecimal(4 / 3);
        
 System.out.println(bd2.doubleValue());
 */
// grau: 40 
// hora: 12 
// minuto: 40.66 
// decimal: 40.21129444        
//System.out.println(""+(Math.random()*2)*3);

/*
 * BigDecimal b1 = new BigDecimal(41.99999999999999994444400000000);
 * BigDecimal b2 = new
 * BigDecimal(41.666657477777777777777777777777700000000000000000000000000007);
 *
 * //b1.setScale(0,RoundingMode.CEILING); //b1.setScale(0);
 * b2.setScale(0,BigDecimal.ROUND_UP);
 *
 * System.out.println(b1.doubleValue());
 * System.out.println(b2.doubleValue());
 *
 */
/*
 * double g = 0; double m = 0; double s = 0;
 *
 * double decimal = 0; double grau = 40; double minuto = 2; double
 * segundo = 4348.46;
 *
 * decimal = grau + (minuto / 60) + (segundo / 3600);
 * System.out.println("Decimal: " + Math.abs(decimal)); g =
 * Math.floor(decimal); System.out.printf("%.0f", g); m =
 * Math.floor((decimal-g)*60); System.out.printf(", %.0f", m); s =
 * (((decimal-g)*60)-m)*60; System.out.printf(", %.5f", s);
 * System.out.println();
 */
/*
 *
 */
//EntidadeFacade facade = new EntidadeFacade(em);
//EntidadeFacade facade = new EntidadeFacade();
//for(Entidade entidade : facade.findAll()){
//   System.out.println(entidade.getNome());
//}
//57°54'42,99"

/*
 * BigDecimal decimal = new BigDecimal("534002"); decimal.precision();
 * decimal.round(MathContext.UNLIMITED);
 * System.out.println(ConversorLatLong.converte("57544299").round(MathContext.DECIMAL32).toString());
 *
 * String temp = "333";
 *
 * System.out.println(temp.length());
 */
//String cnpj = "00.000.000/0000-00";
        /*
 * String cnpj = "00000000000000"; String uncnpj = "00.000.000/0000-00";
 *
 * System.out.println(cnpj.substring(0,
 * 2)+"."+cnpj.substring(2,5)+"."+cnpj.substring(5,8)+"/"+cnpj.substring(8,12)+"-"+cnpj.substring(12,14));
 * uncnpj = uncnpj.replace(".", ""); uncnpj = uncnpj.replace("/", "");
 * uncnpj = uncnpj.replace("-", "");
 *
 * System.out.println(uncnpj.replace(".", "").replace("/",
 * "").replace("-", "")); System.out.println(uncnpj);
 */
//"."+cnpj.substring(2,3)+"."+cnpj.substring(5,3)+"/"+cnpj.substring(8,4)+"-"+cnpj.substring(10,2)

        /*
         * try { String palavra = "Vy5UbpAs"; MessageDigest digest =
         * MessageDigest.getInstance("MD5"); BigInteger hash = new
         * BigInteger(1,digest.digest(palavra.getBytes("UTF-8")));
         * System.out.println(hash.toString(16).toUpperCase()); } catch
         * (Exception e) { System.out.println(e.getMessage()); }
         * System.out.println(""+(0/3600)); BigInteger in = new BigInteger("0");
         *
         * System.out.println(in.divide(new BigInteger("4")));
         */