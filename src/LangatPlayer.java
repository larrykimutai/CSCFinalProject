public abstract class LangatPlayer {
  private String Name;
  private int Score;
  public LangatPlayer(){
    Name = "";
    Score = 250;// all players start with 250 points
  }
  public LangatPlayer(String name){
    Name = name;
  }

  public LangatPlayer(int score){
    Score = score;
  }


  public String getName(){
    return Name;
  }


  public String toString(){
    String str;

    str = Name + ": " + Score;

    return str;
  }
}