package reservationKSH;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ResPanel extends JPanel{
    private Image backgroundImage;
    private ImageIcon sit1EnteredIcon = new ImageIcon("images/sit_1_ent.png");
    private ImageIcon backEnteredIcon = new ImageIcon("images/btn_back_entered.png");
    public ResPanel(LoginPanel loginPanel){
        setLayout(null); // 널 레이아웃 사용
        backgroundImage = new ImageIcon("images/res.png").getImage(); // 배경 이미지 설정

        // 버튼 설정
        ImageIcon sit1Icon = new ImageIcon("images/sit_1.png");
        ImageIcon backIcon = new ImageIcon("images/btn_back.png");
        JButton sit1Button = new JButton(sit1Icon);
        JButton backButton = new JButton(backIcon);

        // 버튼 위치 및 크기 설정
        sit1Button.setBounds(90,140,sit1Icon.getIconWidth(), sit1Icon.getIconHeight());
        backButton.setBounds(30,30,backIcon.getIconWidth(), backIcon.getIconHeight());

        // 버튼 배경 및 테두리 설정 해제
        sit1Button.setContentAreaFilled(false);
        sit1Button.setBorderPainted(false);
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

        sit1Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                sit1Button.setIcon(sit1EnteredIcon); // 마우스가 버튼 위에 있을 때 아이콘 변경
                sit1Button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 커서 변경
            }

            @Override
            public void mouseExited(MouseEvent e) {
                sit1Button.setIcon(sit1Icon); // 마우스가 버튼을 벗어났을 때 아이콘 복원
                sit1Button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 커서 복원
            }
        });

        // 회원가입 버튼에 대한 이벤트 리스너 설정

        add(sit1Button);
        add(backButton);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}