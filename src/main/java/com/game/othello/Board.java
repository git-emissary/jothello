package com.game.othello;

class Board {

  final int length;

  private char[][] disks;

  // Initializes empty board.
  public Board(int length) {
    this.length = length;
    this.disks = new char[length][length];
    for (int i = 0; i < this.length; i++) {
      for (int j = 0; j < this.length; j++) {
        this.disks[i][j] = Disks.BLANK;
      }
    }
  }

  public Board copy() throws InvalidIndexException {
    Board clone = new Board(this.length);
    for (int i = 0; i < this.length; i++) {
      for (int j = 0; j < this.length; j++) {
        clone.setDisk(i, j, this.disks[i][j]);
      }
    }
    return clone;
  }

  public boolean outOfBounds(int i, int j) {
    return i < 0 || i >= this.length || j < 0 || j >= this.length;
  }

  public void print() {
    System.out.print("\n");
    for (int i = 0; i < this.length; i++) {
      for (int j = 0; j < this.length; j++) {
        if (j > 0) {
          System.out.print(" ");
        }
        System.out.printf("%c", this.disks[i][j]);
      }
      System.out.print("\n");
    }
  }

  private int getStartIndex(int delta, int first, int second) {
    int index = first;
    if (delta > 0) {
      index = Math.min(first, second);
    } else if (delta < 0) {
      index = Math.max(first, second);
    }
    return index;
  }

  private int getSign(int delta) {
    int sign = 0;
    if (delta > 0) {
      sign = 1;
    } else if (delta < 0) {
      sign = -1;
    }
    return sign;
  }

  public void setDisk(int i, int j, char target) throws InvalidIndexException {
    if (this.outOfBounds(i, j)) {
      throw new InvalidIndexException();
    }
    this.disks[i][j] = target;
  }

  public char[][] getDisks() {
    return this.disks;
  }

  // Sets all disks between positions (i1, j1) and (i2, j2) to target.
  // Only straight directions are considered.
  // Does not matter what disks are between those locations.
  //
  // You must use setDisk() method defined above.
  //
  // Hints: you may use getSign() and getStartIndex() defined above.
  //
  // Eg. ROW
  // From positions (2,0) to (2,3)
  // _ _ _ _     _ _ _ _
  // _ _ _ _     _ _ _ _
  // x x x x  -> o o o o
  // _ _ _ _     _ _ _ _
  //
  // Eg. COL
  // From positions (0,1) to (3,1)
  // _ x _ _     _ o _ _
  // _ _ _ _     _ o _ _
  // _ x _ _  -> _ o _ _
  // _ o _ _     _ o _ _
  //
  // Eg. DIAGONAL (only square diagonal is considered)
  // From positions (3,1) to (1,3)
  // _ _ _ _     _ _ _ _
  // _ _ _ x     _ _ _ o
  // _ _ x _ ->  _ _ o _
  // _ x _ _     _ o _ _
  //
  public void flip(int i1, int j1, int i2, int j2, char target) throws InvalidIndexException {
    int deltaRow = i2 - i1;
    int deltaCol = j2 - j1;

    if (this.outOfBounds(i1, j1) || this.outOfBounds(i2, j2)) {
      return;
    }

    // Not proper diagonal.
    if (deltaRow != 0 && deltaCol != 0 && Math.abs(deltaRow) != Math.abs(deltaCol)) {
      return;
    }

    // TODO: fill in rest of implementation
  }

  // This method applies flip() from position (i,j) to the closest target disk(s)
  // in ALL directions. (UP, DOWN, LEFT, RIGHT, DIAGONALS)
  // At (i,j) the disk is BLANK (already checked for you).
  //
  // NOTE: you are to reuse the flip() method defined above.
  //
  // Eg.
  // 5x5 input board, placing disk at 2,2 (centre of board).
  // _ _ o _ _
  // _ x _ _ _
  // o x _ x x
  // _ _ x x _
  // _ _ o _ o
  //
  // Expected output board
  // _ _ o _ _
  // _ x _ _ _
  // o o O x x
  // _ _ o o _
  // _ _ o _ o
  //
  public void cover(int i, int j, char target)
      throws InvalidIndexException, InvalidPlacementException {

    if (this.outOfBounds(i, j)) {
      throw new InvalidIndexException();
    }

    // You can only place a disk in an empty spot.
    if (this.disks[i][j] != Disks.BLANK) {
      throw new InvalidPlacementException();
    }

    // TODO: fill in rest of implementation
  }
}
