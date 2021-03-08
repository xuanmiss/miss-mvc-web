package com.miss.server;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.ansi;

public class BannerHelper {
    public static void banner(String path,int colorIndex){
        Ansi.Color color;
        switch (colorIndex){
            case 0:color=BLACK;break;
            case 1:color=RED;break;
            case 2:color=GREEN;break;
            case 3:color=YELLOW;break;
            case 4:color=BLUE;break;
            case 5:color=MAGENTA;break;
            case 6:color=CYAN;break;
            case 7:color=WHITE;break;
            default:color=DEFAULT;
        }
        BufferedReader in= null;
        AnsiConsole.systemInstall();
        try {
            in = new BufferedReader(new FileReader(new File(path)));
            String str="";
            while ((str=in.readLine())!=null){
                System.out.println(ansi().fg(color).a(str).reset());
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