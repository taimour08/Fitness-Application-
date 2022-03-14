package com.i180686_i181657.splashscreen;

public class ExercisesData
{
    private String Ex_Name ;
    private String Ex_Desc ;
    private int    Ex_pic ;

    public ExercisesData(String ex_Name, String ex_Desc, int ex_pic)
    {
        Ex_Name = ex_Name;
        Ex_Desc = ex_Desc;
        Ex_pic = ex_pic;
    }

    public String getEx_Name()
    {
        return Ex_Name;
    }

    public void setEx_Name(String ex_Name)
    {
        Ex_Name = ex_Name;
    }

    public String getEx_Desc()
    {
        return Ex_Desc;
    }

    public void setEx_Desc(String ex_Desc)
    {
        Ex_Desc = ex_Desc;
    }

    public int getEx_pic()
    {
        return Ex_pic;
    }

    public void setEx_pic(int ex_pic)
    {
        Ex_pic = ex_pic;
    }
}
