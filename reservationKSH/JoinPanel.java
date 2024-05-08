package reservationKSH;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 회원가입 클래스
 */
public class JoinPanel extends JPanel{
    private Image backgroundImage;
    private ImageIcon joinEnteredIcon = new ImageIcon("images/btn_join_entered.png");
    private ImageIcon backEnteredIcon = new ImageIcon("images/btn_back_entered.png");

    public JTextField id;
    public JPasswordField pw;
    public JTextField name;
    public JTextField ngrade;
    public JTextField nclass;

    public JoinPanel(LoginPanel loginPanel){
        setLayout(null); // 널 레이아웃 사용
        backgroundImage = new ImageIcon("images/join.png").getImage(); // 배경 이미지 설정

        id = new JTextField();
        id.setBounds(420,205,200,30);
        id.setOpaque(false);
        id.setBorder(null);
        id.setForeground(Color.WHITE);
        Font font = new Font("SansSerif", Font.PLAIN, 30);
        id.setFont(font);

        pw = new JPasswordField(20);
        pw.setBounds(420,285,200,30);
        pw.setOpaque(false);
        pw.setBorder(null);
        pw.setForeground(Color.WHITE);
        pw.setFont(font);

        // 버튼 설정
        ImageIcon joinIcon = new ImageIcon("images/btn_join.png");
        ImageIcon backIcon = new ImageIcon("images/btn_back.png");
        JButton joinButton = new JButton(joinIcon);
        JButton backButton = new JButton(backIcon);

        // 버튼 위치 및 크기 설정
        joinButton.setBounds(385,550,joinIcon.getIconWidth(), joinIcon.getIconHeight());
        backButton.setBounds(40,40,backIcon.getIconWidth(), backIcon.getIconHeight());

        // 버튼 배경 및 테두리 설정 해제
        joinButton.setContentAreaFilled(false);
        joinButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);

        // 뒤로가기 버튼에 대한 이벤트 리스너 설정
        backButton.addActionListener(e-> {
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
        });

        // 회원가입 버튼에 대한 이벤트 리스너 설정

        add(id);
        add(pw);
        add(joinButton);
        add(backButton);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
