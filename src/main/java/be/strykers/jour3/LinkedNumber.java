package be.strykers.jour3;

public class LinkedNumber {
    private final int value;
    private final int posX;
    private final int posY;
    private LinkedNumber previous;
    private LinkedNumber next;

    public LinkedNumber(int value, int posX, int posY) {
        this.value = value;
        this.posX = posX;
        this.posY = posY;
    }

    public LinkedNumber getPrevious() {
        return previous;
    }

    public void setPrevious(LinkedNumber previous) {
        this.previous = previous;
    }

    public LinkedNumber getNext() {
        return next;
    }

    /**
     * Add a next number to the current number
     *
     * @param nextNumber the next number to add
     * @return the next number
     */
    public LinkedNumber addNext(Integer nextNumber) {
        LinkedNumber next = new LinkedNumber(nextNumber, this.posX + 1, this.posY);
        next.setPrevious(this);
        this.next = next;

        return next;
    }

    public int getValue() {
        return value;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

//    public int getFullValue() {
//        LinkedList<Integer> values = new LinkedList<>();
//
//        LinkedNumber current = this;
//        while (current != null) {
//            values.add(current.getValue());
//            current = current.getNext();
//        }
//
//        current = this.getPrevious();
//        while (current != null) {
//            values.addFirst(current.getValue());
//            current = current.getPrevious();
//        }
//
//        int result = 0;
//        for (int i = 0; i < values.size(); i++) {
//            result += (int) (values.get(i) * Math.pow(10, values.size() - i - 1));
//        }
//
//        return result;
//    }
//
//    public List<LinkedNumber> getNumbers() {
//        LinkedList<LinkedNumber> numbers = new LinkedList<>();
//        numbers.add(this);
//
//        LinkedNumber current = this.next;
//        while (current != null) {
//            numbers.add(current);
//            current = current.getNext();
//        }
//
//        current = this.getPrevious();
//        while (current != null) {
//            numbers.addFirst(current);
//            current = current.getPrevious();
//        }
//
//        return numbers;
//    }
}
