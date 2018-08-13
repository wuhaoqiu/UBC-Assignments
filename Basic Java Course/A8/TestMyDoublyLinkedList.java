package A8;

public class TestMyDoublyLinkedList {
	public static void main(String[] args) {


		String[] array = {"A", "B", "A", "A"};
		MyDoublyLinkedList<String> list = new MyDoublyLinkedList<>(array);
		System.out.printf("%-32s%-10s\n", "Initialized with {A,B,A,A}:", list);

		testAdding(list);
		testGetting(list);
		testSetting(list);
		testRemoving(list);
		testChecking(list);

		System.out.printf("\n%-32s%-10s\n", "ToString: " , list);
		System.out.printf("%-32s%-10s\n", "ToReversedString: " , list.toReversedString());
		
		//the follow code should throw an exception
		System.out.println("\nAdding 'X' at 20: ");
		list.add(20, "X");
	}

	private static void testAdding(MyDoublyLinkedList<String> list) {
		System.out.printf("\n%-32s%-10s\n", "Adding elements: ", list);
		list.addFirst("*"); System.out.printf("%-32s%-10s\n", "  - '*' at first", list); 
		list.addLast("C"); System.out.printf("%-32s%-10s\n", "  - 'C' at last", list);
		list.add("D");System.out.printf("%-32s%-10s\n", "  - 'D'", list);
		list.add(2, "#");	System.out.printf("%-32s%-10s\n", "  - '#' @ 2", list);
	}
	private static void testGetting(MyDoublyLinkedList<String> list) {
		System.out.printf("\n%-32s%-10s\n", "Getting elements: ", list);
		System.out.printf("%-32s%-10s\n", "  - First Element", list.getFirst());
		System.out.printf("%-32s%-10s\n", "  - Last Element", list.getLast());
		System.out.printf("%-32s%-10s\n", "  - Element at 1", list.get(1));
		System.out.printf("%-32s%-10s\n", "  - Element at 20", list.get(20));		
	}
	private static void testSetting(MyDoublyLinkedList<String> list) {
		System.out.printf("\n%-32s%-10s\n", "Setting elements: ", list);
		System.out.printf("%-32s%-10s\n", "  - Element @ 0: '"+list.set(0,"+")+"' -> '+'", list);
		System.out.printf("%-32s%-10s\n", "  - Element @ 2: '"+list.set(2, "-")+"' -> '-'", list);
		System.out.printf("%-32s%-10s\n", "  - Element @ 9: '"+list.set(9, "&")+"' -> '&'", list);
	}
	private static void testRemoving(MyDoublyLinkedList<String> list) {
		System.out.printf("\n%-32s%-10s\n", "Removing elements: ", list);
		System.out.printf("%-32s%-10s\n", "  - First element '" + list.removeFirst() + "'", list);
		System.out.printf("%-32s%-10s\n", "  - Last element '" + list.removeLast() + "'", list);
		System.out.printf("%-32s%-10s\n", "  - Element @ 1 '" + list.remove(1) + "'", list);
		System.out.printf("%-32s%-10s\n", "  - Element @ 9 '" + list.remove(9) + "'", list);
	}
	private static void testChecking(MyDoublyLinkedList<String> list) {
		System.out.printf("\n%-32s%-10s\n", "Checking: ", list);
		System.out.printf("%-32s%-10s\n", "  - Contains 'A'? ", list.contains("A"));
		System.out.printf("%-32s%-10s\n", "  - Contains 'Z'? ", list.contains("Z"));
		System.out.printf("%-32s%-10s\n", "  - First occurence of 'A' @ ", list.indexOf("A"));
		System.out.printf("%-32s%-10s\n", "  - Last occurence of 'A' @ ", list.lastIndexOf("A"));
		System.out.printf("%-32s%-10s\n", "  - First index of 'Z' @", list.indexOf("Z"));
		System.out.printf("%-32s%-10s\n", "  - Last index of 'Z' @", list.lastIndexOf("Z"));		
	}
}
