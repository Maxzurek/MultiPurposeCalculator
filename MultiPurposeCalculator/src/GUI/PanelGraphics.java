package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import utilities.FileManager;


public class PanelGraphics 
{
	enum Shape
	{
		RECTANGLE,
		SQUARE;
	}
	
	enum Operator
	{
		COMPLEMENT,
		DIFFERENCE,
		INTERSECTION,
		SYMETRICAL,
		UNION,
	}
	
	public static class Point
	{
	    public int x;
	    public int y;

	    Point()
	    {
	    	this.x = 0;
	    	this.y = 0;
	    }
	    
	    public Point(final double x, final double y)
	    {
	        this.x = (int) x; // we're dealing with pixels, so just truncate it
	        this.y = (int) y;
	    }

	    @Override
	    public String toString()
	    {
	        return "{" + x + ", " + y + "}";
	    }
	}
	
	public class Panel2 extends JPanel 
	{
		 /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		Panel2() 
		 {
			 // set a preferred size for the custom panel.
			 setPreferredSize(new Dimension(panelWidth,panelHeight));
	     }

	     @Override
	     public void paintComponent(Graphics g) 
	     {
	        super.paintComponent(g);        	  
	       
	        //Fill selected area with '.'
	        for(Map.Entry<String, ArrayList<Point>>entry : circleAreaResults.entrySet())
	        {
	        	g.setColor(Color.LIGHT_GRAY);
	        	for(Point location : entry.getValue())
	        	{
	        		g.drawString(".", location.x, location.y); 				
	        	}
	        }
	        
	        //Draw desired number of circles (2 or 3) @See draw3Circles
	        for(Map.Entry<String, RoundRectangle2D>entry : circles.entrySet())
	        {
	        	g.setColor(Color.BLACK);
	        	
	        	if(!draw3Circles)
	        	{
	        		if(!entry.getKey().equals("C"))
	        		{
	        			g.drawRoundRect(
	        					(int)entry.getValue().getX(),
	        					(int)entry.getValue().getY(), 
	        					(int)entry.getValue().getWidth(), 
	        					(int)entry.getValue().getHeight(), 
	        					(int)entry.getValue().getArcWidth(), 
	        					(int)entry.getValue().getArcWidth()
	        					);			
	        		}
	        	}
	        	else
	        	{
	        		g.drawRoundRect(
        					(int)entry.getValue().getX(),
        					(int)entry.getValue().getY(), 
        					(int)entry.getValue().getWidth(), 
        					(int)entry.getValue().getHeight(), 
        					(int)entry.getValue().getArcWidth(), 
        					(int)entry.getValue().getArcWidth()
        					);			
	        	}
	        }
	        
	        //Draw circle names
	        for(Map.Entry<String, Point>entry : circleNameInfos.entrySet())
	        {
	        	g.setColor(Color.RED);
	        	
	        	if(!draw3Circles)
	        	{
	        		if(!entry.getKey().equals("C"))
	        		{
	        			g.drawString(entry.getKey(), entry.getValue().x, entry.getValue().y);      			
	        		}
	        	}
	        	else
	        	{
	        		g.drawString(entry.getKey(), entry.getValue().x, entry.getValue().y);  
	        	}
	        }
	        
	     }
	}
	
	private static JPanel drawingPanel;
	private int panelWidth = 550;
	private int panelHeight = 450;
    private static boolean draw3Circles;
    public static boolean isDraw3Circles() {return draw3Circles;}
    public static void setDraw3Circles(boolean bool) { draw3Circles = bool;};
    private static boolean resultFromDrawing = false;
    public static boolean isResultFromDrawing() {return resultFromDrawing;}
    public static void setResultFromDrawing(boolean bool) { resultFromDrawing = bool;};
    private Point mouseClickPos;
    private Map<String,RoundRectangle2D> circles = new TreeMap<String, RoundRectangle2D>();
	private static Map<String, ArrayList<Point>> circleAreaResults = new TreeMap<String, ArrayList<Point>>();
	private Map<String, Point> circleNameInfos = new TreeMap<String, Point>();
	private static ArrayList<String> selectedAreas = new  ArrayList<String>();
	
