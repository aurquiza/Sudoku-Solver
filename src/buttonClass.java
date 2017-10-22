import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class buttonClass extends JButton
{
	int cellSection;

	public buttonClass(String s, int cellSec)
	{
		super(s);
		cellSection = cellSec;
	}

}