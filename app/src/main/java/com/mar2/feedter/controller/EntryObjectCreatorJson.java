package com.mar2.feedter.controller;


import android.os.AsyncTask;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.mar2.feedter.model.EntryObject;

/**
 *
 * @author jmart
 */
public class EntryObjectCreatorJson {

    private URL url;

    private static EntryObjectCreatorJson instance = null;
    private static String[] campos = new String[8];

    public ArrayList<EntryObject> leeFeed() {
        ArrayList<EntryObject> entryArray = new ArrayList<>();
        AsyncGenerateEntryObjects generador = new AsyncGenerateEntryObjects();
        try {
            entryArray = generador.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        return entryArray;
    }

    public String getValue(Element parent, String nodeName) {
            return parent.getElementsByTagName(nodeName).item(0).getFirstChild().getNodeValue();
    }

    private EntryObjectCreatorJson() {
    }

    public void setURL(URL url) {
        this.url = url;
    }

    public static EntryObjectCreatorJson getInstance() {
        if (instance == null) {
            instance = new EntryObjectCreatorJson();
        }
        return instance;
    }

    private class AsyncGenerateEntryObjects extends AsyncTask<Void, Integer, ArrayList<EntryObject>> {

        @Override
        protected ArrayList<EntryObject> doInBackground(Void... params) {
            ArrayList<EntryObject> entryArray = new ArrayList<>();
            DocumentBuilder builder = null;
            try {
                builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                Document doc = builder.parse(url.openStream());
                NodeList items = doc.getElementsByTagName("item");
                for (int i = 0; i < items.getLength(); i++) {
                    Element item = (Element) items.item(i);
                    entryArray.add(new EntryObject(getValue(item, "title"),
                            getValue(item, "pubDate"),
                            getValue(item, "description"),
                            "","",""));
                }
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
               //Toast.makeText(EntryFeed.this, "Error al obtener los items!", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return entryArray;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
/*            int progreso = values[0].intValue();
            pbarProgreso.setProgress(progreso);*/
        }

        @Override
        protected void onPreExecute() {
            /*pbarProgreso.setMax(100);
            pbarProgreso.setProgress(0);*/
        }



        @Override
        protected void onCancelled() {
/*            Toast.makeText(, "Tarea cancelada!", Toast.LENGTH_SHORT).show();*/
        }
    }

    public static String encodeHTML(String s)
    {
        StringBuffer out = new StringBuffer();
        for(int i=0; i<s.length(); i++)
        {
            char c = s.charAt(i);
            if(c > 127 || c=='"' || c=='<' || c=='>')
            {
                out.append("&#"+(int)c+";");
            }
            else
            {
                out.append(c);
            }
        }
        return out.toString();
    }
}
