package tree;

import model.Employee;

public class TreeNode {
    public Employee employee;
    public TreeNode left;
    public TreeNode right;

        public TreeNode(Employee employee) {
            this.employee = employee;
            this.left = null;
            this.right = null;
    }
}
