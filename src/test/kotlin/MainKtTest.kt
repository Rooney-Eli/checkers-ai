import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class BoardNavigationTests {

    private val boardSize: Int = 8


    @Test
    fun `canMoveRightOne should be true`() {
        val position = 28
        assertTrue(canMoveUpRightOne(position, boardSize))
    }

    @Test
    fun `canMoveRightOne from end of odd row should be true`() {
        val position = 7
        assertTrue(canMoveUpRightOne(position, boardSize))
    }

    @Test
    fun `canMoveRightOne from end of even row should be false`() {
        val position = 3
        assertFalse(canMoveUpRightOne(position, boardSize))
    }


    @Test
    fun `canMoveLeftOne should be true`() {
        val position = 26
        assertTrue(canMoveLeftOne(position, boardSize))
    }

    @Test
    fun `canMoveLeftOne from start of even row should be true`() {
        val position = 0
        assertTrue(canMoveLeftOne(position, boardSize))
    }

    @Test
    fun `canMoveLeftOne from start of odd row should be false`() {
        val position = 4
        assertFalse(canMoveLeftOne(position, boardSize))
    }

    @Test
    fun `canMoveUpLeftOne should be true`() {
        val position = 31
        assertTrue(canMoveUpLeftOne(position, boardSize))
    }

    @Test
    fun `canMoveUpLeftOne should be false`() {
        val position = 0
        assertFalse(canMoveUpLeftOne(position, boardSize))
    }


    @Test
    fun `canMoveUpRightOne should be true`() {
        val position = 28
        assertTrue(canMoveUpRightOne(position, boardSize))
    }

    @Test
    fun `canMoveUpRightOne should be false`() {
        val position = 3
        assertFalse(canMoveUpRightOne(position, boardSize))
    }



    @Test
    fun `canMoveDownLeftOne should be true`() {
        val position = 3
        assertTrue(canMoveDownLeftOne(position, boardSize))
    }

    @Test
    fun `canMoveDownLeftOne should be false`() {
        val position = 28
        assertFalse(canMoveDownLeftOne(position, boardSize))
    }


    @Test
    fun `canMoveDownRightOne should be true`() {
        val position = 0
        assertTrue(canMoveDownRightOne(position, boardSize))
    }

    @Test
    fun `canMoveDownRightOne should be false`() {
        val position = 31
        assertFalse(canMoveDownRightOne(position, boardSize))
    }

    @Test
    fun `canSkipUpLeft should be true`() {
        val position = 9
        assertTrue(canCaptureUpLeft(position, boardSize))
    }

    @Test
    fun `canSkipUpLeft should be false`() {
        val position = 5
        assertFalse(canCaptureUpLeft(position, boardSize))
    }

    @Test
    fun `canSkipUpRight should be true`() {
        val position = 10
        assertTrue(canCaptureUpRight(position, boardSize))
    }

    @Test
    fun `canSkipUpRight should be false`() {
        val position = 7
        assertFalse(canCaptureUpRight(position, boardSize))
    }

    @Test
    fun `canSkipDownLeft should be true`() {
        val position = 21
        assertTrue(canCaptureDownLeft(position, boardSize))
    }

    @Test
    fun `canSkipDownLeft should be false`() {
        val position = 24
        assertFalse(canCaptureDownLeft(position, boardSize))
    }

    @Test
    fun `canSkipDownRight should be true`() {
        val position = 22
        assertTrue(canCaptureDownRight(position, boardSize))
    }

    @Test
    fun `canSkipDownRight should be false`() {
        val position = 26
        assertFalse(canCaptureDownRight(position, boardSize))
    }

    @Test
    fun moveUpLeft() {
        val startPosition = 5
        val expectedEndPosition = 0
        assertEquals(expectedEndPosition, positionUpLeft(startPosition, boardSize))
    }

    @Test
    fun moveUpRight() {
        val startPosition = 5
        val expectedEndPosition = 1
        assertEquals(expectedEndPosition, positionUpRight(startPosition, boardSize))
    }

    @Test
    fun moveDownLeft() {
        val startPosition = 5
        val expectedEndPosition = 8
        assertEquals(expectedEndPosition, positionDownLeft(startPosition, boardSize))
    }

    @Test
    fun moveDownRight() {
        val startPosition = 5
        val expectedEndPosition = 9
        assertEquals(expectedEndPosition, positionDownRight(startPosition, boardSize))
    }


    @Test
    fun `positionAfterCaptureUpLeft should be True`() {
        val startPosition = 9
        val expectedEndPosition = 0
        assertEquals(expectedEndPosition, positionAfterCaptureUpLeft(startPosition, boardSize))
    }


    @Test
    fun `positionAfterCaptureUpRight should be True`() {
        val startPosition = 9
        val expectedEndPosition = 2
        assertEquals(expectedEndPosition, positionAfterCaptureUpRight(startPosition, boardSize))
    }

    @Test
    fun `positionAfterCaptureDownLeft should be True`() {
        val startPosition = 22
        val expectedEndPosition = 29
        assertEquals(expectedEndPosition, positionAfterCaptureDownLeft(startPosition, boardSize))
    }



    @Test
    fun `positionAfterCaptureDownRight should be True`() {
        val startPosition = 22
        val expectedEndPosition = 31
        assertEquals(expectedEndPosition, positionAfterCaptureDownRight(startPosition, boardSize))
    }


}