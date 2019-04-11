package Practise;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class PKWIKClUSTER {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<15;i++){
		int n = 1622;
		int numLines = 0;//count how many lines
		int lengthLine=0;
		int numGreater=0;
		LinkedList<String> mapList;
		PrintWriter pr=null;
		double[][] arr = new double[n][n];
		int maxSize=0;
		int minSize=0;
		double sum=0;//sum of all probs
		try {
			//read mapping names from the file
			Scanner scan2=new Scanner(new File("mappingcollins.txt"));
			String mapResult = scan2.nextLine();
			String[] mapArray = mapResult.split(",");
			lengthLine=mapArray.length;
			mapList=new LinkedList<>(Arrays.asList(mapArray));
			scan2.close();
			//read original probability data
			Scanner scan = new Scanner(new File("WMcollins.txt"));
			
			while (scan.hasNextLine()) {
				String s = scan.nextLine();
				String[] a = s.split(" ");
				lengthLine=a.length;
				for(int i1=0;i1<lengthLine;i1++){
					arr[numLines][i1]=Double.parseDouble(a[i1]);
					sum+=arr[numLines][i1];
				}
				numLines++;
			}
			System.out.println("sum of all prob is:"+sum);
			//make a[i][i] equals to 1
			for(int i1=0;i1<lengthLine;i1++)
				arr[i1][i1]=1;
			scan.close();
			System.out.println("length of each line is:"+lengthLine);
			System.out.println("num of lines is:"+numLines);
			for(int i1=0;i1<arr.length;i1++){
				for(int j=0;j<arr[0].length;j++){
					//System.out.print(arr[i][j]+"  ");
					if(arr[i1][j]>=0.5)
						numGreater++;
				}
				//System.out.println("");
			}
			System.out.println("num of prob greater than 0.5:"+numGreater);	
			
			LinkedList<Integer> originalRows=new LinkedList<>();
			LinkedList<Integer> processedRows=new LinkedList<>();
			ArrayList<LinkedList> allClusters=new ArrayList<>();//store name
			ArrayList<LinkedList> allClusters2=new ArrayList<>();//store index
			LinkedList<Integer> sizeOfCluster=new LinkedList<>();;
			for(int i1=0;i1<numLines;i1++)//initialze original
				originalRows.add(i1);
			Random rand=new Random();
			int randomNode=0;
			int numOfCluster=0;
			int check=0;
			String partFileName = String.format("%s%d.txt", "resultsOfFirstAlg", i);
			pr=new PrintWriter(new FileOutputStream(new File(partFileName),true));
			while(!originalRows.isEmpty()){
				LinkedList<Integer> cluster=new LinkedList<>();
				LinkedList<String> clusterName=new LinkedList<>();//to store corresponding name
				boolean invalid=true;
				while(invalid){//generate a node that has not been processed
					randomNode=originalRows.get(rand.nextInt(originalRows.size()));
					if(!processedRows.contains(randomNode))
						invalid=false;
				}
				cluster.add(randomNode);
				clusterName.add(mapList.get(randomNode));
				processedRows.add(randomNode);
				originalRows.remove(new Integer(randomNode));
				
				for(int temp:originalRows){
					if(arr[randomNode][temp]>=0.05&&arr[randomNode][temp]!=1){
						cluster.add(temp);
						clusterName.add(mapList.get(temp));
					}
				}
				
				for(int temp:cluster){
					originalRows.remove(new Integer(temp));
				}
				
					allClusters.add(clusterName);
					allClusters2.add(cluster);
					//System.out.println(clusterName.toString());
					check+=clusterName.size();
					sizeOfCluster.add(cluster.size());
					if(cluster.size()>maxSize)
						maxSize=cluster.size();
					if(cluster.size()<maxSize&&cluster.size()!=1)
						minSize=cluster.size();
					numOfCluster++;					
					pr.println(clusterName.toString());
					
			}
			double distance=editDistance(allClusters2, arr);
			pr.println("distances before clustering is:"+sum);
			pr.println("edit distances is:"+distance);
			pr.println("cluster sizes:"+sizeOfCluster.toString());
			pr.println("num of clusters is:"+numOfCluster);
			pr.println("max size of cluster is:"+maxSize);
			pr.println("second min size of cluster is:"+minSize);
			pr.println("min size of cluster is:1");
			pr.println("sum of all nodes is:"+check);
			pr.close();
			System.out.println("edit distances is:"+distance);
			System.out.println("num of clusters is:"+numOfCluster);
			System.out.println("sum of all nodes:"+check);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(new File(".").getAbsoluteFile());// if io
																// exception
																// occurs, using
																// this
																// statement to
																// check the
																// default
																// directory
																// path.

		}
		}// end of out for
	}
	
	public static double editDistance(ArrayList<LinkedList> clusters,double[][] originalMatrix){
		double sum=0;
		double partSum=0;
		for(LinkedList<Integer> element1:clusters){
			for(LinkedList<Integer> element2:clusters){
				partSum=0;// begin to compare nodes from cluster to cluster
				if(element1.get(0)==element2.get(0)){// if two same clusters are compared
					for(int i=0;i<element1.size();i++){
						for(int j=0;j<element2.size();j++){
							partSum=partSum+(1-originalMatrix[(int)element1.get(i)][(int)element2.get(j)]);//prob between noded in the same cluster with same centers
						}//end of fourth for
					}//end of third for
					partSum=partSum/2.0;
				}//end of if
				else{
					for(int i=0;i<element1.size();i++){
						for(int j=0;j<element2.size();j++){
							partSum+=originalMatrix[(int)element1.get(i)][(int)element2.get(j)];//prob between nodes in the same cluster with same centers
						}//end of fourth for
					}//end of third for
					partSum=partSum/2.0;//different clusters will be added two times so just half the sum at the beginning
				}//end of else
				sum+=partSum;
			}//end of second for		
		}//end of first for
		return sum;
	}//end of editDistance method

}
