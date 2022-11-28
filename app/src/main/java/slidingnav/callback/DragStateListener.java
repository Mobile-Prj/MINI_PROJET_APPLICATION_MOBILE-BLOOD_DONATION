package slidingnav.callback;

public interface DragStateListener {

    void onDragStart();

    void onDragEnd(boolean isMenuOpened);
}
