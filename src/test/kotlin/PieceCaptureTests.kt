import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class PieceCaptureTests {
    //assumes maximum capture count is mandatory when available, but when equal a choice is given


    private val boardSize: Int = 8

    private val boardArrange0 = listOf(
        Piece(9, true)
    )


    private val boardArrange1 = listOf(
        Piece(9, true),
        Piece(13, false),
    )

    private val boardArrange2 = listOf(
        Piece(9, true),
        Piece(13, false),
        Piece(14, false),
    )

    private val boardArrange3 = listOf(
        Piece(17, true).apply { isKing = true },
        Piece(13, false),
        Piece(14, false),
    )

    private val boardArrange4 = listOf(
        Piece(4, true),
        Piece(8, false),
        Piece(9, false),
        Piece(10, false),
    )

    private val boardArrange5 = listOf(
        Piece(4, true),
        Piece(8, false),
        Piece(9, false),
        Piece(16, false),
        Piece(10, false),
        Piece(24, false),

    )

    private val boardArrange6 = listOf(
        Piece(13, false),
        Piece(17, false),
    )



    @Test
    fun `b0 should yield 0 capture options`() {
        val expectedCaptureOptionCount = 0
        var totalCaptureOptions = 0

        boardArrange0.forEach { piece ->
            val validCaptures = checkPossibleCaptures(piece, boardSize, boardArrange0)
            totalCaptureOptions += validCaptures.size
        }

        assertEquals(expectedCaptureOptionCount, totalCaptureOptions)
    }

    @Test
    fun `b1 should yield 1 capture option for red with 1 piece captured`() {
        val expectedCaptureOptionCount = 1
        val expectedCapturedPieces = 1
        var totalCaptureOptions = 0
        var totalCapturedPieces = 0

        boardArrange1.forEach { piece ->
            if (piece.isRed) {
                val validCaptures = checkPossibleCaptures(piece, boardSize, boardArrange1)

                //validCaptures.size is equal to the number of captured pieces
                //validCaptures[max].size is equal to the number of capture options
                totalCapturedPieces += validCaptures.size
                totalCaptureOptions += validCaptures[validCaptures.size-1].size
            }
        }

        assertEquals(expectedCaptureOptionCount, totalCaptureOptions)
        assertEquals(expectedCapturedPieces, totalCapturedPieces)
    }

    @Test
    fun `b2 should yield 2 capture option for red with 1 piece captured`() {
        val expectedCaptureOptionCount = 2
        val expectedCapturedPieces = 1
        var totalCaptureOptions = 0
        var totalCapturedPieces = 0

        boardArrange2.forEach { piece ->
            if (piece.isRed) {
                val validCaptures = checkPossibleCaptures(piece, boardSize, boardArrange2)

                //validCaptures.size is equal to the number of captured pieces
                //validCaptures[max].size is equal to the number of capture options
                totalCapturedPieces += validCaptures.size
                totalCaptureOptions += validCaptures[validCaptures.size-1].size
            }
        }

        assertEquals(expectedCaptureOptionCount, totalCaptureOptions)
        assertEquals(expectedCapturedPieces, totalCapturedPieces)
    }

    @Test
    fun `b2 should yield 2 capture option for black`() {
        val expectedCaptureOptionCount = 2
        var totalCaptureOptions = 0

        boardArrange2.forEach { piece ->
            if (!piece.isRed) {
                val validCaptures = checkPossibleCaptures(piece, boardSize, boardArrange2)

                //validCaptures[max].size is equal to the number of capture options
                totalCaptureOptions += validCaptures[validCaptures.size-1].size
            }
        }

        assertEquals(expectedCaptureOptionCount, totalCaptureOptions)
    }

    @Test
    fun `b3 should yield 2 capture option for red king with 1 piece captured`() {
        val expectedCaptureOptionCount = 2
        val expectedCapturedPieces = 1
        var totalCaptureOptions = 0
        var totalCapturedPieces = 0

        boardArrange3.forEach { piece ->
            if (piece.isRed) {
                val validCaptures = checkPossibleCaptures(piece, boardSize, boardArrange3)

                //validCaptures.size is equal to the number of captured pieces
                //validCaptures[max].size is equal to the number of capture options
                totalCapturedPieces += validCaptures.size
                totalCaptureOptions += validCaptures[validCaptures.size-1].size
            }
        }

        assertEquals(expectedCaptureOptionCount, totalCaptureOptions)
        assertEquals(expectedCapturedPieces, totalCapturedPieces)
    }


    @Test
    fun `b4 should yield 1 capture option for red with 3 piece captured`() {
        val expectedCaptureOptionCount = 1
        val expectedCapturedPieces = 3
        var totalCaptureOptions = 0
        var totalCapturedPieces = 0

        boardArrange4.forEach { piece ->
            if (piece.isRed) {
                val validCaptures = checkPossibleCaptures(piece, boardSize, boardArrange4)

                //validCaptures.size is equal to the number of captured pieces
                //validCaptures[max].size is equal to the number of capture options
                totalCapturedPieces += validCaptures.size
                totalCaptureOptions += validCaptures[validCaptures.size-1].size
            }
        }

        assertEquals(expectedCaptureOptionCount, totalCaptureOptions)
        assertEquals(expectedCapturedPieces, totalCapturedPieces)
    }


    @Test
    fun `b5 should yield 2 capture option for red with 3 piece captured`() {
        val expectedCaptureOptionCount = 2
        val expectedCapturedPieces = 3
        var totalCaptureOptions = 0
        var totalCapturedPieces = 0

        boardArrange5.forEach { piece ->
            if (piece.isRed) {
                val validCaptures = checkPossibleCaptures(piece, boardSize, boardArrange5)

                //validCaptures.size is equal to the number of captured pieces
                //validCaptures[max].size is equal to the number of capture options
                totalCapturedPieces += validCaptures.size
                totalCaptureOptions += validCaptures[validCaptures.size-1].size
            }
        }

        assertEquals(expectedCaptureOptionCount, totalCaptureOptions)
        assertEquals(expectedCapturedPieces, totalCapturedPieces)
    }

    @Test
    fun `b0 should yield an empty list`() {
        val result = getSimpleCaptures(boardArrange0[0], boardSize, boardArrange0)
        assertEquals(0, result.size)
    }

    @Test
    fun `b6 should yeild no capture options`() {
        val result = getSimpleCaptures( Piece(8, true), boardSize, boardArrange6)
        assertEquals(0, result.size)
    }

}