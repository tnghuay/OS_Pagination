import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class LRU {
    public static void main(String[] args) {
        int[][] a = new int[4][12];
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap 12 lan theo thu tu: ");
        for (int i = 0; i < 12; i++) {
            a[0][i] = sc.nextInt(); // 12 lần truy cập 
        }

        // Set toàn bộ các ô còn lại trong ma trận a thành -1
        for (int i = 1; i < 4; i++) {
            for (int j = 0; j < 12; j++) {
                a[i][j] = -1;
            }
        }

        HashSet<Integer> s = new HashSet<>();
        HashMap<Integer, Integer> mp = new HashMap<>(); // Lưu pair<trang, stt truy cập>
        a[1][0] = a[0][0];
        s.add(a[1][0]);
        mp.put(a[1][0], 0);

        for (int i = 1; i < 12; i++) {
            int temp = a[0][i];
            mp.put(temp, i);

            // Trang chưa có trong khung và tổng số khung chưa vượt quá 3
            if (!s.contains(temp) && s.size() < 3) {
                s.add(temp);
                for (int j = 1; j < 4; j++) {
                    if (a[j][i - 1] != -1) {
                        a[j][i] = a[j][i - 1];
                    } else {
                        a[j][i] = temp;
                        break;
                    }
                }
            } else if (s.contains(temp)) {
                for (int j = 1; j < 4; j++) {
                    a[j][i] = a[j][i - 1];
                }
            } else if (!s.contains(temp) && s.size() == 3) {
                int id = i, page = -1;
                for (Map.Entry<Integer, Integer> x : mp.entrySet()) {
                    if (s.contains(x.getKey()) && x.getValue() < id) {
                        id = x.getValue();
                        page = x.getKey();
                    }
                }
                for (int j = 1; j < 4; j++) {
                    if (a[j][i - 1] == page) a[j][i] = temp;
                    else a[j][i] = a[j][i - 1];
                }
                s.remove(page);
                s.add(temp);
            }
        } 
        //Output
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 12; j++) {
                if (a[i][j] == -1) {
                    System.out.print("- ");
                } else {
                    System.out.print(a[i][j] + " ");
                }
            }
            System.out.println();
            if (i == 0) System.out.println("-----------------------");
        }
    }
}
