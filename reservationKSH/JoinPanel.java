package reservationKSH;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import static javax.swing.JOptionPane.WARNING_MESSAGE;

/**
 * 회원가입 클래스
 */
public class JoinPanel extends JPanel{
    private Image backgroundImage;
    private ImageIcon joinEnteredIcon = new ImageIcon("images/btn_join_entered.png");
    private ImageIcon idEnteredIcon = new ImageIcon("images/id_double_entered.png");
    private ImageIcon backEnteredIcon = new ImageIcon("images/btn_back_entered.png");

    public JTextField mid;
    public JPasswordField mpw;
    public JPasswordField mpwd;
    public JTextField name;
    public JTextField ngrade;
    public JTextField nclass;


    public char[] savedpw;
    public char[] savedpwd;

    private boolean idCheck(String id){
        return false;
    }

    public JoinPanel(LoginPanel loginPanel){
        setLayout(null); // 널 레이아웃 사용
        backgroundImage = new ImageIcon("images/join.png").getImage(); // 배경 이미지 설정

        mid = new JTextField();
        mid.setBounds(420,205,200,30);
        mid.setOpaque(false);
        mid.setBorder(null);
        mid.setForeground(Color.WHITE);
        Font font = new Font("SansSerif", Font.PLAIN, 30);
        mid.setFont(font);
        mid.setDocument(new JTextFieldLimit(10));

        mpw = new JPasswordField(20);
        mpw.setBounds(420,265,200,30);
        mpw.setOpaque(false);
        mpw.setBorder(null);
        mpw.setForeground(Color.WHITE);
        mpw.setFont(font);

        mpwd = new JPasswordField(20);
        mpwd.setBounds(420,330,200,30);
        mpwd.setOpaque(false);
        mpwd.setBorder(null);
        mpwd.setForeground(Color.WHITE);
        mpwd.setFont(font);

        name = new JTextField();
        name.setBounds(420,395,200,30);
        name.setOpaque(false);
        name.setBorder(null);
        name.setForeground(Color.WHITE);
        name.setFont(font);
        name.setDocument(new JTextFieldLimit(7));

        ngrade = new JTextField();
        ngrade.setBounds(420,460,200,30);
        ngrade.setOpaque(false);
        ngrade.setBorder(null);
        ngrade.setForeground(Color.WHITE);
        ngrade.setFont(font);
        ngrade.setDocument(new JTextFieldLimit(1, "123"));

        nclass = new JTextField();
        nclass.setBounds(420,525,200,30);
        nclass.setOpaque(false);
        nclass.setBorder(null);
        nclass.setForeground(Color.WHITE);
        nclass.setFont(font);
        nclass.setDocument(new JTextFieldLimit(2, "0123456789"));

        // 버튼 설정
        ImageIcon joinIcon = new ImageIcon("images/btn_join.png");
        ImageIcon idIcon = new ImageIcon("images/id_double.png");
        ImageIcon backIcon = new ImageIcon("images/btn_back.png");
        JButton joinButton = new JButton(joinIcon);
        JButton idButton = new JButton(idIcon);
        JButton backButton = new JButton(backIcon);

        // 버튼 위치 및 크기 설정
        joinButton.setBounds(385,600,joinIcon.getIconWidth(), joinIcon.getIconHeight());
        idButton.setBounds(600,205,idIcon.getIconWidth(), idIcon.getIconHeight());
        backButton.setBounds(40,40,backIcon.getIconWidth(), backIcon.getIconHeight());

        // 버튼 배경 및 테두리 설정 해제
        joinButton.setContentAreaFilled(false);
        joinButton.setBorderPainted(false);
        idButton.setContentAreaFilled(false);
        idButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);

        // 뒤로가기 버튼에 대한 이벤트 리스너 설정
        backButton.addActionListener(e-> {
            loginPanel.resetValues();
            loginPanel.switchPanel(loginPanel.getLoginContentPanel());
        });

        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                backButton.setIcon(backEnteredIcon); // 마우스가 버튼 위에 있을 때 아이콘 변경
                backButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 커서 변경
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backButton.setIcon(backIcon); // 마우스가 버튼을 벗어났을 때 아이콘 복원
                backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 커서 복원
            }
        });

        // 아이디 중복체크 이벤트 리스너 발생
        idButton.addActionListener(e-> {
            boolean isDuplicated = idCheck(mid.getText());
            if(isDuplicated){
                JOptionPane.showMessageDialog(null, "이미 사용 중인 아이디입니다.", "경고", JOptionPane.WARNING_MESSAGE);
                return;
            }else{
                loginPanel.userid = mid.getText();
            }


        });

        idButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                idButton.setIcon(idEnteredIcon); // 마우스가 버튼 위에 있을 때 아이콘 변경
                idButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 커서 변경
            }

            @Override
            public void mouseExited(MouseEvent e) {
                idButton.setIcon(idIcon); // 마우스가 버튼을 벗어났을 때 아이콘 복원
                idButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 커서 복원
            }
        });

        joinButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                joinButton.setIcon(joinEnteredIcon); // 마우스가 버튼 위에 있을 때 아이콘 변경
                joinButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 커서 변경
            }

            @Override
            public void mouseExited(MouseEvent e) {
                joinButton.setIcon(joinIcon); // 마우스가 버튼을 벗어났을 때 아이콘 복원
                joinButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 커서 복원
            }


            @Override
            public void mousePressed(MouseEvent e){
                loginPanel.resetInfo();
                loginPanel.username = name.getText();
                savedpw = mpw.getPassword();
                savedpwd = mpwd.getPassword();
                if(!Arrays.equals(savedpw, savedpwd)){
                    JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.", "경고", JOptionPane.WARNING_MESSAGE );
                    return;
                }
                if (savedpw.length > 20) {
                    JOptionPane.showMessageDialog(null, "비밀번호는 20자 이내여야 합니다.", "경고", WARNING_MESSAGE);
                    return;
                }
                for(int i = 0; i<savedpw.length; i++){
                    loginPanel.userpw += savedpw[i];
                }
                JOptionPane.showMessageDialog(JoinPanel.this, "가입완료!");
                loginPanel.resetValues();
                loginPanel.switchPanel(loginPanel.getLoginContentPanel());
            }
        });

        // 회원가입 버튼에 대한 이벤트 리스너 설정

        add(mid);
        add(mpw);
        add(mpwd);
        add(name);
        add(ngrade);
        add(nclass);
        add(joinButton);
        add(backButton);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}

class JTextFieldLimit extends PlainDocument{
    private int limit;
    private String allowedChars;

    JTextFieldLimit(int limit){
        super();
        this.limit = limit;
    }

    JTextFieldLimit(int limit, String allowedChars){
        super();
        this.limit = limit;
        this.allowedChars = allowedChars;
    }

    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if(str == null) return;
        if(allowedChars != null && !allowedChars.isEmpty()){
            for(char c : str.toCharArray()){
                if(limit == 0 || getLength() < limit){
                    if(allowedChars.contains(String.valueOf(c))) {
                        super.insertString(offset, String.valueOf(c), attr);
                    }
                }
            }
        } else{
            if(limit == 0 || getLength() < limit){
                super.insertString(offset, str, attr);
            }
        }
    }
}
