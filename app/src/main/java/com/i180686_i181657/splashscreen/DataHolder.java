package com.i180686_i181657.splashscreen;

public class DataHolder
{
     String pimage , name , mobile , email , password ;

    public DataHolder(String pimage, String name, String mobile, String email, String password)
    {
        this.pimage = pimage;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
    }

    public String getPimage()
    {
        return pimage;
    }

    public void setPimage(String pimage)
    {
        this.pimage = pimage;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getMobile()
    {
        return mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
