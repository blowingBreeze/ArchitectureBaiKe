package com.example.dream.architecturebk.Data;

/**
 * Created by dream on 2016/12/31.
 */

public class TextInfo
{
    private String Text;
    private String Title;
    private int UserID;
    private int DocumentID;

    public String getText()
    {
        return this.Text;
    }

    public String getTitle()
    {
        return this.Title;
    }

    public int getUserID()
    {
        return this.UserID;
    }

    public int getDocumentID()
    {
        return this.DocumentID;
    }

    void setText(String text)
    {
        this.Text = text;
    }

    void setTitle(String title)
    {
        this.Title = title;
    }

    void setUserID(int userID)
    {
        this.UserID = userID;
    }

    void setDocumentID(int documentID)
    {
        this.DocumentID = documentID;
    }
}
