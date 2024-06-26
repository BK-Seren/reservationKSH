package reservationKSH;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
public class LoginPanel extends JFrame{
    public JPanel loginpanel;

    // 각 버튼 이미지아이콘 변수 생성
    public ImageIcon loginButtonImage = new ImageIcon("images/btn_login.png");
    public ImageIcon loginButtonEnteredImage = new ImageIcon("images/btn_login_entered.png");
    public ImageIcon joinButtonImage = new ImageIcon("images/btn_join.png");
    public ImageIcon joinButtonEnteredImage = new ImageIcon("images/btn_join_entered.png");
    public ImageIcon closeButtonImage = new ImageIcon("images/btn_close.png");
    public ImageIcon closeButtonEnteredImage = new ImageIcon("images/btn_close_entered.png");

    // 각 버튼 JButton 변수 생성
    public JButton loginButton = new JButton(loginButtonImage);
    public JButton joinButton = new JButton(joinButtonImage);
    public JButton closeButton = new JButton(closeButtonImage);

    // Screen 가로, 세로 길이
    public static final int SCREEN_WIDTH = 1080;
    public static final int SCREEN_HEIGHT = 720;

    // MainPanel 배경 이미지
    public static Image background = new ImageIcon("images/login.png").getImage();

    public Container cPane;

    public JTextField id;
    public JPasswordField pw;

    public static String username = "root";
    public static String userid = "root";
    public static int userres = 0;
    public String checkpw = "";
    public String checkid = "";
    public static String userpw = "root";

    public void chartoString(){

        char[] chpw = pw.getPassword();
        for(int i = 0; i<chpw.length; i++){
            System.out.println(chpw.length);
            checkpw += chpw[i];
        }
    }

    public void resetValues(){
        checkid = "";
        checkpw = "";
        id.setText("");
        pw.setText("");
    }
    public void resetInfo(){
        username = "";
        userid = "";
        userres = 0;
        userpw = "";
    }

    public void switchPanel(JPanel panel){
        getContentPane().removeAll(); // 기존 패널 제거
        getContentPane().add(panel); // 새로운 패널 추가
        getContentPane().revalidate(); // 레이아웃 관리자에게 레이아웃을 다시 계산하도록 지시
        getContentPane().repaint(); // 화면을 다시 그리도록 지시
    }

    public JPanel getLoginContentPanel(){return loginpanel;}

    public LoginPanel() {
        cPane = getContentPane();
        setTitle("KSH Reservation");
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        Toolkit kit = Toolkit.getDefaultToolkit();

        Image img = kit.getImage("images/logo.png");
        setIconImage(img);

        loginpanel = new drawback();

        id = new JTextField();
        id.setBounds(420,325,200,30);
        id.setOpaque(false);
        id.setBorder(null);
        id.setForeground(Color.WHITE);
        Font font = new Font("SansSerif", Font.PLAIN, 30);
        id.setFont(font);
        id.setDocument(new JTextFieldLimit(10));

        pw = new JPasswordField(20);
        pw.setBounds(420,395,200,30);
        pw.setOpaque(false);
        pw.setBorder(null);
        pw.setForeground(Color.WHITE);
        pw.setFont(font);

        loginButton.setBounds(700,310,130,129);
        loginButton.setBorderPainted(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setFocusPainted(true);

        loginButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e){
                loginButton.setIcon(loginButtonEnteredImage);
                loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e){
                loginButton.setIcon(loginButtonImage);
                loginButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                checkid = id.getText();
                if(userid.equals(checkid)){
                    chartoString();
                    if(userpw.equals(checkpw)){
                        ResPanel resPanel = new ResPanel(LoginPanel.this);

                        getContentPane().removeAll();
                        getContentPane().add(resPanel);
                        getContentPane().revalidate();
                        getContentPane().repaint();
                    }
                    else{
                        JOptionPane.showMessageDialog(LoginPanel.this, "비밀번호가 잘못되었습니다.");
                        resetValues();
                    }
                }
                else{
                    JOptionPane.showMessageDialog(LoginPanel.this, "아이디나 비밀번호가 잘못되었거나 없는 회원 정보입니다.");
                    resetValues();
                }

            }

        });

        joinButton.setBounds(580,500,290,60);
        joinButton.setBorderPainted(false);
        joinButton.setContentAreaFilled(false);
        joinButton.setFocusPainted(true);

        joinButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e){
                joinButton.setIcon(joinButtonEnteredImage);
                joinButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e){
                joinButton.setIcon(joinButtonImage);
                joinButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                JoinPanel joinPanel = new JoinPanel(LoginPanel.this);

                getContentPane().removeAll();
                getContentPane().add(joinPanel);
                getContentPane().revalidate();
                getContentPane().repaint();

            }

        });

        closeButton.setBounds(210,500,290,60);
        closeButton.setBorderPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setFocusPainted(true);

        closeButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e){
                closeButton.setIcon(closeButtonEnteredImage);
                closeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e){
                closeButton.setIcon(closeButtonImage);
                closeButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                dispose();
            }

        });

        cPane.add(loginpanel);
        loginpanel.setLayout(null);
        loginpanel.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        loginpanel.add(loginButton);
        loginpanel.add(joinButton);
        loginpanel.add(closeButton);
        loginpanel.add(id);
        loginpanel.add(pw);
        setVisible(true);
    }
    class drawback extends JPanel{
        @Override
        public void paintComponent(Graphics graphics){

            super.paintComponent(graphics);

            graphics.drawImage(background,0,0, getWidth(), getHeight(),this );
        }

    }
}
