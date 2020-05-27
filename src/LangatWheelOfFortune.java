import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class LangatWheelOfFortune {
  public static void main(String[] args)throws IOException{
    //ask number of players
    int numPlayers = numberOfPlayers();

    //create an array object for each player
    LangatScores[] player = new LangatScores[numPlayers];

    //create object to store the wheel of fortune phrase
    LangatScores Blank = new LangatScores();

    //create array of names that is as long as number of players
    String names[] = new String[numPlayers];

    //get the name of each player
    getPlayerNames(numPlayers, player, names);

    //get phrase to use in game from theme that user selects
    String phrase = getPhrase(theme());

    //set the phrase in the LangatScore class in Blank object
    Blank.setBlank(phrase);

    //create(replace each letter with a "_") puzzle
    String text = createPuzzle(Blank);

    //setText(which is converted phrase) in the Blank object.
    Blank.setText(text);

    //play game
    playGame(numPlayers, player, Blank, phrase);
  }

  public static int numberOfPlayers(){
    /*
    * Get either one, two, or three players and return
    * value.
    * */
    String option = "";
    String [] options = { "1", "2", "3" };

    /*
    exception handler that doesnt return error when dialoge box is closed
    or cancled
     */
    try{
      int choice = JOptionPane.showOptionDialog(
              null,
              "Number of players: ",
              "Option",
              JOptionPane.YES_NO_CANCEL_OPTION,
              JOptionPane.QUESTION_MESSAGE,
              null,
              options,
              options[2]);

      option = options[choice];}
      catch (ArrayIndexOutOfBoundsException AIoBE){
      System.exit(0);
    }
    return Integer.parseInt(option);
  }

  public static String[] getPlayerNames(int numPlayers, LangatScores player[], String names[]){
    /*
    * Loop through each player to recive name as user input
    * and return*
    */
    for(int i = 0; i < numPlayers; i++ ){
      names[i] = JOptionPane.showInputDialog
              ("Player " + (i+1) + " enter name: ");
      player[i] = new LangatScores(names[i]);
    }
    return names;
  }

  public static String theme(){
    /*
    use a dialoge box to provide options and give user ability
    to select options and the return the selected option
     */
    String[] choices = { "Famous Quotes", "Video Games", "TV Shows", "Random"};
    String input = (String) JOptionPane.showInputDialog
            (null, "Chose a theme",
                    "Wheel Of Fortune",
                    JOptionPane.QUESTION_MESSAGE, null,
                    choices, choices[0]);
    return input;
  }

  public static String getPhrase(String theme){
    String phrase = "";
    int number = 0;


    try{
   if(theme.toLowerCase().equals("tv shows")){
     String[] options = {"Friends","Game of Thrones","The Big Bang Theory",
             "Breaking Bad","The Walking Dead","The Simpsons",
             "The Office","Grays Anatomy","Seinfeld","The Sopranos",
             "Saturday Night Live","Doctor Who"};
     Random choice = new Random();
     number = choice.nextInt(options.length);
     phrase = options[number];
   }
   else if(theme.toLowerCase().equals("video games")){
     String[] options = {"Minecraft","Grand Theft Auto","Fortnite",
             "Tetris","World of Warcraft","Mario Cart","Super Smash Bros",
             "Red Dead Redemption","League of Legends","Doom", "God of War",
             "Sonic the HedgeHog","The Legend of Zelda","Metal Gear Solid"};
     Random choice = new Random();
     number = choice.nextInt(options.length);
     phrase = options[number];
   } else if(theme.toLowerCase().equals("famous quotes")){
     String[] options = {"Get busy living or get busy dying", "Love is a serious mental disease",
             "If you want to be happy be","Life is trying things to see if they work",
             "May you live all the days of your life","Those who believe in telekinetics raise my hand"};
     Random choice = new Random();
     number = choice.nextInt(options.length);
     phrase = options[number];
   } else if(theme.toLowerCase().equals("random")){
     String[] options = {"Get busy living or get busy dying", "Love is a serious mental disease",
             "If you want to be happy be","Life is trying things to see if they work",
             "May you live all the days of your life","Those who believe in telekinetics raise my hand",
             "Minecraft","Grand Theft Auto","Fortnite",
             "Tetris","World of Warcraft","Mario Cart","Super Smash Bros",
             "Red Dead Redemption","League of Legends","Doom", "God of War",
             "Sonic the HedgeHog","The Legend of Zelda","Metal Gear Solid","Friends",
             "Game of Thrones","The Big Bang Theory",
             "Breaking Bad","The Walking Dead","The Simpsons",
             "The Office","Grays Anatomy","Seinfeld","The Sopranos",
             "Saturday Night Live","Doctor Who"};
     Random choice = new Random();
     number = choice.nextInt(options.length);
     phrase = options[number];
   }} catch (NullPointerException NPE){
      System.exit(0);
    }
   return phrase;
  }

  public static String createPuzzle(LangatScores phrase){
    /*
    create character array to store phrase that is converted from
    string to char, then replace all letters with '_'.
     */
    char charArray[] = convertToChar(phrase.getBlank());
    char space = ' ';
    char blank = '_';
    /*
    * Scan through original phrase, and everytime there is a 'space' at charAt(i)
    * of phrase, set the charArry at that position to = ' '.
    * But if there is a character that matches the phrase, set the CharArray
    * at that posisition = '_'
    */
    for(int i = 0; i < charArray.length; i++){
      if (phrase.getBlank().charAt(i) == ' '){
        charArray[i] = space;
      } else {
        charArray[i] = blank;
      }
    }
    //Turn char into string and store in 'text' as String variable
    String text = String.copyValueOf(charArray);
    phrase.setPhrase(charArray);
    return text;
  }

  public static char[] convertToChar(String phrase){
    //loop through each Char and replace with value of phrase at elemnt [i].
    char[] Char = new char[phrase.length()];
    for(int i = 0; i < phrase.length(); i++){
      Char[i] = phrase.charAt(i);
    }
    return Char;
  }

  public static void playGame
          (int numPlayers, LangatScores player[], LangatScores blank, String phrase)
  throws IOException {
    //create an array of letters that will be used later to provide options
    String option = "";
    String [] nonVowels =
            { "B","C","D","F","G","H","J",
                    "K","L","M","N","P","Q",
                    "R","S","T","V","W","X",
                    "Y","Z"};
    String[] Vowels = {"A","E","I","O","U"};
    //create objects that will hold the letter that player chooses.
    LangatScores guessVowels = new LangatScores();
    LangatScores guessNonVowel = new LangatScores();

    /*
    set the choices above in the object to make retreval of letter easy
    when providing the choices
     */
    guessNonVowel.setChoiceNon_Vowels(nonVowels);
    guessVowels.setChoiceVowels(Vowels);
    //create varible to store players choice
    String playerChoice = "";

    //do a for loop to reapeat as many times as there are players
    boolean repeatMe = true;
    boolean repeatAll = true;
    boolean solve = true;
    do{
    for(int i = 0; i < numPlayers; i++){
      do{
        /*
        Ask player whether they want to Spin, Buy Vowel, or Sovle
        and store the choice into playerChoice
         */
        playerChoice = guess_Spin_skip(i, player, blank, guessNonVowel, guessVowels);
        if (playerChoice.equals("Spin")) {
          //if the player chose Spin, then the spin method will be ran.
          String choice = SpinWheel(player);
          boolean test;
          /*
          Test if the random choice recived from the psudo spin from the wheel is
          a varible. If it is proceed to providing the options to pick a letter.
          If not, catch and and set test boolean = false.
          Then an if statemt is used, and if test == false, then proceed to excecute
          the "Brankrupt" lines of code.
           */
          try {
            Integer.parseInt(choice);
            int cash = Integer.parseInt(choice);
            System.out.println(cash);

            String[] options;
            options = guessNonVowel.getChoiceNon_Vowels();

          int num = 0;
          String info = blank.getText(blank.getPick(), num);
          num++;
          //create an exception handler to hanlde when user exits from dialog box
          try{
            //dialoge box with letters B-Z(non-Vowles) for user to choose from
            int leter = JOptionPane.showOptionDialog(
                    null,
                    info +  "\n" +player[i].getName() + " pick a letter \t Score: " + player[i].getScore(),
                    "Option",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[i]);

            option = options[leter];}
            catch (ArrayIndexOutOfBoundsException AIOoBE){
            System.exit(0);
            }
            /*
            go through the options and remove the letter from the dialoge box so
            user doesnt pick the same letter again.
             */
            removeNonVowel(option, guessNonVowel);
            blank.setPick(option);

            /*
            if statement that checks if the letter picked by user is in the phrase,
            and if it is, a "CORRECT!" dialoge box is displayed, then points are
            added to the player. else(incorrect) programs continues to next player.
             */
            if(blank.getBlank().toLowerCase().contains(option.toLowerCase())){
              JOptionPane.showMessageDialog
                      (null,"CORRECT!!\nGained: " + choice);
              player[i].setScore(player[i].getScore() + cash);
              repeatMe = true;
            }
            else{
              JOptionPane.showMessageDialog
                      (null,"INCORRECT!!");
              repeatAll = true;
              repeatMe = false;

              if(numPlayers == 1){
              }
            }
            test = true;
          } catch (NumberFormatException NFE) {
            System.out.println(choice);
            test = false;
          }

          //when player recives a Bankrupt option, "BANKRUPT\nLose all points" dialog box appears.
          if (!test) {
            if (choice.equals("Bankrupt")) {
              player[i].setScore(0);
              JOptionPane.showMessageDialog(null, "BANKRUPT\nLose all points");
              repeatMe = false;
            }
          }

          /*
          if player decides to buy letter, display dialoge box with vowels, then player loses
          250 points
           */
        } else if (playerChoice.equals("Buy Vowel")) {
          String[] options;
          options = guessVowels.getChoiceVowels();

          int num = 0;
          String info = blank.getText(blank.getPick(), num);
          num++;

          /*
           exception handler that doesnt return error when dialoge box is closed
           or cancled
           */
          try{
          int leter = JOptionPane.showOptionDialog(
                  null,
                  info + "\n" +player[i].getName() + " pick a letter \t Score: " + player[i].getScore(),
                  "Option",
                  JOptionPane.YES_NO_CANCEL_OPTION,
                  JOptionPane.INFORMATION_MESSAGE,
                  null,
                  options,
                  options[i]);

          option = options[leter];}
          catch (ArrayIndexOutOfBoundsException AIOoBE){
            System.exit(0);
          }
          removeVowel(option, guessVowels);
          blank.setPick(option);

          /*
          if vowel picked mathes any vowel in the phrase, dialoge box displays, and
          player loses 250 points
           */
          if (blank.getBlank().toLowerCase().contains(option.toLowerCase())){
            JOptionPane.showMessageDialog
                    (null, "CORRECT!!\nGained: 250" );
            player[i].setScore(player[i].getScore() + 250
            );
            repeatMe = true;
          } else {
            JOptionPane.showMessageDialog
                  (null,"INCORRECT!!\nLost: 250");
            player[i].setScore(player[i].getScore() - 250);
            repeatMe = false;
          repeatAll = true;}
          /*
          If solve option is picked, the player types guess, then if the input.equals(phrase),
          dialoge box displays and player recieves a thousand points.
          The program also gets terminated using System.exit(0);
           */
        } else if (playerChoice.equals("Solve")) {
          repeatMe = false;
          String info = blank.getText(blank.getPick(), 1);
          String in = JOptionPane.showInputDialog
                  ( info + "\nEnter guess: ");
          try{
          if (in.toLowerCase().equals(blank.getBlank().toLowerCase())) {
            player[i].setScore(player[i].getScore() + 1000);
            repeatAll = false;
            repeatMe = false;
            JOptionPane.showMessageDialog
                    (null, "Congratulations!!!\nThat is correct\nYou Gained 1000!! Points!");
            if(numPlayers == 1){
            }
            /*
            Create a switch statement to display the scores of each player.
             */
            String switchStatement[] = new String[player.length];
            for(int index = 0; index < player.length; index++){
              switchStatement[index] = player[index].getName();
            }
            int switchNumber = 0;
            if(switchStatement.length == 1){
              switchNumber = 1;
            }else if (switchStatement.length == 2){
              switchNumber = 2;
            }else if (switchStatement.length == 3){
              switchNumber = 3;
            }

            //write to file "WheeleOfFortune.txt
            BufferedWriter writer = new BufferedWriter(new FileWriter("WheelOfFortune.txt"));

            switch(switchNumber){

              // display the scores for each player and write to file
              case 1: JOptionPane.showMessageDialog
                      (null,
                              player[switchNumber - 1].getName() +
                                      " scored: " + player[switchNumber - 1].getScore());

                writer.write(player[switchNumber - 1].getName() +
                        " scored: " + player[switchNumber - 1].getScore());
                writer.close();

                //end program
                System.exit(0);
              break;
              case 2: JOptionPane.showMessageDialog
                      (null,
                              player[switchNumber - 2].getName() +
                                      " scored: " + player[switchNumber - 2].getScore());

                      JOptionPane.showMessageDialog
                      (null,
                              player[switchNumber - 1].getName() +
                                      " scored: " + player[switchNumber - 1].getScore());

                      //write to file
                writer.write(player[switchNumber - 2].getName() +
                        " scored: " + player[switchNumber - 2].getScore()
                        + "\n" + player[switchNumber - 1].getName() +
                        " scored: " + player[switchNumber - 1].getScore());
                writer.newLine();
                writer.close();

                //end program
                System.exit(0);
              break;
              case 3: JOptionPane.showMessageDialog
                      (null,
                              player[switchNumber - 3].getName() +
                                      " scored: " + player[switchNumber - 3].getScore());
                JOptionPane.showMessageDialog
                        (null,
                                player[switchNumber - 2].getName() +
                                        " scored: " + player[switchNumber - 2].getScore());
                JOptionPane.showMessageDialog
                        (null,
                                player[switchNumber - 1].getName() +
                                        " scored: " + player[switchNumber - 1].getScore());

                //write to file
                writer.write(player[switchNumber - 3].getName() +
                        " scored: " + player[switchNumber - 3].getScore()
                        + "\n" + player[switchNumber - 2].getName() +
                        " scored: " + player[switchNumber - 2].getScore()
                        + "\n" + player[switchNumber - 1].getName() +
                        " scored: " + player[switchNumber - 1].getScore());
                writer.newLine();
                writer.close();

                System.exit(0);
                break;
            }
          }else repeatAll = true;}
          catch(NullPointerException NPE){
            System.exit(0);
          }

        }

        /*
        while loop created to repeat when current player gets a word correct
        and gets a chance to go again
         */
    } while(repeatMe);
    }
    /*
    while loop created to repeat each players round
     */
  } while(repeatAll);
  }

  public static void removeNonVowel(String letter, LangatScores choice){
    String array[] = choice.getChoiceNon_Vowels();
    String newArr[] = null;

    /*
    go through the letters and if letter == the option that user picked,
    go through and remove that letter, then create a new array that is 1
    less in lenght that original array. Then retore all the letters into
    the new array except the letter picked by user. Then return the new array.
     */
    for(int i = 0; i < array.length; i++){
      if(array[i].equals(letter)){
        newArr = new String[array.length-1];
        for(int index = 0; index < i; index++){
          newArr[index] = array[index];
        }
        for(int j = i; j < array.length - 1; j++){
          newArr[j] = array[j+1];
        }
        break;
      }
    }
    choice.setChoiceNon_Vowels(newArr);
  }
  public static void removeVowel(String letter, LangatScores choice){
    String array[] = choice.getChoiceVowels();
    String newArr[] = null;

    /*
    go through the letters and if letter == the option that user picked,
    go through and remove that letter, then create a new array that is 1
    less in lenght that original array. Then retore all the letters into
    the new array except the letter picked by user. Then return the new array.
     */
    for(int i = 0; i < array.length; i++){
      if(array[i].equals(letter)){
        newArr = new String[array.length-1];
        for(int index = 0; index < i; index++){
          newArr[index] = array[index];
        }
        for(int j = i; j < array.length - 1; j++){
          newArr[j] = array[j+1];
        }
        break;
      }
    }
    choice.setChoiceVowels(newArr);
  }

  public static String guess_Spin_skip
          (int i, LangatScores player[], LangatScores text, LangatScores
                  guessNonVowel, LangatScores guessVowels) {
    String[] Options;
    Options = guessVowels.getChoiceVowels();
    String[] Options2;
    Options2 = guessNonVowel.getChoiceNon_Vowels();


    if(Options.length < 1){
      String option = "swing";
      //get players score
      int score = player[i].getScore();

    /*
    create a do-while loop that will display JOption pane consle with the
    options while the player has enough money to buy the vowel if they
    choose that option. Otherwise the program will continue.
     */
      do {
        String[] options = {"Spin","Solve"};
        int x = 0;
        if(text.getCntr() > 1){
          x = 1;
        }
        String info = text.getText(text.getPick(), x);
        int choice = JOptionPane.showOptionDialog(
                null,
                info + "\n" + player[i].getName() + "\t Score: " + player[i].getScore() ,
                "Option",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[1]);

        option = options[choice];
      } while (option.equals("Buy Vowel") && score < 250);
      //return players choice
      return option;}
      else if(Options2.length < 1){
      String option = "swing";
      //get players score
      int score = player[i].getScore();

    /*
    create a do-while loop that will display JOption pane consle with the
    otions while the player has enough money to buy the vowel if they
    choose that option. Otherwise the program will continue.
     */
      do {
        String[] options = {"Buy Vowel","Solve"};
        int x = 0;
        if(text.getCntr() > 1){
          x = 1;
        }
        String info = text.getText(text.getPick(), x);
        int choice = JOptionPane.showOptionDialog(
                null,
                info + "\n" + player[i].getName() + "\t Score: " + player[i].getScore(),
                "Option",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[1]);

        option = options[choice];
      } while (option.equals("Buy Vowel") && score < 250);
      //return players choice
      return option;}
    else if(Options2.length < 1){
    }




    String option = "swing";
    //get players score
    int score = player[i].getScore();

    /*
    create a do-while loop that will display JOption pane consle with the
    otions while the player has enough money to buy the vowel if they
    choose that option. Otherwise the program will continue.
     */
    do {
      String[] options = {"Spin", "Buy Vowel", "Solve"};
      int x = 0;
      if(text.getCntr() > 1){
        x = 1;
      }
      /*
       exception handler that doesnt return error when dialoge box is close
       or cancled
       */
      try{
      String info = text.getText(text.getPick(), x);
      int choice = JOptionPane.showOptionDialog(
              null,
              info + "\n" + player[i].getName() + "\t Score: " + player[i].getScore(),
              "Option",
              JOptionPane.YES_NO_CANCEL_OPTION,
              JOptionPane.INFORMATION_MESSAGE,
              null,
              options,
              options[2]);

      option = options[choice];}
      catch (ArrayIndexOutOfBoundsException AIOoBE){
        System.exit(0);
      }
      //loop the dialog box if the player chooses to buy vowel but does have enough points
    } while (option.equals("Buy Vowel") && score < 250);
    //return players choice
    return option;
  }

  public static String SpinWheel(LangatScores[] player){
    /*
    A random int will be picked using the Random class, then the
    random int value will be used to pick an element of the choices
    provided below in the wheel variable.

    The the pseudo randomly picked option will be sent back to the
    playgame method.
     */
    Random num = new Random();
    int random = num.nextInt(19);
    int length = player.length;
    if(length > 1){String[] wheel =
            {"850","505","850","Bankrupt","750","1000","750"
                    ,"750","350","550","550","500"
                    ,"250","950","750","550","700"
                    ,"850","550", "850","505","850","750","1000","750"
                    ,"750","350","550","550","500"
                    ,"250","950","750","550","700"
                    ,"850","550"};
      String choice = wheel[random];
      boolean test;
      try{
        Integer.parseInt(choice);
        test = true;
      } catch (NumberFormatException NFE){
        test = false;
      }
      return choice;}

    String[] wheel =
            {"850","505","850"
            ,"Bankrupt","750","1000","750"
            ,"750","350","550","550","500"
            ,"250","950","750","550","700"
            ,"850","550"};
    String choice = wheel[random];
    boolean test;
    try{
      Integer.parseInt(choice);
      test = true;
    } catch (NumberFormatException NFE){
      test = false;
    }
    return choice;
  }
}