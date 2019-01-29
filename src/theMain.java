import java.io.*;
import java.util.regex.*;

public class theMain {
	public static void main(String[] ags) {
		LazyBinarySearchTree lbs = new LazyBinarySearchTree();
		String inputPath = ags[0];
		String outputPath = ags[1];

		//String inputPath = "input.txt";
		//String outputPath = "output.txt";
		File inputFile = new File(inputPath);
		String theInput = "";
		//pattern for command with ":"
		Pattern p = Pattern.compile(":");
		Pattern ins = Pattern.compile("Insert");
		Pattern del = Pattern.compile("Delete");
		Pattern cont = Pattern.compile("Contains");
		//pattern for command without ":"
		Pattern fmin = Pattern.compile("FindMin");
		Pattern fmax = Pattern.compile("FindMax");
		Pattern Pt = Pattern.compile("PrintTree");
		Pattern h = Pattern.compile("Height");
		Pattern s = Pattern.compile("Size");


		try {
			//open the input file and the output file
			InputStreamReader reader = new InputStreamReader(new FileInputStream(inputFile));
			BufferedReader br = new BufferedReader(reader);
			File outputFile = new File(outputPath);
			outputFile.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(outputFile));
			//get every lines in the input file.
			while ((theInput = br.readLine()) != null) {
				Matcher colon = p.matcher(theInput);
				if (colon.find() == true) {
					//if found ":"
					//try to find Insert, Delete, or contains
					Matcher insert = ins.matcher(theInput);
					Matcher delete = del.matcher(theInput);
					Matcher contains = cont.matcher(theInput);
					if (insert.find() == true) {
						try {
							String[] spl = theInput.split(":");
							out.write(String.valueOf(lbs.insert(Integer.parseInt(spl[1]))));
							out.newLine();
						} catch (Exception e) {
							out.write(String.valueOf("Error in insert: " + e));
							out.newLine();
						}
					} else if (delete.find() == true) {
						try {
							String[] spl = theInput.split(":");
							out.write(String.valueOf(lbs.delete(Integer.parseInt(spl[1]))));
							out.newLine();
						} catch (Exception e) {
							out.write(String.valueOf("Error in delete: " + e));
							out.newLine();
						}
					} else if (contains.find() == true) {
						try {
							String[] spl = theInput.split(":");
							out.write(String.valueOf(lbs.contains(Integer.parseInt(spl[1]))));
							out.newLine();
						} catch (Exception e) {
							out.write(String.valueOf("Error in contains: " + e));
							out.newLine();
						}
					}
				}else { 
					//if did not find ":"
					//try to find FindMax, FindMin, PrintTree, Height, or Size
					//if did not find all these Pattern, then print the error message.
					Matcher findMax = fmax.matcher(theInput);
					Matcher findMin = fmin.matcher(theInput);
					Matcher PrintTree = Pt.matcher(theInput);
					Matcher Height = h.matcher(theInput);
					Matcher Size = s.matcher(theInput);
					if (findMax.find() == true) {
						int max = lbs.findMax();
						if (max <= 99 && max >= 1)
							out.write(String.valueOf(max));
						else
							out.write(String.valueOf("This tree is empty"));
						out.newLine();
					} else if (findMin.find() == true) {
						int min = lbs.findMin();
						if (min <= 99 && min >= 1)

							out.write(String.valueOf(min));
						else
							out.write(String.valueOf("This tree is empty"));
						out.newLine();
					} else if (PrintTree.find() == true) {
						out.write(String.valueOf(lbs));
						out.newLine();
					} else if (Height.find() == true) {
						out.write(String.valueOf(lbs.height()));
						out.newLine();
					} else if (Size.find() == true) {
						out.write(String.valueOf(lbs.size()));
						out.newLine();
					} else {
						try {
							throw (new Exception());
						} catch (Exception e) {
							out.write(String.valueOf("Error in Line: " + theInput));
							out.newLine();
						}
					}
				}
			}
			br.close();
			out.close();
		} catch (Exception e) {
			System.out.print(e);
		}
	}

}
