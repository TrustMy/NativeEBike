package com.phonegap.ebike.tool.bean;

import java.util.List;

/**
 * Created by Trust on 2017/5/18.
 */

public class CarStrokeBean {

    /**
     * content : {"trips":[{"fireOnLat":31.249892,"fireOnVoltage":28.1,"fireOffLng":121.432816,"fireOffStation":6244,"fireOffVoltage":27.8,"fireOffTime":1487329958000,"tripDistance":110,"maxSpeed":18,"fireOffLat":31.168448,"fireOffBlock":61778,"termId":860337030343524,"fireOnTime":1487325164000,"fireOnStation":6214,"averSpeed":12,"fireOnLng":121.450312,"fireOnBlock":61393},{"fireOnLat":31.296742,"fireOnVoltage":31,"fireOffLng":121.450312,"fireOffStation":6214,"fireOffVoltage":28.1,"fireOffTime":1487324976000,"tripDistance":110,"maxSpeed":18,"fireOffLat":31.249892,"fireOffBlock":61393,"termId":860337030343524,"fireOnTime":1487323353000,"fireOnStation":6324,"averSpeed":12,"fireOnLng":0,"fireOnBlock":62449},{"fireOnLat":31.29058,"fireOnVoltage":29.6,"fireOffLng":121.44348,"fireOffStation":6324,"fireOffVoltage":27.3,"fireOffTime":1487322951000,"tripDistance":110,"maxSpeed":18,"fireOffLat":31.296742,"fireOffBlock":64418,"termId":860337030343524,"fireOnTime":1487321116000,"fireOnStation":6324,"averSpeed":12,"fireOnLng":121.436376,"fireOnBlock":62449}],"totalPages":1,"currentPage":0,"totalElements":3,"currentSize":3}
     * status : true
     */

    private ContentBean content;
    private boolean status;

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ContentBean getContent() {
        return content;
    }

    public boolean getStatus() {
        return status;
    }

    public static class ContentBean {
        /**
         * trips : [{"fireOnLat":31.249892,"fireOnVoltage":28.1,"fireOffLng":121.432816,"fireOffStation":6244,"fireOffVoltage":27.8,"fireOffTime":1487329958000,"tripDistance":110,"maxSpeed":18,"fireOffLat":31.168448,"fireOffBlock":61778,"termId":860337030343524,"fireOnTime":1487325164000,"fireOnStation":6214,"averSpeed":12,"fireOnLng":121.450312,"fireOnBlock":61393},{"fireOnLat":31.296742,"fireOnVoltage":31,"fireOffLng":121.450312,"fireOffStation":6214,"fireOffVoltage":28.1,"fireOffTime":1487324976000,"tripDistance":110,"maxSpeed":18,"fireOffLat":31.249892,"fireOffBlock":61393,"termId":860337030343524,"fireOnTime":1487323353000,"fireOnStation":6324,"averSpeed":12,"fireOnLng":0,"fireOnBlock":62449},{"fireOnLat":31.29058,"fireOnVoltage":29.6,"fireOffLng":121.44348,"fireOffStation":6324,"fireOffVoltage":27.3,"fireOffTime":1487322951000,"tripDistance":110,"maxSpeed":18,"fireOffLat":31.296742,"fireOffBlock":64418,"termId":860337030343524,"fireOnTime":1487321116000,"fireOnStation":6324,"averSpeed":12,"fireOnLng":121.436376,"fireOnBlock":62449}]
         * totalPages : 1
         * currentPage : 0
         * totalElements : 3
         * currentSize : 3
         */

        private int totalPages;
        private int currentPage;
        private int totalElements;
        private int currentSize;
        private List<TripsBean> trips;

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public void setTotalElements(int totalElements) {
            this.totalElements = totalElements;
        }

        public void setCurrentSize(int currentSize) {
            this.currentSize = currentSize;
        }

