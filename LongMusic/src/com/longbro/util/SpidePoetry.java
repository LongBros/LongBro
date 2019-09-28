package com.longbro.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * 爬取http://www.exam58.com的诗词
 */
public class SpidePoetry {
    public static  void  main(String args[]){
        getPoems();
    }

    /**
     * 得到所有诗人的所有诗的链接
     */
    private static void getPoems() {
        try{
            Document doc= Jsoup.connect("http://www.exam58.com/lbds").get();
            ArrayList<String> poets=getPoets(doc,"d6");//得到所有诗人的链接

            for(String poet:poets){
                Document doc1= Jsoup.connect("http://www.exam58.com/"+poet).get();
                Elements e=doc1.getElementsByClass("listbox");
                String poems[]=e.toString().split("</b>]");
                for (int i=1;i<poems.length;i++){
                    System.out.println("--------------------------------------");
                    poems[i]=poems[i].substring(0,poems[i].indexOf("</li>"));
                    String poem=poems[i].substring(poems[i].indexOf("href")+6,poems[i].indexOf("html")+4);//古诗链接
                    String poetT="";//诗人名
                    String poemC="";//古诗诗名
                    if(poems[i].contains("》")){
                        if(poems[i].contains("<b>")){
                            poetT=poems[i].substring(poems[i].indexOf("_blank\">")+11,poems[i].indexOf("《"));
                        }else{
                            poetT=poems[i].substring(poems[i].indexOf("_blank\">")+8,poems[i].indexOf("《"));
                        }
                        poemC=poems[i].substring(poems[i].indexOf("《")+1,poems[i].indexOf("》"));
                    }
                    System.out.println(poems[i]);

                    System.out.println(poem+"--"+poetT+"--《"+poemC+"》");
                }
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            }

        }catch (Exception e){

        }
    }

    private static ArrayList<String> getPoets(Document doc, String d6) {
        ArrayList<String> poets=new ArrayList<String>();
        Elements poet = doc.getElementsByClass(d6);
        String spoet=poet.toString();
        String s[]=spoet.split("<li>");
        for (String spo: s){
            System.out.println(spo);
            if(spo.contains("</li>")){
                String spoeHref="";
                if (spo.contains("class")){
                    spoeHref=spo.substring(spo.indexOf("href")+7,spo.indexOf("class")-3);//诗人链接
                }else{
                    spoeHref=spo.substring(spo.indexOf("href")+7,spo.indexOf("\">")-1);//诗人链接
                }
                String spoeName=spo.substring(spo.indexOf("\">")+2,spo.indexOf("</a>")-2);
                //System.out.println(spoeHref+"--------"+spoeName);
                poets.add(spoeHref);
            }

        }
        return  poets;
    }
}
