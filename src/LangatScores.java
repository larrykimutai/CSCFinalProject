public class LangatScores extends LangatPlayer {
  private int Score = 250;
  private String[] ChoiceNon_Vowels;
  private String[] ChoiceVowels;
  private char[] Phrase;
  private String Text;
  private String Blank; //initial phrase
  private String withBlank;
  private int cntr;
  private String pick;
  private String LastOne;

  //default constructor
  public LangatScores(){
    super();
  }

  public LangatScores(String name){
    super(name);
  }
  public LangatScores(int score){
    super(score);
  }

  public void setBlank(String blank){
    Blank = blank;
  }

  public void setScore(int score){
    Score = score;
  }
  public int getScore(){
    return Score;
  }

  public int getCntr(){
    return cntr;
  }
  public String getPick(){
    return pick;
  }

  public void setPick(String pick1){
    pick = pick1;
  }

  public String getText(String options, int x){
    LastOne = Blank;
    if (x == 1){
    char[] BlankArray = new char[Blank.length()];
    for(int in = 0; in < Blank.length(); in++){
      BlankArray[in] = Blank.charAt(in);
    }
    /*
    replace all occurrences of players letter pick in the phrase with "~",
    then go through and replace any alphabetical letter with "_"(but since
    the letter that the placer picked has already been replaced with "~", it
    remains like this). The replace all occurrences of "~", with the option the
    player picked...which now results in something like this - Phrase: "The Office"
    Players Choice: "f" new phrase: _ _ _    _ f f _ _ _
     */
    withBlank = Blank.toLowerCase().replaceAll(options.toLowerCase(),"~");
    withBlank = withBlank.replaceAll("[a-zA-Z]","_");
      withBlank = withBlank.replaceAll("~",options);




    char[] Textchar = new char[Text.length()];
    char[] withBlankchar = new char[withBlank.length()];
    for(int i = 0; i < Text.length(); i++){
      withBlankchar[i] = withBlank.charAt(i);
    }
      boolean test = true;
      int cntr = 0;
      String withBlankString = Blank;
      for(int i = 0; i < Text.length(); i++) {

            for (int ind = 0; ind < Text.length(); ind++) {
              Textchar[ind] = Text.charAt(ind);
              BlankArray[ind] = withBlank.charAt(ind);
              String value = String.valueOf(withBlankchar[ind]);
              String value2 = String.valueOf(Blank.charAt(ind));
              String value3 = String.valueOf(LastOne.charAt(ind));
              String value4 = String.valueOf(Text.charAt(ind));
              if(value3.equalsIgnoreCase(value2) && (value.equalsIgnoreCase(value4))){
                withBlankchar[ind] = Text.charAt(ind);
              }else if(value3.equalsIgnoreCase(value2)){
                withBlankchar[ind] = Blank.charAt(ind);
              }else if (value.equalsIgnoreCase(value3)) {
                if(value4.equalsIgnoreCase(value2)){
                  withBlankchar[ind] = Blank.charAt(ind);
                }else
                withBlankchar[ind] = LastOne.charAt(ind);
              }
              else{
                withBlankchar[ind] = '_';
              }

              withBlankString = String.valueOf(withBlankchar);
              withBlank = withBlankString;
              LastOne = withBlankString;
            }
            test = false;
            cntr++;

    Text = withBlankString;
    cntr++;
    /*
    return the texts and replaces all single characters with a space,
    this spaces out the blanks so it doesnt look like one line.
    from ___ to _ _ _ .
     */
    return Text.replace("", " ").trim();
    }
    }
    else
      cntr++;
    return Text.replace("", " ").trim();
}
  public String getBlank(){
    return Blank;
  }

  public void setText(String text){
    Text = text;
  }

  public void setPhrase(char[] phrase){
    //store char phrase to char Phrase
    Phrase = new char[phrase.length];
    for(int i = 0; i < phrase.length; i++){
      Phrase[i] = phrase[i];
    }
    cntr++;
  }

  public void setChoiceVowels(String[] arrayChoice){
    ChoiceVowels = new String[arrayChoice.length];
    for(int i = 0; i < arrayChoice.length; i++){
      ChoiceVowels [i] = arrayChoice[i];
    }
  }
  public void setChoiceNon_Vowels(String[] arrayChoice){

    ChoiceNon_Vowels = new String[arrayChoice.length];
      for(int i = 0; i < arrayChoice.length; i++){
        ChoiceNon_Vowels [i] = arrayChoice[i];
      }
  }

  public String[] getChoiceNon_Vowels(){
    return ChoiceNon_Vowels;
  }
  public String[] getChoiceVowels(){
    return ChoiceVowels;
  }

  public String getName(){
    return super.getName();
  }
}