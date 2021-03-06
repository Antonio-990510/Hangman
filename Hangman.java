import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
public class Hangman_2 extends JFrame implements ActionListener {
	String[] words = { "accretion", "accede", "adaptive", "zebra", "airdrop", "align", "zodiac", "analeptic",
			"annoyance", "apocrypha", "archer", "artemisia", "astigmat", "queen", "azerbaijan", "balmy", "bartend",
			"beatrice", "belligerent", "beryllium", "billionth", "blaspheme", "boatyard", "borate", "brake", "brim",
			"bucket", "burnout", "cady", "cancer", "cardamom", "casteth", "cell", "changeover", "chevron",
			"christianson", "claire", "clothbound", "coffman", "comedy", "compressor", "confusion", "contagion", "cope",
			"cosmology", "coypu", "criteria", "culver", "czech", "dayton", "decorticate", "delouse", "depressor",
			"devise", "digging", "dispelled", "dogleg", "downdraft", "drumlin", "dyspeptic", "efface", "ella",
			"emplace", "eohippus", "erskine", "evasive", "exhilarate", "extradite", "fanciful", "fennel", "filtrate",
			"flathead", "fluvial", "formidable", "freddy", "fulcrum", "gallop", "geisha", "gibby", "globule", "gore",
			"greatcoat", "guanine", "hackney", "hank", "hawaiian", "hell", "heterogamous", "histrionic", "honey",
			"hovel", "hydrogen", "illegitimate", "importation", "inconclusive", "inertance", "inheritor", "insult",
			"inveigh", "isochronous", "jellyfish", "joyful", "katharine", "kingfisher", "krieger", "laos", "lead",
			"leprosy", "limbic", "loam", "lorraine", "luscious", "magnetite", "mangel", "marquis", "mausoleum", "medea",
			"merganser", "middle", "minneapolis", "mold", "morass", "mule", "myrtle", "necklace", "newtonian",
			"nonogenarian", "nurture", "october", "onomatopoeic", "orleans", "pacesetting", "pappy", "passband",
			"pearl", "perception", "pervade", "phyllis", "piracy", "pleasant", "polity", "posable", "precious",
			"priestley", "prolong", "proverb", "puppyish", "quartic", "rafferty", "reactant", "referral", "render",
			"restitution", "rhombic", "robertson", "rousseau", "sacral", "sandbag", "saxophone", "scops", "seaward",
			"sensorimotor", "shafer", "shiplap", "sibyl", "since", "slaughter", "smutty", "solicit", "southernmost",
			"spigot", "squad", "startup", "stigmata", "strengthen", "substitution", "suntanned", "swastika", "synopses",
			"tanzania", "telegram", "testicle", "thirst", "tientsin", "tonic", "trainee", "trench", "trouser", "tutu",
			"unitarian", "utrecht", "venal", "victorious", "volatile", "wander", "wearied", "while", "wilson", "wood",
			"wynn", "zeroes" };
	int[] checked = new int[201]; // ????????? ?????? ???????????? ??????
	int word_length; // ????????? ??????
	int guessNum; // ????????? ?????? (10????????? ???????????? 8????????? ????????????)
	int guessNumShow; // ????????? ?????? (10????????? ???????????? 8????????? ????????????)
	int level; // ?????? ?????????
	int[] num_key= {4,5,6,7,8,9,10,11,12};
	int selection;
	int setWordLength;
	int setWordLength2;
	int h_count;
	int min_idx;
	int max_idx;
	int score;
	int check_key;
	int spell_key;
	char[] word1 = new char[12]; // ???????????? ????????? ???????????? char
	ArrayList<Character> w_check = new ArrayList<>();
	String[] slevel = { "Easy", "Medium", "Hard" }; // ?????????
	String[] word2 = new String[12]; // ????????? ????????? String
	String check_word;
	String endMsg = "Game over.";
	String aaa;
	double wins;
	double looses;
	double winningProsentige;
	String[] alpa_key = {
			"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
			"L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
			"X", "Y", "Z" };
	JButton[] Jbutton = new JButton[alpa_key.length];
	JButton begin = new JButton("BEGIN");
	JButton easy = new JButton("EASY");
	JButton medium = new JButton("MEDIUM");
	JButton hard = new JButton("HARD");
	JButton chance = new JButton("Chance");
	JLabel Jlabel = new JLabel("The number of hints: 3");
	JButton reset = new JButton("RESET");
	JLabel Min = new JLabel("Min: ", JLabel.LEFT); // ?????? ???????????? ?????????
	JComboBox<Integer> minCombo=new JComboBox<Integer>();
	JLabel Max = new JLabel("Max: ", JLabel.LEFT); // ?????? ???????????? ?????????
	JComboBox<Integer> maxCombo=new JComboBox<Integer>();
	JLabel text = new JLabel("Skill level: ", JLabel.LEFT); // ?????? ???????????? ?????????
	JPanel displayTOP = new JPanel();
	JPanel display1 = new JPanel();
	JPanel display2 = new JPanel();
	Font normalFont = new Font("Arial", Font.BOLD, 16);
	Font warningFont = new Font("Arial", Font.BOLD, 20);
	public Hangman_2() {
		setTitle("?????? ??????");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(850, 700);
		setVisible(true);
	}
	public void init() {
		begin.addActionListener(this);
		easy.addActionListener(this);
		medium.addActionListener(this);
		hard.addActionListener(this);
		chance.addActionListener(this);
		reset.addActionListener(this);
		minCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox<Integer> minC=(JComboBox<Integer>)e.getSource();
				min_idx=minC.getSelectedIndex();
				if(min_idx>max_idx) {
					min_idx=max_idx;
					minC.setSelectedIndex(max_idx);
				}
				setWordLength=num_key[min_idx];
				repaint();
			}
		});
		maxCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox<Integer> maxC=(JComboBox<Integer>)e.getSource();
				max_idx=maxC.getSelectedIndex();
				if(min_idx>max_idx) {
					max_idx=min_idx;
					maxC.setSelectedIndex(max_idx);
				}
				setWordLength2=num_key[max_idx];
				repaint();
			}
		});
		GridLayout aaa = new GridLayout(3, 0); // ?????? panel??? ?????? layout ??????
		FlowLayout bbb = new FlowLayout(); // displayTOP??? ?????? layout // begin??????
		FlowLayout ccc = new FlowLayout(); // display2??? ?????? layout
		GridLayout ddd = new GridLayout(3, 9, 3, 3); // display1??? ?????? layout // ?????????
		Container root = getContentPane(); // ???????????? ????????? ?????? root
		root.setLayout(aaa); // grid
		root.setBackground(Color.white);
		displayTOP.add(begin);
		displayTOP.add(chance);
		displayTOP.add(Jlabel);
		displayTOP.setLayout(bbb);
		displayTOP.setBackground(Color.white);
		root.add(displayTOP); // TOP??? ??????????????? ??????
		display1.setLayout(ddd);
		display1.setBackground(Color.white);
		for (int i = 0; i < alpa_key.length; i++) {
			Jbutton[i] = new JButton(alpa_key[i]);
			Jbutton[i].setBackground(Color.ORANGE);
			Jbutton[i].setEnabled(false);
			display1.add(Jbutton[i]);
			Jbutton[i].addActionListener(this);
		}
		root.add(display1);
		for(int i=0;i<num_key.length;i++) {
			minCombo.addItem(num_key[i]);
			maxCombo.addItem(num_key[i]);
		}
		maxCombo.setSelectedIndex(num_key.length-1);
		display2.setLayout(ccc);
		display2.setBackground(Color.white);
		display2.add(text);
		display2.add(easy);
		display2.add(medium);
		display2.add(hard);
		display2.add(Min);
		display2.add(minCombo);
		display2.add(Max);
		display2.add(maxCombo);
		display2.add(reset);
		root.add(display2);
		setContentPane(root);
		// begin??? ????????? ???????????? ????????? (true)
		easy.setEnabled(false);
		guessNumShow = 10;
		h_count = 3;
		medium.setEnabled(true);
		hard.setEnabled(true);
		minCombo.setEnabled(true);
		maxCombo.setEnabled(true);
		reset.setVisible(false);
		reset.setEnabled(false);
		chance.setEnabled(false);
		for (int i = 0; i < checked.length; i++) {
			checked[i] = 0; // ?????? ???????????? ?????? ?????? (0)?????? ?????????
		}
		for (int i = 0; i < word_length; i++) {
			word1[i] = ' '; // character // ???????????? ????????? ????????? ?????????
			word2[i] = " "; // string // ????????? ????????? ???
		}
		/* ????????? ???????????? ????????? ?????? */
		wins = 0;
		looses = 0;
		winningProsentige = 0.0;
		repaint();
	}
	public void paint(Graphics screen) {
		super.paint(screen);
		Graphics2D screen2D = (Graphics2D) screen;
		screen2D.setFont(warningFont);
		screen2D.drawLine(70, 60, 130, 60);
		screen2D.drawLine(70, 60, 70, 80);
		screen2D.drawLine(130, 60, 130, 170);
		screen2D.drawLine(60, 170, 160, 170);
		if (level == 0) {
			switch (guessNum) {
				case 1:
					screen2D.drawOval(60, 80, 20, 20); // ??????
					break;
				case 2:
					screen2D.drawOval(60, 80, 20, 20); // ??????
					screen2D.drawLine(70, 100, 70, 130); // ??????
					break;
				case 3:
					screen2D.drawOval(60, 80, 20, 20); // ??????
					screen2D.drawLine(70, 100, 70, 130); // ??????
					screen2D.drawLine(70, 110, 80, 110); // ???
					break;
				case 4:
					screen2D.drawOval(60, 80, 20, 20); // ??????
					screen2D.drawLine(70, 100, 70, 130); // ??????
					screen2D.drawLine(70, 110, 90, 110); // ???
					break;
				case 5:
					screen2D.drawOval(60, 80, 20, 20); // ??????
					screen2D.drawLine(70, 100, 70, 130); // ??????
					screen2D.drawLine(60, 110, 90, 110); // ???
					break;
				case 6:
					screen2D.drawOval(60, 80, 20, 20); // ??????
					screen2D.drawLine(70, 100, 70, 130); // ??????
					screen2D.drawLine(50, 110, 90, 110); // ???
					break;
				case 7:
					screen2D.drawOval(60, 80, 20, 20); // ??????
					screen2D.drawLine(70, 100, 70, 130); // ??????
					screen2D.drawLine(50, 110, 90, 110); // ???
					screen2D.drawLine(70, 130, 62, 140); // ??? ??????
					break;
				case 8:
					screen2D.drawOval(60, 80, 20, 20); // ??????
					screen2D.drawLine(70, 100, 70, 130); // ??????
					screen2D.drawLine(50, 110, 90, 110); // 
					screen2D.drawLine(70, 130, 55, 150); // ??? ??????
					break;
				case 9:
					screen2D.drawOval(60, 80, 20, 20); // ??????
					screen2D.drawLine(70, 100, 70, 130); // ??????
					screen2D.drawLine(50, 110, 90, 110); // ???
					screen2D.drawLine(70, 130, 55, 150); // ??? ??????
					screen2D.drawLine(70, 130, 77, 140); // ?????? ??????
					break;
				case 10:
					screen2D.drawOval(60, 80, 20, 20); // ??????
					screen2D.drawLine(70, 100, 70, 130); // ??????
					screen2D.drawLine(50, 110, 90, 110); // ???
					screen2D.drawLine(70, 130, 55, 150); // ??? ??????
					screen2D.drawLine(70, 130, 85, 150); // ?????? ??????
					break;
			}
		}
		if (level == 1) {
			switch (guessNum) {
				case 1:
					screen2D.drawOval(60, 80, 20, 20); // ??????
					break;
				case 2:
					screen2D.drawOval(60, 80, 20, 20); // ??????
					screen2D.drawLine(70, 100, 70, 130); // ??????
					break;
				case 3:
					screen2D.drawOval(60, 80, 20, 20); // ??????
					screen2D.drawLine(70, 100, 70, 130); // ??????
					screen2D.drawLine(50, 110, 70, 110); // ???
					break;
				case 4:
					screen2D.drawOval(60, 80, 20, 20); // ??????
					screen2D.drawLine(70, 100, 70, 130); // ??????
					screen2D.drawLine(50, 110, 90, 110); // ???
					break;
				case 5:
					screen2D.drawOval(60, 80, 20, 20); // ??????
					screen2D.drawLine(70, 100, 70, 130); // ??????
					screen2D.drawLine(50, 110, 90, 110); // ???
					screen2D.drawLine(70, 130, 62, 140); // ??? ??????
					break;
				case 6:
					screen2D.drawOval(60, 80, 20, 20); // ??????
					screen2D.drawLine(70, 100, 70, 130); // ??????
					screen2D.drawLine(50, 110, 90, 110); // ???
					screen2D.drawLine(70, 130, 55, 150); // ??? ??????
					break;
				case 7:
					screen2D.drawOval(60, 80, 20, 20); // ??????
					screen2D.drawLine(70, 100, 70, 130); // ??????
					screen2D.drawLine(50, 110, 90, 110); // ???
					screen2D.drawLine(70, 130, 55, 150); // ??? ??????
					screen2D.drawLine(70, 130, 77, 140); // ?????? ??????
					break;
				case 8:
					screen2D.drawOval(60, 80, 20, 20); // ??????
					screen2D.drawLine(70, 100, 70, 130); // ??????
					screen2D.drawLine(50, 110, 90, 110); // ???
					screen2D.drawLine(70, 130, 55, 150); // ??? ??????
					screen2D.drawLine(70, 130, 85, 150); // ?????? ??????
					break;
			}
		}
		if (level == 2) {
			switch (guessNum) {
				case 1:
					screen2D.drawOval(60, 80, 20, 20); // ??????
					break;
				case 2:
					screen2D.drawOval(60, 80, 20, 20); // ??????
					screen2D.drawLine(70, 100, 70, 130); // ??????
					break;
				case 3:
					screen2D.drawOval(60, 80, 20, 20); // ??????
					screen2D.drawLine(70, 100, 70, 130); // ??????
					screen2D.drawLine(70, 110, 90, 110); // ???
					break;
				case 4:
					screen2D.drawOval(60, 80, 20, 20); // ??????
					screen2D.drawLine(70, 100, 70, 130); // ??????
					screen2D.drawLine(50, 110, 90, 110); // ???
					break;
				case 5:
					screen2D.drawOval(60, 80, 20, 20); // ??????
					screen2D.drawLine(70, 100, 70, 130); // ??????
					screen2D.drawLine(50, 110, 90, 110); // ???
					screen2D.drawLine(70, 130, 55, 150); // ??? ??????
					break;
				case 6:
					screen2D.drawOval(60, 80, 20, 20); // ??????
					screen2D.drawLine(70, 100, 70, 130); // ??????
					screen2D.drawLine(50, 110, 90, 110); // ???
					screen2D.drawLine(70, 130, 55, 150); // ??? ??????
					screen2D.drawLine(70, 130, 85, 150); // ?????? ??????
					break;
			}
		}
		screen2D.setColor(Color.RED);
		screen2D.drawString(Integer.toString(guessNumShow) + " guesses left", 340, 240);
		screen2D.setFont(normalFont);
		screen2D.setColor(Color.BLACK);
		screen2D.drawString("Current skill level: " + slevel[level], 220, 220);
		screen2D.drawString("Score: " + score, 450, 220);
		screen2D.drawString("Wins ", 220, 200);
		screen2D.drawString(Integer.toString((int) wins), 265, 200);
		screen2D.drawString("Looses", 300, 200);
		screen2D.drawString(Integer.toString((int) looses), 365, 200);
		screen2D.drawString("WinningProsentige", 400, 200);
		screen2D.drawString(Double.toString(winningProsentige) + "%", 555, 200);
		if (word_length >= 4 && word_length <= 12) {
			for (int i = 0; i < word_length; i++) {
				screen2D.setFont(normalFont);
				screen2D.setColor(Color.BLACK);
				screen2D.drawString(word2[i], 300 + i * 20, 150);
			}
		}
		else {
			// System.out.println("?????? ?????? ??????");
		}
	}
	public static boolean remains(final int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 0) {
				return true;
			}
		}
		return false;
	}
	public void wordSelect() {
		double sel_num = Math.random() * 201;// 0~200.xx
		selection = (int) Math.floor(sel_num); // 0~200
		while (true) { /* ?????? ????????? ????????? ?????? ???????????? ????????? ???????????? ??? */
			// ????????? ????????? ??? ????????? ??????
			if(checked[selection] == 0&&setWordLength<=words[selection].length()&&setWordLength2>=words[selection].length()) { // ?????? ?????? ????????? ???????????? 0, ????????? ????????? ????????? ????????? ??????
				break;
			}
			else {
				sel_num = Math.random() * 201;
				selection = (int) Math.floor(sel_num);
			}
		}
		String sel_Word;
		if (words[selection] != null) { // ?????? ????????? null??? ???????????????
			sel_Word = words[selection].toLowerCase();
			word_length = sel_Word.length();
			char[] temp = sel_Word.toCharArray(); // character ????????? ??????
			for (int index1 = 0; index1 < word_length; index1++) {
				word1[index1] = temp[index1];
			}
			for (int index2 = 0; index2 < word_length; index2++) {
				word2[index2] = "_"; // .?????? _??? ???????????? ????????? ?????? ?????? ?????????
			}
		}
	}
	public void word_reset() {
		for (int i = 0; i < word_length; i++) {
			word2[i] = "_";
		}
		wordSelect();
	}
	public void spell_check(char spell) {
		check_key = 0;
		for (int i = 0; i < word_length; i++) { // 12??? ?????? ????????? ??????
			if (word1[i] != ' ') {
				if (word1[i] == spell) {
					word2[i] = "" + spell;
					check_key = 1;
					repaint();
				}
			}
		}
		if (check_key == 0) { // ????????? ??? ???????????? check_key??? 0?????? ?????? ??????(a)??? ??????
			guessNum--;
			guessNumShow--;
			repaint();
		}
		System.out.println(words[selection]);
		Adjust_display();
		repaint();
	}
	public void Adjust_display() {
		boolean correct = false;
		for (int i = 0; i < word_length; i++) {
			if (word2[i] != "_") {
				correct = true;
			}
			else {
				correct = false;
				break;
			}
		}
		if (correct) {
			for (int i = 0; i < Jbutton.length; i++) {
				Jbutton[i].setEnabled(false);
			}
			begin.setEnabled(true);
			if (level == 0) {
				guessNumShow = 10;
				medium.setEnabled(true);
				hard.setEnabled(true);
			} else if (level == 1) {
				guessNumShow = 8;
				easy.setEnabled(true);
				hard.setEnabled(true);
			} else if (level == 2) {
				guessNumShow = 6;
				easy.setEnabled(true);
				medium.setEnabled(true);
			}
			minCombo.setEnabled(true);
			maxCombo.setEnabled(true);
			checked[selection] = 1;
			wins++;
			winningProsentige = (wins / (wins + looses)) * 100.0;
			score += guessNum * 10;
			chance.setEnabled(false);
			h_count = 3;
			Jlabel.setText("The number of hints: " + h_count);
			w_check.clear();
			repaint();
		}
		if (guessNum <= 0) { // ?????? ?????? ??????
			// ?????? ?????? ??? ?????? ??????
			for (int i = 0; i < Jbutton.length; i++) {
				Jbutton[i].setEnabled(false);
			}
			// ????????? ????????? ??????
			for (int i = 0; i < word_length; i++) {
				word2[i] = "" + word1[i];
			}
			begin.setEnabled(true);
			// level??? ?????? ?????? ????????? ??????
			if (level == 0) {
				guessNumShow = 10;
				medium.setEnabled(true);
				hard.setEnabled(true);
			} else if (level == 1) {
				guessNumShow = 8;
				easy.setEnabled(true);
				hard.setEnabled(true);
			} else if (level == 2) {
				guessNumShow = 6;
				easy.setEnabled(true);
				medium.setEnabled(true);
			}
			minCombo.setEnabled(true);
			maxCombo.setEnabled(true);
			checked[selection] = 1;
			looses++;
			winningProsentige = (wins / (wins + looses)) * 100.0;
			h_count = 3;
			Jlabel.setText("The number of hints: " + h_count);
			w_check.clear();
			chance.setEnabled(false);
			repaint();
		}
		boolean val = remains(checked);
		if (!val) {
			System.out.println("?????? ??????");
			reset.setVisible(true);
			reset.setEnabled(true);
			begin.setEnabled(false);
			easy.setEnabled(false);
			medium.setEnabled(false);
			hard.setEnabled(false);
			for (int i = 0; i < alpa_key.length; i++) {
				Jbutton[i].setEnabled(false);
			}
			for (int i = 0; i < word_length; i++) {
				word1[i] = ' '; // character // ???????????? ????????? ????????? ?????????
				word2[i] = " "; // string // ????????? ????????? ???
			}
			word2[0] = endMsg;
		}
	}
	public void actionPerformed(ActionEvent event) {
		String typed = event.getActionCommand(); // ?????? ????????? ???????????? ?????????
		if (typed.equals("BEGIN")) {
			check_key = 99;
			for (int i = 0; i < word_length; i++) {
				word1[i] = ' ';
				word2[i] = "_";
			}
			easy.setEnabled(false);
			medium.setEnabled(false);
			hard.setEnabled(false);
			minCombo.setEnabled(false);
				maxCombo.setEnabled(false);
			if (level == 0) {
				guessNum = 10;
			} else if (level == 1) {
				guessNum = 8;
			} else if (level == 2) {
				guessNum = 6;
			}
			for (int i = 0; i < Jbutton.length; i++) {
				Jbutton[i].setEnabled(true);
				Jbutton[i].setText(alpa_key[i]);
				Jbutton[i].setBackground(Color.ORANGE);
				Jbutton[i].setFont(normalFont);
			}
			repaint();
			for (int i = 0; i < Jbutton.length; i++) {
				Jbutton[i].setEnabled(true);
			}
			h_count = 3;
			Jlabel.setText("The number of hints: " + h_count);
			begin.setEnabled(false);
			word_reset();
		}
		for (int i = 0; i < Jbutton.length; i++) {
			if (typed.equals(Jbutton[i].getActionCommand())) {
				Jbutton[i].setEnabled(false);
				String aa = Jbutton[i].getActionCommand().toLowerCase();
				char[] bb = aa.toCharArray();
				w_check.add(bb[0]);
				spell_check(bb[0]);
				spell_key = i;
				if (check_key == 0) {
					Jbutton[i].setText("??");
					Jbutton[i].setFont(new Font("", Font.BOLD, 40));
					Jbutton[i].setBackground(Color.RED);
				}
				if (check_key == 1) {
					Jbutton[i].setText("???");
					Jbutton[i].setFont(new Font("", Font.BOLD, 30));
					Jbutton[i].setBackground(Color.GREEN);
				}
				if (guessNumShow == 1) {
					chance.setEnabled(true);
				}
			}
		}
		if (typed.equals("EASY")) {
			easy.setEnabled(false);
			medium.setEnabled(true);
			hard.setEnabled(true);
			level = 0;
			guessNumShow = 10;
			repaint();
		}
		if (typed.equals("MEDIUM")) {
			easy.setEnabled(true);
			medium.setEnabled(false);
			hard.setEnabled(true);
			level = 1;
			guessNumShow = 8;
			repaint();
		}
		if (typed.equals("HARD")) {
			easy.setEnabled(true);
			medium.setEnabled(true);
			hard.setEnabled(false);
			level = 2;
			guessNumShow = 6;
			repaint();
		}
		if (typed.equals("Chance")) {
			String word = words[selection];
			int w_length = word.length();
			char hint_word;
			for (int i = 0; i < w_length; i++) {
				if (!w_check.contains(word.charAt(i))) {
					hint_word = word.charAt(i);
					w_check.add(hint_word);
					aaa = Character.toString(hint_word);
					spell_check(hint_word);
					h_count--;
					Jlabel.setText("The number of hints: " + h_count);
					break;
				}
			}
			for (int i = 0; i < alpa_key.length; i++) {
				if (alpa_key[i].toLowerCase().equals(aaa)) {
					Jbutton[i].setText("???");
					Jbutton[i].setFont(new Font("", Font.BOLD, 30));
					Jbutton[i].setBackground(Color.GREEN);
					Jbutton[i].setEnabled(false);
					break;
				}
			}
			if (h_count == 0) {
				chance.setEnabled(false);
			}
		}
		if (typed.equals("RESET")) {
			wins = 0;
			looses = 0;
			winningProsentige = 0.0;
			for (int i = 0; i < checked.length; i++) {
				checked[i] = 0; // ?????? ???????????? ?????? ?????? (0)?????? ?????????
			}
			easy.setEnabled(false);
			guessNumShow = 10;
			guessNum = 0;
			begin.setEnabled(true);
			medium.setEnabled(true);
			hard.setEnabled(true);
			reset.setEnabled(false);
			reset.setVisible(false);
			for (int i = 0; i < word_length; i++) {
				word1[i] = ' '; // character // ???????????? ????????? ????????? ?????????
				word2[i] = " "; // string // ????????? ????????? ???
			}
			repaint();
		}
	}
	public static void main(String[] args) {
		Hangman_2 h = new Hangman_2();
		h.init();
	}
}