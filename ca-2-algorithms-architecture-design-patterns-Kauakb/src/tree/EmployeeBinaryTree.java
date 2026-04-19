package tree;

import model.Employee;

import java.util.*;


 //Binary tree representing employee hierarchy.
 //insertion to fill left-to-right.

public class EmployeeBinaryTree {
    private TreeNode root;
    private int nodeCount;

    public EmployeeBinaryTree() {
        this.root = null;
        this.nodeCount = 0;
    }

    // Insert a list of employees into the tree using level-order insertion
    public void insertLevelOrder(List<Employee> employees) {
        if (employees == null || employees.isEmpty()) {
            System.out.println("No employees to insert.");
            return;
        }

        root = new TreeNode(employees.get(0)); // first element as root
        nodeCount = 1;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int index = 1;

        // Continue inserting while there are employees left
        while (!queue.isEmpty() && index < employees.size()) {
            TreeNode current = queue.poll();

            // Insert left child if available
            if (index < employees.size()) {
                current.left = new TreeNode(employees.get(index++));
                nodeCount++;
                queue.add(current.left);
            }

            // Insert right child if available
            if (index < employees.size()) {
                current.right = new TreeNode(employees.get(index++));
                nodeCount++;
                queue.add(current.right);
            }
        }
    }

    // Print tree level by level
    public void levelOrderTraversal() {
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }

        System.out.println("\n=== Level-Order Traversal (Employee Hierarchy) ===");
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            System.out.println("\n--- Level " + level + " ---");

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                System.out.println(node.employee.getFullName() + " (" +
                        node.employee.getManagerType() + " - " +
                        node.employee.getDepartment() + ")");

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            level++;
        }
    }

    // Height of the tree (number of levels)
    public int getHeight() {
        return calculateHeight(root);
    }

    private int calculateHeight(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(calculateHeight(node.left), calculateHeight(node.right));
    }

    // Number of nodes in the tree
    public int getNodeCount() {
        return nodeCount;
    }

    // Display basic stats
    public void displayTreeStats() {
        System.out.println("\n=== Binary Tree Statistics ===");
        System.out.println("Total Nodes: " + getNodeCount());
        System.out.println("Tree Height: " + getHeight());
    }
}