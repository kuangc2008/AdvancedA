package myapp.kc.com.view;


public interface IDetailWebView {

    boolean canScrollVertically(int direction);

    void customScrollBy(int dy);

    void customScrollTo(int toY);
    void customScrollTo(int toY, boolean needSlop);

    int customGetContentHeight();

    int customGetWebScrollY();

    int customComputeVerticalScrollRange();
    int customComputeVerticalScrolllOffset();
}
