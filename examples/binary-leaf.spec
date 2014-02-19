// grammar file for binary trees (counting leaves and internal nodes)


BinNode ::= Leaf * <1> + BinNode * BinNode * <0> ;


