package vislic;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.io.*;
import java.security.MessageDigest;
import java.security.*;

public class Vislic
{
    public void MakeLicense()
    {
        String ss="",sc="";
        Calendar cal=new GregorianCalendar();
        cal.setTimeInMillis(System.currentTimeMillis());
        cal.add(Calendar.MONTH,6);
        String dt=new SimpleDateFormat("dd.MM.yy").format(cal.getTime());
        String[] d=dt.split("\\.");
        String License="Ошибка в заданных параметрах";
        try
        {
            if(d.length==3)
            {
                int day = Integer.parseInt(d[0]);
                int month = Integer.parseInt(d[1]);
                int year = Integer.parseInt(d[2]);
                if (day >= 1 && day <= 31 && month >= 1 && month <= 12 &&
                    year >= 5 && year < 22) {
//                    ss+="e"+ (d[2].length()==1 ? "0"+d[2] : d[2] )+ (d[1].length()==1 ? "0"+d[1] : d[1] )+ (d[0].length()==1 ? "0"+d[0] : d[0] );
// Дата окончания лицензии
ss="e210201";
                    sc=ss+"-";
// o - кол-во объектов 0-неограничено
                    int o=0;
                    ss+="c"+o/1000;
                    if(o!=0) sc+="c"+o/1000+"-";
// u - кол-во пользователей, 0- неограниченно
                    int u=0;
                    ss+="u"+u;
                    if(u!=0) sc+="u"+u+"-";
// s -кол-во сегментов
                    int s=17;
                    ss+="s"+s;
                    sc+="s"+s+"-";
// f -features
                    String f="1111111111";
                    if(f.matches("[01]+"))
                    {
                        ss+="f"+Integer.parseInt(f,2);
                        sc+="f"+Integer.parseInt(f,2)+"a";
// ОС Windows?                        
                        boolean x86=true;
// Hostid                        
                        String hostid="4cd20174";
                        if(hostid.matches("[0-9a-fA-F]+"))
                        {
                            MessageDigest md=null;
                            try {
                                    md=MessageDigest.
                                    getInstance("MD5");
                            } catch (NoSuchAlgorithmException
                                    ex1) {
                            }
                            String[] x={"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};
                            String sbc=hostid;
                            if(!x86)
                            {

                                byte[] bc=md.digest(hostid.toUpperCase().getBytes());
                                sbc="";
                                int bbc;
                                for(int ibc=0;ibc<4;ibc++)
                                {
                                    bbc=bc[4+ibc];
                                    if(bbc<0) bbc+=256;
                                    sbc+=x[bbc/16]+x[bbc%16];
                                }
                            }

//  Port
                            int port=55555;
                            ss+="/"+sbc+":"+port+"/KsiLK28+=/";
// n1- номер лицензии
                                String nl="6670";
                                if (nl.matches("\\d+")) {
                                ss += nl;
                                byte[] b = null;
                                try {
                                    b = ss.getBytes(
                                            "US-ASCII");
                                } catch (
                                        UnsupportedEncodingException
                                        ex) {
                                }

                                md.reset();
                                byte[] out = md.digest(b);
                                int ll = out.length;
                                String ss0 = "";
                                int bb;
                                for (int j = 0; j < ll; j++) {
                                    bb = out[j];
                                    if (bb < 0) bb += 256;
                                    ss0 += x[bb / 16] + x[bb % 16];
                                }
                                byte[] b2=new byte[4];
                                for(int k=0;k<4;k++)
                                {
                                    bb=out[k];
                                    if(bb<0) bb+=256;
                                    b2[k]=(byte)(97+(bb % 26));
                                }
                                License=sc+new String(b2);
                                System.out.println("Исходные строки: "+ss+"   "+sc+"\nhash-code: "+ new String(out)+" ("+ss0+")\nЛицензия: "+License);
                           }
                        }
                    }
                    
                }
            }
        }
        catch(NumberFormatException e)
        {

        }
    }

    public static void main(String[] args)
    {
        Vislic vislic=new Vislic();
        vislic.MakeLicense();
        System.exit(0);
    }
}

