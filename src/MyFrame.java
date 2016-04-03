import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class MyFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String basePath = "C:\\U¿ytkownicy\\DS\\mgr\\Analiza_SEM\\foto";

	private File targetFile;
	public static BufferedImage targetImg;
	public static BufferedImage targetImg2;
	static BufferedImage I = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
	private JPanel MainPanel;
	private JPanel ContentPanel;
	private JPanel ButtonPanel;
	private JPanel ImagePanel;
	private JPanel ResultPanel;
	private JPanel FunctionPanel;
	private JPanel ImagePanel2;
	private JPanel ImagePanel3;
	public final JFileChooser jFileChooserOtworzPlik = new JFileChooser();
	public JTextArea textArea = new JTextArea();
	private Calculations calculation = new Calculations();

	public File file;
	public JTextArea text1 = new JTextArea();
	private int startx = 0;
	private int starty = 0;
	private int maxNum = 0;
	private int height;
	private int width;
	private int[][] pixels;
	private Fracture siatka[][];
	private double crackAngle;
	
	private JTextField statusBar = new JTextField();
	private JTextField idBar = new JTextField();
	private JTextField rozmiarBar = new JTextField();
	private JTextField crackAngleBar = new JTextField();
	private JTextField crackAreaBar = new JTextField();
	private JTextField stDtA = new JTextField();
	private JTextField minimalCrackLength = new JTextField();
	private JTextField crackLengthToAreaRatio = new JTextField();

	private JLabel progBar = new JLabel();
	private JLabel lstatusBar = new JLabel();
	private JLabel lidBar = new JLabel();
	private JLabel lrozmiarBar = new JLabel();
	private JLabel crackAngleLabel = new JLabel();
	private JLabel crackAreaLabel = new JLabel();
	private JLabel lstDtA = new JLabel();
	private JLabel minDl = new JLabel();
	private JLabel minWsp = new JLabel();
	private JLabel operatorBar = new JLabel();
	private JLabel metodaProgowaniaBar = new JLabel();
	
	private JTextField textField = new JTextField();
	private final static String newline = "\n";
	final private JCheckBox chkP = new JCheckBox("Progowanie");
	final private JCheckBox chkKol = new JCheckBox("Odwrócone kolory");
	final private JCheckBox chkPogr = new JCheckBox("Pogrubienie");
	final private JCheckBox chkDel = new JCheckBox("Eliminacja");
	private JTextField jtfInput;
	private JTextArea jtAreaOutput;
	private HashSet<Integer> set = new HashSet<Integer>();
	private JComboBox<String> edgeDetectorsList;
	private JComboBox<String> thresholdMethodList;

	String[] edgeDetectors = { "Laplace 1", "Laplace 2", "Roberts", "Roberts 2", "Sobel pionowy", "Sobel poziomy",
			"Sobel mieszany", "Prewitt pionowy", "Prewitt poziomy", "Prewitt mieszany" };
	String[] thresholdMethod = { "Metoda bazuj¹ca na prostej statystyce obrazu", "Otsu" };

	public void actionPerformed(ActionEvent evt) {
		String text = jtfInput.getText();
		jtAreaOutput.append(text + newline);
		jtfInput.selectAll();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MyFrame();
			}
		});
	}

	public void setTarget(File reference) {
		try {
			targetFile = reference;
			targetImg = ImageIO.read(targetFile);
		} catch (IOException ex) {
			Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
		}
		Border thickBorder = new LineBorder(new Color(44, 146, 126), 5);

		Dimension dimension = new Dimension(targetImg.getWidth(), targetImg.getHeight());
		setPreferredSize(dimension);
		height = targetImg.getHeight();
		width = targetImg.getWidth();
		pixels = new int[height][width];
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++) {
				Color mycolor = new Color(targetImg.getRGB(j, i));
				int r = mycolor.getRed();
				int b = mycolor.getBlue();
				int g = mycolor.getGreen();

				pixels[i][j] = ((int) (r + g + b)) / 3;

			}
		calculation.WorkBorder(pixels, height, width);
		ImagePanel.setLayout(new BorderLayout(0, 0));
		ImagePanel.add(new JLabel(new ImageIcon(targetImg)));
		ImagePanel.setBorder(thickBorder);
		ImagePanel.repaint();
		setVisible(true);
	}

	private void filter(BufferedImageOp op) {
		if (targetImg == null)
			return;
		targetImg = op.filter(targetImg, null);
		ImagePanel2.setLayout(new BorderLayout(0, 0));
		ImagePanel2.add(new JLabel(new ImageIcon(targetImg)));

		Border thickBorder = new LineBorder(new Color(44, 146, 126), 5);

		ImagePanel2.setBorder(thickBorder);
		ImagePanel2.repaint();
		setVisible(true);
	}

	private void Gradfilter(BufferedImageOp op, BufferedImageOp op2) {
		if (targetImg == null)
			return;


		BufferedImage tempImg1;
		BufferedImage tempImg2;

		tempImg1 = op.filter(targetImg, null);
		tempImg2 = op2.filter(targetImg, null);

		int width = targetImg.getWidth();
		int height = targetImg.getHeight();
		BufferedImage tempImg3 = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
		int[][] pixelsX = new int[height][width];
		int[][] pixelsY = new int[height][width];
		int[] pixels = new int[width * height];
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++) {
				Color mycolor = new Color(tempImg1.getRGB(j, i));
				int r = mycolor.getRed();
				int b = mycolor.getBlue();
				int g = mycolor.getGreen();

				pixelsX[i][j] = ((int) (r + g + b)) / 3;

				mycolor = new Color(tempImg2.getRGB(j, i));
				r = mycolor.getRed();
				b = mycolor.getBlue();
				g = mycolor.getGreen();

				pixelsY[i][j] = ((int) (r + g + b)) / 3;
			}

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (i == 0 || i == width - 1 || j == 0 || j == height - 1)
					pixelsX[i][j] = pixelsY[i][j] = pixels[j + i * width] = 0; 
				else {
					pixels[j + i * width] = Math.abs(pixelsX[i][j]) + Math.abs(pixelsY[i][j]);

				}
			}
		}
		tempImg3.getRaster().setPixels(0, 0, width, height, pixels);
		targetImg = tempImg3;
		ImagePanel2.setLayout(new BorderLayout(0, 0));
		ImagePanel2.add(new JLabel(new ImageIcon(tempImg3)));

		Border thickBorder = new LineBorder(new Color(44, 146, 126), 5);

		ImagePanel2.setBorder(thickBorder);
		ImagePanel2.repaint();
		setVisible(true);
	}

	private void convolve(float[] elements) {
		int temp = (int) Math.sqrt(elements.length);
		Kernel kernel = new Kernel(temp, temp, elements);
		ConvolveOp op = new ConvolveOp(kernel);
		filter(op);
	}

	private void GradConvolve(float[] elements, float[] elements2) {
		int temp = (int) Math.sqrt(elements.length);
		Kernel kernel1 = new Kernel(temp, temp, elements);
		ConvolveOp op1 = new ConvolveOp(kernel1);

		int temp2 = (int) Math.sqrt(elements2.length);
		Kernel kernel2 = new Kernel(temp2, temp2, elements2);
		ConvolveOp op2 = new ConvolveOp(kernel2);

		Gradfilter(op1, op2);
	}

	public MyFrame() {

		setTitle("Analiza SEM");

		MainPanel = new JPanel();
		MainPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		MainPanel.setBackground(Color.WHITE);

		ContentPanel = new JPanel();
		ContentPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		ContentPanel.setBackground(Color.WHITE);

		ButtonPanel = new JPanel();
		ButtonPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		ButtonPanel.setBackground(Color.WHITE);

		FunctionPanel = new JPanel();
		FunctionPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		FunctionPanel.setBackground(Color.WHITE);
		
		ResultPanel = new JPanel();
		ResultPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		ResultPanel.setBackground(Color.WHITE);

		textField.setPreferredSize(new Dimension(40, 25));

		add(MainPanel);
		MainPanel.add(ContentPanel);
		JScrollPane jsp = new JScrollPane(MainPanel);
		add(jsp, BorderLayout.CENTER);
		MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.Y_AXIS));


		MainPanel.add(ButtonPanel);
		MainPanel.add(FunctionPanel);
		MainPanel.add(ResultPanel);

		ImagePanel = new JPanel();
		ImagePanel.setBorder(new EmptyBorder(0, 0, 480, 640));
		ImagePanel.setBackground(Color.BLACK);

		ImagePanel2 = new JPanel();
		ImagePanel2.setBorder(new EmptyBorder(0, 0, 0, 0));
		ImagePanel2.setBackground(Color.BLACK);

		ImagePanel3 = new JPanel();
		ImagePanel3.setBorder(new EmptyBorder(0, 0, 0, 0));
		ImagePanel3.setBackground(Color.BLACK);

		ContentPanel.add(ImagePanel);

		final DrawPanel drawPanel = new DrawPanel();

		drawPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				super.mouseClicked(me);

				startx = me.getX();
				starty = me.getY();
				statusBar.setText("(" + startx + "," + starty + ")");
				Fracture s = drawPanel.getFracture(startx, starty, drawPanel.getFractures());

				for (Fracture b : drawPanel.getFractures()) {
					if (b.getId() == s.getId() && b.getId() != 0) {
						if (!b.isSelected()) {
							b.setSelected(true);
						} else {
							b.setSelected(false);
						}
						drawPanel.repaint();
					}
				}

			}
		});

		JButton przycisk1 = new JButton("Wczytaj obraz");
		przycisk1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser(basePath);
				fc.setFileFilter(new BMPImageFileFilter());
				int res = fc.showOpenDialog(null);
				// We have an image!
				try {
					if (res == JFileChooser.APPROVE_OPTION) {
						File file = fc.getSelectedFile();
						ImagePanel.removeAll();

						setTarget(file);

					} // Oops!
					else {
						JOptionPane.showMessageDialog(null, "You must select one image to be the reference.",
								"Aborting...", JOptionPane.WARNING_MESSAGE);
					}
				} catch (Exception iOException) {
				}
				drawPanel.fractures.clear();
				targetImg2 = null;
				ContentPanel.add(drawPanel);
			}

		});

		JButton przycisk2 = new JButton("Zastosuj detektor krawêdzi");
		przycisk2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				ImagePanel2.removeAll();
				String choose = (String) edgeDetectorsList.getSelectedItem();
				System.out.println(choose);
				switch (choose) {
				case "Laplace 1":
					float[] laplace1 = { -1.0f, 0.0f, -1.0f, 0.0f, 4.0f, 0.0f, -1.0f, 0.0f, -1.0f };
					convolve(laplace1);
					break;
				case "Laplace 2":
					float[] laplace2 = { -1.0f, -1.0f, -1.0f, -1.0f, 8.0f, -1.0f, -1.0f, -1.0f, -1.0f };
					convolve(laplace2);
					break;
				case "Roberts":
					float[] roberts1 = { 1.0f, 0.0f, 0.0f, -1.0f };
					float[] roberts2 = { 0.0f, 1.0f, -1.0f, 0.0f };
					GradConvolve(roberts1, roberts2);
					break;
				case "Roberts 2":
					float[] roberts12 = { 1.0f, 0.0f, -1.0f, 0.0f };
					float[] roberts22 = { 1.0f, -1.0f, 0.0f, 0.0f };
					GradConvolve(roberts12, roberts22);
					break;
				case "Sobel pionowy":
					float[] sobelPi = { 1.0f, 0.0f, -1.0f, 2.0f, 0.0f, -2.0f, 1.0f, 0.0f, -1.0f };
					convolve(sobelPi);
					break;
				case "Sobel poziomy":
					float[] sobelPoz = { -1.0f, -2.0f, -1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 2.0f, 1.0f };
					convolve(sobelPoz);
					break;
				case "Sobel mieszany":
					float[] sobel1 = { 1.0f, 0.0f, -1.0f, 2.0f, 0.0f, -2.0f, 1.0f, 0.0f, -1.0f };
					float[] sobel2 = { -1.0f, -2.0f, -1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 2.0f, 1.0f };
					GradConvolve(sobel1, sobel2);
					break;
				case "Prewitt pionowy":
					float[] prewittPi = { 1.0f, 0.0f, -1.0f, 1.0f, 0.0f, -1.0f, 1.0f, 0.0f, -1.0f };
					convolve(prewittPi);
					break;
				case "Prewitt poziomy":
					float[] prewittPoz = { -1.0f, -1.0f, -1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f };
					convolve(prewittPoz);
					break;
				case "Prewitt mieszany":
					float[] prewitt1 = { 1.0f, 0.0f, -1.0f, 1.0f, 0.0f, -1.0f, 1.0f, 0.0f, -1.0f };
					float[] prewitt2 = { -1.0f, -1.0f, -1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f };
					GradConvolve(prewitt1, prewitt2);
					break;
				}

				ContentPanel.add(ImagePanel2);
				ContentPanel.add(ImagePanel3);
			}
		});
		JButton przycisk4 = new JButton("Przetwarzanie obrazu");
		przycisk4.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				ImagePanel3.removeAll();
				if (targetImg == null)
					return;

				int height = targetImg.getHeight();
				int width = targetImg.getWidth();
				int[][] pixels = new int[height][width];

				maxNum = 0;
				pixels = calculation.getPixelsColor(height, width, pixels, targetImg);
				 int workGround =calculation.WorkBorder(pixels,height, width); // to sie
				// wyklucza z obliczaniem progu
				System.out.println(workGround);

				siatka = new Fracture[width][height];

				int shadow = 0;
				if (chkP.isSelected()) {
					String choose = (String) thresholdMethodList.getSelectedItem();
					switch (choose) {
					case "Otsu":
						shadow = calculation.Otsu(workGround, width, pixels);
						break;
					case "Metoda bazuj¹ca na prostej statystyce obrazu":
						shadow = calculation.oblicz_prog(workGround, width, pixels);
						break;

					}
					
				} else
					shadow = Integer.parseInt(textField.getText());

				siatka = calculation.doBinaryzation(height, width, pixels, shadow, siatka, workGround);

				siatka = calculation.poprId(siatka, height, width);

				// zastosuj hashmap do zliczenia pekniec
				for (int i = 0; i < height; i++)
					for (int j = 0; j < width; j++)
						if (siatka[j][i].getId() != 0)
							set.add(siatka[j][i].getId());
				maxNum = set.size();

				// odrzucanie

				if (chkDel.isSelected()) {
					double minCrackLength = Double.parseDouble(minimalCrackLength.getText());
					double lengthToAreaRatio = Double.parseDouble(crackLengthToAreaRatio.getText());
					siatka = calculation.eliminateSmallCracks(height, width, siatka, minCrackLength, lengthToAreaRatio,
							set);
				}
				set.clear();
				// poprawia id

				// zastosuj hashmap do zliczenia pekniec
				for (int i = 0; i < height; i++)
					for (int j = 0; j < width; j++)
						if (siatka[j][i].getId() != 0)
							set.add(siatka[j][i].getId());
				maxNum = set.size();

				// Pogrubianie
				if (chkPogr.isSelected())
					siatka = calculation.expandCracks(siatka, height, width, workGround);

				// przekazanie do odrysowania
				for (int i = 0; i < height; i++)
					for (int j = 0; j < width; j++)
						drawPanel.addFracture(siatka[j][i]);

				String strI = Integer.toString(maxNum);
				idBar.setText(strI);
				ImagePanel3.setLayout(new BorderLayout(0, 0));

				Border thickBorder = new LineBorder(new Color(44, 146, 126), 5);
		
				ImagePanel3.setBorder(thickBorder);
				ImagePanel3.repaint();
				

			}
		});

	

		JButton przycisk5 = new JButton("Wyznacz parametry stereologiczne");

		przycisk5.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				int width = targetImg.getWidth();
				int height = targetImg.getHeight();
				int x1, x2, y1, y2, x11 = 0, x22 = 0, y11 = 0, y22 = 0;
				System.out.println(maxNum);
				Object help[] = new Object[set.size()];
				HashMap<Integer, Integer> mapa = new HashMap<Integer, Integer>();

				set.toArray(help);
				HashMap<Integer, List<Fracture>> fractureMap = new HashMap<Integer, List<Fracture>>();
				
				
				for (int i = 0; i < help.length; i++)
					mapa.put((Integer) help[i], i);
				for (int i = 0; i < set.size(); i++)
					fractureMap.put(i, new ArrayList<Fracture>()) ;
				for (int i = 0; i < height; i++)
					for (int j = 0; j < width; j++) {
						if (siatka[j][i].getId() != 0) {
							int k = mapa.get(siatka[j][i].getId());
							fractureMap.get(k).add(siatka[j][i]);
						}
					}
				
				double rozmiar = 0;

				double maxRoz = 0;
				double powierzchnia = 0;
				for (int i = 0; i < set.size(); i++) {
					boolean select = fractureMap.get(i).get(0).isSelected();
					
					if (select) {
						for (int z = 0; z < fractureMap.get(i).size(); z++)
							for (int j = 0; j < fractureMap.get(i).size(); j++) {
								x1 = fractureMap.get(i).get(z).getX();
								y1 = fractureMap.get(i).get(z).getY();
								x2 = fractureMap.get(i).get(j).getX();
								y2 = fractureMap.get(i).get(j).getY();
								rozmiar = calculation.calcCrackLength(x1, x2, y2, y1); // obliczanie
																						// d³ugoœci
																						// pêkniêcia
																						// (px)
								if (rozmiar > maxRoz) {
									maxRoz = rozmiar;
									x11 = x1;
									x22 = x2;
									y11 = y1;
									y22 = y2;
								}
							}
						crackAngle = calculation.calcAngle(x11, x22, y11, y22);//
						powierzchnia = fractureMap.get(i).size();
					}
				}
				double temp = maxRoz * 100;
				temp = Math.round(temp);
				temp /= 100;
				maxRoz = temp;

				temp = calculation.calcLengthtoAreaRatio(maxRoz, powierzchnia) * 100;
				temp = Math.round(temp);
				temp /= 100;
				// i tak nie zaznacza dobrze
				rozmiarBar.setText("" + maxRoz + "");
				crackAngleBar.setText("" + crackAngle + "");
				crackAreaBar.setText("" + powierzchnia + "");
				stDtA.setText("" + temp + "");
			}
		});
		

		edgeDetectorsList = new JComboBox<String>(edgeDetectors);
		edgeDetectorsList.setSelectedIndex(0);

		thresholdMethodList = new JComboBox<String>(thresholdMethod);
		thresholdMethodList.setSelectedIndex(0);

		statusBar.setBounds(0, 0, 5, 5);
		idBar.setBounds(0, 0, 5, 5);
		crackAngleBar.setBounds(0, 0, 5, 5);
		rozmiarBar.setBounds(0, 0, 5, 5);
		crackAreaBar.setBounds(0, 0, 5, 5);

		lstatusBar.setBounds(0, 0, 5, 5);
		lidBar.setBounds(0, 0, 5, 5);
		crackAngleLabel.setBounds(0, 0, 5, 5);
		lrozmiarBar.setBounds(0, 0, 5, 5);
		crackAreaLabel.setBounds(0, 0, 5, 5);
		stDtA.setBounds(0, 0, 5, 5);
		lstDtA.setBounds(0, 0, 5, 5);
		minDl.setBounds(0, 0, 5, 5);
		minimalCrackLength.setBounds(0, 0, 5, 5);
		minWsp.setBounds(0, 0, 5, 5);
		crackLengthToAreaRatio.setBounds(0, 0, 5, 5);

		lstatusBar.setPreferredSize(new Dimension(135, 25));
		lidBar.setPreferredSize(new Dimension(80, 25));
		crackAngleLabel.setPreferredSize(new Dimension(90, 25));
		lrozmiarBar.setPreferredSize(new Dimension(110, 25));
		crackAreaLabel.setPreferredSize(new Dimension(80, 25));
		lstDtA.setPreferredSize(new Dimension(100, 25));
		minDl.setPreferredSize(new Dimension(170, 25));
		minWsp.setPreferredSize(new Dimension(160, 25));

		statusBar.setPreferredSize(new Dimension(100, 25));
		idBar.setPreferredSize(new Dimension(100, 25));
		crackAngleBar.setPreferredSize(new Dimension(100, 25));
		rozmiarBar.setPreferredSize(new Dimension(100, 25));
		crackAreaBar.setPreferredSize(new Dimension(100, 25));
		stDtA.setPreferredSize(new Dimension(100, 25));
		minimalCrackLength.setPreferredSize(new Dimension(40, 25));
		crackLengthToAreaRatio.setPreferredSize(new Dimension(40, 25));

		lstatusBar.setText("Wspó³rzêdne klikniêcia:");
		lidBar.setText("Iloœæ pêkniêæ:");
		crackAngleLabel.setText("K¹t nachylenia:");
		lrozmiarBar.setText("D³ugoœæ pêkniêcia:");
		crackAreaLabel.setText("Powierzchnia:");
		lstDtA.setText("Stosunek d³/pow:");
		minDl.setText("Minimalna d³ugoœæ pêkniêcia");
		minWsp.setText("Minimalny stosunek d³/pow");
		progBar.setText("Próg:");
		operatorBar.setText("Detektor krawêdzi:");
		metodaProgowaniaBar.setText("Metoda automatycznego progowania:");
		
		chkP.setBounds(0, 0, 5, 5);
		chkKol.setBounds(0, 0, 5, 5);
		chkPogr.setBounds(0, 0, 5, 5);
		chkDel.setBounds(0, 0, 5, 5);


		ButtonPanel.add(przycisk1);
		ButtonPanel.add(przycisk2);
		ButtonPanel.add(przycisk4);
		ButtonPanel.add(przycisk5);


		
		ResultPanel.add(lstatusBar);
		ResultPanel.add(statusBar);
		ResultPanel.add(lidBar);
		ResultPanel.add(idBar);
		ResultPanel.add(crackAngleLabel);
		ResultPanel.add(crackAngleBar);
		ResultPanel.add(lrozmiarBar);
		ResultPanel.add(rozmiarBar);
		ResultPanel.add(crackAreaLabel);
		ResultPanel.add(crackAreaBar);
		ResultPanel.add(lstDtA);
		ResultPanel.add(stDtA);

		FunctionPanel.add(progBar);
		FunctionPanel.add(textField);
		FunctionPanel.add(chkP);
		FunctionPanel.add(chkKol);
		FunctionPanel.add(chkPogr);
		FunctionPanel.add(chkDel);
		FunctionPanel.add(minDl);
		FunctionPanel.add(minimalCrackLength);
		FunctionPanel.add(minWsp);
		FunctionPanel.add(crackLengthToAreaRatio);
		FunctionPanel.add(operatorBar);
		FunctionPanel.add(edgeDetectorsList);
		FunctionPanel.add(metodaProgowaniaBar);
		FunctionPanel.add(thresholdMethodList);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		// setSize(1000, 1000);
		setVisible(true);

	}
}
