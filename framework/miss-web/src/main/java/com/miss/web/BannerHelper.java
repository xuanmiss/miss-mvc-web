package com.miss.web;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BannerHelper {
    public static void banner(String path,int colorIndex){
        Ansi.Color color;
        switch (colorIndex){
            case 0:color= Ansi.Color.BLACK;break;
            case 1:color= Ansi.Color.RED;break;
            case 2:color= Ansi.Color.GREEN;break;
            case 3:color= Ansi.Color.YELLOW;break;
            case 4:color= Ansi.Color.BLUE;break;
            case 5:color= Ansi.Color.MAGENTA;break;
            case 6:color= Ansi.Color.CYAN;break;
            case 7:color= Ansi.Color.WHITE;break;
            default:color= Ansi.Color.DEFAULT;
        }
        BufferedReader in= null;
        AnsiConsole.systemInstall();
        try {
            in = new BufferedReader(new FileReader(new File(path)));
            String str="";
            while ((str=in.readLine())!=null){
                System.out.println(Ansi.ansi().fg(color).a(str).reset());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(in!=null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            AnsiConsole.systemUninstall();
        }
    }
}