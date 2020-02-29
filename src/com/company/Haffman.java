class Hafffman{


	static class Node {
        public int frequency; // the frequency of this tree
        public char data;
        public Node left, right;
    }

    void decode(String s, Node root) {
	    StringBuilder res = new StringBuilder();
        Node currNode = root;
        for(int i = 0; i< s.length(); i++){
            if(currNode.data != '\u0000'){
                res.append(currNode.data);
                currNode = root;
            }
            if(s.charAt(i) == '0') currNode = currNode.left;
            if(s.charAt(i) == '1') currNode = currNode.right;
        }
        res.append(currNode.data);
        System.out.println(res);
    }
}