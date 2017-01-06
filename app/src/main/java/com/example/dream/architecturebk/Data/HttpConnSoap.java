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

public class HttpConnSoap
{
    public InputStream GetWebServer(String methodName, ArrayList<String> Parameters, ArrayList<String> ParamValues)
    {
        String ServerUrl = "http://192.168.1.235:80/ArchitectureWebService.asmx";
        String soapAction = "http://tempuri.org/" + methodName;
        String soapStart = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                + "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                + "<soap:Body />";
        String mreakString = "<" + methodName + "  xmlns=\"http://tempuri.org/\">";

        String argueString, paramString, temp;
        for (int i = 0; i < Parameters.size(); i++)
        {
            paramString = Parameters.get(i).toString();
            argueString = ParamValues.get(i).toString();    //设置方法的参数名为webService中的参数的名称
            temp = "<" + paramString + ">" + argueString + "</" + paramString + ">";
            mreakString = mreakString + temp;
        }
        mreakString = mreakString + "</" + methodName + ">";
        String soapEnd = "</soap:Envelope>";
        String requestData = soapStart + mreakString + soapEnd;
        Log.d("requestData", requestData);

        try
        {
            URL url = new URL(ServerUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            byte[] bytes = requestData.getBytes("utf-8");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);
            con.setConnectTimeout(6000);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            con.setRequestProperty("SOAPAction", soapAction);
            con.setRequestProperty("Content-Length", "" + bytes.length);
            OutputStream outputStream = con.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
            InputStream inputStream = con.getInputStream();
            return inputStream;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    //获取用户信息列表
    public UserInfo paraseUserInfo(InputStream inputStream)
    {
        UserInfo userInfo = new UserInfo();
        XmlPullParser parser = Xml.newPullParser();

        try
        {
            parser.setInput(inputStream, "UTF-8");
            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT)
            {
                switch (eventType)
                {
                    case XmlPullParser.START_DOCUMENT:
                        eventType = parser.next();
                        break;
                    case XmlPullParser.START_TAG:
                        String name = parser.getName();
                        if (name.equalsIgnoreCase("UserID"))
                        {
                            eventType = parser.next();
                            userInfo.setUserID(Integer.parseInt(parser.getText()));
                        }
                        else if (name.equalsIgnoreCase("DocumentNum"))
                        {
                            eventType = parser.next();
                            userInfo.setDocumentNum(Integer.parseInt(parser.getText()));
                        }
                        else
                        {
                            eventType = parser.next();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        eventType = parser.next();
                        break;
                    default:
                        eventType = parser.next();
                        break;
                }
            }
            inputStream.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return userInfo;
    }

    //获取词条信息----通过文章ID
    public TextInfo paraseTextInfo(InputStream inputStream)
    {
        TextInfo textInfo = new TextInfo();
        XmlPullParser parser = Xml.newPullParser();

        try
        {
            parser.setInput(inputStream, "UTF-8");
            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT)
            {
                switch (eventType)
                {
                    case XmlPullParser.START_DOCUMENT:
                        eventType = parser.next();
                        break;
                    case XmlPullParser.START_TAG:
                        String name = parser.getName();
                        if (name.equalsIgnoreCase("UserID"))
                        {
                            eventType = parser.next();
                            textInfo.setUserID(Integer.parseInt(parser.getText()));
                        }
                        else if (name.equalsIgnoreCase("DocumentID"))
                        {
                            eventType = parser.next();
                            textInfo.setDocumentID(Integer.parseInt(parser.getText()));
                        }
                        else if (name.equalsIgnoreCase("Text"))
                        {
                            eventType = parser.next();
                            textInfo.setText(parser.getText());
                        }
                        else if (name.equalsIgnoreCase("Title"))
                        {
                            eventType = parser.next();
                            textInfo.setTitle(parser.getText());
                        }
                        else
                        {
                            eventType = parser.next();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        eventType = parser.next();
                        break;
                    default:
                        eventType = parser.next();
                        break;
                }
            }
            inputStream.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return textInfo;
    }

    //获取词条信息l列表
    public ArrayList<TextInfo> paraseTextInfoList(InputStream inputStream)
    {
        ArrayList<TextInfo> list = new ArrayList<TextInfo>();
        XmlPullParser parser = Xml.newPullParser();

        try
        {
            parser.setInput(inputStream, "UTF-8");
            int eventType = parser.getEventType();
            TextInfo textInfo = new TextInfo();

            while (eventType != XmlPullParser.END_DOCUMENT)
            {
                switch (eventType)
                {
                    case XmlPullParser.START_DOCUMENT:
                        eventType = parser.next();
                        break;
                    case XmlPullParser.START_TAG:
                        String name = parser.getName();
                        if (name.equalsIgnoreCase("UserID"))
                        {
                            eventType = parser.next();
                            textInfo.setUserID(Integer.parseInt(parser.getText()));
                        }
                        else if (name.equalsIgnoreCase("DocumentID"))
                        {
                            eventType = parser.next();
                            textInfo.setDocumentID(Integer.parseInt(parser.getText()));
                        }
                        else if (name.equalsIgnoreCase("Text"))
                        {
                            eventType = parser.next();
                            textInfo.setText(parser.getText());
                        }
                        else if (name.equalsIgnoreCase("Title"))
                        {
                            eventType = parser.next();
                            textInfo.setTitle(parser.getText());
                        }
                        else
                        {
                            eventType = parser.next();
                        }

                        break;
                    case XmlPullParser.END_TAG:
                        name = parser.getName();
                        if (name.equalsIgnoreCase("TextInfo"))
                        {
                            eventType = parser.next();
                            list.add(textInfo);
                            textInfo = new TextInfo();
                        }
                        else
                        {
                            eventType = parser.next();
                        }
                        break;
                    default:
                        eventType = parser.next();
                        break;
                }
            }
            inputStream.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }

    //添加用户
    public boolean paraseAddUserInfo(InputStream inputStream)
    {
        XmlPullParser parser = Xml.newPullParser();
        boolean AddUserInfoState = false;
        try
        {
            parser.setInput(inputStream, "UTF-8");
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT)
            {
                switch (eventType)
                {
                    case XmlPullParser.START_DOCUMENT:
                        AddUserInfoState = false;
                        eventType = parser.next();
                        break;
                    case XmlPullParser.START_TAG:
                        String name = parser.getName();
                        if (name.equalsIgnoreCase("InsertUserInfoResult"))
                        {
                            eventType = parser.next();
                            AddUserInfoState = Boolean.parseBoolean(parser.getText());
                        }
                        else
                        {
                            eventType = parser.next();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        eventType = parser.next();
                        break;
                    default:
                        eventType = parser.next();
                        break;
                }

            }
            inputStream.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return AddUserInfoState;
    }

    //登录
    public boolean paraseLogin(InputStream inputStream)
    {
        XmlPullParser parser = Xml.newPullParser();
        boolean LoginState = false;
        try
        {
            parser.setInput(inputStream, "UTF-8");
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT)
            {
                switch (eventType)
                {
                    case XmlPullParser.START_DOCUMENT:
                        LoginState = false;
                        eventType = parser.next();
                        break;
                    case XmlPullParser.START_TAG:
                        String name = parser.getName();
                        if (name.equalsIgnoreCase("LoginResult"))
                        {
                            eventType = parser.next();
                            LoginState = Boolean.parseBoolean(parser.getText());
                        }
                        else
                        {
                            eventType = parser.next();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        eventType = parser.next();
                        break;
                    default:
                        eventType = parser.next();
                        break;
                }
            }
            inputStream.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return LoginState;
    }

    //添加文章
    public int paraseAddText(InputStream inputStream)
    {
        XmlPullParser parser = Xml.newPullParser();
        int AddTextID = 0;
        try
        {
            parser.setInput(inputStream, "UTF-8");
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT)
            {
                switch (eventType)
                {
                    case XmlPullParser.START_DOCUMENT:
                        AddTextID = 0;
                        eventType = parser.next();
                        break;
                    case XmlPullParser.START_TAG:
                        String name = parser.getName();
                        if (name.equalsIgnoreCase("InsertTextInfoResult"))
                        {
                            eventType = parser.next();
                            AddTextID = Integer.parseInt(parser.getText());
                        }
                        else
                        {
                            eventType = parser.next();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        eventType = parser.next();
                        break;
                    default:
                        eventType = parser.next();
                        break;
                }
            }
            inputStream.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return AddTextID;
    }

    //删除文章
    public boolean paraseDeleteText(InputStream inputStream)
    {
        XmlPullParser parser = Xml.newPullParser();
        boolean DeleteTextState = false;
        try
        {
            parser.setInput(inputStream, "UTF-8");
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT)
            {
                switch (eventType)
                {
                    case XmlPullParser.START_DOCUMENT:
                        DeleteTextState = false;
                        eventType = parser.next();
                        break;
                    case XmlPullParser.START_TAG:
                        String name = parser.getName();
                        if (name.equalsIgnoreCase("DeleteTextInfoResult"))
                        {
                            eventType = parser.next();
                            DeleteTextState = Boolean.parseBoolean(parser.getText());
                        }
                        else
                        {
                            eventType = parser.next();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        eventType = parser.next();
                        break;
                    default:
                        eventType = parser.next();
                        break;
                }

            }
            inputStream.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return DeleteTextState;
    }

    //更新文章
    public boolean paraseUpdateText(InputStream inputStream)
    {
        XmlPullParser parser = Xml.newPullParser();
        boolean UpdateTextState = false;
        try
        {
            parser.setInput(inputStream, "UTF-8");
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT)
            {
                switch (eventType)
                {
                    case XmlPullParser.START_DOCUMENT:
                        UpdateTextState = false;
                        eventType = parser.next();
                        break;
                    case XmlPullParser.START_TAG:
                        String name = parser.getName();
                        if (name.equalsIgnoreCase("UpdateTextInfoResult"))
                        {
                            eventType = parser.next();
                            UpdateTextState = Boolean.parseBoolean(parser.getText());
                        }
                        else
                        {
                            eventType = parser.next();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        eventType = parser.next();
                        break;
                    default:
                        eventType = parser.next();
                        break;
                }
            }
            inputStream.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return UpdateTextState;
    }

    //获取文章数量
    public int paraseGetDocNum(InputStream inputStream)
    {
        XmlPullParser parser = Xml.newPullParser();
        int Num=0;

        try
        {
            parser.setInput(inputStream, "UTF-8");
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT)
            {
                switch (eventType)
                {
                    case XmlPullParser.START_DOCUMENT:
                        Num=0;
                        eventType = parser.next();
                        break;
                    case XmlPullParser.START_TAG:
                        String name = parser.getName();
                        if (name.equalsIgnoreCase("GetDocNumResult"))
                        {
                            eventType = parser.next();
                            Num = Integer.parseInt(parser.getText());
                        }
                        else
                        {
                            eventType = parser.next();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        eventType = parser.next();
                        break;
                    default:
                        eventType = parser.next();
                        break;
                }
            }
            inputStream.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return Num;
    }
}
