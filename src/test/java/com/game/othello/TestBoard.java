package com.game.othello;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.lang.AssertionError;

public class TestBoard {

  private void setRow(Board board, int row, char target) throws InvalidIndexException {
    for (int i = 0; i < board.length; i++) {
      board.setDisk(row, i, target);
    }
  }

  private void setCol(Board board, int col, char target) throws InvalidIndexException {
    for (int i = 0; i < board.length; i++) {
      board.setDisk(i, col, target);
    }
  }

  private void assertEqBoards(Board expected, Board board) {
    try {
      assertEquals(expected.getDisks(), board.getDisks());
    } catch (AssertionError e) {
      System.out.printf("\nwant:");
      expected.print();
      System.out.printf("got:");
      board.print();
      System.out.println();
      throw e;
    }
  }

  @Test
  public void flipRow() throws InvalidIndexException {
    // _ _ _ _
    // x x x x
    // _ _ _ _
    // _ _ _ _
    Board board = new Board(4);
    this.setRow(board, 1, Disks.CROSS);
    Board board2 = board.copy();
    // _ _ _ _
    // o o o o
    // _ _ _ _
    // _ _ _ _
    Board expected = new Board(4);
    this.setRow(expected, 1, Disks.ORB);

    board.flip(1, 0, 1, 3, Disks.ORB); // flip left to right
    assertEqBoards(expected, board);

    board2.flip(1, 3, 1, 0, Disks.ORB); // flip right to left
    assertEqBoards(expected, board2);
  }

  @Test
  public void flipCol() throws InvalidIndexException {
    // _ _ x _
    // _ _ x _
    // _ _ x _
    // _ _ x _
    Board board = new Board(4);
    this.setCol(board, 2, Disks.CROSS);
    Board board2 = board.copy();
    // _ _ o _
    // _ _ o _
    // _ _ o _
    // _ _ o _
    Board expected = new Board(4);
    this.setCol(expected, 2, Disks.ORB);

    board.flip(0, 2, 3, 2, Disks.ORB); // flip top to bottom
    assertEqBoards(expected, board);

    board2.flip(3, 2, 0, 2, Disks.ORB); // flip bottom to top
    assertEqBoards(expected, board2);
  }

  // Minor diagonal is '/'
  @Test
  public void flipMinorDiagonal() throws InvalidIndexException {
    // _ _ _ _ _
    // _ _ _ _ x
    // _ _ _ x _
    // _ _ x _ _
    // _ x _ _ _
    Board board = new Board(5);
    board.setDisk(1, 4, Disks.CROSS);
    board.setDisk(2, 3, Disks.CROSS);
    board.setDisk(3, 2, Disks.CROSS);
    board.setDisk(4, 1, Disks.CROSS);
    Board board2 = board.copy();
    // _ _ _ _ _
    // _ _ _ _ o
    // _ _ _ o _
    // _ _ o _ _
    // _ o _ _ _
    Board expected = new Board(5);
    expected.setDisk(1, 4, Disks.ORB);
    expected.setDisk(2, 3, Disks.ORB);
    expected.setDisk(3, 2, Disks.ORB);
    expected.setDisk(4, 1, Disks.ORB);

    board.flip(1, 4, 4, 1, Disks.ORB); // flip top to bottom
    assertEqBoards(expected, board);

    board2.flip(4, 1, 1, 4, Disks.ORB); // flip bottom to top
    assertEqBoards(expected, board2);
  }

  // Major diagonal is '\'
  @Test
  public void flipMajorDiagonal() throws InvalidIndexException {
    // _ x _ _ _
    // _ _ x _ _
    // _ _ _ x _
    // _ _ _ _ x
    // _ _ _ _ _
    Board board = new Board(5);
    board.setDisk(0, 1, Disks.CROSS);
    board.setDisk(1, 2, Disks.CROSS);
    board.setDisk(2, 3, Disks.CROSS);
    board.setDisk(3, 4, Disks.CROSS);
    Board board2 = board.copy();
    // _ o _ _ _
    // _ _ o _ _
    // _ _ _ o _
    // _ _ _ _ o
    // _ _ _ _ _
    Board expected = new Board(5);
    expected.setDisk(0, 1, Disks.ORB);
    expected.setDisk(1, 2, Disks.ORB);
    expected.setDisk(2, 3, Disks.ORB);
    expected.setDisk(3, 4, Disks.ORB);

    board.flip(0, 1, 3, 4, Disks.ORB); // flip left to right
    assertEqBoards(expected, board);

    board2.flip(3, 4, 0, 1, Disks.ORB); // flip right to left
    assertEqBoards(expected, board2);
  }

