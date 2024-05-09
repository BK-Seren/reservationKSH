package reservationKSH;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
public class UserPanel extends JPanel{
    private Image backgroundImage;
    private ImageIcon logoutEnteredIcon = new ImageIcon("images/btn_logout_entered.png");
    private ImageIcon backEnteredIcon = new ImageIcon("images/btn_back_entered.png");
    public UserPanel(LoginPanel loginPanel){
        setLayout(null); // 널 레이아웃 사용
        backgroundImage = new ImageIcon("images/suc.png").getImage(); // 배경 이미지 설정

        // 버튼 설정
        ImageIcon logoutIcon = new ImageIcon("images/btn_logout.png");
        ImageIcon backIcon = new ImageIcon("images/btn_back.png");
        JButton logoutButton = new JButton(logoutIcon);
        JButton backButton = new JButton(backIcon);

        // 버튼 위치 및 크기 설정
        logoutButton.setBounds(420,500,logoutIcon.getIconWidth(), logoutIcon.getIconHeight());
        backButton.setBounds(30,30,backIcon.getIconWidth(), backIcon.getIconHeight());

        // 버튼 배경 및 테두리 설정 해제
        logoutButton.setContentAreaFilled(false);
        logoutButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);

        // 뒤로가기 버튼에 대한 이벤트 리스너 설정
        backButton.addActionListener(e-> {
            ResPanel resPanel = new ResPanel(loginPanel);
            loginPanel.switchPanel(resPanel);
        });

        logoutButton.addActionListener(e-> {
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

        logoutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                logoutButton.setIcon(logoutEnteredIcon); // 마우스가 버튼 위에 있을 때 아이콘 변경
                logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 커서 변경
            }

            @Override
            public void mouseExited(MouseEvent e) {
                logoutButton.setIcon(logoutIcon); // 마우스가 버튼을 벗어났을 때 아이콘 복원
                logoutButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 커서 복원
            }
        });

        add(logoutButton);
        add(backButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.PLAIN, 34)); // 폰트 설정
        g.drawString(LoginPanel.username, 520, 360); // 텍스트 그리기
        if(LoginPanel.userres == 1){
            g.drawString(String.valueOf(LoginPanel.userres), 570, 460);
        }
        else{
            g.drawString("-", 570, 455);
        }

    }

}