	private final ArrayList<Point> CIRCLE3_AREA1 = FileManager.readFile("circle3areaPositions1");
	private final ArrayList<Point> CIRCLE3_AREA2 = FileManager.readFile("circle3areaPositions2");
	private final ArrayList<Point> CIRCLE3_AREA3 = FileManager.readFile("circle3areaPositions3");
	private final ArrayList<Point> CIRCLE3_AREA4 = FileManager.readFile("circle3areaPositions4");
	private final ArrayList<Point> CIRCLE3_AREA5 = FileManager.readFile("circle3areaPositions5");
	private final ArrayList<Point> CIRCLE3_AREA6 = FileManager.readFile("circle3areaPositions6");
	private final ArrayList<Point> CIRCLE3_AREA7 = FileManager.readFile("circle3areaPositions7");
	private final ArrayList<Point> CIRCLE3_AREA8 = FileManager.readFile("circle3areaPositions8");	
	private final ArrayList<Point> CIRCLE2_AREA1 = FileManager.readFile("circle2areaPositions1");
	private final ArrayList<Point> CIRCLE2_AREA2 = FileManager.readFile("circle2areaPositions2");
	private final ArrayList<Point> CIRCLE2_AREA3 = FileManager.readFile("circle2areaPositions3");
	private final ArrayList<Point> CIRCLE2_AREA4 = FileManager.readFile("circle2areaPositions4");
	private Map<String, ArrayList<Point>> circlesAreaPos = new TreeMap<String,ArrayList<Point>>();
	
	public void setupPanel(Core core, JLayeredPane panelCalculator) 
	{
		circlesAreaPos.put("C3_A1", CIRCLE3_AREA1);
		circlesAreaPos.put("C3_A2", CIRCLE3_AREA2);
		circlesAreaPos.put("C3_A3", CIRCLE3_AREA3);
		circlesAreaPos.put("C3_A4", CIRCLE3_AREA4);
		circlesAreaPos.put("C3_A5", CIRCLE3_AREA5);
		circlesAreaPos.put("C3_A6", CIRCLE3_AREA6);
		circlesAreaPos.put("C3_A7", CIRCLE3_AREA7);
		circlesAreaPos.put("C3_A8", CIRCLE3_AREA8);
		circlesAreaPos.put("C2_A1", CIRCLE2_AREA1);
		circlesAreaPos.put("C2_A2", CIRCLE2_AREA2);
		circlesAreaPos.put("C2_A3", CIRCLE2_AREA3);
		circlesAreaPos.put("C2_A4", CIRCLE2_AREA4);		
		
		Rectangle bounds = core.getFrame().getBounds();
		draw3Circles = true;
		drawingPanel = new Panel2();
		drawingPanel.setBounds(bounds.width,0, panelWidth, panelHeight);
		drawingPanel.setBackground(new java.awt.Color(255, 255, 255));
		drawingPanel.setVisible(false);
		drawingPanel.addMouseListener(new MouseAdapter() 
		{
			public void mousePressed(MouseEvent evt) 
			{	
				mouseClickPos = new Point(evt.getX(), evt.getY());
			}
	        public void mouseReleased(MouseEvent evt) 
	        {     		
	        	resultFromDrawing = true;
	        	Map<String, ArrayList<Point>> selectedArea = getSelectedArea();
	        	circleAreaResults.putAll(selectedArea);
	        	selectedAreas.add(selectedArea.keySet().toString());
	        	drawingPanel.repaint();
	        }
		});		
		panelCalculator.add(drawingPanel);
		
	    drawCircles();
	    drawingPanel.repaint();
	    
	}
	
	public JPanel getPanel() {return drawingPanel;}
	
	public void drawResult(String result)
	{
		circleAreaResults.clear();
		
		for(char c : result.toLowerCase().toCharArray())
		{
			switch(c)
			{
			case '1':
				
				if(draw3Circles)
				{
					fillArea(31);
				}
				else
				{
					fillArea(21);
				}
				break;
				
			case '2':
				
				if(draw3Circles)
				{
					fillArea(32);
				}
				else
				{
					fillArea(22);
				}
				break;
				
			case '3':
				
				if(draw3Circles)
				{
					fillArea(33);
				}
				else
				{
					fillArea(23);
				}
				break;

			case '4':
				
				if(draw3Circles)
				{
					fillArea(34);
				}
				else
				{
					fillArea(24);
				}
				break;
				
			case '5':
				
				if(draw3Circles)
				{
					fillArea(35);
				}
				break;
				
			case '6':
				
				if(draw3Circles)
				{
					fillArea(36);
				}
				break;
				
			case '7':
				
				if(draw3Circles)
				{
					fillArea(37);
				}
				break;
				
			case '8':
				
				if(draw3Circles)
				{
					fillArea(38);
				}
				break;
			}
		}
		
		drawingPanel.repaint();
	}
	
