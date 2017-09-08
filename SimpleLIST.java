import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;

class SimpleLIST extends JFrame implements ActionListener,Solvable
{  JFrame f;
   JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,shuffle,solve,back=null;
   
   //String imagedeleted;
   String[] imgs;
   Node list,shufList;
   
   public SimpleLIST()
   {  list=null;
      shufList=null; 
      //imagedeleted=null;
      
      f=new JFrame("Puzzle Game/EASY ");
      //f.getContentPane().setBackground(Color.BLUE);
      f.setLocation(350,170);
      f.setLayout(new GridLayout(4,3));
      
      back = new JButton("BACK");
      back.setFont(new Font("ARIAL",1,20));
      back.setBounds(220,450,140,70);
      back.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
             f.setVisible(false);
             StartPage f=new StartPage(); 
         }
      });
     
      
      imgs=new String[]{"img1.jpg","img2.jpg","img3.jpg","img4.jpg","img5.jpg","img6.jpg","img7.jpg","img8.jpg","img9.jpg","null.jpg"};
      createList();
   }
   
   //create list creates original picture list 
   public void createList()
   {  Node temp=null;  
      for(int i=1;i<=9;i++)
      {  Node newnode=new Node(imgs[i-1],i);
         if(list==null)
         {   list=newnode;
             temp=list;
         }
         else{
            temp.right=newnode;
            newnode.left=temp;
            temp=temp.right;
         }
      }
      createPuzzle();   //calls createPuzzle to create puzzled picture list
   }
   
   public void createPuzzle()
   {  //copy contents of original list to shufList
      Node temp1=list;
      shufList=null;
      Node temp2=shufList;
      f.getContentPane().removeAll();
      while(temp1!=null)
      {  Node newnode=new Node(temp1.str,temp1.token);     
         if(shufList==null)
         {   shufList=newnode;
             temp2=shufList;
         }
         else{
            temp2.right=newnode;
            newnode.left=temp2;
            temp2=temp2.right;
         }
         temp1=temp1.right;
      }
      
      //now shuffle copied shufList
      shuffle();
      while(!isCrctShuffled())
      {  shuffle();
      }
           
      /*insert images of shufList to all buttons 
         and display it in frame   
      */
      
       // goto Node's token number 9 and set that to null
      temp1=shufList;
      while(temp1.token!=9 && temp1!=null)
         temp1=temp1.right;
      if(temp1.token==9)
         temp1.str=imgs[9];
      
      
      //set tokens in increasing order (because token represents position of node/image in list)
      int count=0;   
      temp1=shufList;
      while(temp1!=null)
      {  count++; 
         temp1.token=count;
         temp1=temp1.right;
      }

      b1=new JButton(new ImageIcon(shufList.str));
     
      f.add(b1);
      count=1;
      temp1=shufList;
      while(temp1!=null)
      {  temp1=temp1.right;  
         count++;
         switch(count)
         {  case 2:  b2=new JButton(new ImageIcon(temp1.str));
                     f.add(b2);
                     break;  
            case 3:  b3=new JButton(new ImageIcon(temp1.str));
                     f.add(b3);
                     break;
            case 4:  b4=new JButton(new ImageIcon(temp1.str));
                     f.add(b4);
                     break;
            case 5:  b5=new JButton(new ImageIcon(temp1.str));
                     f.add(b5);
                     break;
            case 6:  b6=new JButton(new ImageIcon(temp1.str));
                     f.add(b6);
                     break;
            case 7:  b7=new JButton(new ImageIcon(temp1.str));
                     f.add(b7);
                     break;
            case 8:  b8=new JButton(new ImageIcon(temp1.str));
                     f.add(b8);
                     break;
            case 9:  b9=new JButton(new ImageIcon(temp1.str));
                     f.add(b9);
                     break;
         }
      }
      shuffle=new JButton("Shuffle again");
      shuffle.setFont(new Font("ARIAL",1,12));
      f.add(shuffle);
         
      solve=new JButton("Solve for me");
      solve.setFont(new Font("ARIAL",1,15));

      f.add(solve);  
                  
      f.add(back);
      
      //add to frame and set actionListener
      b1.addActionListener(this);  
      b2.addActionListener(this);  
      b3.addActionListener(this);
      b4.addActionListener(this);
      b5.addActionListener(this);  
      b6.addActionListener(this);  
      b7.addActionListener(this);
      b8.addActionListener(this);
      b9.addActionListener(this);
      shuffle.addActionListener(this);
      solve.addActionListener(this);
            
      f.setSize(380,420);
      f.setVisible(true);
      f.setResizable(false);
      f.setDefaultCloseOperation(EXIT_ON_CLOSE);    
   }

   // event handlers
   /* whenever the button is clicked swap method to be called
   */
   public void actionPerformed(ActionEvent e)
   {  String ix=null;
      if(e.getSource()==b1)
        ix=( (ImageIcon)b1.getIcon()).getDescription();
         
      else if(e.getSource()==b2)
         ix=( (ImageIcon)b2.getIcon()).getDescription();
            
      else if(e.getSource()==b3)
         ix=( (ImageIcon)b3.getIcon()).getDescription();
   
      else if(e.getSource()==b4)
         ix=( (ImageIcon)b4.getIcon()).getDescription();
        
      else if(e.getSource()==b5)
         ix=( (ImageIcon)b5.getIcon()).getDescription();
   
      else if(e.getSource()==b6)
         ix=( (ImageIcon)b6.getIcon()).getDescription();
   
      else if(e.getSource()==b7)
         ix=( (ImageIcon)b7.getIcon()).getDescription();
         
      else if(e.getSource()==b8)
         ix=( (ImageIcon)b8.getIcon()).getDescription();
   
      else if(e.getSource()==b9)
         ix=( (ImageIcon)b9.getIcon()).getDescription();
      
         
      if(e.getSource()==shuffle)
      {  //shuffle();
         createPuzzle();     
      }
      else if(e.getSource()==solve)
              /*System.out.println("Puzzle solved");*/ solvePuzzle();
           else  Listswap(ix);
     
      //after swapping and adjusting list and displaying check whether game is complete
      if(isComplete())
      {  Node temp=shufList;
         while(temp.right!=null)
            temp=temp.right;
         temp.str="img9.jpg";
         adjustpuz();  
         System.out.println("YOU WON");
         JOptionPane.showMessageDialog(this,"You won");
         //f.dispose(); 
      }
   }
   
   //swaping after the action performed
   public void Listswap(String y)
   {  Node temp1=shufList;
      while(temp1.str!=y)
         temp1=temp1.right;
      Node temp2=shufList;
      while(temp2.str!="null.jpg")
         temp2=temp2.right;
        if(Math.abs(temp1.token-temp2.token)==3 || Math.abs(temp1.token-temp2.token)==1)
        {  if(Math.abs(temp1.token-temp2.token)==1)
           { if( ((temp2.token==4 && temp1.token==3)||(temp2.token==3 && temp1.token==4))|| ((temp2.token==7 && temp1.token==6)||(temp2.token==6 && temp1.token==7)) )
            {            }
            else{
              String s=temp1.str;
              temp1.str=temp2.str;
              temp2.str=s;
            }
           }else{
              String s=temp1.str;
              temp1.str=temp2.str;
              temp2.str=s;
            }
        }
         
      //adjust frame to redraw buttons after swap
      adjustpuz();
   }
   public void adjustpuz()
   {  Node temp1=shufList;
      b1.setIcon(new ImageIcon(temp1.str));
      int num=1;
      while(temp1!=null)
      {
         num++;
         temp1=temp1.right;
         if(num==2)
            b2.setIcon(new ImageIcon(temp1.str));
         else if(num==3)
            b3.setIcon(new ImageIcon(temp1.str));
         else if(num==4)
            b4.setIcon(new ImageIcon(temp1.str));
         else if(num==5)
            b5.setIcon(new ImageIcon(temp1.str));
         else if(num==6)
            b6.setIcon(new ImageIcon(temp1.str));
         else if(num==7)
            b7.setIcon(new ImageIcon(temp1.str));
         else if(num==8)
            b8.setIcon(new ImageIcon(temp1.str));
         else if(num==9)
            b9.setIcon(new ImageIcon(temp1.str));
       }
   }

   public void listTraverse(Node x)
   {  Node temp=x;
      for(int i=1;i<=9;i++)
      {  if(temp!=null)
            System.out.print(temp.token+"+"+temp.str+"\n");
         if(i%3==0)
            System.out.println();
         if(temp!=null)
            temp=temp.right;
      }
   }
   
   public boolean isComplete()
   {  Node temp1=shufList;
      Node temp2=list;
      while(temp1.right!=null)
      {  if(temp1.str!=temp2.str)
            return false;
         temp1=temp1.right;
         temp2=temp2.right;
      }
      return true;
   }
   public boolean isCrctShuffled()
   {  int[] s=new int[9];  
      Node temp2=shufList;
      int j=0,count=0;
      while(temp2!=null)
      {  s[j]=Integer.parseInt((temp2.str).substring(3,4));
         //System.out.print(s[j]);
         j++;
         temp2=temp2.right;
      }
      int pos=0;
      for(int i=0;i<8;i++)
      {  if(s[i]>s[i+1])
            count++;
        if(s[i]==9 && (i+1)%2==0)
            pos++;
        else if(s[i]==2 && (i+1)%2==0)
                  pos++;
         //System.out.print(count);          
      }
      if(count==3 || pos>0)
         return false;
      else 
         return true;

   }
   public void shuffle()
   {  Node temp=shufList;
      Node t=shufList;
      for(int i=8;i>=0;i--)
      {  int j=0+(int)(Math.random()*i+1);
         temp=shufList;
         while(temp.token!=i+1)
            temp=temp.right;
         int r=temp.token;
         String s=temp.str;
         
         t=shufList;
         while(t.token!=j)
            t=t.right;
         
         temp.token=t.token;
         temp.str=t.str;
         
         t.token=r;
         t.str=s;
      }
      //System.out.println("IS GAME COMPLETE:"+isComplete());
   }
   public void solvePuzzle()
   {  Node temp1=list;
      int count=0;
      while(temp1!=null)
      {  count++;  
         
         switch(count)
         {  case 1:  b1.setIcon(new ImageIcon(temp1.str));
                     break;  
            case 2:  b2.setIcon(new ImageIcon(temp1.str));
                     break;  
            case 3:  b3.setIcon(new ImageIcon(temp1.str));
                     break;
            case 4:  b4.setIcon(new ImageIcon(temp1.str));
                     break;
            case 5:  b5.setIcon(new ImageIcon(temp1.str));
                     break;
            case 6:  b6.setIcon(new ImageIcon(temp1.str));
                     break;
            case 7:  b7.setIcon(new ImageIcon(temp1.str));
                     break;
            case 8:  b8.setIcon(new ImageIcon(temp1.str));
                     break;
            case 9:  b9.setIcon(new ImageIcon(temp1.str));
                     break;
         }
         temp1=temp1.right; 
      }

   }
}
