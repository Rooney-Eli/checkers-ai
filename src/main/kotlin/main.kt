
fun main() {
    val boardSize = 8

    val p1 = Piece(1, true)
    val p2 = Piece(6, false)
    val p3 = Piece(14, false)
    val p4 = Piece(15, false)
    val p5 = Piece(21, false)

    val c1 = Capture(previousCapture = null, 1, 10, p2)
    val c2 = Capture(previousCapture = c1, 10, 17, p3)
    val c3 = Capture(previousCapture = c1, 10, 19, p4)
    val c4 = Capture(previousCapture = c2, 17, 24, p5)




    val pieceList = listOf(
        p1, p2, p3, p4, p5
    )



    val captureList = checkPossibleCaptures(p1, boardSize, pieceList)

    fun printLastCap(capture: Capture) {
        if(capture.previousCapture == null) {
            println("${capture.origin} -> (${capture.capturedPiece}) -> ${capture.destination}")
        }
        else{
            printLastCap(capture.previousCapture)
            println("${capture.origin} -> (${capture.capturedPiece}) -> ${capture.destination}")
        }
    }

    if(captureList.last().size == 1) {
        val lastCapture = captureList.last()[0]
        printLastCap(lastCapture)

    }



    //red on first 3 rows
    //black on last 3 rows



}

data class Piece(
    var position: Int,
    val isRed: Boolean
) {
    var isKing: Boolean = false
}







//fun possiblePieceCaptures(piece: Piece, boardSize: Int, pieces: List<Piece>, captureList: MutableList<MutableList<Pair<Piece, Int>>>): List<Pair<Piece, Int>> {
//
//    if (piece.isRed) {
//        captureList.add(checkPieceCanCaptureDownLeft(piece, boardSize, pieces, captureList))
//        checkPieceCanCaptureDownRight(piece, boardSize, pieces, captureList)
//
//        if(piece.isKing) {
//            checkPieceCanCaptureUpLeft(piece, boardSize, pieces, captureList)
//            checkPieceCanCaptureUpRight(piece, boardSize, pieces, captureList)
//        }
//    } else { // is black
//        checkPieceCanCaptureUpLeft(piece, boardSize, pieces, captureList)
//        checkPieceCanCaptureUpRight(piece, boardSize, pieces, captureList)
//
//        if(piece.isKing) {
//            checkPieceCanCaptureDownLeft(piece, boardSize, pieces, captureList)
//            checkPieceCanCaptureDownRight(piece, boardSize, pieces, captureList)
//        }
//    }
//}


fun checkPieceCanMoveDownLeft(
    piece: Piece,
    boardSize: Int,
    pieces: List<Piece>,
    moveList: MutableList<Int>
) {

    //check if board even goes that far
    if(canMoveDownLeftOne(piece.position, boardSize)) {

        //get the actual position at the end of the desired move
        val positionDownLeftOne = positionDownLeft(piece.position, boardSize)

        //check if there is a piece in the way
        pieces.forEach { otherPiece ->

            //if there is a piece there
            if (otherPiece.position == positionDownLeftOne) {

                //check if it is enemy
                if(otherPiece.isRed != piece.isRed) {

                    //check if it can be jumped
                    if(canCaptureDownLeft(piece.position, boardSize)) {
                        //must prioritize this move
//                        captureList.add(positionDownLeftOne)
                    }
                }

            } else {
                moveList.add(positionDownLeftOne)
            }
        }
    }
}



