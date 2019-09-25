package com.android.myapplicationtest.bean;

/**
 * @author： zcs
 * @time：2019/9/24 on 15:53
 * @description：统一返回测试类
 * 剥离code和 message
 */
public class ChildBean {


    private PageBean page;
    private int totalPages;
    private int totalElements;


    public PageBean getPage() {
        return page;
    }
    public void setPage(PageBean page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalElements() {
        return totalElements;
    }
    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }


    public static class PageBean {
        /**
         * offset : 0
         * pageSize : 50
         * pageNumber : 0
         */

        private int offset;
        private int pageSize;
        private int pageNumber;

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPageNumber() {
            return pageNumber;
        }

        public void setPageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
        }
    }


}
