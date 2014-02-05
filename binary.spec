// grammar file for binary trees (counting leaves and internal nodes)


BinNode ::= Leaf * <1> + BinNode * BinNode * <1> + TreeNode * <1>;
TreeNode ::= BinNode * BinNode * BinNode * <1>;