  @Test(expected = InvalidPlacementException.class)
  public void coverNonEmptySpot() throws InvalidIndexException, InvalidPlacementException {
    Board board = new Board(2);
    board.setDisk(0, 0, Disks.ORB);
    board.cover(0, 0, Disks.ORB);
  }

  @Test
  public void succCover() throws InvalidIndexException, InvalidPlacementException {
    // _ _ _ o _ _ _ _ (0)
    // _ _ _ x _ _ o _ (1)
    // _ o _ x _ x _ _ (2)
    // _ _ x x _ o _ _ (3)
    // o x x _ x x x _ (4)
    // _ _ _ o x _ _ _ (5)
    // _ o o o x x _ _ (6)
    // _ _ _ x _ _ o _ (7)
    Board board = new Board(8);
    // 1st row
    board.setDisk(0, 3, Disks.ORB);
    // 2nd row
    board.setDisk(1, 3, Disks.CROSS);
    board.setDisk(1, 6, Disks.ORB);
    // 3rd row
    board.setDisk(2, 1, Disks.ORB);
    board.setDisk(2, 3, Disks.CROSS);
    board.setDisk(2, 5, Disks.CROSS);
    // 4th row
    board.setDisk(3, 2, Disks.CROSS);
    board.setDisk(3, 3, Disks.CROSS);
    board.setDisk(3, 5, Disks.ORB);
    // 5th row
    board.setDisk(4, 0, Disks.ORB);
    board.setDisk(4, 1, Disks.CROSS);
    board.setDisk(4, 2, Disks.CROSS);
    board.setDisk(4, 4, Disks.CROSS);
    board.setDisk(4, 5, Disks.CROSS);
    board.setDisk(4, 6, Disks.CROSS);
    // 6th row
    board.setDisk(5, 3, Disks.ORB);
    board.setDisk(5, 4, Disks.CROSS);
    // 7th row
    board.setDisk(6, 1, Disks.ORB);
    board.setDisk(6, 2, Disks.ORB);
    board.setDisk(6, 3, Disks.ORB);
    board.setDisk(6, 4, Disks.CROSS);
    board.setDisk(6, 5, Disks.CROSS);
    // 8th row
    board.setDisk(7, 3, Disks.CROSS);
    board.setDisk(7, 6, Disks.ORB);

    // _ _ _ o _ _ _ _
    // _ _ _ o _ _ o _
    // _ o _ o _ x _ _
    // _ _ o o _ o _ _
    // o o o O x x x _
    // _ _ _ o o _ _ _
    // _ o o o x o _ _
    // _ _ _ x _ _ o _
    Board expected = new Board(8);
    // 1st row
    expected.setDisk(0, 3, Disks.ORB);
    // 2nd row
    expected.setDisk(1, 3, Disks.ORB);
    expected.setDisk(1, 6, Disks.ORB);
    // 3rd row
    expected.setDisk(2, 1, Disks.ORB);
    expected.setDisk(2, 3, Disks.ORB);
    expected.setDisk(2, 5, Disks.CROSS);
    // 4th row
    expected.setDisk(3, 2, Disks.ORB);
    expected.setDisk(3, 3, Disks.ORB);
    expected.setDisk(3, 5, Disks.ORB);
    // 5th row
    expected.setDisk(4, 0, Disks.ORB);
    expected.setDisk(4, 1, Disks.ORB);
    expected.setDisk(4, 2, Disks.ORB);
    expected.setDisk(4, 3, Disks.ORB);
    expected.setDisk(4, 4, Disks.CROSS);
    expected.setDisk(4, 5, Disks.CROSS);
    expected.setDisk(4, 6, Disks.CROSS);
    // 6th row
    expected.setDisk(5, 3, Disks.ORB);
    expected.setDisk(5, 4, Disks.ORB);
    // 7th row
    expected.setDisk(6, 1, Disks.ORB);
    expected.setDisk(6, 2, Disks.ORB);
    expected.setDisk(6, 3, Disks.ORB);
    expected.setDisk(6, 4, Disks.CROSS);
    expected.setDisk(6, 5, Disks.ORB);
    // 8th row
    expected.setDisk(7, 3, Disks.CROSS);
    expected.setDisk(7, 6, Disks.ORB);

    board.cover(4, 3, Disks.ORB);
    assertEqBoards(expected, board);
  }
}
