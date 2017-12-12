import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class guessTheNumberDLC implements ActionListener{
  //Properties
  //Gui Components
  JFrame frame;
  JPanel panel;
  JButton buttonPlay;
  JButton buttonLower;
  JButton buttonHigher;
  JLabel labelRandomNumber;
  JLabel labelComments;
  JLabel labelScore;
  
  //Logic Variables
  int intRandom1;
  int intRandom2;
  int intCorrect = 0;
  int intTotalPlayed = 0;
  double dblPercent;
  
  //Constructor
  public guessTheNumberDLC(){
    //Creates the panel.
    panel = new JPanel();
    panel.setPreferredSize(new Dimension(480, 720));
    panel.setLayout(null);
    
    //Creates the play button
    buttonPlay = new JButton("Play");
    buttonPlay.setSize(400, 72);
    buttonPlay.setLocation(40, 480);
    buttonPlay.addActionListener(this);
    panel.add(buttonPlay);
    
    //Creates the "Lower" button.
    buttonLower = new JButton("Lower");
    buttonLower.setSize(200, 72);
    buttonLower.setLocation(40, 480);
    buttonLower.addActionListener(this);
    buttonLower.setVisible(false); //Makes it invisible
    panel.add(buttonLower);
    
    //Creates the "Higher" button.
    buttonHigher = new JButton("Higher");
    buttonHigher.setSize(200, 72);
    buttonHigher.setLocation(240, 480);
    buttonHigher.addActionListener(this);
    buttonHigher.setVisible(false); //Makes it invisible
    panel.add(buttonHigher);
    
    //Random Number label.
    labelRandomNumber = new JLabel("", JLabel.CENTER);
    labelRandomNumber.setSize(400, 400);
    labelRandomNumber.setLocation(40, 40);
    labelRandomNumber.setFont(new Font("SANS_SERIF", Font.PLAIN, 128));
    panel.add(labelRandomNumber);
    
    //Comments label.
    labelComments = new JLabel("Welcome to High Low!", JLabel.CENTER);
    labelComments.setSize(400, 72);
    labelComments.setLocation(40, 552);
    panel.add(labelComments);
    
    //Score label.
    labelScore = new JLabel("Score: 0/0. Percentage: 0%", JLabel.CENTER);
    labelScore.setSize(400, 40);
    labelScore.setLocation(40, 680);
    panel.add(labelScore);
    
    //Creates the frame.
    frame = new JFrame("High Low");
    frame.setContentPane(panel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
    
  }
  
  //Methods
  public void actionPerformed(ActionEvent event){
    if(event.getSource() == buttonPlay){
      start();
    }else if(event.getSource() == buttonLower){
      reset(false);
    }else if(event.getSource() == buttonHigher){
      reset(true);
    }
  }
  
  
  public void start(){
    //Makes the button invisible and the higher/lower buttons visible.
    buttonPlay.setVisible(false);
    buttonLower.setVisible(true);
    buttonHigher.setVisible(true);
    
    //Generates both the random numbers. If the second random number is equal to the first, then reroll.
    intRandom1 = (int)(Math.random()*10+1);
    intRandom2 = (int)(Math.random()*10+1);
    
    while(intRandom2 == intRandom1){
      intRandom2 = (int)(Math.random()*10+1);
    }
    
    //Changes the labels
    labelRandomNumber.setText("" + intRandom1);
    labelComments.setText("Is the second number higher or lower?");
  }
  
  public void reset(boolean blnHigher){
    //Changes the visibility of the different buttons.
    buttonPlay.setVisible(true);
    buttonLower.setVisible(false);
    buttonHigher.setVisible(false);
    
    //Checks if the user guessed the right answer.
    if((intRandom2 > intRandom1 && blnHigher == true) || (intRandom2 < intRandom1 && blnHigher == false)){
      intCorrect++;
      intTotalPlayed++;
      labelComments.setText("Correct! The second number was "+intRandom2+"!");    
    }else{
      intTotalPlayed++;
      labelComments.setText("Incorrect! The second number was "+intRandom2+"!");
    }
    
    //Changes the result label.
    dblPercent = Math.round((double)(intCorrect) / (double)(intTotalPlayed) * 1000.0) / 10.0;
    labelScore.setText("Score: "+intCorrect+"/"+intTotalPlayed+". Percentage: "+dblPercent+"%");
  }
  
  //Main Program
  public static void main(String[] args){
    guessTheNumberDLC gui = new guessTheNumberDLC();
  }
}