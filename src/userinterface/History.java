package userinterface;

import java.util.ArrayList;

public class History {
  private ArrayList<String> history = new ArrayList<String>();
  private int pointer;
  public History()
  {
      pointer=0;
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
      history.add(s);
      pointer = history.size();
  }
  public String moveForward()
  {
      if( pointer< history.size()-1)
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
