package BtreeV2;

public class Test{
	public static void main(String args[]){
		
		BTree b=new BTree(3);
		/*
		b.insert(45,b.root.pointer);
		b.insert(24,b.root.pointer);
		b.insert(3,b.root.pointer);
		b.insert(37,b.root.pointer);
		b.insert(11,b.root.pointer);
		b.insert(53,b.root.pointer);
		b.insert(90,b.root.pointer);
		b.insert(50,b.root.pointer);
		b.insert(61,b.root.pointer);
		b.insert(70,b.root.pointer);
		b.insert(100,b.root.pointer);
		*/


		b.insert(1,b.root.pointer);
		b.insert(2,b.root.pointer);
		b.insert(3,b.root.pointer);
		b.insert(4,b.root.pointer);
		b.insert(5,b.root.pointer);
		b.insert(6,b.root.pointer);
		b.insert(7,b.root.pointer);
		b.insert(8,b.root.pointer);
		b.insert(9,b.root.pointer);
		b.insert(10,b.root.pointer);
		b.insert(11,b.root.pointer);
		b.insert(12,b.root.pointer);
		b.insert(13,b.root.pointer);
		b.insert(14,b.root.pointer);
		b.insert(15,b.root.pointer);
		b.insert(16,b.root.pointer);
		b.insert(17,b.root.pointer);
		b.insert(18,b.root.pointer);
		b.insert(19,b.root.pointer);
		b.insert(20,b.root.pointer);
		b.insert(21,b.root.pointer);
		b.insert(22,b.root.pointer);
		b.insert(23,b.root.pointer);
		b.insert(24,b.root.pointer);
		b.insert(25,b.root.pointer);
		b.insert(26,b.root.pointer);
		b.insert(27,b.root.pointer);
		b.insert(28,b.root.pointer);
		

		TreePrinter tp=new TreePrinter(b.root);
		tp.printAllTree();

	}
}