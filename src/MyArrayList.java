import java.util.Comparator;

public class MyArrayList {

    private static final int SIZE_FACTOR=2;

    public String[] data;

    private int index;

    private int size;

    MyArrayList(){
        this.data=new String[10];
        this.size=10;
    }

    public void add(String obj){
        //System.out.println(obj+"    index:"+this.index+"size:"+this.size+"data size:"+this.data.length);
        if(this.index==this.size-1){
            increaseSizeAndReallocate();
        }
        data[this.index]=obj;
        this.index++;
    }

    private void increaseSizeAndReallocate() {
        this.size=this.size*SIZE_FACTOR;
        String[] newData = new String[this.size];
        System.arraycopy(data, 0, newData, 0, data.length);
        this.data=newData;
        //System.out.println("***index:"+this.index+"size:"+this.size+"data size:"+this.data.length);
    }

    public static class MyComparator implements Comparator<String> {
        public int compare(String s1, String s2) {
            if (s1 == null) {
                return (s2 == null) ? 0 : +1;
            } else {
                return (s2 == null) ? -1 : s1.compareTo(s2);
            }
        }
    }

}