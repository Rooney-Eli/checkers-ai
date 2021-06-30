/*
   A B C D E F G H
1  _ 0 _ 0 _ 0 _ 0
2  0 _ 0 _ 0 _ 0 _
3  _ 0 _ 0 _ 0 _ 0
4  - _ - _ - _ - _
5  _ - _ - _ - _ -
6  1 _ 1 _ 1 _ 1 _
7  _ 1 _ 1 _ 1 _ 1
8  1 _ 1 _ 1 _ 1 _


move 1:
    OPTIONS: //4 initial, 7 possibilities
        B3 -> A4, C4
        D3 -> C4, E4
        F3 -> E4, G4
        H3 -> G4

move 2:
    OPTIONS: //4 initial, 7 possibilities
    A6 -> B5
    C6 -> B5, D5
    E6 -> D5, F5
    G6 -> F5, H5

Jump outcomes:
if (move 1 == B3 -> A4) AND (move 2 == C6 -> B5)



   A B C D E F G H
1  _ 0 _ 0 _ 0 _ 0
2  0 _ 0 _ 0 _ 0 _
3  _ 0 _ 0 _ 0 _ 0
4  - _ - _ - _ - _
5  _ - _ - _ - _ -
6  1 _ 1 _ 1 _ 1 _
7  _ 1 _ 1 _ 1 _ 1
8  1 _ 1 _ 1 _ 1 _



*/


fun main() {

    val boardSize = 8
    val pieces = mutableListOf<Piece>()

    //red on first 3 rows
    (0 until (boardSize/2) * 3).forEach {
        pieces.add(Piece(it, true))
    }

    //black on last 3 rows
    ((boardSize*(boardSize/2)) - ((boardSize/2) * 3) until boardSize*(boardSize/2)).forEach {
        pieces.add(Piece(it, false))
    }


    val score = playOutTurns(pieces, true, boardSize, 3)


    println()

}

fun makeMove(pieces: MutableList<Piece>, captureSimple: CaptureSimple?, move: Move?) {
    captureSimple?.let {
        it.capturedPositions.forEach {

        }
    }
}


fun playOutTurns(pieces: List<Piece>, isRed: Boolean, boardSize: Int, numTurns: Int): Int {
    if(numTurns <= 0) {
        return 0
    }

    var bestCaptureScore = 0
    var redScore = 0

    val piecesCopy = pieces.map { it }.toMutableList()

    val bestCapture = exploreCaptureOptions(pieces, boardSize, isRed)
    if(bestCapture.isEmpty()) {
        return exploreMoveOptions(pieces, boardSize, isRed, numTurns)
    } else if(bestCapture.size > 1) {

        bestCapture.forEach { capture ->
            capture.capturedPositions.forEach { capturedPiecePosition ->
                piecesCopy.removeIf { it.position == capturedPiecePosition }
            }
            val currentPiece = piecesCopy.find { p -> p.position == capture.origin } ?: return -1
            piecesCopy.removeIf { p -> p.position == capture.origin }
            piecesCopy.add(Piece(capture.destination, currentPiece.isRed).apply { isKing = currentPiece.isKing })

            val result = playOutTurns(piecesCopy, !isRed, boardSize, numTurns - 1)
            if(result > bestCaptureScore) bestCaptureScore = result
        }
        if(isRed) {
            redScore += bestCaptureScore
        } else {
            redScore -= bestCaptureScore
        }
        return redScore

    } else {
        bestCapture[0].capturedPositions.forEach { capturedPiecePosition ->
            piecesCopy.removeIf { it.position == capturedPiecePosition }
        }
        bestCaptureScore += 1
        bestCaptureScore += playOutTurns(pieces, !isRed, boardSize, numTurns - 1)

        if(isRed) {
            redScore += bestCaptureScore
        } else {
            redScore -= bestCaptureScore
        }
        return redScore
    }

}




fun exploreMoveOptions(pieces: List<Piece>, boardSize: Int, isRed: Boolean, numTurns: Int): Int {
    if(numTurns <= 0) {
        return 0
    }

    val collectiveMoves = mutableListOf<Move>()
    var bestMoveScore = 0
    pieces.forEach {
        if (it.isRed == isRed) {
            val move = checkPossibleMoves(it, boardSize, pieces)
            move?.let { collectiveMoves.add(it) }
        }
    }

    collectiveMoves.forEach {
        val piecesCopy = pieces.map { it }.toMutableList()
        it.destinations.forEach {  dest ->
            val piecesCopyCopy = piecesCopy.map { itc -> itc }.toMutableList()
            piecesCopyCopy.removeIf { p -> p.position == it.origin }
            piecesCopyCopy.add(Piece(dest, it.piece.isRed).apply { isKing = it.piece.isKing })
            val result = playOutTurns(piecesCopyCopy, !isRed, boardSize, numTurns - 1)
            if(result > bestMoveScore) bestMoveScore = result
        }
    }

    return bestMoveScore
}

fun exploreCaptureOptions(pieces: List<Piece>, boardSize: Int, red: Boolean): List<CaptureSimple> {
    val greatestCaptures = mutableListOf<CaptureSimple>()

    pieces.forEach {
        if(it.isRed == red) {
            val captures = getSimpleCaptures(it, boardSize, pieces)
            if(captures.isNotEmpty()) {
                captures.forEach { capture ->
                    if(greatestCaptures.isEmpty()) {
                        greatestCaptures.add(capture)
                    } else if (capture.capturedPositions.size > greatestCaptures[0].capturedPositions.size) {
                        greatestCaptures.clear()
                        greatestCaptures.add(capture)
                    } else if (capture.capturedPositions.size == greatestCaptures[0].capturedPositions.size) {
                        greatestCaptures.add(capture)
                    }
                }
            }
        }
    }
    return greatestCaptures
}

data class Piece(
    var position: Int,
    val isRed: Boolean
) {
    var isKing: Boolean = false
}

