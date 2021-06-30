data class Capture(
    val previousCapture: Capture?,
    val origin: Int,
    val destination: Int,
    val capturedPiece: Piece
)

//make the capture object like the move object
data class CaptureSimple(
    val origin: Int,
    val destination: Int,
    val routeTaken: List<Int>,
    val capturedPositions: List<Int>,
)

fun getSimpleCaptures(piece: Piece, boardSize: Int, pieces: List<Piece>): List<CaptureSimple> {
    val complexCapture = checkPossibleCaptures(piece, boardSize, pieces)

    if(complexCapture.isEmpty()) {
        return emptyList()
    }

    return complexCapture[complexCapture.size - 1].map {
        parseCapture(it)
    }
}


fun parseCapture(cap: Capture): CaptureSimple {
    val finalDestination = cap.destination
    val visitedPositions = mutableListOf<Int>()
    val capturedPositions = mutableListOf<Int>()

    fun chaseCapture(capture: Capture) {
        if(capture.previousCapture == null) {
            visitedPositions.add(capture.origin)
            capturedPositions.add(capture.capturedPiece.position) }
        else{
            chaseCapture(capture.previousCapture)
            visitedPositions.add(capture.origin)
            capturedPositions.add(capture.capturedPiece.position)
        }
    }

    chaseCapture(cap)
    visitedPositions.add(finalDestination)

    return CaptureSimple(
        visitedPositions[0],
        finalDestination,
        visitedPositions,
        capturedPositions
    )
}

fun checkPossibleCaptures(piece: Piece, boardSize: Int, pieces: List<Piece>): List<List<Capture>> {
    val depthList = mutableListOf<MutableList<Capture>>()
    return pieceCaptureRouter(piece, boardSize, pieces, 0, depthList, previousCapture = null)
}

fun pieceCaptureRouter(piece: Piece, boardSize: Int, pieces: List<Piece>, captureDepth: Int, depthList: MutableList<MutableList<Capture>>, previousCapture: Capture?  ): List<List<Capture>> {

    if (piece.isRed) {
        checkPieceCanCaptureDirection(
            piece,
            boardSize,
            pieces,
            ::positionDownLeft,
            ::canCaptureDownLeft,
            ::positionAfterCaptureDownLeft,
            captureDepth,
            depthList,
            previousCapture
        )
        checkPieceCanCaptureDirection(
            piece,
            boardSize,
            pieces,
            ::positionDownRight,
            ::canCaptureDownRight,
            ::positionAfterCaptureDownRight,
            captureDepth,
            depthList,
            previousCapture
        )

        if(piece.isKing) {
            checkPieceCanCaptureDirection(
                piece,
                boardSize,
                pieces,
                ::positionUpLeft,
                ::canCaptureUpLeft,
                ::positionAfterCaptureUpLeft,
                captureDepth,
                depthList,
                previousCapture
            )

            checkPieceCanCaptureDirection(
                piece,
                boardSize,
                pieces,
                ::positionUpRight,
                ::canCaptureUpRight,
                ::positionAfterCaptureUpRight,
                captureDepth,
                depthList,
                previousCapture
            )

        }
    } else { // is black
        checkPieceCanCaptureDirection(
            piece,
            boardSize,
            pieces,
            ::positionUpLeft,
            ::canCaptureUpLeft,
            ::positionAfterCaptureUpLeft,
            captureDepth,
            depthList,
            previousCapture
        )

        checkPieceCanCaptureDirection(
            piece,
            boardSize,
            pieces,
            ::positionUpRight,
            ::canCaptureUpRight,
            ::positionAfterCaptureUpRight,
            captureDepth,
            depthList,
            previousCapture
        )

        if(piece.isKing) {
            checkPieceCanCaptureDirection(
                piece,
                boardSize,
                pieces,
                ::positionDownLeft,
                ::canCaptureDownLeft,
                ::positionAfterCaptureDownLeft,
                captureDepth,
                depthList,
                previousCapture
            )

            checkPieceCanCaptureDirection(
                piece,
                boardSize,
                pieces,
                ::positionDownRight,
                ::canCaptureDownRight,
                ::positionAfterCaptureDownRight,
                captureDepth,
                depthList,
                previousCapture
            )
        }
    }
    return depthList
}

fun checkPieceCanCaptureDirection(
    piece: Piece,
    boardSize: Int,
    pieces: List<Piece>,
    positionAfterMoveFun: (Int, Int) -> Int,
    canCaptureFun: (Int, Int) -> Boolean,
    positionAfterCaptureFun: (Int, Int) -> Int,
    captureDepth: Int,
    depthList: MutableList<MutableList<Capture>>,
    previousCapture: Capture?
) {

    //check if board even goes that far
    if (canCaptureFun(piece.position, boardSize)) {

        //get destination position if skip goes through
        val positionAfterMove = positionAfterMoveFun(piece.position, boardSize)

        //check if there is an enemy piece in position to be captured
        pieces.forEach { otherPiece ->
            if (otherPiece.position == positionAfterMove) {
                if (otherPiece.isRed != piece.isRed) {

                    val resultPosition = positionAfterCaptureFun(piece.position, boardSize)
                    pieces.forEach {
                        if (it.position == resultPosition) {
                            return
                        }
                    }

                    //add the piece to be captured and the resulting position of the acting piece

                    val capture = Capture(previousCapture, piece.position, resultPosition, otherPiece)

                    //crown the piece if it jumps to the appropriate row
                    if (!piece.isKing && piece.isRed && isRedKingMakerRow(resultPosition, boardSize)) {
                        piece.isKing = true
                    } else if (!piece.isKing && !piece.isRed && isBlackKingMakerRow(resultPosition, boardSize)) {
                        piece.isKing = true
                    }

                    val piecesWithCapturedRemoved = pieces.filter { it != otherPiece && it != piece }.toMutableList()

                    val pieceMoved = Piece(resultPosition, piece.isRed)

                    //this is an easy way to enable capturing backwards which is allowed after a forward capture
                    pieceMoved.isKing = true

                    piecesWithCapturedRemoved.add(pieceMoved)

                    if(depthList.size < captureDepth + 1) depthList.add(mutableListOf())
                    depthList[captureDepth].add(capture)


                    pieceCaptureRouter(
                        pieceMoved,
                        boardSize,
                        piecesWithCapturedRemoved,
                        captureDepth + 1,
                        depthList,
                        capture
                    )

                }
            }
        }
    }
}