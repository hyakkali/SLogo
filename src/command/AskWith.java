package command;

import controller.Controller;

public class AskWith implements Command {

    private Command myConditionList;
    private Command myTurtleList;

    public AskWith(Command conditionList, Command turtleList){
        this.myConditionList = conditionList;
        this.myTurtleList = turtleList;
    }


    @Override
    public double execute(Controller controller) {

    }

}
