package backend;

import resources.constants.Constants;
import command.Command;
import main.Controller;

import java.util.List;


/**
 * This class is a node of the abstract syntax tree. It contains information about itself (whether it
 * represents a double value, a variable, or a command) and it also contains a List<AbsTreeNode> of arguments.
 * Subtrees are evaluated recursively from the head node.
 */

public class AbsTreeNode {

    private Command myCommand; //Will need to edit based on how commands are implemented
    private String myVariable;
    private String myFunction;
    private double myValue;
    private boolean validBlock;
    private List<AbsTreeNode> myArguments;
    private SLogoData myData;
    private Controller myController;

    public AbsTreeNode(Command command, String variable, String function, double value,
                       boolean block, List<AbsTreeNode> arguments, SLogoData data) {
        myCommand = command;
        myVariable = variable;
        myFunction = function;
        myValue = value;
        validBlock = block;
        myArguments = arguments;
        myData = data;
    }


    public Command getCommand() {
        return myCommand;
    }

    public boolean isBlock() {
        return validBlock;
    }

    public boolean isVariable() {
        return (myVariable != null);
    }

    public String getVariableName() {
        return myVariable;
    }

    public List<AbsTreeNode> getArguments() {
        return myArguments;
    }

    public String getFunctionName() {
        return myFunction;
    }

    protected void addArg(AbsTreeNode newNode) {
        myArguments.add(newNode);
    }

    private double runFunc() {
        for (int x = 0; x < myArguments.get(0).getArguments().size(); x++) {
            myData.addVariable(new Variable(myArguments.get(0).getArguments().get(x).getVariableName(),
                    myArguments.get(2).getArguments().get(x).evaluate()));
        }
        double retVal = myArguments.get(1).evaluate();
        for (int x = 0; x < myArguments.get(0).getArguments().size(); x++) {
            myData.deleteVariable(myArguments.get(0).getArguments().get(x).getVariableName());
        }
        myArguments.remove(2);
        return retVal;
    }

//    protected boolean isMathCmd() {
//        if (validBlock) {
//            return false;
//        }
//        if (myCommand == null) {
//            return true;
//        }
//        return myCommand.isMathCmd();
//    }

    /**
     * @return The double value of the recrusively calculated value of this node. Returns its own double value
     * if this node is a double or variable, else evaluates this node's function with its parameters.
     */
    public double evaluate() {
        System.out.println(this);
        System.out.println(this.myArguments);
        if (validBlock) {
            return myArguments.get(0).evaluate();
        }
        if (myFunction != null) {
            if (myArguments.size() > 2) {
                return runFunc();
            }
            else {return 0.0;}
        }
        if (myVariable != null) {
            if (myData.getVariable(myVariable) != null) {
                return myData.getVariable(myVariable).getValue();
            }
            else {
                throw new IllegalArgumentException(Constants.DEFAULT_RESOURCES.getString("VariableNotFoundError"));
            }
        }
        if (myCommand == null) {
            return myValue;
        }
        return myCommand.execute(myController);
    }



}
