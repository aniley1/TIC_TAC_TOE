import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGame extends JFrame implements ActionListener {
    private JButton[][] buttons;
    private boolean isPlayerX;
    private int movesLeft;

    public TicTacToeGame() {
        setTitle("Tic-Tac-Toe");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttons = new JButton[3][3];
        isPlayerX = true;
        movesLeft = 9;

        // Create buttons and add ActionListener
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[row][col].addActionListener(this);
                add(buttons[row][col]);
            }
        }

        // Set GridLayout for the JFrame
        setLayout(new GridLayout(3, 3));
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        // If the button is already clicked or the game is over, return
        if (!clickedButton.getText().equals("") || movesLeft == 0) {
            return;
        }

        // Set "X" or "O" on the clicked button based on the player's turn
        if (isPlayerX) {
            clickedButton.setText("X");
        } else {
            clickedButton.setText("O");
        }

        // Check for a winner after every move
        if (checkForWinner()) {
            if (isPlayerX) {
                JOptionPane.showMessageDialog(this, "Player X wins!");
            } else {
                JOptionPane.showMessageDialog(this, "Player O wins!");
            }
            resetGame();
            return;
        }

        // Toggle player turn and decrement moves left
        isPlayerX = !isPlayerX;
        movesLeft--;

        // If there are no moves left, it's a draw
        if (movesLeft == 0) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetGame();
        }
    }

    private boolean checkForWinner() {
        // Check rows, columns, and diagonals for a winning combination
        for (int i = 0; i < 3; i++) {
            // Check rows
            if (!buttons[i][0].getText().equals("") && buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                    buttons[i][1].getText().equals(buttons[i][2].getText())) {
                return true;
            }

            // Check columns
            if (!buttons[0][i].getText().equals("") && buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                    buttons[1][i].getText().equals(buttons[2][i].getText())) {
                return true;
            }
        }

        // Check diagonals
        if (!buttons[0][0].getText().equals("") && buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][2].getText())) {
            return true;
        }

        if (!buttons[0][2].getText().equals("") && buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][0].getText())) {
            return true;
        }

        return false;
    }

    private void resetGame() {
        // Clear the buttons and reset the game
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }

        isPlayerX = true;
        movesLeft = 9;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToeGame());
    }
}
