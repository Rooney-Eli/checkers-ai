class SimpleTree() {
    private var currentNodeId = 0

    private val nodeList = mutableListOf<Node>()

    fun addNode(capturedPiece: Piece, parentId: Int ): Int {
        nodeList.add(Node(currentNodeId + 1, currentNodeId, capturedPiece, mutableListOf()))
        currentNodeId++
        return currentNodeId
    }

    fun getNode(nodeId: Int): Node {
        return nodeList[nodeId]
    }
}


data class Node(
    val nodeId: Int,
    val parentId: Int,
    val capturedPiece: Piece,
    val children: MutableList<Node>
)
