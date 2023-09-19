import tkinter as tk
from tkinter import messagebox

# Constants for the game
EMPTY = ""
PLAYER_X = "X"
PLAYER_O = "O"

# Initialize the game board
board = [EMPTY] * 9
current_player = PLAYER_X
game_over = False

# Create a GUI window
window = tk.Tk()
window.title("Tic-Tac-Toe")

# Create buttons for the game board
buttons = [tk.Button(window, text=EMPTY, font=("Arial", 24), width=6, height=2) for _ in range(9)]

# Define the button click event handler
def button_click(index):
    global current_player, game_over
    if not game_over and board[index] == EMPTY:
        set_move(index, current_player)
        check_game_over()
        if not game_over:
            current_player = PLAYER_X if current_player == PLAYER_O else PLAYER_O

# Place buttons on the grid
for i in range(3):
    for j in range(3):
        index = i * 3 + j
        buttons[index].grid(row=i, column=j)
        buttons[index].config(command=lambda idx=index: button_click(idx))

# Function to set a move on the board
def set_move(index, player):
    board[index] = player
    buttons[index].config(text=player)

# Check if the game is over
def check_game_over():
    global game_over
    for line in [[0, 1, 2], [3, 4, 5], [6, 7, 8], [0, 3, 6], [1, 4, 7], [2, 5, 8], [0, 4, 8], [2, 4, 6]]:
        if board[line[0]] == board[line[1]] == board[line[2]] != EMPTY:
            game_over = True
            messagebox.showinfo("Game Over", f"Player {board[line[0]]} wins!")
            return
    if EMPTY not in board:
        game_over = True
        messagebox.showinfo("Game Over", "It's a draw!")

# Main game loop
if __name__ == "__main__":
    # Start the GUI main loop
    window.mainloop()
