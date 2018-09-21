package gui.swing;

import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

class Window01 extends JFrame{
//	컴포넌트 배치용 공간
	private Container con = this.getContentPane();
	
	private JLabel result;
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	private Dialog popup = new Dialog(Window01.this, true);
	
	private JButton Button_1 = new JButton("확인");
	private JButton Button_2 = new JButton("취소");
	
	private JTextField id = new JTextField();
	private JPasswordField pw = new JPasswordField();
	
	private JLabel id_name = new JLabel("ID");
	private JLabel pw_name = new JLabel("Password");
	
	public Window01() {
		this.display();
		this.event();
		this.menu();
		this.setTitle("스윙 예제");
		this.setSize(500, 400);
		this.setLocation(100,100);
//		this.setLocationByPlatform();
		this.setVisible(true);
	}

	private void menu() {
		this.getContentPane().setLayout(null);
		
		Button_1.setBounds(112, 292, 97, 23);
		this.getContentPane().add(Button_1);
		
		Button_2.setBounds(262, 292, 97, 23);
		this.getContentPane().add(Button_2);
		
		id.setBounds(262, 66, 116, 21);
		this.getContentPane().add(id);
		id.setColumns(10);
		
		pw.setBounds(262, 140, 116, 18);
		this.getContentPane().add(pw);
		pw.setColumns(10);

		id_name.setBounds(112, 69, 57, 15);
		this.getContentPane().add(id_name);
		
		pw_name.setBounds(112, 143, 86, 15);
		this.getContentPane().add(pw_name);
		
		result = new JLabel("결과 화면");
		result.setBounds(131, 190, 300, 70);
		this.getContentPane().add(result);
		
	}

	private void event() {
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE); 	//자동 프로그램 종료 선언 (JFrame.DISPOSE_ON_CLOSE[CLOSE로 끝나는 종료 명령어들]) 
//															자신이 JFrame일땐 DISPOSE_ON_CLOSE이렇게만 쓰여도 된다
		WindowListener window = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int choice = JOptionPane.showConfirmDialog(Window01.this, "정말 종료하시겠습니까?", 
						"종료", JOptionPane.YES_NO_CANCEL_OPTION);
				if(choice == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
				return;
			}
		};
		this.addWindowListener(window);
		
		Button_1.addActionListener(e->{
			String strId=id.getText();
			String strPw=String.valueOf(pw.getPassword());
			
			String regex = "^[0-9a-z\\-_]{8,20}$";
			String regex_2 = "^[0-9a-zA-Z\\-_\\!\\@\\#\\$]{6,15}$";
			
			if(Pattern.matches(regex, strId) && Pattern.matches(regex_2, strPw)){
				JOptionPane.showConfirmDialog(Window01.this, "정상적으로 로그인이 되었습니다.", "확인", JOptionPane.PLAIN_MESSAGE);
			}else{
				JOptionPane.showConfirmDialog(Window01.this, "아이디와 비밀번호가 틀렸습니다.", "실패", JOptionPane.PLAIN_MESSAGE);
			}
		}); 
	}
	private void display() {
		this.setLocation((dim.width/2)-(getWidth()/2),(dim.height/2)-(getHeight()/2));
	}
	
}

public class Test01_exam {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
		}catch (Exception e) {
			e.printStackTrace();
		}
		Window01 frame = new Window01();			
		frame.setVisible(true);
	}
}
