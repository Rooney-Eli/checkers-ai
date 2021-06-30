import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class PieceMovementTests {

    private val boardSize: Int = 8

    private val boardArrange1 = listOf(
        Piece(1, true),
        Piece(5, true)
    )

    private val boardArrange2 = listOf(
        Piece(1, true),
        Piece(5, true),
        Piece(6, true),
    )

    private val boardArrange3 = listOf(
        Piece(1, true),
        Piece(5, false),
        Piece(6, false),
    )

    private val boardArrange4 = listOf(
        Piece(5, true),
        Piece(8, false),
        Piece(9, false),
    )

    private val boardArrange5 = listOf(
        Piece(9, false),
        Piece(13, true),
    )

    private val boardArrange6 = listOf(
        Piece(9, false).apply { isKing = true },
        Piece(13, true).apply { isKing = true },
    )








    private val fullBoardRed = (0 until boardSize*(boardSize/2)).map {
        Piece(it, true)
    }

    private val fullBoardBlack = (0 until boardSize*(boardSize/2)).map {
        Piece(it, false)
    }



    @Test
    fun `b1 should yield 3 total moves`() {
        val expectedMoveCount = 3
        var totalMoveCounter = 0

        boardArrange1.forEach { piece ->
            val validMoves = checkPossibleMoves(piece, boardSize, boardArrange1)
            validMoves?.let { totalMoveCounter += it.destinations.size }
        }

        assertEquals(expectedMoveCount, totalMoveCounter)
    }


    @Test
    fun `b2 should yield 4 total moves`() {
        val expectedMoveCount = 4
        var totalMoveCounter = 0

        boardArrange2.forEach { piece ->
            val validMoves = checkPossibleMoves(piece, boardSize, boardArrange2)
            validMoves?.let { totalMoveCounter += it.destinations.size }
        }

        assertEquals(expectedMoveCount, totalMoveCounter)
    }


    @Test
    fun `b3 should yield 0 total moves for red`() {
        val expectedMoveCount = 0
        var totalMoveCounter = 0

        boardArrange3.forEach { piece ->
            if(piece.isRed) {
                val validMoves = checkPossibleMoves(piece, boardSize, boardArrange3)
                validMoves?.let { totalMoveCounter += it.destinations.size }
            }
        }

        assertEquals(expectedMoveCount, totalMoveCounter)
    }


    @Test
    fun `b3 should yield 2 total moves for black`() {
        val expectedMoveCount = 2
        var totalMoveCounter = 0

        boardArrange3.forEach { piece ->
            if(!piece.isRed) {
                val validMoves = checkPossibleMoves(piece, boardSize, boardArrange3)
                validMoves?.let { totalMoveCounter += it.destinations.size }
            }
        }

        assertEquals(expectedMoveCount, totalMoveCounter)
    }

    @Test
    fun `b4 should yield 0 total moves for red`() {
        val expectedMoveCount = 0
        var totalMoveCounter = 0

        boardArrange4.forEach { piece ->
            if(piece.isRed) {
                val validMoves = checkPossibleMoves(piece, boardSize, boardArrange4)
                validMoves?.let { totalMoveCounter += it.destinations.size }
            }
        }

        assertEquals(expectedMoveCount, totalMoveCounter)
    }



    @Test
    fun `b5 should yield 2 total moves for red`() {
        val expectedMoveCount = 2
        var totalMoveCounter = 0

        boardArrange5.forEach { piece ->
            if(piece.isRed) {
                val validMoves = checkPossibleMoves(piece, boardSize, boardArrange5)
                validMoves?.let { totalMoveCounter += it.destinations.size }
            }
        }

        assertEquals(expectedMoveCount, totalMoveCounter)
    }

    @Test
    fun `b5 should yield 2 total moves for black`() {
        val expectedMoveCount = 2
        var totalMoveCounter = 0

        boardArrange5.forEach { piece ->
            if(!piece.isRed) {
                val validMoves = checkPossibleMoves(piece, boardSize, boardArrange5)
                validMoves?.let { totalMoveCounter += it.destinations.size }
            }
        }

        assertEquals(expectedMoveCount, totalMoveCounter)
    }


    @Test
    fun `b6 should yield 3 total moves for red king`() {
        val expectedMoveCount = 3
        var totalMoveCounter = 0

        boardArrange6.forEach { piece ->
            if(piece.isRed) {
                val validMoves = checkPossibleMoves(piece, boardSize, boardArrange6)
                validMoves?.let { totalMoveCounter += it.destinations.size }
            }
        }

        assertEquals(expectedMoveCount, totalMoveCounter)
    }

    @Test
    fun `b6 should yield 3 total moves for black king`() {
        val expectedMoveCount = 3
        var totalMoveCounter = 0

        boardArrange6.forEach { piece ->
            if(!piece.isRed) {
                val validMoves = checkPossibleMoves(piece, boardSize, boardArrange6)
                validMoves?.let { totalMoveCounter += it.destinations.size }
            }
        }

        assertEquals(expectedMoveCount, totalMoveCounter)
    }




    @Test
    fun `full red board should yield no possible moves`() {
        val expectedMoveCount = 0
        var totalMoveCounter = 0

        fullBoardRed.forEach { piece ->
            val validMoves = checkPossibleMoves(piece, boardSize, fullBoardRed)
            validMoves?.let { totalMoveCounter += it.destinations.size }
        }

        assertEquals(expectedMoveCount, totalMoveCounter)
    }

    @Test
    fun `full black board should yield no possible moves`() {
        val expectedMoveCount = 0
        var totalMoveCounter = 0

        fullBoardBlack.forEach { piece ->
            val validMoves = checkPossibleMoves(piece, boardSize, fullBoardBlack)
            validMoves?.let { totalMoveCounter += it.destinations.size }
        }

        assertEquals(expectedMoveCount, totalMoveCounter)
    }


}