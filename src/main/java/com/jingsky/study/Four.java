package com.jingsky.study;

import java.awt.Container;
import java.awt.Graphics;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Four extends JFrame{
    public Four(){
        setTitle("我的简易时钟");
        setLayout(null);
        setBounds(200,200,600,600);
        Container container = getContentPane();
        setVisible(true);
        Clock c= new Clock();
        c.setBounds(0,0,600,500);
        container.add(c);
    }
}
class Clock extends JPanel implements Runnable{
    Thread runner;
    static int x=250,y=250,r1=50,r2=75,r3=100;//坐标原点和时钟半径
    int h,m,s;//当前的时分秒和相应的角度
    double hx,hy,mx,my,sx,sy;
    Clock()
    {
        start();
    }
    public void start(){//开启一个线程
        if (runner==null)
            runner = new Thread(this);
        runner.start();
    }
    public void run()
    {
        while(true)
        {
            Date date=new Date();//分别得到当前的时分秒
            String hour = String.format("%tI",date);
            h=Integer.parseInt(hour);
            String minute = String.format("%tM",date);
            m= Integer.parseInt(minute);
            String second = String.format("%tS",date);
            s= Integer.parseInt(second);
            System.out.println("当前时间是:"+hour+"小时"+minute+"分钟"+second+"秒");
            double d=3600*h+60*m+s;//算出一共多少秒
            double hdeg=d/120;//24小时是360度，可得1s是1/120度
            hdeg=Math.toRadians(hdeg);
            d=60*m+s;
            double mdeg=d/10;//60分钟是360度，可得1s是0.1度
            mdeg=Math.toRadians(mdeg);
            double sdeg=s*6;//60秒是360度，可得1s是6度
            sdeg=Math.toRadians(sdeg);
            hx=r1*Math.sin(hdeg)+x;//分别根据角度和半径计算个个指针所在位置
            hy=r1*Math.cos(hdeg+Math.PI)+y;
            mx=r2*Math.sin(mdeg)+x;
            my=r2*Math.cos(mdeg+Math.PI)+y;
            sx=r3*Math.sin(sdeg)+x;
            sy=r3*Math.cos(sdeg+Math.PI)+y;
            repaint();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
    public void paint(Graphics g){
        g.clearRect(0,0,500,500);
        g.drawString("我的简易时钟",220,50);
        g.drawString("0",245,135);
        g.drawString("3",355,250);
        g.drawString("6",245,365);
        g.drawString("9",125,250);
        g.drawOval(120,120,250,250);//画出这个圆
        g.drawLine(x,y,(int)hx,(int)hy);//因为drawLine只能画整数
        g.drawLine(x,y,(int)mx,(int)my);
        g.drawLine(x,y,(int)sx,(int)sy);

    }
}