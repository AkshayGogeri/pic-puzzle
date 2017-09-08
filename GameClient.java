import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;

class GameClient
{  public static void main(String args[])
      {  JFrame wframe=new JFrame("Welcome");
         wframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         ImageIcon img=new ImageIcon("welcome.png");
         JLabel welcome=new JLabel(img);
         wframe.add(welcome);
         wframe.setVisible(true);
         wframe.setLocation(300,170);
         wframe.setSize(700,400);
         for(int i=0;i<1000000000;)
         {  i=i-2;
            i=i+1;
         }
         wframe.setVisible(false);
         StartPage f=new StartPage();    
      }
}