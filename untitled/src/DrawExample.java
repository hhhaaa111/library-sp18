//import edu.princeton.cs.introcs.StdDraw;
public class DrawExample {
    public static void main(String[] args) {
        // 设置画布大小
        StdDraw.setCanvasSize(800, 800);

        // 设置坐标系范围
        StdDraw.setXscale(0, 10);
        StdDraw.setYscale(0, 10);

        // 画一个圆
        StdDraw.circle(5.0, 5.0, 2.0);

        // 画一个点
        StdDraw.point(3.0, 3.0);

        // 显示内容
        StdDraw.show();
    }
}
