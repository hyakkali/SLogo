package userinterface;

import java.util.ArrayList;

public class CMDHistory {
    /*
        Aouthor @Conrad handles remembering past input so that users can recall commands previously written in
     */

  private ArrayList<String> history = new ArrayList<String>();
  private int pointer;

  public CMDHistory()
  {
      pointer=0;
      history.add("");
  }

  public boolean hasNext()
  {
      return pointer<history.size()-1;
  }

  public boolean hasPrev()
  {
      return pointer>0;
  }

  public void add(String s)
  {
          history.set(history.size()-1,s);
          history.add("");
          pointer = history.size()-1;
  }

  /*
       Sets the console to display
   */
  public String moveForward()
  {

      if( pointer < history.size()-1)
          pointer++;

      return history.get(pointer);
  }

    public String moveBack()
    {
        if( pointer > 0)
            pointer--;
        return history.get(pointer);
    }
}
