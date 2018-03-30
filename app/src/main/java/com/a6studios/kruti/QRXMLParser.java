package com.a6studios.kruti;

/**
 * Created by HP on 3/16/2018.
 */

public class QRXMLParser {
    private String xml;

    public QRXMLParser(String xml1){
        xml = xml1;
    }

    public String getAttribute(String x) {
        int p, q, r;

        p = xml.indexOf(x);

        if(p==-1)
            return null;
        else {
            q = xml.indexOf("\"",p);
            r = xml.indexOf("\"",q+1);
            //s = xml.indexOf("\"",r);
            String result = xml.substring(q+1,r);
            return result;
        }
    }
}
