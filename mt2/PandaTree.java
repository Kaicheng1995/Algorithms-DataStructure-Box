public class PandaTree {
    private PandaNode root;
    private PandaBear leastRisk;
    private PandaBear mostRisk;

    public PandaTree(PandaBear bear) {
        this.root = new PandaNode(bear, null, null);
        this.leastRisk = bear;
        this.mostRisk = bear;
    }

    public PandaBear mostAtRisk() {
        return this.mostRisk;
    }

    public PandaBear leastAtRisk() {
        return this.leastRisk;
    }

    public void add(PandaBear bear) {
        add(bear, this.root);
        if (bear.compareTo(this.leastRisk) > 0)
            this.leastRisk = bear;
        if (bear.compareTo(this.mostRisk) < 0)
            this.mostRisk = bear;
    }

    private PandaNode add(PandaBear bear, PandaNode node) {
        if (node == null) {
            return new PandaNode(bear, null, null);
        }
        if (node.item.compareTo(bear) > 0) {
            node.left = add(bear, node.left);
        } else if (node.item.compareTo(bear) < 0) {
            node.right = add(bear, node.right);
        }
        return node;
    }

    private class PandaNode {
        private PandaBear item;
        private PandaNode left;
        private PandaNode right;
        private PandaNode(PandaBear bear, PandaNode l, PandaNode r) {
            this.item = bear;
            this.left = l;
            this.right = r;
        }
    }
}