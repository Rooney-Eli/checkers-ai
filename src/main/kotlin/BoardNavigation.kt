fun column(position: Int, boardSize: Int) = position % (boardSize / 2)
fun row(position: Int, boardSize: Int) = position / (boardSize / 2)

fun canMoveUpOne(position: Int, boardSize: Int) = row(position, boardSize) > 0
fun canMoveUpTwo(position: Int, boardSize: Int) = row(position, boardSize) > 1

fun canMoveDownOne(position: Int, boardSize: Int) = row(position, boardSize) < boardSize - 1
fun canMoveDownTwo(position: Int, boardSize: Int) = row(position, boardSize) < boardSize - 2


fun canMoveLeftOne(position: Int, boardSize: Int) =
    if(row(position, boardSize) % 2 == 0) {
        true
    } else {
        column(position, boardSize) > 0
    }

fun canMoveLeftTwo(position: Int, boardSize: Int) = column(position, boardSize) > 0

fun canMoveRightOne(position: Int, boardSize: Int) =
    if(row(position, boardSize) % 2 == 0) { //check if row is even or odd
        column(position, boardSize) < (boardSize / 2) - 1 //cant be against the edge of board
    } else {
        true //right end of odd rows are never against the edge
    }

fun canMoveRightTwo(position: Int, boardSize: Int) = column(position, boardSize) < (boardSize / 2) - 1


fun canMoveUpLeftOne(position: Int, boardSize: Int) = canMoveUpOne(position, boardSize) && canMoveLeftOne(position, boardSize)
fun canMoveUpRightOne(position: Int, boardSize: Int) = canMoveUpOne(position, boardSize) && canMoveRightOne(position, boardSize)
fun canMoveDownLeftOne(position: Int, boardSize: Int) = canMoveDownOne(position, boardSize) && canMoveLeftOne(position, boardSize)
fun canMoveDownRightOne(position: Int, boardSize: Int) = canMoveDownOne(position, boardSize) && canMoveRightOne(position, boardSize)


fun canCaptureUpLeft(position: Int, boardSize: Int) = canMoveUpTwo(position, boardSize) && canMoveLeftTwo(position, boardSize)
fun canCaptureUpRight(position: Int, boardSize: Int) = canMoveUpTwo(position, boardSize) && canMoveRightTwo(position, boardSize)
fun canCaptureDownLeft(position: Int, boardSize: Int) = canMoveDownTwo(position, boardSize) && canMoveLeftTwo(position, boardSize)
fun canCaptureDownRight(position: Int, boardSize: Int) = canMoveDownTwo(position, boardSize) && canMoveRightTwo(position, boardSize)



fun positionUpLeft(position: Int, boardSize: Int): Int {
    return if (row(position, boardSize) % 2 == 0) { //row is even
        position - (boardSize / 2)
    } else { //row is odd
        position - (boardSize / 2) - 1
    }
}



fun positionUpRight(position: Int, boardSize: Int): Int {
    return if (row(position, boardSize) % 2 == 0) { //row is even
        position - (boardSize / 2) + 1
    } else { //row is odd
        position - (boardSize / 2)
    }
}

fun positionDownLeft(position: Int, boardSize: Int): Int {
    return if (row(position, boardSize) % 2 == 0) { //row is even
        position + (boardSize / 2)
    } else { //row is odd
        position + (boardSize / 2) - 1
    }
}

fun positionDownRight(position: Int, boardSize: Int): Int {
    return if (row(position, boardSize) % 2 == 0) { //row is even
        position + (boardSize / 2) + 1
    } else { //row is odd
        position + (boardSize / 2)
    }
}

fun positionAfterCaptureUpLeft(position: Int, boardSize: Int): Int {
    return positionUpLeft(positionUpLeft(position, boardSize), boardSize)
}

fun positionAfterCaptureUpRight(position: Int, boardSize: Int): Int {
    return positionUpRight(positionUpRight(position, boardSize), boardSize)
}

fun positionAfterCaptureDownLeft(position: Int, boardSize: Int): Int {
    return positionDownLeft(positionDownLeft(position, boardSize), boardSize)
}

fun positionAfterCaptureDownRight(position: Int, boardSize: Int): Int {
    return positionDownRight(positionDownRight(position, boardSize), boardSize)
}


fun isRedKingMakerRow(position: Int, boardSize: Int) = row(position, boardSize) == boardSize-1
fun isBlackKingMakerRow(position: Int, boardSize: Int) = row(position, boardSize) == 0
