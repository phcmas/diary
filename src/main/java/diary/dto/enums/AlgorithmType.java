package diary.dto.enums;

public enum AlgorithmType {
    DP(0),
    DFS(1),
    BFS(2),
    GREEDY(3),
    TWO_POINTER(4),
    HASH_TABLE(5),
    HEAP(6),
    UNION_FIND(7),
    ETC(8);

    private final int type;

    AlgorithmType(int type) {this.type = type;}

    public String getString() {
        switch (type) {
            case 0 : return "DP";
            case 1 : return "DFS";
            case 2 : return "BFS";
            case 3 : return "Greedy";
            case 4 : return "TwoPointer";
            case 5 : return "HashTable";
            case 6 : return "Heap";
            case 7 : return "UnionFind";
            case 8 : return "ETC";
            default: return "ERROR";
        }
    }


}
