import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Stack {
	private JFrame myFrame;		//Window itself
	private JPanel myPanel;		//Stacks portion of the window
	private JLabel inputLabel;		//Label for input
	private JLabel outputLabel; 	//Label for output
	private JTextField inputField;		//Text field for input
	private JTextField outputField;		//Text field for output
	private JButton pushFunction;	//Button for push
	private JButton popFunction;	//Button for pop
	private JButton peekFunction;		//Button for peek
	private JButton isEmptyFunction;		//Button for isEmpty 
	private int input;
	private Integer index = 0;
	private Integer[] myStack = new Integer[2];
	
	public Stack()
	{
		myFrame = new JFrame("Stack Program");		//Makes a new window called "Stack Program"
		myPanel = new JPanel();			//Makes a new panel for the window
		
		inputLabel = new JLabel("Input:");		//Makes a label titled "Input"
		outputLabel = new JLabel("Output:");		//Makes a label titled "Output"
		
		inputField = new JTextField();		//Makes a text field for input
		outputField = new JTextField();		//Makes a text field for output
		outputField.setEditable(false);			//Disables input for the output text field
		
		pushFunction = new JButton("Push");		//Makes a new button titled "Push"
		pushFunction.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						input = Integer.parseInt(inputField.getText());
						push(input);
					}
				});
	
		popFunction = new JButton("Pop");		//Makes a new button titled "Pop"
		popFunction.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if (empty() == true)
							outputField.setText("The stack is empty!");
						else
							pop();
					}
				});
		
		peekFunction = new JButton("Peek");			//Makes a new button titled "Peek"
		peekFunction.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if (peek() == 0)
							outputField.setText("The stack is empty!");
						else
							outputField.setText(peek() + "" + " is at the top of the stack.");
					}
				});
		
		isEmptyFunction = new JButton("isEmpty");		//Makes a new button titled "isEmpty"
		isEmptyFunction.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if (empty() == true)
							outputField.setText("The stack is empty!");
						else
							outputField.setText("The stack is NOT empty!");
					}
				});
		
		myPanel.setLayout(new GridLayout(0,4));		//Makes the panel a grid layout with a max of 4 columns, and automatically adjusts for the number of rows
		
		//Add stuff to the panel in order
		myPanel.add(inputLabel);	//Adds the input label to the panel
		myPanel.add(inputField);	//Adds the input text field to the panel
		myPanel.add(pushFunction);		//Adds the push button to the panel
		myPanel.add(popFunction);		//Adds the pop button to the panel
		//Fills all four columns on the grid, so it moves down to the next row
		myPanel.add(outputLabel);		//Adds the output label to the panel
		myPanel.add(outputField);		//Adds the output text field to the panel
		myPanel.add(peekFunction);		//Adds the peek button to the panel
		myPanel.add(isEmptyFunction);		//Adds the isEmpty button to the panel
		myFrame.add(myPanel);		//Adds the panel to the window
		
		myFrame.pack();		//Automatically sizes the components of the window equally
		myFrame.setVisible(true);		//Makes the window show up
	}
	
	public void push(int variable)
	{
		if (index + 1 > myStack.length)
			resize("UP");
		myStack[index] = variable;
		//System.out.println("Pushed: " + variable + " in index: " + index);
		if (index != myStack.length)
			index++;
	}
	
	public int pop()
	{
		//System.out.println("Popped: " + valueTaken + " from index: " + (index - 1));
		if (index + 1 <= (myStack.length / 4) * 3)
			resize("DOWN");
		index--;
		return index+1;
	}
	
	public int peek()
	{
		if (empty() == true)
			return 0;
		else
			return myStack[index - 1];
	}
	
	public boolean empty()
	{
		if (index == 0)
		{
			if (index < 0)
				index = 0;
			return true;
		}
		else
			return false;
			
	}
	
	private void resize(String stringValue)
	{
		if (stringValue.equals("UP"))
		{
			Integer[] newArray = new Integer[myStack.length * 2];
		
			for (int i = 0; i < myStack.length; i++)
			{
				newArray[i] = myStack[i];
				if (i > myStack.length)
					newArray[i] = null;
			}
		
			myStack = new Integer[newArray.length];
		
			for (int i = 0; i < newArray.length; i++)
			{
				myStack[i] = newArray[i];
			}
		}
		else if (stringValue.equals("DOWN"))
		{
			Integer[] newArray = new Integer[myStack.length / 2];
			
			for (int i = 0; i < newArray.length; i++)
			{
				newArray[i] = myStack[i];
				//System.out.println("newArray[" + i + "] = " + newArray[i]);
			}
			
			myStack = new Integer[newArray.length];
			
			for (int i = 0; i < newArray.length; i++)
				myStack[i] = newArray[i];
			
			index = myStack.length;
		}
	}
}

