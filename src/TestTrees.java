//Quick test written up to compare efficiency of both trees.
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
public class TestTrees {

    public static void main(String[] args) {
        int[] testSize = {1000,2000,4000,8000,16000};
        double binaryTotalDepth, binaryMaxDepth;
        double rbTotalDepth, rbMaxDepth;
        int i,j,k;
        //Array storing distribution of RT values(rt<1.2,rt<1.25,rt<1.3,rt<=1.35,rt>1.35)
        //Array storing distribution of RM values(rm<1.6,rm<1.75,rm<1.9,rm<=2.05,rm>2.05)
        int[][] rtDistribution = {{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
        int[][] rmDistribution = {{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
        //Loops through all test sizes (i.e. 1000,2000,...)
        for(i=0; i<5; i++) {
            //Create new array of testSize[i] size, fill it with values from 1 to testSize[i]
            int[] testArray = new int[testSize[i]];
            for(k=1; k<=testSize[i]; k++) {
                testArray[k-1] = k;
            }
            //Test 500 times
            for(j=0; j<500; j++) {
                //Randomly permute array
                shuffleArray(testArray);
                //Initialize a new binary search tree, and a red black tree
                BinarySearchTree binaryTree = new BinarySearchTree();
                RBTree rbTree = new RBTree();
                //Insert values of the now permuted set into both of the trees
                for(k=0; k<testSize[i]; k++){
                    binaryTree.insert(testArray[k]);
                    rbTree.insert(testArray[k]);
                }
                //Calculate total depth and max depth for both trees
                binaryTotalDepth = binaryTree.totalDepth();
                binaryMaxDepth = binaryTree.maxDepth();
                rbTotalDepth = rbTree.totalDepth();
                rbMaxDepth = rbTree.maxDepth();
                //Calculate RT and RM values
                double currentRT = binaryTotalDepth/rbTotalDepth;
                double currentRM = binaryMaxDepth/rbMaxDepth;
                //Find distribution of RT values, increment value corresponding to it
                if(currentRT < 1.2) {
                    rtDistribution[i][0]++;
                }
                else if(currentRT < 1.25) {
                    rtDistribution[i][1]++;
                }
                else if(currentRT < 1.30) {
                    rtDistribution[i][2]++;
                }
                else if(currentRT<=1.35) {
                    rtDistribution[i][3]++;
                }
                else {
                    rtDistribution[i][4]++;
                }
                //Find distribution of RM values, increment value corresponding to it
                if(currentRM < 1.6) {
                    rmDistribution[i][0]++;
                }
                else if(currentRM < 1.75) {
                    rmDistribution[i][1]++;
                }
                else if(currentRM < 1.9) {
                    rmDistribution[i][2]++;
                }
                else if(currentRM<=2.05) {
                    rmDistribution[i][3]++;
                }
                else {
                    rmDistribution[i][4]++;
                }
            }
        }
        //Output the total depth and max depth distributions for each n set size, use that for table
        for(i=0; i<5; i++) {
            for(j=0; j<5; j++) {
                System.out.println("Test "+i+" Total Depth Distribution: "+rtDistribution[i][j]);
                System.out.println("Test "+i+" Max Depth Distribution: "+rmDistribution[i][j]);
            }
        }
    }

    //Using the Fisher-Yates shuffle
    static void shuffleArray(int[] ar) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

}