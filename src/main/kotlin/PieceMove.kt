data class Move(
    val piece: Piece,
    val origin: Int,
    val destinations: List<Int>
)

fun checkPossibleMoves(piece: Piece, boardSize: Int, pieces: List<Piece>): List<Move> {
    val moveList = pieceMoveRouter(piece, boardSize, pieces)
    //val moves = Move(piece, piece.position, )
}

fun checkPieceCanMove(
    piece: Piece,
    boardSize: Int,
    pieces: List<Piece>,
    pieceCanMoveFun: (Int, Int) -> Boolean,
    positionAfterMoveFun: (Int, Int) -> Int
): Int {
    if(pieceCanMoveFun(piece.position, boardSize)) {
        val resultDestination = positionAfterMoveFun(piece.position, boardSize)

        pieces.forEach {

            if (it.position == resultDestination) {
                return -1
            }
        }
        return resultDestination
    }

    return -1
}

fun pieceMoveRouter(piece: Piece, boardSize: Int, pieces: List<Piece>): List<Int> {
    val moveList = mutableListOf<Int>()

    if (piece.isRed) {

        val resultDL = checkPieceCanMove(
            piece,
            boardSize,
            pieces,
            ::canMoveDownLeftOne,
            ::positionDownLeft
        )
        if(resultDL != -1) moveList.add(resultDL)

        val resultDR = checkPieceCanMove(
            piece,
            boardSize,
            pieces,
            ::canMoveDownRightOne,
            ::positionDownRight
        )
        if(resultDR != -1) moveList.add(resultDR)

        if(piece.isKing) {
            val resultUL = checkPieceCanMove(
                piece,
                boardSize,
                pieces,
                ::canMoveUpLeftOne,
                ::positionUpLeft
            )
            if(resultUL != -1) moveList.add(resultUL)

            val resultUL = checkPieceCanMove(
                piece,
                boardSize,
                pieces,
                ::canMoveUpRightOne,
                ::positionUpRight
            )
            if(resultUR != -1) moveList.add(resultUR)

        }
    } else { // is black
        checkPieceCanMove(
            piece,
            boardSize,
            pieces,
            ::positionUpLeft,
            ::canCaptureUpLeft,
            ::positionAfterCaptureUpLeft
        )

        checkPieceCanMove(
            piece,
            boardSize,
            pieces,
            ::positionUpRight,
            ::canCaptureUpRight,
            ::positionAfterCaptureUpRight
        )

        if(piece.isKing) {
            checkPieceCanMove(
                piece,
                boardSize,
                pieces,
                ::positionDownLeft,
                ::canCaptureDownLeft,
                ::positionAfterCaptureDownLeft
            )

            checkPieceCanMove(
                piece,
                boardSize,
                pieces,
                ::positionDownRight,
                ::canCaptureDownRight,
                ::positionAfterCaptureDownRight
            )
        }
    }
}