// grammar file for binary trees (counting leaves and internal nodes)


BinNode ::= Leaf * <1> + BinNode * BinNode * <1> + BinNode * TreeNode * <1>;
TreeNode ::= BinNode * BinNode * BinNode ;