	public void drawResultFromDefinedSet(String result, String setU, String setA, String setB, String setC)
	{
		int setNumber = 1;
		String areaToDraw = "";
		String buffer = "";
		ArrayList<String> setToParse = new ArrayList<String>();
		ArrayList<String> resultElements = new ArrayList<String>();
		ArrayList<String> setUElements = new ArrayList<String>();
		ArrayList<String> setAElements = new ArrayList<String>();
		ArrayList<String> setBElements = new ArrayList<String>();
		ArrayList<String> setCElements = new ArrayList<String>();
		
		setToParse.add(result.toLowerCase());
		setToParse.add(setU.toLowerCase());
		setToParse.add(setA.toLowerCase());
		setToParse.add(setB.toLowerCase());
		setToParse.add(setC.toLowerCase());
		
		
		for(String set : setToParse)
		{
			for(char c : set.toCharArray())
			{
				if(c == ';')
				{
					if(setNumber == 1)
					{
						resultElements.add(buffer);					
					}
					else if(setNumber == 2)
					{
						setUElements.add(buffer);					
					}
					else if(setNumber == 3)
					{
						setAElements.add(buffer);					
					}
					else if(setNumber == 4)
					{
						setBElements.add(buffer);					
					}
					else if(setNumber == 5)
					{
						setCElements.add(buffer);					
					}
					buffer = "";
				}
				else if(c != '{' && c != '}')
				{
					buffer = buffer.concat(Character.toString(c));
				}				
			}
			
			setNumber++;
		}
		
		for(String element : resultElements)
		{
			if(setAElements.contains(element) && 
			   !setBElements.contains(element) && 
			   !setCElements.contains(element))
			{
				areaToDraw = areaToDraw.concat("1");
			}
			else if(setAElements.contains(element) &&
					setBElements.contains(element) &&
					!setCElements.contains(element))
			{
				areaToDraw = areaToDraw.concat("2");
			}
			else if(!setAElements.contains(element) &&
					setBElements.contains(element) &&
					!setCElements.contains(element))
			{
				areaToDraw = areaToDraw.concat("3");
			}
			else if(setAElements.contains(element) &&
					!setBElements.contains(element) &&
					setCElements.contains(element))
			{
				areaToDraw = areaToDraw.concat("4");
			}
			else if(!setAElements.contains(element) &&
					!setBElements.contains(element) &&
					setCElements.contains(element))
			{
				areaToDraw = areaToDraw.concat("5");
			}
			else if(!setAElements.contains(element) &&
					setBElements.contains(element) &&
					setCElements.contains(element))
			{
				areaToDraw = areaToDraw.concat("6");
			}
			else if(setAElements.contains(element) &&
					setBElements.contains(element) &&
					setCElements.contains(element))
			{
				areaToDraw = areaToDraw.concat("7");
			}
			else if(!setAElements.contains(element) &&
					!setBElements.contains(element) &&
					!setCElements.contains(element))
			{
				areaToDraw = areaToDraw.concat("8");
			}
		}
		
		drawResult(areaToDraw);
	}
	
	public static String parseSelectedAreaResult()
	{
		StringBuilder result = new StringBuilder();
		TreeSet<String> sortedResult = new TreeSet<String>();
		
		result = result.append("{");
		
		for(String area : selectedAreas)
		{
			switch(area)
			{
			case "[C3_A1]":
				sortedResult.add("1");
				break;
			case "[C3_A2]":
				sortedResult.add("2");
				break;
			case "[C3_A3]":
				sortedResult.add("3");
				break;
			case "[C3_A4]":
				sortedResult.add("4");
				break;
			case "[C3_A5]":
				sortedResult.add("5");
				break;
			case "[C3_A6]":
				sortedResult.add("6");
				break;
			case "[C3_A7]":
				sortedResult.add("7");
				break;
			case "[C3_A8]":
				sortedResult.add("8");
				break;
			case "[C2_A1]":
				sortedResult.add("1");
				break;
			case "[C2_A2]":
				sortedResult.add("2");
				break;
			case "[C2_A3]":
				sortedResult.add("3");
				break;
			case "[C2_A4]":
				sortedResult.add("4");
				break;
			}
		}
		
		for(String s : sortedResult)
		{
			result = result.append(s);
			result = result.append(";");
		}
		
		result.setCharAt(result.length()-1, '}');
		
		return result.toString();
	}