        public void setTrips(List<TripsBean> trips) {
            this.trips = trips;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public int getTotalElements() {
            return totalElements;
        }

        public int getCurrentSize() {
            return currentSize;
        }

        public List<TripsBean> getTrips() {
            return trips;
        }

        public static class TripsBean {
            /**
             * fireOnLat : 31.249892
             * fireOnVoltage : 28.1
             * fireOffLng : 121.432816
             * fireOffStation : 6244
             * fireOffVoltage : 27.8
             * fireOffTime : 1487329958000
             * tripDistance : 110
             * maxSpeed : 18
             * fireOffLat : 31.168448
             * fireOffBlock : 61778
             * termId : 860337030343524
             * fireOnTime : 1487325164000
             * fireOnStation : 6214
             * averSpeed : 12
             * fireOnLng : 121.450312
             * fireOnBlock : 61393
             */

            private double fireOnLat;
            private double fireOnVoltage;
            private double fireOffLng;
            private int fireOffStation;
            private double fireOffVoltage;
            private long fireOffTime;
            private int tripDistance;
            private int maxSpeed;
            private double fireOffLat;
            private int fireOffBlock;
            private long termId;
            private long fireOnTime;
            private int fireOnStation;
            private int averSpeed;
            private double fireOnLng;
            private int fireOnBlock;

            public void setFireOnLat(double fireOnLat) {
                this.fireOnLat = fireOnLat;
            }

            public void setFireOnVoltage(double fireOnVoltage) {
                this.fireOnVoltage = fireOnVoltage;
            }

            public void setFireOffLng(double fireOffLng) {
                this.fireOffLng = fireOffLng;
            }

            public void setFireOffStation(int fireOffStation) {
                this.fireOffStation = fireOffStation;
            }

            public void setFireOffVoltage(double fireOffVoltage) {
                this.fireOffVoltage = fireOffVoltage;
            }

            public void setFireOffTime(long fireOffTime) {
                this.fireOffTime = fireOffTime;
            }

            public void setTripDistance(int tripDistance) {
                this.tripDistance = tripDistance;
            }

            public void setMaxSpeed(int maxSpeed) {
                this.maxSpeed = maxSpeed;
            }

            public void setFireOffLat(double fireOffLat) {
                this.fireOffLat = fireOffLat;
            }

            public void setFireOffBlock(int fireOffBlock) {
                this.fireOffBlock = fireOffBlock;
            }

            public void setTermId(long termId) {
                this.termId = termId;
            }

            public void setFireOnTime(long fireOnTime) {
                this.fireOnTime = fireOnTime;
            }

            public void setFireOnStation(int fireOnStation) {
                this.fireOnStation = fireOnStation;
            }

            public void setAverSpeed(int averSpeed) {
                this.averSpeed = averSpeed;
            }

            public void setFireOnLng(double fireOnLng) {
                this.fireOnLng = fireOnLng;
            }

            public void setFireOnBlock(int fireOnBlock) {
                this.fireOnBlock = fireOnBlock;
            }

            public double getFireOnLat() {
                return fireOnLat;
            }

            public double getFireOnVoltage() {
                return fireOnVoltage;
            }

            public double getFireOffLng() {
                return fireOffLng;
            }

            public int getFireOffStation() {
                return fireOffStation;
            }

            public double getFireOffVoltage() {
                return fireOffVoltage;
            }

            public long getFireOffTime() {
                return fireOffTime;
            }

            public int getTripDistance() {
                return tripDistance;
            }

            public int getMaxSpeed() {
                return maxSpeed;
            }

            public double getFireOffLat() {
                return fireOffLat;
            }

            public int getFireOffBlock() {
                return fireOffBlock;
            }

            public long getTermId() {
                return termId;
            }

            public long getFireOnTime() {
                return fireOnTime;
            }

            public int getFireOnStation() {
                return fireOnStation;
            }

            public int getAverSpeed() {
                return averSpeed;
            }

            public double getFireOnLng() {
                return fireOnLng;
            }

            public int getFireOnBlock() {
                return fireOnBlock;
            }
        }
    }
}
