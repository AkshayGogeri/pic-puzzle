import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;

class StartPage
{  SimpleLIST l;
   DifficultLIST dl;
   JFrame f;
   JLabel l1,l2,l3;
   JButton start,Rules,Easy,difficult;
   JPanel p;
   StartPage()
   {
   
      f=new JFrame("Picture Puzzle Game");
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setLocation(300,120);
      f.getContentPane().setBackground(Color.BLUE);
      
      l1=new JLabel("PUZZLE GAME");
      l2=new JLabel("START GAME");
      l3=new JLabel("RULES");
      l1.setBounds(220,50,260,50);
      l1.setFont(new Font("ARIAL",3,30));
      l2.setBounds(0,180,200,50);
      l2.setFont(new Font("ARIAL",2,30));
      l3.setBounds(150,220,200,50);
      l3.setFont(new Font("ARIAL",2,30));
    
      Easy = new JButton("EASY");
      Easy.setFont(new Font("ARIAL",1,20));
      Easy.setBounds(230,180,140,70);
      difficult = new JButton("DIFFICULT");
      difficult.setFont(new Font("ARIAL",1,20));
      difficult.setBounds(430,180,140,70);
      Rules = new JButton("RULES");
      Rules.setFont(new Font("ARIAL",1,20));
      Rules.setBounds(230,260,140,70);
      
      f.add(Easy);
      f.add(difficult);
      f.add(Rules);
      f.add(l1);
      f.add(l2);
      f.add(l3);
      
      f.setSize(750,600);
      f.setVisible(true);
      f.setResizable(false);
      
      
      
      
      
      Easy.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
         f.dispose();
         l=new SimpleLIST();
                 }
      });
      difficult.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
         f.dispose();
         dl=new DifficultLIST();
         }
      });     
      
      //Rules
      Rules.addActionListener(new ActionListener() {
   	   public void actionPerformed(ActionEvent arg0) {
            f.setVisible(false);
      	   final JFrame rframe=new JFrame("Rules");
            rframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JButton back=new JButton("Back");
            back.setBounds(100,650,120,60);
            back.setFont(new Font("ARIAL",1,20));
            String bk="back.jpg";
            back.setIcon(new ImageIcon(bk));
            ImageIcon img=new ImageIcon("Rules.jpg"); 
            JLabel menu=new JLabel(img);  
            ImageIcon img1=new ImageIcon("3x3.gif"); 
            JLabel menu1=new JLabel(img1);
            menu1.setBounds(100,340,300,300);
            ImageIcon img2=new ImageIcon("4x4.gif"); 
            JLabel menu2=new JLabel(img2);
            menu2.setBounds(410,340,300,300);
            rframe.add(back);
            rframe.add(menu1);
            rframe.add(menu2);
            rframe.add(menu); 
            rframe.setSize(750,800);
            rframe.setLocation(400,10);
            rframe.setVisible(true);
            rframe.setResizable(false);
            back.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent arg0) {
                  rframe.setVisible(false);
            	   f.setVisible(true);
               }
            }); 
         }
      });
 
   }
  
}