	public static void clearPaint()
	{
		resultFromDrawing = false;
		circleAreaResults.clear();
		selectedAreas.clear();
		drawingPanel.repaint();
	}

	private Map<String, ArrayList<Point>> getSelectedArea()
	{
		Map<String, ArrayList<Point>> selectedArea = new TreeMap<String, ArrayList<Point>>();
		CharSequence circle2Key = "C2";
		CharSequence circle3Key = "C3";
		
		for(Map.Entry<String, ArrayList<Point>> entry : circlesAreaPos.entrySet())
		{
			if(!draw3Circles)
			{
				if(entry.getKey().contains(circle2Key))
				{
					for(Point position : entry.getValue())
					{
						if((position.x == mouseClickPos.x) && (position.y == mouseClickPos.y))
						{
							selectedArea.put(entry.getKey(), entry.getValue());
							break;
						}
					}			
				}
			}
			else
			{
				if(entry.getKey().contains(circle3Key))
				{
					for(Point position : entry.getValue())
					{
						if((position.x == mouseClickPos.x) && (position.y == mouseClickPos.y))
						{
							selectedArea.put(entry.getKey(), entry.getValue());
							break;
						}
					}					
				}
			}
		}
		
		return selectedArea;
	}
	
	private void fillArea(int areaNumber)
	{
		switch(areaNumber)
		{
		case 21:
			circleAreaResults.put("C2_A1", CIRCLE2_AREA1);
			break;
		case 22:
			circleAreaResults.put("C2_A2", CIRCLE2_AREA2);
			break;
		case 23:
			circleAreaResults.put("C2_A3", CIRCLE2_AREA3);
			break;
		case 24:
			circleAreaResults.put("C2_A4", CIRCLE2_AREA4);
			break;
		case 31:
			circleAreaResults.put("C3_A1", CIRCLE3_AREA1);
			break;
		case 32:
			circleAreaResults.put("C3_A2", CIRCLE3_AREA2);
			break;
		case 33:
			circleAreaResults.put("C3_A3", CIRCLE3_AREA3);
			break;
		case 34:
			circleAreaResults.put("C3_A4", CIRCLE3_AREA4);
			break;
		case 35:
			circleAreaResults.put("C3_A5", CIRCLE3_AREA5);
			break;
		case 36:
			circleAreaResults.put("C3_A6", CIRCLE3_AREA6);
			break;
		case 37:
			circleAreaResults.put("C3_A7", CIRCLE3_AREA7);
			break;
		case 38:
			circleAreaResults.put("C3_A8", CIRCLE3_AREA8);
			break;
		}
	}
	
	private void drawCircles()
	{	
		final int DIAMETER = 280;
		final int RADIUS = DIAMETER/2;
		final Point STARTING_CIRCLE_A_POS = new Point(20,20);
		final Point STARTING_CIRCLE_B_POS = new Point(240,20);
		final Point STARTING_CIRCLE_C_POS = new Point(130,150);
		RoundRectangle2D circleA;
		RoundRectangle2D circleB;
		RoundRectangle2D circleC; 
		
		circleA = new RoundRectangle2D.Double
				(
				STARTING_CIRCLE_A_POS.x, STARTING_CIRCLE_A_POS.x, DIAMETER, DIAMETER, DIAMETER, DIAMETER
				);
		circles.put("A", circleA);
		circleNameInfos.put("A", new Point(STARTING_CIRCLE_A_POS.x+50, STARTING_CIRCLE_A_POS.y+50));
		
		circleB = new RoundRectangle2D.Double
				(
				STARTING_CIRCLE_B_POS.x, STARTING_CIRCLE_B_POS.y, DIAMETER, DIAMETER, DIAMETER, DIAMETER
				);
		circles.put("B", circleB);
		circleNameInfos.put("B", new Point(STARTING_CIRCLE_B_POS.x+215, STARTING_CIRCLE_B_POS.y+50));
		
		if(draw3Circles)
		{
			circleC = new RoundRectangle2D.Double
					(
					STARTING_CIRCLE_C_POS.x ,STARTING_CIRCLE_C_POS.y, DIAMETER, DIAMETER, DIAMETER, DIAMETER
					);
			circles.put("C", circleC);
			circleNameInfos.put("C", new Point(STARTING_CIRCLE_C_POS.x+RADIUS, STARTING_CIRCLE_C_POS.y+DIAMETER-5));	
		}
	}
}
