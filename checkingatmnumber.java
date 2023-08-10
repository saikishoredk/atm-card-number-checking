import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMGUI extends JFrame {

    private Bank theBank;
    private User curUser;
    private JTextField userIDField;
    private JPasswordField pinField;
    private JTextArea accountSummaryArea;
    private JComboBox<String> accountComboBox;
    private JTextField amountField;
    private JTextArea memoArea;

    public ATMGUI() {
        super("ATM Application");

        theBank = new Bank("State Bank Of India");

        User aUser = theBank.addUser("Aarsh", "Dhokai", "1111");
        Account newAccount = new Account("Checking", aUser, theBank);
        aUser.addAccount(newAccount);
        theBank.addAccount(newAccount);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        JPanel loginPanel = new JPanel(new GridLayout(3, 2));
        JLabel userIDLabel = new JLabel("User ID:");
        JLabel pinLabel = new JLabel("PIN:");
        userIDField = new JTextField();
        pinField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userID = userIDField.getText();
                String pin = new String(pinField.getPassword());
                curUser = theBank.userLogin(userID, pin);
                if (curUser != null) {
                    showUserMenu();
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect User ID/PIN, Please try again");
                }
            }
        });
        loginPanel.add(userIDLabel);
        loginPanel.add(userIDField);
        loginPanel.add(pinLabel);
        loginPanel.add(pinField);
        loginPanel.add(new JLabel()); // Empty label for spacing
        loginPanel.add(loginButton);
        add(loginPanel, BorderLayout.CENTER);

        accountSummaryArea = new JTextArea();
        accountSummaryArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(accountSummaryArea);
        add(scrollPane, BorderLayout.NORTH);

        pack();
        setLocationRelativeTo(null);
    }

    private void showUserMenu() {
        removeAll();
        JPanel userMenuPanel = new JPanel(new BorderLayout());

        accountSummaryArea.setText(curUser.getAccountSummary());

        JPanel optionsPanel = new JPanel(new GridLayout(5, 1));
        JButton showHistoryButton = new JButton("Show Transaction History");
        showHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTransactionHistory();
            }
        });
        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showWithdrawDialog();
            }
        });
        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDepositDialog();
            }
        });
        JButton transferButton = new JButton("Transfer");
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTransferDialog();
            }
        });
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        optionsPanel.add(showHistoryButton);
        optionsPanel.add(withdrawButton);
        optionsPanel.add(depositButton);
        optionsPanel.add(transferButton);
        optionsPanel.add(quitButton);

        userMenuPanel.add(optionsPanel, BorderLayout.CENTER);
        add(userMenuPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
        pack();
    }

    private void showTransactionHistory() {
        // Implement transaction history display here
    }

    private void showWithdrawDialog() {
        // Implement withdraw dialog display here
    }

    private void showDepositDialog() {
        // Implement deposit dialog display here
    }

    private void showTransferDialog() {
        // Implement transfer dialog display here
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ATMGUI().setVisible(true);
            }
        });
    }
}
