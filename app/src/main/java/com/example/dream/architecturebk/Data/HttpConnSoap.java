package com.example.dream.architecturebk.Data;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by dream on 2016/12/31.
 */

final class HttpConnSoap {
    public InputStream GetWebServer(String methodName, ArrayList<String> Parameters, ArrayList<String> ParamValues){
        ArrayList<String> values=new ArrayList<String>();  //用于存储从服务器获取来的值
        String ServerUrl="http://10.0.2.2:2051/ArchitectureWebService.asmx";
        String soapAction="http://tempuri.org/"+methodName;
        String soapStart="<?xml version=\"1.0\"  encoding=\"utf-8\"?>"
                + "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                + "<soap:Body />";
        String mreakString="<"+methodName+"xmlns=\"http://tempuri.org/\">";

        String argueString, paramString,temp;
        for (int i=0;i<Parameters.size();i++){
            paramString=Parameters.get(i).toString();
            argueString=ParamValues.get(i).toString();    //设置方法的参数名为webService中的参数的名称
            temp="<"+paramString+">"+argueString+"</"+paramString+">";
            mreakString=mreakString+temp;
        }
        mreakString = mreakString + "</" + methodName + ">";
        String soapEnd="</soap:Envelope>";
        String requestData=soapStart+mreakString+soapEnd;
        Log.d("requestData",requestData);

        try{
            URL url=new URL(ServerUrl);
            HttpURLConnection con=(HttpURLConnection)url.openConnection();
            byte[] bytes=requestData.getBytes("utf-8");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);
            con.setConnectTimeout(6000);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "text/xml;charset=utf-8");
            con.setRequestProperty("SOAPAction",soapAction);
            con.setRequestProperty("Content-Length", "" + bytes.length);
            OutputStream outputStream=con.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
            InputStream inputStream=con.getInputStream();

            return inputStream;
        }
        catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }

    //获取用户信息列表
    public ArrayList<UserInfo> paraseUserInfo(InputStream inputStream){
        ArrayList<UserInfo> list=new ArrayList<UserInfo>();
        XmlPullParser parser= Xml.newPullParser();

        try{
            parser.setInput(inputStream,"UTF-8");
            int eventType=parser.getEventType();
            UserInfo userInfo=new UserInfo();

            while (eventType!=XmlPullParser.END_DOCUMENT){
                switch (eventType){
                    case XmlPullParser.START_DOCUMENT:
                        userInfo=new UserInfo();
                        break;
                    case XmlPullParser.START_TAG:
                        String name=parser.getName();
                        if(name.equalsIgnoreCase("UserID")){
                            eventType=parser.next();
                            userInfo.setUserID(Integer.parseInt(parser.getText()));
                        }else if(name.equalsIgnoreCase("DocumentNum")){
                            eventType=parser.next();
                            userInfo.setDocumentNum(Integer.parseInt(parser.getText()));
                        }else{
                            eventType=parser.next();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        list.add(userInfo);
                        userInfo=null;
                        break;
                }
                eventType=parser.next();
            }
            inputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //获取词条信息l列表
    public  ArrayList<TextInfo> paraseTextInfo(InputStream inputStream){
        ArrayList<TextInfo> list=new ArrayList<TextInfo>();
        XmlPullParser parser= Xml.newPullParser();

        try{
            parser.setInput(inputStream,"UTF-8");
            int eventType=parser.getEventType();
            TextInfo textInfo=new TextInfo();

            while (eventType!=XmlPullParser.END_DOCUMENT){
                switch (eventType){
                    case XmlPullParser.START_DOCUMENT:
                        textInfo=new TextInfo();
                        break;
                    case XmlPullParser.START_TAG:
                        String name=parser.getName();
                        if(name.equalsIgnoreCase("UserID")){
                            eventType=parser.next();
                            textInfo.setUserID(Integer.parseInt(parser.getText()));
                        }else if(name.equalsIgnoreCase("DocumentID")){
                            eventType=parser.next();
                            textInfo.setDocumentID(Integer.parseInt(parser.getText()));
                        }else if (name.equalsIgnoreCase("Text")) {
                            eventType = parser.next();
                            textInfo.setText(parser.getText());
                        }else if(name.equalsIgnoreCase("Title")){
                            eventType=parser.next();
                            textInfo.setTitle(parser.getText());
                        }else {
                            eventType=parser.next();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        list.add(textInfo);
                        textInfo=null;
                        break;
                }
                eventType=parser.next();
            }
            inputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //添加用户

    //更新用户文章数量

    //添加文章

    //删除文章


}
