package userinterface;

import java.util.ArrayList;

public class History {
  private ArrayList<String> history = new ArrayList<String>();
  private int pointer;
  public History()
  {
      pointer=0;history.add("");
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
      //switch indicies if its already there
          history.set(history.size()-1,s);
          history.add("");
          pointer = history.size()-1;
          //System.out.print(pointer);
  }
  public String moveForward()
  {

      if( pointer < history.size()-1)
          pointer++;
      System.out.print(pointer);

      return history.get(pointer);
  }

    public String moveBack()
    {
        if( pointer > 0)
            pointer--;
        System.out.print(pointer);

        return history.get(pointer);
    }
}
