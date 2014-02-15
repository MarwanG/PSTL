// grammar file for binary trees (counting leaves and internal nodes)


BinNode ::= Leaf2 * <1> + BinNode * BinNode * <1>;
//TreeNode ::= BinNode * BinNode * BinNode;
