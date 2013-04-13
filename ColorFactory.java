/*
Author: Michele Pierini
Date: 04/09/13
Program Name: ColorFactory.java
Objective: Create a program that produces a color factory.
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ColorFactory extends JFrame
{
    private JPanel jp, jp2;
    private JLabel title;
    private JLabel dec, oct, bin, hex;
    private JLabel redValue, greenValue, blueValue;
    private JLabel redLabel, greenLabel, blueLabel;
    private JRadioButton decButton, octButton, binButton, hexButton;
    private JScrollBar redBar, greenBar, blueBar;
    private OvalGraphic oval;
    private Chart chart;
    private int r,g,b;
	
    //******************************ColorFactory()*******************************
    public ColorFactory()
    {
        setTitle("Color Factory");
        title = new JLabel("Color Factory", JLabel.CENTER);
        setSize(500, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        buildValueChartPanel();
        buildRadioButtonPanel();
        buildScrollbarPanel();
		
        setLayout(new BorderLayout());
	//need to experiment with alternate layouts
        chart = new Chart();
        oval = new OvalGraphic();
		
        add(oval,BorderLayout.WEST);
        add(title, BorderLayout.NORTH);
        add(chart, BorderLayout.CENTER);
        add(jp, BorderLayout.EAST);
        add(jp2, BorderLayout.SOUTH);
		
        setVisible(true);
    }
	
    //******************************buildRadioButtonPanel()*******************************
    private void buildRadioButtonPanel()
    {
        jp = new JPanel(new GridLayout(7,2));
		
        dec = new JLabel("Decimal");
        oct = new JLabel("Octal");
        bin = new JLabel("Binary");
        hex = new JLabel("Hexadecimal");
		
        decButton = new JRadioButton();
        octButton = new JRadioButton();
        binButton = new JRadioButton();
        hexButton = new JRadioButton();
		
        ButtonGroup group = new ButtonGroup();
        group.add(decButton);
        group.add(octButton);
        group.add(binButton);
        group.add(hexButton);
		

        JRadioButtonActionListener jrbal = new JRadioButtonActionListener();
        decButton.addActionListener(jrbal);
        octButton.addActionListener(jrbal);
        binButton.addActionListener(jrbal);
        hexButton.addActionListener(jrbal);
        decButton.setSelected(true);
		
        jp.add(decButton);
        jp.add(dec);
        jp.add(octButton);
        jp.add(oct);
        jp.add(binButton);
        jp.add(bin);
        jp.add(hexButton);
        jp.add(hex);
    }
	
    //******************************buildScrollbarPanel()*******************************
    private void buildScrollbarPanel()
    {
        redBar = new JScrollBar(JScrollBar.HORIZONTAL,0,0,0,255);
        redLabel = new JLabel("Red");
        greenBar = new JScrollBar(JScrollBar.HORIZONTAL,0,0,0,255);
        greenLabel = new JLabel("Green");
        blueBar = new JScrollBar(JScrollBar.HORIZONTAL,0,0,0,255);
        blueLabel = new JLabel("Blue");
		
        AdjustmentListener al = new AdjustmentListener()
        {
            public void adjustmentValueChanged(AdjustmentEvent ae)
            {
                if (ae.getSource()==redBar||ae.getSource()==greenBar||ae.getSource()==blueBar)
                {
                    r = redBar.getValue();
                    chart.rHeight = r/3;
                    chart.rY -= chart.rHeight;
		    //need to update chart to behave correctly on value decreases
					
                    g = greenBar.getValue();
                    chart.gHeight = g/3;
                    chart.gY -= chart.gHeight;
					
                    b = blueBar.getValue();
                    chart.bHeight = b/3;
                    chart.bY -= chart.bHeight;

                    chart.repaint();
					
                    if (decButton.isSelected()||ae.getSource()==decButton)
                    {
                        redValue.setText(Integer.toString(r));
                        greenValue.setText(Integer.toString(g));
                        blueValue.setText(Integer.toString(b));
                    }
                    
                    if (hexButton.isSelected())
                    {
                        redValue.setText(Integer.toHexString(r));
                        greenValue.setText(Integer.toHexString(g));
                        blueValue.setText(Integer.toHexString(b));
                    }
                    if (octButton.isSelected())
                    {
                        redValue.setText(Integer.toOctalString(r));
                        greenValue.setText(Integer.toOctalString(g));
                        blueValue.setText(Integer.toOctalString(b));
                    }
                    if (binButton.isSelected())
                    {
                        redValue.setText(Integer.toBinaryString(r));
                        greenValue.setText(Integer.toBinaryString(g));
                        blueValue.setText(Integer.toBinaryString(b));
                    }
                }
                oval.setForeground(new Color(r,g,b));
                oval.repaint();
            }
        };
		
        redBar.addAdjustmentListener(al);
        greenBar.addAdjustmentListener(al);
        blueBar.addAdjustmentListener(al);
		
        jp.add(redBar);
        jp.add(redLabel);
        jp.add(greenBar);
        jp.add(greenLabel);
        jp.add(blueBar);
        jp.add(blueLabel);
    }
	
    //******************************buildValueChartPanel()*******************************
    private void buildValueChartPanel()
    {
        jp2 = new JPanel(new FlowLayout(FlowLayout.CENTER));

        redValue = new JLabel("0");
        greenValue = new JLabel("0");
        blueValue = new JLabel("0");
		
        jp2.add(redValue);
        jp2.add(greenValue);
        jp2.add(blueValue);
    }
	
    public class OvalGraphic extends Canvas
    {
        public void paint(Graphics g)
        {
            g.fillOval(0, 50, 100,500);
            setSize(100,100);
        }
    }

    public class Chart extends JPanel
    {
        public int rHeight=1, gHeight=1, bHeight=1;
	//need to set height of 1 as default
        public int rY=200,gY=200,bY=200;

        public void paint(Graphics g)
        {
            g.setColor(Color.red);
            g.fillRect(210,rY,30,rHeight);

            g.setColor(Color.green);
            g.fillRect(250,gY,30,gHeight);
            
            g.setColor(Color.blue);
            g.fillRect(290,bY,30,bHeight);
        }
    }
	
    public class JRadioButtonActionListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent ae)
        {
            if (ae.getSource()==decButton)
            {
                redValue.setText(Integer.toString(r));
                greenValue.setText(Integer.toString(g));
                blueValue.setText(Integer.toString(b));
            }
            if (ae.getSource()==hexButton)
            {    
                redValue.setText(Integer.toHexString(r));
                greenValue.setText(Integer.toHexString(g));
                blueValue.setText(Integer.toHexString(b));
            }
            if (ae.getSource()==octButton)
            {
                redValue.setText(Integer.toOctalString(r));
                greenValue.setText(Integer.toOctalString(g));
                blueValue.setText(Integer.toOctalString(b));
            }
            if (ae.getSource()==binButton)
            {
                redValue.setText(Integer.toBinaryString(r));
                greenValue.setText(Integer.toBinaryString(g));
                blueValue.setText(Integer.toBinaryString(b));
            }
        }
    }
}
