import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class MoveTests {

    private val boardSize: Int = 8

    private val boardArrange0 = listOf(
        Piece(8, true),
        Piece(20, false),
    )


    private val boardArrange1 = listOf(
        Piece(9, true),
        Piece(17, false),
    )

    private val boardArrange2 = listOf(
        Piece(8, true),
        Piece(9, true),
        Piece(13, false),
        Piece(14, false),
        Piece(20, false),
        Piece(23, false),

    )


    @Test
    fun `b0 should give red a capture when red goes first`() {
        val score = playOutTurns(boardArrange0, true, boardSize, 3)
        assertEquals(1, score)
    }

    @Test
    fun `b1 should give black a capture when red goes first`() {
        val score = playOutTurns(boardArrange1, true, boardSize, 3)
        assertEquals(1, score)
    }

    @Test
    fun `b1 should give red a capture when black goes first`() {
        val score = playOutTurns(boardArrange1, false, boardSize, 3)
        assertEquals(1, score)
    }

    @Test
    fun `b2 should give red a score of 2 given a 2 turn prediction`() {
        val score = playOutTurns(boardArrange2, true, boardSize, 2)
        assertEquals(2, score)
        println()
    }

}