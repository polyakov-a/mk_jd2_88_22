package by.it_academy.jd2.mk_jd2_88_22.messenger.model;

public class Pageable {

    private int page = 1;
    private int size = 20;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        if (page > 1) {
            this.page = page;
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        if (size > 0) {
            this.size = size;
        }
    }
}
