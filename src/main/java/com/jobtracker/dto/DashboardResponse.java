package com.jobtracker.dto;

public class DashboardResponse {

    private long totalApplications;
    private long interviews;
    private long offers;
    private long rejected;

    public DashboardResponse(long totalApplications, long interviews,
                             long offers, long rejected) {
        this.totalApplications = totalApplications;
        this.interviews = interviews;
        this.offers = offers;
        this.rejected = rejected;
    }

    public long getTotalApplications() {
        return totalApplications;
    }

    public long getInterviews() {
        return interviews;
    }

    public long getOffers() {
        return offers;
    }

    public long getRejected() {
        return rejected;
    }
}