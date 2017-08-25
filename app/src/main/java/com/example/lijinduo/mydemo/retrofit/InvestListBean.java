package com.example.lijinduo.mydemo.retrofit;

import android.databinding.BaseObservable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/8/24
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class InvestListBean extends HttpResult implements Serializable{

    /**
     * resCode : 1
     * resData : {"collection":0,"list":[{"addApr":0,"addTime":1503470898000,"amount":100000,"apr":16,"directional":false,"goodType":"推荐产品","id":"4179","imageUrl":"https://static.edspay.com/article/1707030096087633-89504E47/view.html","isRecomment":1,"logonRequired":true,"minAmount":100,"name":"新手专享XS328","newbieOnly":true,"progress":50.5,"recommended":false,"redirectUrl":"invest/detail.html","remainAmount":49500,"scoreRate":0,"status":"1","tenderTimes":11,"timeLimit":7,"timeToStart":0,"timeType":"1","type":113,"uuid":"1708232004194673"},{"addApr":0,"addTime":1503409465000,"amount":600000,"apr":7.6,"directional":false,"goodType":"长期稳健","id":"4166","imageUrl":"https://static.edspay.com/article/1707032107763036-89504E47/view.html","isRecomment":0,"logonRequired":true,"minAmount":1000,"name":"E金贷-00006","newbieOnly":false,"progress":4.89,"recommended":false,"redirectUrl":"invest/detail.html","remainAmount":570688,"scoreRate":0,"status":"1","tenderTimes":0,"timeLimit":14,"timeToStart":0,"timeType":"1","type":113,"uuid":"1708220056679764"},{"addApr":0,"addTime":1503026855000,"amount":200000,"apr":10,"directional":false,"goodType":"长期稳健","id":"4074","imageUrl":"https://static.edspay.com/article/1707032107763036-89504E47/view.html","isRecomment":0,"logonRequired":true,"minAmount":100,"name":"白拿专属标002","newbieOnly":false,"progress":14.7,"recommended":false,"redirectUrl":"invest/detail.html","remainAmount":170600,"scoreRate":0,"status":"1","tenderTimes":0,"timeLimit":12,"timeToStart":0,"timeType":"0","type":113,"uuid":"1708181540961532"},{"addApr":0,"addTime":1503464590000,"amount":600000,"apr":9.2,"directional":false,"goodType":"长期稳健","id":"4172","imageUrl":"https://static.edspay.com/article/1707032107763036-89504E47/view.html","isRecomment":0,"logonRequired":true,"minAmount":100,"name":"E分期-2期-00014","newbieOnly":false,"progress":24.38,"recommended":false,"redirectUrl":"invest/detail.html","remainAmount":453697,"scoreRate":0,"status":"1","tenderTimes":0,"timeLimit":2,"timeToStart":0,"timeType":"0","type":113,"uuid":"1708231729373656"},{"addApr":0,"addTime":1503478529000,"amount":300000,"apr":11.8,"directional":false,"goodType":"长期稳健","id":"4181","imageUrl":"https://static.edspay.com/article/1707032107763036-89504E47/view.html","isRecomment":0,"logonRequired":true,"minAmount":100,"name":"E分期-6期-00020","newbieOnly":false,"progress":42.37,"recommended":false,"redirectUrl":"invest/detail.html","remainAmount":172881,"scoreRate":0,"status":"1","tenderTimes":0,"timeLimit":6,"timeToStart":0,"timeType":"0","type":113,"uuid":"1708232017244169"},{"addApr":0,"addTime":1503466450000,"amount":600000,"apr":8.2,"directional":false,"goodType":"长期稳健","id":"4176","imageUrl":"https://static.edspay.com/article/1707032107763036-89504E47/view.html","isRecomment":0,"logonRequired":true,"minAmount":100,"name":"E分期-1期-00154","newbieOnly":false,"progress":58.27,"recommended":false,"redirectUrl":"invest/detail.html","remainAmount":250390,"scoreRate":0,"status":"1","tenderTimes":0,"timeLimit":1,"timeToStart":0,"timeType":"0","type":113,"uuid":"1708230428906600"},{"addApr":0,"addTime":1503468835000,"amount":300000,"apr":14.2,"directional":false,"goodType":"长期稳健","id":"4178","imageUrl":"https://static.edspay.com/article/1707032107763036-89504E47/view.html","isRecomment":0,"logonRequired":true,"minAmount":100,"name":"E分期-12期-00016","newbieOnly":false,"progress":83.8,"recommended":false,"redirectUrl":"invest/detail.html","remainAmount":48610,"scoreRate":0,"status":"1","tenderTimes":0,"timeLimit":12,"timeToStart":0,"timeType":"0","type":113,"uuid":"1708231722855738"},{"addApr":0,"addTime":1503482728000,"amount":300000,"apr":10.6,"directional":false,"goodType":"长期稳健","id":"4184","imageUrl":"https://static.edspay.com/article/1707032107763036-89504E47/view.html","isRecomment":0,"logonRequired":true,"minAmount":100,"name":"E分期-3期-00033","newbieOnly":false,"progress":84.3,"recommended":false,"redirectUrl":"invest/detail.html","remainAmount":47100,"scoreRate":0,"status":"1","tenderTimes":0,"timeLimit":3,"timeToStart":0,"timeType":"0","type":113,"uuid":"1708231692853016"},{"addApr":0,"addTime":1503466416000,"amount":600000,"apr":8.2,"directional":false,"goodType":"已售罄","id":"4175","imageUrl":"https://static.edspay.com/article/1707031609257468-89504E47/view.html","isRecomment":0,"logonRequired":true,"minAmount":100,"name":"E分期-1期-00153","newbieOnly":false,"progress":100,"recommended":false,"redirectUrl":"invest/detail.html","remainAmount":0,"scoreRate":0,"status":"6","tenderTimes":0,"timeLimit":1,"timeToStart":0,"timeType":"0","type":113,"uuid":"1708231619925164"}],"pageCount":380,"pageNumber":1,"pageSize":10}
     * resMsg : SUCCESS
     */
    private ResDataBean resData;

    public ResDataBean getResData() {
        return resData;
    }

    public void setResData(ResDataBean resData) {
        this.resData = resData;
    }


    public static class ResDataBean {
        /**
         * collection : 0
         * list : [{"addApr":0,"addTime":1503470898000,"amount":100000,"apr":16,"directional":false,"goodType":"推荐产品","id":"4179","imageUrl":"https://static.edspay.com/article/1707030096087633-89504E47/view.html","isRecomment":1,"logonRequired":true,"minAmount":100,"name":"新手专享XS328","newbieOnly":true,"progress":50.5,"recommended":false,"redirectUrl":"invest/detail.html","remainAmount":49500,"scoreRate":0,"status":"1","tenderTimes":11,"timeLimit":7,"timeToStart":0,"timeType":"1","type":113,"uuid":"1708232004194673"},{"addApr":0,"addTime":1503409465000,"amount":600000,"apr":7.6,"directional":false,"goodType":"长期稳健","id":"4166","imageUrl":"https://static.edspay.com/article/1707032107763036-89504E47/view.html","isRecomment":0,"logonRequired":true,"minAmount":1000,"name":"E金贷-00006","newbieOnly":false,"progress":4.89,"recommended":false,"redirectUrl":"invest/detail.html","remainAmount":570688,"scoreRate":0,"status":"1","tenderTimes":0,"timeLimit":14,"timeToStart":0,"timeType":"1","type":113,"uuid":"1708220056679764"},{"addApr":0,"addTime":1503026855000,"amount":200000,"apr":10,"directional":false,"goodType":"长期稳健","id":"4074","imageUrl":"https://static.edspay.com/article/1707032107763036-89504E47/view.html","isRecomment":0,"logonRequired":true,"minAmount":100,"name":"白拿专属标002","newbieOnly":false,"progress":14.7,"recommended":false,"redirectUrl":"invest/detail.html","remainAmount":170600,"scoreRate":0,"status":"1","tenderTimes":0,"timeLimit":12,"timeToStart":0,"timeType":"0","type":113,"uuid":"1708181540961532"},{"addApr":0,"addTime":1503464590000,"amount":600000,"apr":9.2,"directional":false,"goodType":"长期稳健","id":"4172","imageUrl":"https://static.edspay.com/article/1707032107763036-89504E47/view.html","isRecomment":0,"logonRequired":true,"minAmount":100,"name":"E分期-2期-00014","newbieOnly":false,"progress":24.38,"recommended":false,"redirectUrl":"invest/detail.html","remainAmount":453697,"scoreRate":0,"status":"1","tenderTimes":0,"timeLimit":2,"timeToStart":0,"timeType":"0","type":113,"uuid":"1708231729373656"},{"addApr":0,"addTime":1503478529000,"amount":300000,"apr":11.8,"directional":false,"goodType":"长期稳健","id":"4181","imageUrl":"https://static.edspay.com/article/1707032107763036-89504E47/view.html","isRecomment":0,"logonRequired":true,"minAmount":100,"name":"E分期-6期-00020","newbieOnly":false,"progress":42.37,"recommended":false,"redirectUrl":"invest/detail.html","remainAmount":172881,"scoreRate":0,"status":"1","tenderTimes":0,"timeLimit":6,"timeToStart":0,"timeType":"0","type":113,"uuid":"1708232017244169"},{"addApr":0,"addTime":1503466450000,"amount":600000,"apr":8.2,"directional":false,"goodType":"长期稳健","id":"4176","imageUrl":"https://static.edspay.com/article/1707032107763036-89504E47/view.html","isRecomment":0,"logonRequired":true,"minAmount":100,"name":"E分期-1期-00154","newbieOnly":false,"progress":58.27,"recommended":false,"redirectUrl":"invest/detail.html","remainAmount":250390,"scoreRate":0,"status":"1","tenderTimes":0,"timeLimit":1,"timeToStart":0,"timeType":"0","type":113,"uuid":"1708230428906600"},{"addApr":0,"addTime":1503468835000,"amount":300000,"apr":14.2,"directional":false,"goodType":"长期稳健","id":"4178","imageUrl":"https://static.edspay.com/article/1707032107763036-89504E47/view.html","isRecomment":0,"logonRequired":true,"minAmount":100,"name":"E分期-12期-00016","newbieOnly":false,"progress":83.8,"recommended":false,"redirectUrl":"invest/detail.html","remainAmount":48610,"scoreRate":0,"status":"1","tenderTimes":0,"timeLimit":12,"timeToStart":0,"timeType":"0","type":113,"uuid":"1708231722855738"},{"addApr":0,"addTime":1503482728000,"amount":300000,"apr":10.6,"directional":false,"goodType":"长期稳健","id":"4184","imageUrl":"https://static.edspay.com/article/1707032107763036-89504E47/view.html","isRecomment":0,"logonRequired":true,"minAmount":100,"name":"E分期-3期-00033","newbieOnly":false,"progress":84.3,"recommended":false,"redirectUrl":"invest/detail.html","remainAmount":47100,"scoreRate":0,"status":"1","tenderTimes":0,"timeLimit":3,"timeToStart":0,"timeType":"0","type":113,"uuid":"1708231692853016"},{"addApr":0,"addTime":1503466416000,"amount":600000,"apr":8.2,"directional":false,"goodType":"已售罄","id":"4175","imageUrl":"https://static.edspay.com/article/1707031609257468-89504E47/view.html","isRecomment":0,"logonRequired":true,"minAmount":100,"name":"E分期-1期-00153","newbieOnly":false,"progress":100,"recommended":false,"redirectUrl":"invest/detail.html","remainAmount":0,"scoreRate":0,"status":"6","tenderTimes":0,"timeLimit":1,"timeToStart":0,"timeType":"0","type":113,"uuid":"1708231619925164"}]
         * pageCount : 380
         * pageNumber : 1
         * pageSize : 10
         */

        private int collection;
        private int pageCount;
        private int pageNumber;
        private int pageSize;



        private ArrayList<Bean> list;

        public ArrayList<Bean> getList() {
            return list;
        }

        public void setList(ArrayList<Bean> list) {
            this.list = list;
        }

        public int getCollection() {
            return collection;
        }

        public void setCollection(int collection) {
            this.collection = collection;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getPageNumber() {
            return pageNumber;
        }

        public void setPageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }



        public static class Bean extends BaseObservable implements Serializable {
            /**
             * addApr : 0
             * addTime : 1503470898000
             * amount : 100000
             * apr : 16
             * directional : false
             * goodType : 推荐产品
             * id : 4179
             * imageUrl : https://static.edspay.com/article/1707030096087633-89504E47/view.html
             * isRecomment : 1
             * logonRequired : true
             * minAmount : 100
             * name : 新手专享XS328
             * newbieOnly : true
             * progress : 50.5
             * recommended : false
             * redirectUrl : invest/detail.html
             * remainAmount : 49500
             * scoreRate : 0
             * status : 1
             * tenderTimes : 11
             * timeLimit : 7
             * timeToStart : 0
             * timeType : 1
             * type : 113
             * uuid : 1708232004194673
             */

            private int addApr;
            private long addTime;
            private int amount;
            private String apr;
            private boolean directional;
            private String goodType;
            private String id;
            private String imageUrl;
            private int isRecomment;
            private boolean logonRequired;
            private int minAmount;
            private String name;
            private boolean newbieOnly;
            private double progress;
            private boolean recommended;
            private String redirectUrl;
            private int remainAmount;
            private int scoreRate;
            private String status;
            private int tenderTimes;
            private int timeLimit;
            private int timeToStart;
            private String timeType;
            private int type;
            private String uuid;

            public int getAddApr() {
                return addApr;
            }

            public void setAddApr(int addApr) {
                this.addApr = addApr;
            }

            public long getAddTime() {
                return addTime;
            }

            public void setAddTime(long addTime) {
                this.addTime = addTime;
            }

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public String getApr() {
                return apr;
            }

            public void setApr(String apr) {
                this.apr = apr;
            }

            public boolean isDirectional() {
                return directional;
            }

            public void setDirectional(boolean directional) {
                this.directional = directional;
            }

            public String getGoodType() {
                return goodType;
            }

            public void setGoodType(String goodType) {
                this.goodType = goodType;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public int getIsRecomment() {
                return isRecomment;
            }

            public void setIsRecomment(int isRecomment) {
                this.isRecomment = isRecomment;
            }

            public boolean isLogonRequired() {
                return logonRequired;
            }

            public void setLogonRequired(boolean logonRequired) {
                this.logonRequired = logonRequired;
            }

            public int getMinAmount() {
                return minAmount;
            }

            public void setMinAmount(int minAmount) {
                this.minAmount = minAmount;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public boolean isNewbieOnly() {
                return newbieOnly;
            }

            public void setNewbieOnly(boolean newbieOnly) {
                this.newbieOnly = newbieOnly;
            }

            public double getProgress() {
                return progress;
            }

            public void setProgress(double progress) {
                this.progress = progress;
            }

            public boolean isRecommended() {
                return recommended;
            }

            public void setRecommended(boolean recommended) {
                this.recommended = recommended;
            }

            public String getRedirectUrl() {
                return redirectUrl;
            }

            public void setRedirectUrl(String redirectUrl) {
                this.redirectUrl = redirectUrl;
            }

            public int getRemainAmount() {
                return remainAmount;
            }

            public void setRemainAmount(int remainAmount) {
                this.remainAmount = remainAmount;
            }

            public int getScoreRate() {
                return scoreRate;
            }

            public void setScoreRate(int scoreRate) {
                this.scoreRate = scoreRate;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public int getTenderTimes() {
                return tenderTimes;
            }

            public void setTenderTimes(int tenderTimes) {
                this.tenderTimes = tenderTimes;
            }

            public int getTimeLimit() {
                return timeLimit;
            }

            public void setTimeLimit(int timeLimit) {
                this.timeLimit = timeLimit;
            }

            public int getTimeToStart() {
                return timeToStart;
            }

            public void setTimeToStart(int timeToStart) {
                this.timeToStart = timeToStart;
            }

            public String getTimeType() {
                return timeType;
            }

            public void setTimeType(String timeType) {
                this.timeType = timeType;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getUuid() {
                return uuid;
            }

            public void setUuid(String uuid) {
                this.uuid = uuid;
            }
        }
    }
}
