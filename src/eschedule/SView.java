package eschedule;
import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.util.*;

public class SView extends JFrame{
	Schedule sch;
	List<Match> matches;
	JPanel base;
	
	SView(Schedule s) {
		sch = s;
		matches = s.getMatch();
		settings();
	}
	
	private void settings() {
		
		setMinimumSize(new Dimension(300, 400));
		setPreferredSize(new Dimension(300, 600));
		
		base = new JPanel();
		base.setLayout(new BoxLayout(base, BoxLayout.Y_AXIS));
		
		JScrollPane scrollPane = new JScrollPane(base);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane);
	}
	
	public void addMatches() {
		for (Match m : matches) {
			if (m.getStatus() == Match.Status.VISIBLE) {
				addMatch(m);
			}
		}			
	}
	
	private void addWeekBlock(Match[] match) {

	}
	
	void addMatch(Match m) {
		JPanel match = new JPanel();
		Team t1 = m.getTeam(1);
		Team t2 = m.getTeam(2);
		
		match.setLayout(new BoxLayout(match, BoxLayout.X_AXIS));
		match.setBorder(BorderFactory.createTitledBorder(Tools.getSDF().format(m.getTime())));
		JLabel m11 = new JLabel(t1.toString());
		JLabel m12 = new JLabel(new ImageIcon(t1.getSprite()));
		JLabel m21 = new JLabel(t2.toString());
		JLabel m22 = new JLabel(new ImageIcon(t2.getSprite()));
		JLabel div = new JLabel(" vs. ");
		match.add(m12);
		match.add(m11);
		match.add(div);
		match.add(m21);
		match.add(m22);
		base.add(match);
	}
	
	public void display() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		base.setVisible(true);
		setVisible(true);
	}
}
