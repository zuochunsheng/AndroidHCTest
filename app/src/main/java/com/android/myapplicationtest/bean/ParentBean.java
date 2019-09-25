package com.android.myapplicationtest.bean;

/**
 * @author： zcs
 * @time：2019/9/24 on 17:24
 * @description：
 */
public class ParentBean extends NormalResultBean {

    private Data data;

    public Data getData() {
        return data;
    }
    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        private PageBean page;
        private int totalPages;
        private int totalElements;
        //private List<DataBean> data;


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

//        public List<DataBean> getData() {
//            return data;
//        }
//
//        public void setData(List<DataBean> data) {
//            this.data = data;
//        }


//        public static class DataBean {
//
//            private String id;
//            private String name;
//            private String viewStatus;
//            private int orderNum;
//            private LangBean lang;
//            private List<BroadcastListBean> broadcastList;
//            private List<ModuleListBean> moduleList;
//
//            public String getId() {
//                return id;
//            }
//
//            public void setId(String id) {
//                this.id = id;
//            }
//
//            public String getName() {
//                return name;
//            }
//
//            public void setName(String name) {
//                this.name = name;
//            }
//
//            public String getViewStatus() {
//                return viewStatus;
//            }
//
//            public void setViewStatus(String viewStatus) {
//                this.viewStatus = viewStatus;
//            }
//
//            public int getOrderNum() {
//                return orderNum;
//            }
//
//            public void setOrderNum(int orderNum) {
//                this.orderNum = orderNum;
//            }
//
//            public LangBean getLang() {
//                return lang;
//            }
//
//            public void setLang(LangBean lang) {
//                this.lang = lang;
//            }
//
//            public List<BroadcastListBean> getBroadcastList() {
//                return broadcastList;
//            }
//
//            public void setBroadcastList(List<BroadcastListBean> broadcastList) {
//                this.broadcastList = broadcastList;
//            }
//
//            public List<ModuleListBean> getModuleList() {
//                return moduleList;
//            }
//
//            public void setModuleList(List<ModuleListBean> moduleList) {
//                this.moduleList = moduleList;
//            }
//
//            public static class LangBean {
//                /**
//                 * value : zh_CN
//                 * name : 中文
//                 */
//
//                private String value;
//                private String name;
//
//                public String getValue() {
//                    return value;
//                }
//
//                public void setValue(String value) {
//                    this.value = value;
//                }
//
//                public String getName() {
//                    return name;
//                }
//
//                public void setName(String name) {
//                    this.name = name;
//                }
//            }
//
//            public static class BroadcastListBean {
//                /**
//                 * id : 5be38aa9853ec813aa5361a2
//                 * isShow : true
//                 * orderNum : 2
//                 * broadcastId : 5be652cb853ec86edf54dec4
//                 * imgUrl : https://s.dragonfly.fm/image/0/cn/cn_1541821139579.jpg
//                 * type : ALBUM
//                 * externalUrl : TT0000089
//                 * topicOrAlbumId : 5be006c6853ec81ed2382104
//                 */
//
//                private String id;
//                private boolean isShow;
//                private int orderNum;
//                private String broadcastId;
//                private String imgUrl;
//                private String type;
//                private String externalUrl;
//                private String topicOrAlbumId;
//
//                public String getId() {
//                    return id;
//                }
//
//                public void setId(String id) {
//                    this.id = id;
//                }
//
//                public boolean isIsShow() {
//                    return isShow;
//                }
//
//                public void setIsShow(boolean isShow) {
//                    this.isShow = isShow;
//                }
//
//                public int getOrderNum() {
//                    return orderNum;
//                }
//
//                public void setOrderNum(int orderNum) {
//                    this.orderNum = orderNum;
//                }
//
//                public String getBroadcastId() {
//                    return broadcastId;
//                }
//
//                public void setBroadcastId(String broadcastId) {
//                    this.broadcastId = broadcastId;
//                }
//
//                public String getImgUrl() {
//                    return imgUrl;
//                }
//
//                public void setImgUrl(String imgUrl) {
//                    this.imgUrl = imgUrl;
//                }
//
//                public String getType() {
//                    return type;
//                }
//
//                public void setType(String type) {
//                    this.type = type;
//                }
//
//                public String getExternalUrl() {
//                    return externalUrl;
//                }
//
//                public void setExternalUrl(String externalUrl) {
//                    this.externalUrl = externalUrl;
//                }
//
//                public String getTopicOrAlbumId() {
//                    return topicOrAlbumId;
//                }
//
//                public void setTopicOrAlbumId(String topicOrAlbumId) {
//                    this.topicOrAlbumId = topicOrAlbumId;
//                }
//            }
//
//            public static class ModuleListBean {
//                /**
//                 * id : 5be38aa9853ec813aa5361a4
//                 * topicId : 5be0084d853ec81e3c1c81f4
//                 * name : tttttttt
//                 * orderNum : 1
//                 * albumList : [{"id":"5be6538c853ec86edf54dec7","orderNum":1,"albumId":"5be006c6853ec81ed2382104","albumCode":"TT0000089","albumName":"Annie Besant","imgUrl":"https://s.dragonfly.fm/image/0/Album%7C1546496442515/Album%7C1546496442515_1546496442004.jpg","price":100,"author":"Annie BESANT (1847 - 1933)","podcast":"timothyFR","supplierId":"5bd6d544853ec83bec14dc4a","supplierName":"网银云音乐","brandName":"wangyi","channelId":"5bdc154e853ec861b9fe8230","channelName":"Ekonomi","lang":{"value":"zh_CN","name":"中文"}},{"id":"5c21a6bb853ec8364d69c3cd","orderNum":2,"albumId":"5be8e175853ec878441b3099","albumCode":"TT0000114","albumName":"Childhood","imgUrl":"https://s.dragonfly.fm/image/0/Album%7C1541988682138/Album%7C1541988682138_1541988682997.jpg","price":1,"author":"Leo Tolstoy","podcast":"Expatriate","supplierId":"5be8dfe3853ec878441b3097","supplierName":"测试机构5","brandName":"dwj","channelId":"5bd6e69d853ec83bd5d58af7","channelName":"悬疑 惊悚","lang":{"value":"zh_CN","name":"中文"}},{"id":"5c21a6bd853ec8364d69c3ce","orderNum":3,"albumId":"5be4f8c5853ec8143d462b8a","albumCode":"TT0000094","albumName":"测试专辑","imgUrl":"https://s.dragonfly.fm/image/0/cn/cn_1541732476330.jpg","price":6,"author":"作者者者者","podcast":"播客客客客","supplierId":"5be2c2c8853ec80f3dd30266","supplierName":"666","brandName":"777","channelId":"5bd6e69d853ec83bd5d58af7","channelName":"悬疑 惊悚","lang":{"value":"zh_CN","name":"中文"}},{"id":"5c3d8b0d853ec845a5e7a0a5","orderNum":4,"albumId":"5c3d89c9853ec8124e9f3cbd","albumCode":"TT0001384","albumName":"relax","imgUrl":"https://s.dragonfly.fm/image/0/Album%7C1547536754681/Album%7C1547536754681_1547536756101.jpg","price":10,"author":"ss","podcast":"ss","channelId":"5be8f633853ec876fd84dd1c","channelName":"旅行&冒险","lang":{"value":"zh_CN","name":"中文"}}]
//                 */
//
//                private String id;
//                private String topicId;
//                private String name;
//                private int orderNum;
//                private List<AlbumListBean> albumList;
//
//                public String getId() {
//                    return id;
//                }
//
//                public void setId(String id) {
//                    this.id = id;
//                }
//
//                public String getTopicId() {
//                    return topicId;
//                }
//
//                public void setTopicId(String topicId) {
//                    this.topicId = topicId;
//                }
//
//                public String getName() {
//                    return name;
//                }
//
//                public void setName(String name) {
//                    this.name = name;
//                }
//
//                public int getOrderNum() {
//                    return orderNum;
//                }
//
//                public void setOrderNum(int orderNum) {
//                    this.orderNum = orderNum;
//                }
//
//                public List<AlbumListBean> getAlbumList() {
//                    return albumList;
//                }
//
//                public void setAlbumList(List<AlbumListBean> albumList) {
//                    this.albumList = albumList;
//                }
//
//                public static class AlbumListBean {
//                    /**
//                     * id : 5be6538c853ec86edf54dec7
//                     * orderNum : 1
//                     * albumId : 5be006c6853ec81ed2382104
//                     * albumCode : TT0000089
//                     * albumName : Annie Besant
//                     * imgUrl : https://s.dragonfly.fm/image/0/Album%7C1546496442515/Album%7C1546496442515_1546496442004.jpg
//                     * price : 100.0
//                     * author : Annie BESANT (1847 - 1933)
//                     * podcast : timothyFR
//                     * supplierId : 5bd6d544853ec83bec14dc4a
//                     * supplierName : 网银云音乐
//                     * brandName : wangyi
//                     * channelId : 5bdc154e853ec861b9fe8230
//                     * channelName : Ekonomi
//                     * lang : {"value":"zh_CN","name":"中文"}
//                     */
//
//                    private String id;
//                    private int orderNum;
//                    private String albumId;
//                    private String albumCode;
//                    private String albumName;
//                    private String imgUrl;
//                    private double price;
//                    private String author;
//                    private String podcast;
//                    private String supplierId;
//                    private String supplierName;
//                    private String brandName;
//                    private String channelId;
//                    private String channelName;
//                    private LangBeanX lang;
//
//                    public String getId() {
//                        return id;
//                    }
//
//                    public void setId(String id) {
//                        this.id = id;
//                    }
//
//                    public int getOrderNum() {
//                        return orderNum;
//                    }
//
//                    public void setOrderNum(int orderNum) {
//                        this.orderNum = orderNum;
//                    }
//
//                    public String getAlbumId() {
//                        return albumId;
//                    }
//
//                    public void setAlbumId(String albumId) {
//                        this.albumId = albumId;
//                    }
//
//                    public String getAlbumCode() {
//                        return albumCode;
//                    }
//
//                    public void setAlbumCode(String albumCode) {
//                        this.albumCode = albumCode;
//                    }
//
//                    public String getAlbumName() {
//                        return albumName;
//                    }
//
//                    public void setAlbumName(String albumName) {
//                        this.albumName = albumName;
//                    }
//
//                    public String getImgUrl() {
//                        return imgUrl;
//                    }
//
//                    public void setImgUrl(String imgUrl) {
//                        this.imgUrl = imgUrl;
//                    }
//
//                    public double getPrice() {
//                        return price;
//                    }
//
//                    public void setPrice(double price) {
//                        this.price = price;
//                    }
//
//                    public String getAuthor() {
//                        return author;
//                    }
//
//                    public void setAuthor(String author) {
//                        this.author = author;
//                    }
//
//                    public String getPodcast() {
//                        return podcast;
//                    }
//
//                    public void setPodcast(String podcast) {
//                        this.podcast = podcast;
//                    }
//
//                    public String getSupplierId() {
//                        return supplierId;
//                    }
//
//                    public void setSupplierId(String supplierId) {
//                        this.supplierId = supplierId;
//                    }
//
//                    public String getSupplierName() {
//                        return supplierName;
//                    }
//
//                    public void setSupplierName(String supplierName) {
//                        this.supplierName = supplierName;
//                    }
//
//                    public String getBrandName() {
//                        return brandName;
//                    }
//
//                    public void setBrandName(String brandName) {
//                        this.brandName = brandName;
//                    }
//
//                    public String getChannelId() {
//                        return channelId;
//                    }
//
//                    public void setChannelId(String channelId) {
//                        this.channelId = channelId;
//                    }
//
//                    public String getChannelName() {
//                        return channelName;
//                    }
//
//                    public void setChannelName(String channelName) {
//                        this.channelName = channelName;
//                    }
//
//                    public LangBeanX getLang() {
//                        return lang;
//                    }
//
//                    public void setLang(LangBeanX lang) {
//                        this.lang = lang;
//                    }
//
//                    public static class LangBeanX {
//                        /**
//                         * value : zh_CN
//                         * name : 中文
//                         */
//
//                        private String value;
//                        private String name;
//
//                        public String getValue() {
//                            return value;
//                        }
//
//                        public void setValue(String value) {
//                            this.value = value;
//                        }
//
//                        public String getName() {
//                            return name;
//                        }
//
//                        public void setName(String name) {
//                            this.name = name;
//                        }
//                    }
//                }
//            }
//        }
    }
}
